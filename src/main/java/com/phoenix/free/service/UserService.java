package com.phoenix.free.service;

import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.controller.response.NewlyCreatedUsersGraphResponse;
import com.phoenix.free.controller.response.QRCodeResponse;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    SessionData login(String code);

    User getUserById(Long id);

    User updateUserById(UpdateUserByIdRequest updateUserByIdRequest, Long id);

    String uploadPortrait(Long userId, MultipartFile multipartFile);

    List<User> getUserList(int page);
    List<User> getAdminList(int page);

    NewlyCreatedUsersGraphResponse getNewlyCreatedUsers();

    String getQRCode() throws IOException;

    void sendOutMes(String token,Long userId);
}
