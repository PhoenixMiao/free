package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import com.phoenix.free.service.FoodInfoService;
import com.phoenix.free.dto.SessionData;
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

    @Auth
    @PostMapping("/addFood")
    @ApiOperation(value = "添加新食物",response = String.class)
    public Object addFoodInfo(@NotNull @Valid @RequestBody AddFoodInfoRequest addFoodInfoRequest){
        Long id = sessionUtils.getUserId();

        //TODO 用户具有管理员权限才能向数据库中添加食物

        AssertUtil.isNull(foodInfoService.getFoodByName(addFoodInfoRequest.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加食物信息");
        foodInfoService.addFoodInfo(addFoodInfoRequest);
        return "添加成功";
    }

    @Auth
    @GetMapping("/getFoodById/{id}")
    @ApiOperation(value = "按编号查找食物",response = Food.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object getFoodById(@NotBlank @PathVariable("id") Long id){
        return foodInfoService.getFoodById(id);
    }

    @Auth
    @GetMapping("/getFoodByName/{name}")
    @ApiOperation(value = "按名称查找食物",response = Food.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object getFoodById(@NotBlank @PathVariable("name") String name){
        return foodInfoService.getFoodByName(name);
    }

    @Auth
    @GetMapping("/deleteFoodById/{id}")
    @ApiOperation(value = "按编号删除食物",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object deleteFoodById(@NotBlank @PathVariable("id") Long id){

        //TODO 用户具有管理员权限才能从数据库中删除食物信息

        foodInfoService.deleteFoodById(id);
        return "删除成功";
    }

    @Auth
    @GetMapping("/deleteFoodByName/{name}")
    @ApiOperation(value = "按名称删除食物",response = String.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object deleteFoodByName(@NotBlank @PathVariable("name") String name){

        //TODO 用户具有管理员权限才能从数据库中删除食物信息

        foodInfoService.deleteFoodByName(name);
        return "删除成功";
    }

}
