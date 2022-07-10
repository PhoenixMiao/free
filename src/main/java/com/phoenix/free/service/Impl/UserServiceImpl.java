package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonConstants;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.config.YmlConfig;
import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.dto.SessionData;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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


    @Override
    public SessionData login(String code) {

        //shadow test
        if(CommonConstants.SHADOW_TEST.equals(code)){
            sessionUtils.setSessionId(CommonConstants.SHADOW_TEST);
            return new SessionData();
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
                .introduction("该用户很懒，什么东西都没有写~")
                .type(0)
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

        if(userMapper.updateById(user)==0){
            throw new CommonException(CommonErrorCode.UPDATE_FAIL);
        }

        return userMapper.selectById(id);

    }


    private WxSession getWxSessionByCode(String code){
        Map<String, String> requestUrlParam = new HashMap<>();
        //小程序appId
        requestUrlParam.put("appid", ymlConfig.getAppId());
//        requestUrlParam.put("appid", "wx22fa1182d4e66c4a");
        //小程序secret
        requestUrlParam.put("secret", ymlConfig.getAppSecret());
//        requestUrlParam.put("secret", "200e82982f7ec2a2812fc3ae9f2d5f15");
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数：授权类型
        requestUrlParam.put("grant_type", "authorization_code");
        //发送post请求读取调用微信接口获取openid用户唯一标识
        String result = HttpUtil.get(CommonConstants.WX_SESSION_REQUEST_URL, requestUrlParam);
//        String result = HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", requestUrlParam);

        return JsonUtil.toObject(result, WxSession.class);
    }

    private void checkWxSession(WxSession wxSession){
        if(wxSession.getErrcode() != null) {
            AssertUtil.isFalse(-1 == wxSession.getErrcode(), CommonErrorCode.WX_LOGIN_BUSY, wxSession.getErrmsg());
            AssertUtil.isFalse(40029 == wxSession.getErrcode(), CommonErrorCode.WX_LOGIN_INVALID_CODE, wxSession.getErrmsg());
            AssertUtil.isFalse(45011 == wxSession.getErrcode(), CommonErrorCode.WX_LOGIN_FREQUENCY_REFUSED, wxSession.getErrmsg());
            AssertUtil.isTrue(wxSession.getErrcode() == 0, CommonErrorCode.WX_LOGIN_UNKNOWN_ERROR,wxSession.getErrmsg());
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
            AssertUtil.notNull(name,CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
            String extension = name.substring(name.lastIndexOf("."));

            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET_NAME, user.getSessionId() + extension, multipartFile.getInputStream(), objectMetadata);

            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            Upload upload = transferManager.upload(putObjectRequest);
            uploadResult = upload.waitForUploadResult();

            res =  cosClient.getObjectUrl(COS_BUCKET_NAME,user.getSessionId()).toString()+extension;

            user.setPortrait(res);

        } catch (Exception e){
            //e.printStackTrace();
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }

        if(userMapper.updateById(user)==0) throw new CommonException(CommonErrorCode.UPDATE_FAIL);

        // 确定本进程不再使用 transferManager 实例之后，关闭之
        // 详细代码参见本页：高级接口 -> 关闭 TransferManager
        transferManager.shutdownNow(true);

        return res;
    }

}