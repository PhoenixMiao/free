package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.AddPartnerMessageRequest;
import com.phoenix.free.controller.response.UserAssessInfoResponse;
import com.phoenix.free.service.AddPartnerMessageService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/addPartnerMessage")
@RestController
public class AddPartnerMessageController {
    @Autowired
    private AddPartnerMessageService addPartnerMessageService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @GetMapping("/getAll")
    @ApiOperation(value = "查看当前用户接到的所有搭档申请",response = List.class)
    public Object getAddPartnerMessageList(){
        Long id = sessionUtils.getUserId();
        return addPartnerMessageService.getAddPartnerMessageByUserId(id);
    }

    @Auth
    @GetMapping("/refuse")
    @ApiOperation(value = "拒绝搭档申请",response = String.class)
    public Object refuseAddPartnerMessage(Long id){
        addPartnerMessageService.deleteAddPartnerMessage(id);
        return "已拒绝申请";
    }

    @Auth
    @PostMapping("/send")
    @ApiOperation(value = "发送搭档申请",response = String.class)
    public Object sendAddPartnerMessage(@NotNull @Valid @RequestBody AddPartnerMessageRequest addPartnerMessageRequest){
        addPartnerMessageService.sendAddPartnerMessage(addPartnerMessageRequest);
        return "已发送申请";
    }
}
