package com.phoenix.free.service;

import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    SessionData login(String code);

    User getUserById(Long id);

    User updateUserById(UpdateUserByIdRequest updateUserByIdRequest, Long id);

    String uploadPortrait(Long userId, MultipartFile multipartFile);
}
