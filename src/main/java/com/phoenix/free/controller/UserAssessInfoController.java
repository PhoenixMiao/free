package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.controller.response.UserAssessInfoResponse;
import com.phoenix.free.service.UserAssessInfoService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class UserAssessInfoController {
    @Autowired
    private UserAssessInfoService userAssessInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @GetMapping("/assessInfo")
    @ApiOperation(value = "查看当前用户评估信息",response = UserAssessInfoResponse.class)
    public Object getUserAssessInfo(){
        Long id = sessionUtils.getUserId();
        return userAssessInfoService.getAssessByUserId(0L);
    }

    @Auth
    @PostMapping("/updateAssessInfo")
    @ApiOperation(value = "更新当前用户评估信息",response = String.class)
    public Object updateUserAssessInfo(@NotNull @Valid @RequestBody UpdateUserAssessInfoRequest updateUserAssessInfoRequest){
        Long id = sessionUtils.getUserId();
        updateUserAssessInfoRequest.setUserId(id);
        userAssessInfoService.updateAssessByUserId(updateUserAssessInfoRequest);
        return "操作成功";
    }
}
