package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddPartnerMessageRequest;
import com.phoenix.free.entity.AddPartnerMessage;

import java.util.List;

public interface AddPartnerMessageService {
    int sendAddPartnerMessage(AddPartnerMessageRequest addPartnerMessageRequest);
    void deleteAddPartnerMessage(Long id);
    AddPartnerMessage getAddPartnerMessageById(Long id);
    List<AddPartnerMessage> getAddPartnerMessageByUserId(Long userId);
}
