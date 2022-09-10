package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.ExerciseInfoService;
import com.phoenix.free.service.FileService;
import com.phoenix.free.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class ExerciseInfoServiceImpl implements ExerciseInfoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Autowired
    private FileService fileService;

    public Exercise getExerciseInfoById(Long id) {
        return exerciseMapper.selectById(id);
    }

    public Exercise getExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("name", name);
        return exerciseMapper.selectOne(wrapper);
    }

    @Override
    public Exercise updateExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long id) {
        Exercise exercise = exerciseMapper.selectById(id);
        AssertUtil.isNotNull(exercise, CommonErrorCode.DATA_NOT_EXISTS);

        exercise.setName(addExerciseInfoRequest.getName());
        exercise.setCategory(addExerciseInfoRequest.getCategory());
        exercise.setRatio(addExerciseInfoRequest.getRatio());
        exercise.setState(addExerciseInfoRequest.getState());

        AssertUtil.isFalse(exerciseMapper.updateById(exercise) == 0, CommonErrorCode.UPDATE_FAIL);

        return exercise;
    }

    @Override
    public List<Exercise> searchExerciseInfo(String name, int page) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .like("name", name)
                .last("LIMIT 15 OFFSET " + offset);
        return exerciseMapper.selectList(wrapper);
    }

    @Override
    public List<Exercise> getNewlyAddedExercise() {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .orderByDesc("id")
                .last("LIMIT 5");
        return exerciseMapper.selectList(wrapper);
    }

    @Override
    public void addPic(Long userId, Long id, MultipartFile file) {
        Exercise exercise = exerciseMapper.selectById(id);
        AssertUtil.isNotNull(exercise, CommonErrorCode.DATA_NOT_EXISTS);
        String url = null;
        if(!Objects.isNull(file)){
            String type = "/" + exercise.getName();
            url = fileService.uploadPicture(file, userId, type);
        }
        exercise.setPic(url);
        AssertUtil.isFalse(exerciseMapper.updateById(exercise) == 0, CommonErrorCode.UPDATE_FAIL);
        return ;
    }

    @Override
    public Long addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long userId) {
        String url = null;
        User user = userMapper.selectById(userId);
        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        if(!Objects.isNull(addExerciseInfoRequest.getPic())){
            String type = "/" + addExerciseInfoRequest.getName();
            MultipartFile file = addExerciseInfoRequest.getPic();
            url = fileService.uploadPicture(file, userId, type);
        }
        Exercise exercise = Exercise.builder()
                .name(addExerciseInfoRequest.getName())
                .pic(url)
                .category(addExerciseInfoRequest.getCategory())
                .ratio(addExerciseInfoRequest.getRatio())
                .state(addExerciseInfoRequest.getState())
                .build();
        if(exerciseMapper.insert(exercise) == 1){
            return exercise.getId();
        }
        else return -1l;
    }

    @Override
    public int deleteExerciseInfoById(Long id) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return exerciseMapper.delete(wrapper);
    }

    @Override
    public int deleteExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return exerciseMapper.delete(wrapper);
    }
}
