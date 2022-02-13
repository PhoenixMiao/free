package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.response.UserAssessInfoResponse;
import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.service.UserHealthInfoService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserHealthInfoController {
    @Autowired
    private UserHealthInfoService userHealthInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @GetMapping("/healthInfo")
    @ApiOperation(value = "查看当前用户健康信息",response = UserHealthInfoResponse.class)
    public Object getUserAssessInfo(){
        Long id = sessionUtils.getUserId();
        return userHealthInfoService.getUserHealthInfo(id);
    }
}
