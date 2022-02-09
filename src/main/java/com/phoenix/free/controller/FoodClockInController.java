package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.service.FoodClockInService;
import com.phoenix.free.service.FoodInfoService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class FoodClockInController {
    @Autowired
    private FoodClockInService foodClockInService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @PostMapping("/foodClockIn")
    @ApiOperation(value = "饮食打卡",response = String.class)
    public Object addFoodInfo(@NotNull @Valid @RequestBody FoodClockInRequest foodClockInRequest){
        Long id = sessionUtils.getUserId();

        foodClockInService.addFoodClockIn(foodClockInRequest, id);
        return "打卡成功";
    }
}
