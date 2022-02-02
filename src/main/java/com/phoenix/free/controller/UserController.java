package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.controller.response.UserResponse;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping("/login/{code}")
    @ApiOperation(value = "登录",response = SessionData.class)
    @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "path")
    public Object login(@NotBlank @PathVariable("code") String code){

        return userService.login(code);

    }

    @Auth
    @GetMapping("/info")
    @ApiOperation(value = "查看当前用户信息",response = UserResponse.class)
    public Object getUserByIdResponse(){
        Long id = sessionUtils.getUserId();
        UserResponse userResponse = userService.getUserById(id);
        return userResponse;
    }

    @Auth
    @PostMapping("/info")
    @ApiOperation(value = "更新当前用户信息",response = String.class)
    public Object updateUserById(@NotNull @Valid @RequestBody UpdateUserByIdRequest updateUserByIdRequest){
        Long id = sessionUtils.getUserId();
        userService.updateUserById(updateUserByIdRequest,id);
        return "操作成功";
    }
}
