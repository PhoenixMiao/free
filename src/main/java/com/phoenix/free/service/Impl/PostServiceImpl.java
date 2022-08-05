package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.controller.request.AddPostRequest;
import com.phoenix.free.entity.Post;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.PostMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.PostService;
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
import java.util.List;
import java.util.Objects;

import static com.phoenix.free.common.CommonConstants.COS_BUCKET_NAME;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransferManager transferManager;

    @Autowired
    private COSClient cosClient;

    @Override
    public Long addNewPost(AddPostRequest addPostRequest, Long userId) {
        User user = userMapper.selectById(userId);
        String url = null;

        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        // 如果需要上传图片
        if(!Objects.isNull(addPostRequest.getPic())){
            ObjectMetadata objectMetadata = new ObjectMetadata();
            MultipartFile file = addPostRequest.getPic();
            objectMetadata.setContentLength(file.getSize());

            UploadResult uploadResult = null;

            try {

                String name = file.getOriginalFilename();
                AssertUtil.isNotNull(name, CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
                String extension = name.substring(name.lastIndexOf("."));
                // key : userSessionId/foodClockIn/yyyy-MM-dd_HH_mm_ss
                String key = user.getSessionId() + "/post/" + now.replace(" ", "_").replace(":", "_");

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
        }

        Post post = Post.builder()
                .userId(userId)
                .image(url)
                .desc(addPostRequest.getContent())
                .title(addPostRequest.getTitle())
                .createTime(now)
                .build();

        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("create_time", now)
                .eq("user_id", userId);
        if(Objects.isNull(postMapper.selectOne(wrapper))){
            if(1 == postMapper.insert(post)){
                return postMapper.selectOne(wrapper).getId();
            }
            else return -1L;
        }
        else throw new CommonException(CommonErrorCode.OPERATION_TOO_FREQUENT);
    }

    @Override
    public Post getPostById(Long id) {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.select("id,user_id,title,content AS `desc`,pic AS image,create_time,version")
                .eq("id", id);
        return postMapper.selectOne(wrapper);
    }

    @Override
    public List<Post> getPosts(int page) {
        String offset = String.valueOf(page * 15);
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.select("id,user_id,title,content AS `desc`,pic AS image,create_time,version")
                .last("LIMIT 15 OFFSET " + offset);
        return postMapper.selectList(wrapper);
    }

    @Override
    public List<Post> getPostByUserId(Long id, int page) {
        String offset = String.valueOf(page * 15);
        QueryWrapper<Post> wrapper = new QueryWrapper<>();
        wrapper.select("id,user_id,title,content AS `desc`,pic AS image,create_time,version")
                .eq("user_id", id)
                .last("LIMIT 15 OFFSET " + offset);
        return postMapper.selectList(wrapper);
    }

    @Override
    public int deletePostById(Long id) {
        return postMapper.deleteById(id);
    }
}
