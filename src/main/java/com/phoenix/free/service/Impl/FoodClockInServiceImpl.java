package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.FoodClockInService;
import com.phoenix.free.util.AssertUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.phoenix.free.common.CommonConstants.COS_BUCKET_NAME;

@Service
public class FoodClockInServiceImpl implements FoodClockInService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FoodClockInMapper foodClockInMapper;

    @Autowired
    private TransferManager transferManager;

    @Autowired
    private COSClient cosClient;

    public Long addFoodClockIn(FoodClockInRequest foodClockInRequest, Long userId) {
        User user = userMapper.selectById(1l);
        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        MultipartFile file = foodClockInRequest.getPic();
        objectMetadata.setContentLength(file.getSize());

        UploadResult uploadResult = null;
        String url = null;

        try {

            String name = file.getOriginalFilename();
            AssertUtil.notNull(name, CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
            String extension = name.substring(name.lastIndexOf("."));
            // key = userSessionId/foodClockIn/yyyy-MM-dd_HH:mm:ss
            String key = user.getSessionId() + "/foodClockIn/" + now.replace(" ", "_");

            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET_NAME, key + extension, file.getInputStream(), objectMetadata);

            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            Upload upload = transferManager.upload(putObjectRequest);
            uploadResult = upload.waitForUploadResult();

//            File pic = new File("C:\\Users\\HEXD0\\Pictures\\test.jpg");
//            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET_NAME, key + extension, pic);
//            cosClient.putObject(putObjectRequest);
            url =  cosClient.getObjectUrl(COS_BUCKET_NAME,key).toString() + extension;

        } catch (Exception e){
            //e.printStackTrace();
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }

        FoodClockIn foodClockIn = FoodClockIn.builder()
                .userId(userId)
                .recordTime(now)
                .content(foodClockInRequest.getContent())
                .pic(url)
                .totalEnergy(foodClockInRequest.getTotalEnergy())
                .totalSugar(foodClockInRequest.getTotalSugar())
                .totalFat(foodClockInRequest.getTotalFat())
                .totalProtein(foodClockInRequest.getTotalProtein())
                .totalCellulose(foodClockInRequest.getTotalCellulose())
                .foodInfoId(foodClockInRequest.getFoodInfoId())
                .build();

        if(1 == foodClockInMapper.insert(foodClockIn)){
            QueryWrapper<FoodClockIn> wrapper = new QueryWrapper<>();
            wrapper.select("*")
                    .eq("record_time", now)
                    .eq("user_id", userId);
            return foodClockInMapper.selectOne(wrapper).getId();
        }
        else return -1l;
    }

    public FoodClockIn getFoodClockInById(Long id) {
        return foodClockInMapper.selectById(id);
    }

    public List<FoodClockIn> getFoodClockInByUserId(Long userId) {
        QueryWrapper<FoodClockIn> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("user_id", userId);
        return foodClockInMapper.selectList(wrapper);
    }
}
