package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.common.Result;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.entity.Food;
import com.phoenix.free.service.FoodInfoService;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
    public Object addFoodInfo(@RequestPart("file") MultipartFile file, @RequestPart("request") AddFoodInfoRequest request){
        Long id = sessionUtils.getUserId();
        AssertUtil.isNull(foodInfoService.getFoodInfoByName(request.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加食物信息");
        if(!file.getOriginalFilename().isEmpty()){
            request.setPic(file);
        }
        else{
            request.setPic(null);
        }
        return Result.success(foodInfoService.addFoodInfo(request, id));
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "按编号查找食物信息",response = Food.class)
    public Object getFoodInfoById(@NotBlank @PathVariable("id") Long id){
        return foodInfoService.getFoodInfoById(id);
    }

    @Auth
    @GetMapping("/get/name={name}/page={page}")
    @ApiOperation(value = "按名称查找食物信息",response = Food.class, responseContainer = "List")
    public Object getFoodInfoById(@NotBlank @PathVariable("name") String name, @PathVariable("page") int page){
        AssertUtil.isTrue(page >= 0, CommonErrorCode.INVALID_PARAM);
        return foodInfoService.searchFoodInfo(name, page);
    }

    @Auth
    @GetMapping("/list/page={page}")
    @ApiOperation(value = "所有食物信息",response = Food.class, responseContainer = "List")
    public Object getFoodInfoList(@NotBlank @PathVariable("page") int page){
        AssertUtil.isTrue(page >= 0, CommonErrorCode.INVALID_PARAM);
        return foodInfoService.searchFoodInfo("", page);
    }

    @Admin
    @GetMapping("/list/new")
    @ApiOperation(value = "最近添加的食物列表",response = Food.class, responseContainer = "List")
    public Object getNewlyAddedFood(){
        return foodInfoService.getNewlyAddedFood();
    }

    @Admin
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "按编号删除食物信息",response = String.class)
    public Object deleteFoodInfoById(@NotBlank @PathVariable("id") Long id){
        return Result.success(foodInfoService.deleteFoodInfoById(id));
    }

    @Admin
    @GetMapping("/delete/name={name}")
    @ApiOperation(value = "按名称删除食物信息",response = String.class)
    public Object deleteFoodInfoByName(@NotBlank @PathVariable("name") String name){
        return Result.success(foodInfoService.deleteFoodInfoByName(name));
    }

    @Admin
    @PostMapping("/update/id={id}")
    @ApiOperation(value = "修改食物信息", response = Food.class)
    public Object updateFoodInfo(@NotBlank @PathVariable("id") Long id, @RequestBody AddFoodInfoRequest request){
        return foodInfoService.updateFoodInfo(request, id);
    }

}
