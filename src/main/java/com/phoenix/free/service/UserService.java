package com.phoenix.free.service;

import com.phoenix.free.dto.SessionData;

public interface UserService {

    /**
     * 登录
     * @param code
     * @return
     */
    SessionData login(String code);
}
