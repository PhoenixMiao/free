package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.AddPartnerPostRequest;
import com.phoenix.free.entity.AddPartnerPost;
import com.phoenix.free.mapper.AddPartnerPostMapper;
import com.phoenix.free.service.AddPartnerPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddPartnerPostServiceImpl implements AddPartnerPostService
{
    @Autowired
    private AddPartnerPostMapper addPartnerPostMapper;

    public int sendAddPartnerPost(AddPartnerPostRequest addPartnerPostRequest) {
        AddPartnerPost post = AddPartnerPost.builder()
                .userId(addPartnerPostRequest.getUserId())
                .content(addPartnerPostRequest.getContent())
                .pic(addPartnerPostRequest.getPic())
                .createTime(addPartnerPostRequest.getCreateTime())
                .userId2(addPartnerPostRequest.getUserId2())
                .build();
        return addPartnerPostMapper.insertAddPartnerPost(post);
    }

    public void deleteAddPartnerPost(Long id) {
        addPartnerPostMapper.deleteAddPartnerPost(id);
    }

    public AddPartnerPost getAddPartnerPostById(Long id) {
        return addPartnerPostMapper.getAddPartnerPostById(id);
    }

    public List<AddPartnerPost> getAddPartnerPostByUserId(Long userId) {
        return addPartnerPostMapper.getAddPartnerPostByUserId(userId);
    }
}
