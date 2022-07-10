package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.service.ExerciseClockInService;
import com.phoenix.free.service.FoodClockInService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping("/clockIn")
@RestController
public class ClockInController {
    @Autowired
    private ExerciseClockInService exerciseClockInService;

    @Autowired
    private FoodClockInService foodClockInService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @PostMapping("/exercise")
    @ApiOperation(value = "运动打卡",response = String.class)
    public Object addExerciseClockIn(@NotNull @Valid @RequestBody ExerciseClockInRequest exerciseClockInRequest){
        Long id = sessionUtils.getUserId();

        exerciseClockInService.addExerciseClockIn(exerciseClockInRequest, id);
        return "打卡成功";
    }

    @Auth
    @PostMapping("/food")
    @ApiOperation(value = "饮食打卡",response = String.class)
    public Object addFoodClockIn(@RequestPart("file") MultipartFile file, @RequestPart("request") FoodClockInRequest foodClockInRequest){
        Long id = sessionUtils.getUserId();
        if(StringUtils.hasText(file.getName()) && !file.getName().equals("file")){
            foodClockInRequest.setPic(file);
        }
        else{
            foodClockInRequest.setPic(null);
        }
        return foodClockInService.addFoodClockIn(foodClockInRequest, id);
    }

}
