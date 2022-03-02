package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.AddPartnerMessageRequest;
import com.phoenix.free.entity.AddPartnerMessage;
import com.phoenix.free.mapper.AddPartnerMessageMapper;
import com.phoenix.free.service.AddPartnerMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddPartnerMessageServiceImpl implements AddPartnerMessageService
{
    @Autowired
    private AddPartnerMessageMapper addPartnerMessageMapper;

    public int sendAddPartnerMessage(AddPartnerMessageRequest addPartnerMessageRequest) {
        AddPartnerMessage message = AddPartnerMessage.builder()
                .userId(addPartnerMessageRequest.getUserId())
                .content(addPartnerMessageRequest.getContent())
                .pic(addPartnerMessageRequest.getPic())
                .createTime(addPartnerMessageRequest.getCreateTime())
                .userId2(addPartnerMessageRequest.getUserId2())
                .build();
        return addPartnerMessageMapper.insertAddPartnerMessage(message);
    }

    public void deleteAddPartnerMessage(Long id) {
        addPartnerMessageMapper.deleteAddPartnerMessage(id);
    }

    public AddPartnerMessage getAddPartnerMessageById(Long id) {
        return addPartnerMessageMapper.getAddPartnerMessageById(id);
    }

    public List<AddPartnerMessage> getAddPartnerMessageByUserId(Long userId) {
        return addPartnerMessageMapper.getAddPartnerMessageByUserId(userId);
    }
}
