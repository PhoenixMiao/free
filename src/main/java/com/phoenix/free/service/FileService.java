package com.phoenix.free.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadPicture(MultipartFile file, Long userId, String type);
}
