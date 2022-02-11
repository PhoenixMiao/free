package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.service.ExerciseClockInService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class ExerciseClockInController {
    @Autowired
    private ExerciseClockInService exerciseClockInService;

    @Autowired
    private SessionUtils sessionUtils;

    @Auth
    @PostMapping("/exerciseClockIn")
    @ApiOperation(value = "运动打卡",response = String.class)
    public Object addExerciseClockIn(@NotNull @Valid @RequestBody ExerciseClockInRequest exerciseClockInRequest){
        Long id = sessionUtils.getUserId();

        exerciseClockInService.addExerciseClockIn(exerciseClockInRequest, id);
        return "打卡成功";
    }
}
