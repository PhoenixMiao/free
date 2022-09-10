package com.phoenix.free.controller;

import com.phoenix.free.annotation.Admin;
import com.phoenix.free.annotation.Auth;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.Result;
import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.entity.Food;
import com.phoenix.free.service.ExerciseInfoService;
import com.phoenix.free.service.UserService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @ApiOperation(value = "添加新运动信息", response = Long.class)
    public Object addExerciseInfo(@RequestBody AddExerciseInfoRequest request){
        Long id = sessionUtils.getUserId();
        AssertUtil.isNull(exerciseInfoService.getExerciseInfoByName(request.getName()), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION,"请勿重复添加运动信息");
        request.setPic(null);
        return Result.success(exerciseInfoService.addExerciseInfo(request, id));
    }

    @Admin
    @PostMapping("/addPic/id={id}")
    @ApiOperation(value = "添加新运动信息图片")
    public Object addExerciseInfoPicture(@RequestPart("file") MultipartFile pic, @PathVariable("id") Long id){
        Long userId = sessionUtils.getUserId();
        exerciseInfoService.addPic(userId, id, pic);
        return Result.success(null);
    }

    @Auth
    @GetMapping("/get/id={id}")
    @ApiOperation(value = "按编号查找运动信息",response = Exercise.class)
    public Object getExerciseInfoById(@NotBlank @PathVariable("id") Long id){
        return Result.success(exerciseInfoService.getExerciseInfoById(id));
    }

    @Auth
    @GetMapping("/get/name={name}/page={page}")
    @ApiOperation(value = "按名称查找运动信息",response = Exercise.class, responseContainer = "List")
    public Object getExerciseInfoById(@NotBlank @PathVariable("name") String name, @PathVariable("page") int page){
        AssertUtil.isTrue(page >= 0, CommonErrorCode.INVALID_PARAM);
        return Result.success(exerciseInfoService.searchExerciseInfo(name, page));
    }

    @Auth
    @GetMapping("/list/page={page}")
    @ApiOperation(value = "所有运动列表",response = Exercise.class, responseContainer = "List")
    public Object getExerciseInfoList(@NotBlank @PathVariable("page") int page){
        AssertUtil.isTrue(page >= 0, CommonErrorCode.INVALID_PARAM);
        return Result.success(exerciseInfoService.searchExerciseInfo("", page));
    }

    @Admin
    @GetMapping("/list/new")
    @ApiOperation(value = "最近添加的运动列表",response = Exercise.class, responseContainer = "List")
    public Object getNewlyAddedExercise(){
        return exerciseInfoService.getNewlyAddedExercise();
    }

    @Admin
    @GetMapping("/delete/id={id}")
    @ApiOperation(value = "按编号删除运动信息",response = String.class)
    public Object deleteExerciseInfoById(@NotBlank @PathVariable("id") Long id){
        return Result.success(exerciseInfoService.deleteExerciseInfoById(id));
    }

    @Admin
    @Deprecated
    @GetMapping("/delete/name={name}")
    @ApiOperation(value = "按名称删除运动信息",response = String.class)
    public Object deleteExerciseInfoByName(@NotBlank @PathVariable("name") String name){
        return Result.success(exerciseInfoService.deleteExerciseInfoByName(name));
    }

    @Admin
    @PostMapping("/update/id={id}")
    @ApiOperation(value = "修改运动信息", response = Exercise.class)
    public Object updateExerciseInfo(@NotBlank @PathVariable("id") Long id, @RequestBody AddExerciseInfoRequest request){
        return exerciseInfoService.updateExerciseInfo(request, id);
    }
}
