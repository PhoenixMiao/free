package com.phoenix.free.service.Impl;

import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.FileService;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.phoenix.free.common.CommonConstants.COS_BUCKET_NAME;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransferManager transferManager;

    @Autowired
    private COSClient cosClient;

    @Override
    public String uploadPicture(MultipartFile file, Long userId, String type) {
        User user = userMapper.selectById(userId);
        String url = null;

        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());

        UploadResult uploadResult = null;

        try {

            String name = file.getOriginalFilename();
            AssertUtil.notNull(name, CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
            String extension = name.substring(name.lastIndexOf("."));
            // example key : userSessionId/foodClockIn/yyyy-MM-dd_HH_mm_ss
            String key = user.getSessionId() + type;

            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKET_NAME, key + extension, file.getInputStream(), objectMetadata);

            // 高级接口会返回一个异步结果Upload
            // 可同步地调用 waitForUploadResult 方法等待上传完成，成功返回UploadResult, 失败抛出异常
            Upload upload = transferManager.upload(putObjectRequest);
            uploadResult = upload.waitForUploadResult();

            url =  cosClient.getObjectUrl(COS_BUCKET_NAME,key).toString() + extension;

        } catch (Exception e){
            //e.printStackTrace();
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }
        return url;
    }
}
