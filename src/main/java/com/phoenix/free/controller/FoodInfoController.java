package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
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

@RequestMapping("/foodInfo")
@RestController
public class FoodInfoController {
    @Autowired
    private FoodInfoService foodInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserService userService;

    @Admin
    @PostMapping("/add")
    @ApiOperation(value = "添加新食物信息",response = String.class)
    public Object addFoodInfo(@NotNull @Valid @RequestBody AddFoodInfoRequest addFoodInfoRequest){
        AssertUtil.isNull(foodInfoService.getFoodInfoByName(addFoodInfoRequest.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加食物信息");
        foodInfoService.addFoodInfo(addFoodInfoRequest);
        return "添加成功";
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "按编号查找食物信息",response = Food.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object getFoodInfoById(@NotBlank @PathVariable("id") Long id){
        return foodInfoService.getFoodInfoById(id);
    }

    @Auth
    @GetMapping("/get/name={name}")
    @ApiOperation(value = "按名称查找食物信息",response = Food.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object getFoodInfoById(@NotBlank @PathVariable("name") String name){
        return foodInfoService.getFoodInfoByName(name);
    }

    @Admin
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "按编号删除食物信息",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object deleteFoodInfoById(@NotBlank @PathVariable("id") Long id){
        foodInfoService.deleteFoodInfoById(id);
        return "删除成功";
    }

    @Admin
    @GetMapping("/delete/name={name}")
    @ApiOperation(value = "按名称删除食物信息",response = String.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object deleteFoodInfoByName(@NotBlank @PathVariable("name") String name){
        foodInfoService.deleteFoodInfoByName(name);
        return "删除成功";
    }

}
