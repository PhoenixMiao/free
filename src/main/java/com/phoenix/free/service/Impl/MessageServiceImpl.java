package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.response.MessageResponse;
import com.phoenix.free.controller.response.UserBriefInfoResponse;
import com.phoenix.free.entity.Message;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.MessageMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.MessageService;
import com.phoenix.free.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long sendMessage(Long senderId, Long receiverId) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("sender_id", senderId)
                .eq("receiver_id", receiverId)
                .or()
                .eq("sender_id", receiverId)
                .eq("receiver_id", senderId);
        AssertUtil.isNull(messageMapper.selectOne(wrapper), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        Message message = Message.builder()
                .senderId(senderId)
                .receiverId(receiverId)
                .createTime(now)
                .build();
        if(1 == messageMapper.insert(message)){
            wrapper.select("*")
                    .eq("sender_id", senderId)
                    .eq("receiver_id", receiverId);
            message = messageMapper.selectOne(wrapper);
            return message.getId();
        }
        return -1l;

    }

    @Override
    public List<MessageResponse> checkMessages(Long userId, int page) {
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .eq("receiver_id", userId)
                .last("LIMIT 15 OFFSET " + offset);
        List<Message> messageList = messageMapper.selectList(wrapper);
        List<MessageResponse> response = new ArrayList<>();
        for(Message m : messageList) {
            User sender = userMapper.selectById(m.getSenderId());
            UserBriefInfoResponse senderInfo = UserBriefInfoResponse.builder()
                    .id(sender.getId())
                    .nickname(sender.getNickname())
                    .portrait(sender.getPortrait())
                    .build();
            MessageResponse res = MessageResponse.builder()
                    .id(m.getId())
                    .createTime(m.getCreateTime())
                    .sender(senderInfo)
                    .build();
            response.add(res);
        }
        return response;
    }

    @Override
    public MessageResponse checkOneMessage(Long id) {
        Message message = messageMapper.selectById(id);
        AssertUtil.isFalse(Objects.isNull(message), CommonErrorCode.DATA_NOT_EXISTS);
        User sender = userMapper.selectById(message.getSenderId());
        UserBriefInfoResponse senderInfo = UserBriefInfoResponse.builder()
                .id(sender.getId())
                .nickname(sender.getNickname())
                .portrait(sender.getPortrait())
                .build();
        MessageResponse response = MessageResponse.builder()
                .id(message.getId())
                .createTime(message.getCreateTime())
                .sender(senderInfo)
                .build();
        return response;
    }

    @Override
    public int deleteMessage(Long id) {
        return messageMapper.deleteById(id);
    }
}
