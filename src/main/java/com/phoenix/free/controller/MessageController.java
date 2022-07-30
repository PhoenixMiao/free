package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.response.MessageResponse;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.service.MessageService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RequestMapping("/message")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @GetMapping("/send/receiver={receiverId}")
    @ApiOperation(value = "向指定用户发送添加搭档申请", response = Long.class)
    public Object send(@NotBlank @PathVariable("receiverId") Long receiverId){
        Long id = sessionUtils.getUserId();
        return messageService.sendMessage(id, receiverId);
    }

    @Auth
    @GetMapping("/get/page={page}")
    @ApiOperation(value = "查看自己收到的申请", response = MessageResponse.class, responseContainer = "List")
    public Object getMessages(@NotBlank @PathVariable("page") int page){
        Long id = sessionUtils.getUserId();
        return messageService.checkMessages(id, page);
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "查看某一条申请", response = MessageResponse.class)
    public Object getOneMessage(@NotBlank @PathVariable("id") Long id){
        return messageService.checkOneMessage(id);
    }

    @Auth
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "删除某一条申请", response = Integer.class)
    public Object delete(@NotBlank @PathVariable("id") Long id){
        return messageService.deleteMessage(id);
    }
}
