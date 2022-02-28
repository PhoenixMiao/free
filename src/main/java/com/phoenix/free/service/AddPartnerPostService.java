package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddPartnerPostRequest;
import com.phoenix.free.entity.AddPartnerPost;

import java.util.List;

public interface AddPartnerPostService {
    int sendAddPartnerPost(AddPartnerPostRequest addPartnerPostRequest);
    void deleteAddPartnerPost(Long id);
    AddPartnerPost getAddPartnerPostById(Long id);
    List<AddPartnerPost> getAddPartnerPostByUserId(Long userId);
}
