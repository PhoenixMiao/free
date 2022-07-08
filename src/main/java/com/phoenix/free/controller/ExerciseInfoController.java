package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.Result;
import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.service.ExerciseInfoService;
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

@RequestMapping("/exerciseInfo")
@RestController
public class ExerciseInfoController {
    @Autowired
    private ExerciseInfoService exerciseInfoService;

    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserService userService;

    @Admin
    @PostMapping("/add")
    @ApiOperation(value = "添加新运动信息",response = String.class)
    public Object addExerciseInfo(@NotNull @Valid @RequestBody AddExerciseInfoRequest addExerciseInfoRequest){
        AssertUtil.isNull(exerciseInfoService.getExerciseInfoByName(addExerciseInfoRequest.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加运动信息");
        return Result.success(exerciseInfoService.addExerciseInfo(addExerciseInfoRequest));
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "按编号查找运动信息",response = Exercise.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object getExerciseInfoById(@NotBlank @PathVariable("id") Long id){
        return Result.success(exerciseInfoService.getExerciseInfoById(id));
    }

    @Auth
    @GetMapping("/get/name={name}")
    @ApiOperation(value = "按名称查找运动信息",response = Exercise.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object getExerciseInfoById(@NotBlank @PathVariable("name") String name){
        return Result.success(exerciseInfoService.getExerciseInfoByName(name));
    }

    @Admin
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "按编号删除运动信息",response = String.class)
    @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path")
    public Object deleteExerciseInfoById(@NotBlank @PathVariable("id") Long id){
        return Result.success(exerciseInfoService.deleteExerciseInfoById(id));
    }

    @Admin
    @GetMapping("/delete/name={name}")
    @ApiOperation(value = "按名称删除运动信息",response = String.class)
    @ApiImplicitParam(name = "name", value = "name", required = true, paramType = "path")
    public Object deleteExerciseInfoByName(@NotBlank @PathVariable("name") String name){
        return Result.success(exerciseInfoService.deleteExerciseInfoByName(name));
    }
}
