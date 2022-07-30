package com.phoenix.free.service;

import com.phoenix.free.controller.response.MessageResponse;

import java.util.List;

public interface MessageService {
    Long sendMessage(Long senderId, Long receiverId);

    List<MessageResponse> checkMessages(Long userId, int page);
    MessageResponse checkOneMessage(Long id);

    int deleteMessage(Long id);
}
