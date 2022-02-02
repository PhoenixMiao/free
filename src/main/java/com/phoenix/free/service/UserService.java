package com.phoenix.free.service;

import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.controller.response.UserResponse;
import com.phoenix.free.dto.SessionData;

public interface UserService {

    /**
     * 登录
     * @param code
     * @return
     */
    SessionData login(String code);

    UserResponse getUserById(Long id);
    void updateUserById(UpdateUserByIdRequest updateUserByIdRequest, Long id);
}
