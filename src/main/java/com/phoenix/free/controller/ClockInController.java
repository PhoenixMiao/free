package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.controller.response.ClockInGraphResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.service.ExerciseClockInService;
import com.phoenix.free.service.FoodClockInService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

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
    @ApiOperation(value = "运动打卡",response = Long.class)
    public Object addExerciseClockIn(@RequestBody ExerciseClockInRequest exerciseClockInRequest){
        Long id = sessionUtils.getUserId();
        exerciseClockInRequest.setPic(null);
        return exerciseClockInService.addExerciseClockIn(exerciseClockInRequest, id);
    }

    @Auth
    @PostMapping("/food")
    @ApiOperation(value = "饮食打卡",response = Long.class)
    public Object addFoodClockIn(@RequestBody FoodClockInRequest foodClockInRequest){
        Long id = sessionUtils.getUserId();
        foodClockInRequest.setPic(null);
        return foodClockInService.addFoodClockIn(foodClockInRequest, id);
    }

    @Auth
    @GetMapping("/exercise/pic/id={id}/sequence={sequence}")
    @ApiOperation(value = "为运动打卡添加图片")
    public Object addPicForExerciseClockIn(@RequestPart("file") MultipartFile file, @PathVariable("id") Long id, @PathVariable("sequence") int sequence){
        AssertUtil.isTrue(sequence > 0 && sequence < 10, CommonErrorCode.PARAMS_INVALID);
        AssertUtil.isNotNull(file, CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
        Long userId = sessionUtils.getUserId();
        exerciseClockInService.addPic(userId, id, file, sequence);
        return null;
    }

    @Auth
    @GetMapping("/food/pic/id={id}/sequence={sequence}")
    @ApiOperation(value = "为饮食打卡添加图片")
    public Object addPicForFoodClockIn(@RequestPart("file") MultipartFile file, @PathVariable("id") Long id, @PathVariable("sequence") int sequence){
        AssertUtil.isTrue(sequence > 0 && sequence < 10, CommonErrorCode.PARAMS_INVALID);
        AssertUtil.isNotNull(file, CommonErrorCode.FILENAME_CAN_NOT_BE_NULL);
        Long userId = sessionUtils.getUserId();
        foodClockInService.addPic(userId, id, file, sequence);
        return null;
    }

    @Auth
    @GetMapping("/get/food/userId={id}/page={page}")
    @ApiOperation(value = "查看用户饮食打卡", response = FoodClockIn.class, responseContainer = "List")
    public Object getFoodClockIn(@NotBlank @PathVariable("id") Long id, @PathVariable("page") int page){
        //todo 权限校验
        return foodClockInService.getFoodClockInByUserId(id, page);
    }

    @Auth
    @GetMapping("/get/exercise/userId={id}/page={page}")
    @ApiOperation(value = "查看用户运动打卡", response = ExerciseClockIn.class, responseContainer = "List")
    public Object getExerciseClockIn(@NotBlank @PathVariable("id") Long id, @PathVariable("page") int page){
        //todo 权限校验
        return exerciseClockInService.getExerciseClockInByUserId(id, page);
    }

    @Admin
    @GetMapping("/graph/exercise")
    @ApiOperation(value = "查看用户运动打卡数据统计", response = ClockInGraphResponse.class)
    public Object getExerciseClockInGraph(){
        return exerciseClockInService.getExerciseClockInGraph();
    }

    @Admin
    @GetMapping("/graph/food")
    @ApiOperation(value = "查看用户饮食打卡数据统计", response = ClockInGraphResponse.class)
    public Object getFoodClockInGraph(){
        return foodClockInService.getFoodClockInGraph();
    }
}
