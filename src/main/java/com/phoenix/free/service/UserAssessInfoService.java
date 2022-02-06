package com.phoenix.free.service;

import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.controller.response.UserAssessInfoResponse;

public interface UserAssessInfoService {

    void updateAssessByUserId(UpdateUserAssessInfoRequest updateUserAssessInfoRequest, Long userId);
    void generateNewAssessInfo();
    UserAssessInfoResponse getAssessByUserId(Long userId);
}
