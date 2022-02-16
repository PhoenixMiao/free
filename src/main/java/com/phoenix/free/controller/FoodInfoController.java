package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.FoodInfo;
import com.phoenix.free.service.FoodInfoService;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
public class FoodInfoController {
    @Autowired
    private FoodInfoService foodInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserService userService;

    @Auth
    @PostMapping("/addFoodInfo")
    @ApiOperation(value = "添加新食物信息",response = String.class)
    public Object addFoodInfo(@NotNull @Valid @RequestBody AddFoodInfoRequest addFoodInfoRequest){
        Long id = sessionUtils.getUserId();

        AssertUtil.isTrue((1 == userService.getUserById(id).getIsAdmin()), CommonErrorCode.UNAUTHORIZED_OPERATION, "需要管理员权限");

        AssertUtil.isNull(foodInfoService.getFoodInfoByName(addFoodInfoRequest.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加食物信息");
        foodInfoService.addFoodInfo(addFoodInfoRequest);
        return "添加成功";
    }

    @Auth
    @GetMapping("/getFoodInfoById/{id}")
    @ApiOperation(value = "按编号查找食物信息",response = FoodInfo.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object getFoodInfoById(@NotBlank @PathVariable("id") Long id){
        return foodInfoService.getFoodInfoById(id);
    }

    @Auth
    @GetMapping("/getFoodInfoByName/{name}")
    @ApiOperation(value = "按名称查找食物信息",response = FoodInfo.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object getFoodInfoById(@NotBlank @PathVariable("name") String name){
        return foodInfoService.getFoodInfoByName(name);
    }

    @Auth
    @GetMapping("/deleteFoodInfoById/{id}")
    @ApiOperation(value = "按编号删除食物信息",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object deleteFoodInfoById(@NotBlank @PathVariable("id") Long id){
        Long userId = sessionUtils.getUserId();

        AssertUtil.isTrue((1 == userService.getUserById(userId).getIsAdmin()), CommonErrorCode.UNAUTHORIZED_OPERATION, "需要管理员权限");

        foodInfoService.deleteFoodInfoById(id);
        return "删除成功";
    }

    @Auth
    @GetMapping("/deleteFoodInfoByName/{name}")
    @ApiOperation(value = "按名称删除食物信息",response = String.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object deleteFoodInfoByName(@NotBlank @PathVariable("name") String name){
        Long userId = sessionUtils.getUserId();

        AssertUtil.isTrue((1 == userService.getUserById(userId).getIsAdmin()), CommonErrorCode.UNAUTHORIZED_OPERATION, "需要管理员权限");

        foodInfoService.deleteFoodInfoByName(name);
        return "删除成功";
    }

}
