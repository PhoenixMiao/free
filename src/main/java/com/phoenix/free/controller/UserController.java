package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.common.Result;
import com.phoenix.free.controller.request.UpdateUserByIdRequest;
import com.phoenix.free.dto.SessionData;
import com.phoenix.free.entity.User;
import com.phoenix.free.service.Impl.UserServiceImpl;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api("用户相关操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionUtils sessionUtils;

    @GetMapping("/login/{code}")
    @ApiOperation(value = "登录",response = SessionData.class)
    @ApiImplicitParam(name = "code", value = "code", required = true, paramType = "path")
    public Object login(@NotBlank @PathVariable("code") String code){
        try{
            return Result.success(userService.login(code));
        }catch (CommonException e){
            return Result.result(e.getCommonErrorCode());
        }

    }

    @Auth
    @GetMapping("")
    @ApiOperation(value = "查看任意用户信息",response = User.class)
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "query", dataType = "Long")
    public Object getUserByIdResponse(@NotNull @RequestParam("userId")Long userId){
        try{
            return Result.success(userService.getUserById(userId));
        }catch (CommonException e){
            throw new CommonException(e.getCommonErrorCode());
        }
    }

    @Admin
    @GetMapping("/list/users/page={page}")
    @ApiOperation(value = "查看用户列表（不包括管理员）", response = User.class, responseContainer = "List")
    public Object getUserList(@NotBlank @PathVariable("page") int page){
        return userService.getUserList();
    }

    @Admin
    @GetMapping("/list/admins/page={page}")
    @ApiOperation(value = "查看管理员列表",response = User.class, responseContainer = "List")
    public Object getAdminList(@NotBlank @PathVariable("page") int page){
        return userService.getAdminList();
    }

    @Auth
    @GetMapping("/whoami")
    @ApiOperation(value = "查看登录用户信息",response = User.class)
    public Object whoami(){
        try{
            return Result.success(sessionUtils.getSessionData());
        }catch (CommonException e){
            return Result.result(e.getCommonErrorCode());
        }
    }

    @Auth
    @PostMapping("/update")
    @ApiOperation(value = "更新当前用户信息",response = String.class)
    public Object updateUserById(@NotNull @Valid @RequestBody UpdateUserByIdRequest updateUserByIdRequest){
        try {
            return Result.success(userService.updateUserById(updateUserByIdRequest,sessionUtils.getUserId()));
        }catch (CommonException e){
            return Result.result(e.getCommonErrorCode());
        }
    }

    @Auth
    @PostMapping(value = "/upload", produces = "application/json")
    @ApiOperation(value = "上传用户头像")
    public Object uploadPortrait(MultipartFile file) {
        try{
            return Result.success(userService.uploadPortrait(sessionUtils.getUserId(), file));
        }catch (CommonException e){
            return Result.result(e.getCommonErrorCode());
        }

    }

    @Auth
    @GetMapping(value = "/portrait/userId={id}")
    @ApiOperation(value = "获取用户头像", response = String.class)
    public Object getPortrait(@NotBlank @PathVariable("id") Long id) {
        try{
            return Result.success(userService.getUserById(id).getPortrait());
        }catch (CommonException e){
            throw new CommonException(CommonErrorCode.USER_NOT_EXIST);
        }

    }
}
