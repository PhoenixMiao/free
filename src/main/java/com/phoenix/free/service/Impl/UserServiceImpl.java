package com.phoenix.free.service.Impl;

import com.alibaba.fastjson.JSONException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonConstants;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.config.YmlConfig;
import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.controller.response.NewlyCreatedUsersGraphResponse;
import com.phoenix.free.controller.response.QRCodeResponse;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.dto.WxAccessToken;
import com.phoenix.free.dto.WxErrCode;
import com.phoenix.free.dto.WxSession;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.phoenix.free.common.CommonConstants.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private YmlConfig ymlConfig;

    @Autowired
    private TransferManager transferManager;

    @Autowired
    private COSClient cosClient;

    @Autowired
    private WebSocket webSocket;

    @Value("${mini-app.app-id}")
    private String appId;

    @Value("${mini-app.app-secret}")
    private String appSecret;


    @Override
    public SessionData login(String code) {

        //shadow test
        if(CommonConstants.SHADOW_TEST.equals(code)){
            sessionUtils.setSessionId(CommonConstants.SHADOW_TEST);
            User shadowTest = userMapper.selectById(1l);
            return new SessionData(shadowTest);
        }

        WxSession wxSession = Optional.ofNullable(
                getWxSessionByCode(code))
                .orElse(new WxSession());

        checkWxSession(wxSession);

        String sessionId = sessionUtils.generateSessionId();

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("open_id",wxSession.getOpenId());
        User user = userMapper.selectOne(userQueryWrapper);

        if(user != null){
            sessionUtils.setSessionId(user.getSessionId());
            return new SessionData(user);
        }


        user = User.builder()
                .createTime(TimeUtil.getCurrentTimestamp())
                .openId(wxSession.getOpenId())
                .unionId(wxSession.getUnionId())
                .sessionKey(wxSession.getSessionKey())
                .sessionId(sessionId)
                .version(1)
                .introduction("??????????????????????????????????????????~")
                .isAdmin(0)
                .gender(0)
                .build();

        userMapper.insert(user);

        return new SessionData(user);
    }

    public User getUserById(Long id) throws CommonException{
        User user = userMapper.selectById(id);
        if(user==null) throw new CommonException(CommonErrorCode.USER_NOT_EXIST);
        return user;
    }

    public User updateUserById(UpdateUserByIdRequest updateUserByIdRequest, Long id) throws CommonException{

        User user = userMapper.selectById(id);
        user.setAge(updateUserByIdRequest.getAge());
        user.setNickname(updateUserByIdRequest.getNickname());
        user.setPhone(updateUserByIdRequest.getPhone());
        user.setSchool(updateUserByIdRequest.getSchool());
        user.setIntroduction(updateUserByIdRequest.getIntroduction());
        user.setIsAdmin(updateUserByIdRequest.getIsAdmin());

        if(userMapper.updateById(user)==0){
            throw new CommonException(CommonErrorCode.UPDATE_FAIL);
        }

        return userMapper.selectById(id);

    }


    private WxSession getWxSessionByCode(String code){
        Map<String, String> requestUrlParam = new HashMap<>();
        //?????????appId
        requestUrlParam.put("appid", ymlConfig.getAppId());
//        requestUrlParam.put("appid", "wx22fa1182d4e66c4a");
        //?????????secret
        requestUrlParam.put("secret", ymlConfig.getAppSecret());
//        requestUrlParam.put("secret", "200e82982f7ec2a2812fc3ae9f2d5f15");
        //?????????????????????code
        requestUrlParam.put("js_code", code);
        //???????????????????????????
        requestUrlParam.put("grant_type", "authorization_code");
        //??????post????????????????????????????????????openid??????????????????
        String result = HttpUtil.get(CommonConstants.WX_SESSION_REQUEST_URL, requestUrlParam).toString();
//        String result = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", requestUrlParam);

        return JsonUtil.toObject(result, WxSession.class);
    }

    private void checkWxSession(WxSession wxSession){
        if(wxSession.getErrcode() != null) {
            AssertUtil.isFalse(-1 == wxSession.getErrcode(), CommonErrorCode.WX_SYS_BUSY, wxSession.getErrmsg());
            AssertUtil.isFalse(40029 == wxSession.getErrcode(), CommonErrorCode.WX_LOGIN_INVALID_CODE, wxSession.getErrmsg());
            AssertUtil.isFalse(45011 == wxSession.getErrcode(), CommonErrorCode.WX_LOGIN_FREQUENCY_REFUSED, wxSession.getErrmsg());
            AssertUtil.isTrue(wxSession.getErrcode() == 0, CommonErrorCode.WX_LOGIN_UNKNOWN_ERROR,wxSession.getErrmsg());
        }
    }

    private void checkWxAccessToken(WxAccessToken wxAccessToken) {
        if(wxAccessToken.getErrcode() != null) {
            AssertUtil.isFalse(-1 == wxAccessToken.getErrcode(), CommonErrorCode.WX_SYS_BUSY,wxAccessToken.getErrmsg());
            AssertUtil.isFalse(40001 == wxAccessToken.getErrcode(), CommonErrorCode.WX_APPSECRET_INVALID,wxAccessToken.getErrmsg());
            AssertUtil.isFalse(40002 == wxAccessToken.getErrcode(), CommonErrorCode.WX_GRANTTYPE_MUSTBE_CLIENTCREDENTIAL,wxAccessToken.getErrmsg());
            AssertUtil.isFalse(40013 == wxAccessToken.getErrcode(), CommonErrorCode.WX_APPID_INVALID,wxAccessToken.getErrmsg());
        }
    }

    private void checkWxErrCode(WxErrCode wxErrCode) {
        if(wxErrCode.getErrcode() != null) {
            AssertUtil.isFalse(-1 == wxErrCode.getErrcode(), CommonErrorCode.WX_SYS_BUSY,wxErrCode.getErrmsg());
            AssertUtil.isFalse(45063 == wxErrCode.getErrcode(), CommonErrorCode.WX_QRCODE_UNAUTHORIZED,wxErrCode.getErrmsg());
            AssertUtil.isFalse(45009 == wxErrCode.getErrcode(), CommonErrorCode.WX_QRCODE_TOO_FREQUENT,wxErrCode.getErrmsg());
        }
    }

    @Override
    public String uploadPortrait(Long userId, MultipartFile multipartFile) throws CommonException{

        User user = userMapper.selectById(1l);
        String portrait = user.getPortrait();

        try{
            if(user.getPortrait()!=null){
                cosClient.deleteObject(COS_BUCKET_NAME,portrait.substring(portrait.indexOf(user.getSessionId())));
            }
        }catch (Exception e){
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());

        UploadResult uploadResult = null;
        String res = null;

        try {

            String name = multipartFile.getOriginalFilename();
            AssertUtil.isNotNull(name,CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
            String extension = name.substring(name.lastIndexOf("."));

            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET_NAME, user.getSessionId() + extension, multipartFile.getInputStream(), objectMetadata);

            // ???????????????????????????????????????Upload
            // ?????????????????? waitForUploadResult ???????????????????????????????????????UploadResult, ??????????????????
            Upload upload = transferManager.upload(putObjectRequest);
            uploadResult = upload.waitForUploadResult();

            res =  cosClient.getObjectUrl(COS_BUCKET_NAME,user.getSessionId()).toString()+extension;

            user.setPortrait(res);

        } catch (Exception e){
            //e.printStackTrace();
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }

        if(userMapper.updateById(user)==0) throw new CommonException(CommonErrorCode.UPDATE_FAIL);

        // ??????????????????????????? transferManager ????????????????????????
        // ??????????????????????????????????????? -> ?????? TransferManager
        transferManager.shutdownNow(true);

        return res;
    }

    @Override
    public List<User> getUserList(int page) {
        String offset = String.valueOf(page * 15);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("is_admin", 0)
                .last("LIMIT 15 OFFSET " + offset);
        return userMapper.selectList(wrapper);
    }

    @Override
    public List<User> getAdminList(int page) {
        String offset = String.valueOf(page * 15);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("is_admin", 1)
                .or()
                .eq("is_admin", 2)
                .last("LIMIT 15 OFFSET " + offset);
        return userMapper.selectList(wrapper);
    }

    @Override
    public NewlyCreatedUsersGraphResponse getNewlyCreatedUsers() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDay, beginDay;
        List<Integer> stats = new ArrayList<>();
        List<String> date = new ArrayList<>();

        for (int i = 6; i >= 0; i--){
            beginDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getDayBegin(), i));
            date.add(beginDay);
            endDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getBeginDayOfTomorrow(), i));

            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.between("create_time", beginDay, endDay);
            stats.add(userMapper.selectCount(wrapper));
        }

        return new NewlyCreatedUsersGraphResponse(stats, date);
    }

    @Override
    public String getQRCode() throws IOException{
        Map<String, String> requestUrlParam = new HashMap<>();
        requestUrlParam.put("grant_type","client_credential");
        requestUrlParam.put("appid",appId);
        requestUrlParam.put("secret",appSecret);
        String result = null;

        result = HttpUtil.get(WX_ACCESS_TOKEN_REQUEST_URL,requestUrlParam);

        System.out.println(result);
        WxAccessToken wxAccessToken = JsonUtil.toObject(result,WxAccessToken.class);

        assert wxAccessToken != null;
        checkWxAccessToken(wxAccessToken);

        String requestURL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + wxAccessToken.getAccess_token();

//        return QRCodeResponse.builder()
//                .token(UUID.randomUUID().toString().substring(0,31))
//                .url(requestURL)
//                .build();

//        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("scene",);
//        requestBody.put("page",);
//        requestBody.put("env_version","develop");

        JSONObject createQrParam = new JSONObject();
        createQrParam.put("scene", UUID.randomUUID().toString().substring(0,31));
        createQrParam.put("page", "pages/login/loginInfo/loginInfo");

//        CloseableHttpResponse response = HttpUtil.post("https://api.weixin.qq.com/wxa/getwxacodeunlimit",requestUrlParam,requestBody);

        PrintWriter out = null;
        InputStream in = null;
        String base64Code = null;
        //????????????????????????
        byte[] data = null;
        try {
            URL realUrl = new URL(requestURL);
            // ?????????URL???????????????
            URLConnection conn = realUrl.openConnection();
            // ???????????????????????????
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ??????POST??????????????????????????????
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ??????URLConnection????????????????????????
            out = new PrintWriter(conn.getOutputStream());
            // ??????????????????,??????connection??????????????????????????????connection???????????????????????????????????????????????????connection?????????connection?????????????????????URL???????????????
            out.print(createQrParam);
            // flush??????????????????
            out.flush();
            //??????URL???connection???????????????????????????????????????????????????url???????????????,??????????????????????????????????????????????????????????????????????????????????????????
            in = conn.getInputStream();
            // ????????????????????????
            try {
                //?????????????????????????????????????????????????????????????????????
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1000000];
                int rc = 0;
                while ((rc = in.read(buff,0,100)) > 0) {
                    //??????????????????????????????????????????????????????
                    swapStream.write(buff,0,rc);
                }
                //??????connection?????????????????????????????????????????????????????????????????????????????????????????????????????????swapStream???
                data = swapStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            base64Code = new String(Objects.requireNonNull(Base64.getEncoder().encode(data)));
            //Base64???byte[]??????
            System.out.println(base64Code);
        } catch (Exception e) {
            System.out.println("?????? POST ?????????????????????" + e);
            e.printStackTrace();
        }

        // ??????finally?????????????????????????????????
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return base64Code;
    }

    @Override
    public void sendOutMes(String token,Long userId){
        User user = userMapper.selectById(userId);
        WebSocketUtils.sendMessage(token,user.getSessionId());
    }

}