package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.service.ExerciseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseInfoServiceImpl implements ExerciseInfoService {
    @Autowired
    private ExerciseMapper exerciseMapper;

    public Exercise getExerciseInfoById(Long id) {
        return exerciseMapper.selectById(id);
    }

    public Exercise getExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("name", name);
        return exerciseMapper.selectOne(wrapper);
    }

    public int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest) {
        Exercise exercise = Exercise.builder()
                .name(addExerciseInfoRequest.getName())
                .category(addExerciseInfoRequest.getCategory())
                .ratio(addExerciseInfoRequest.getRatio())
                .build();
        return exerciseMapper.insert(exercise);
    }

    public int deleteExerciseInfoById(Long id) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return exerciseMapper.delete(wrapper);
    }

    public int deleteExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return exerciseMapper.delete(wrapper);
    }
}
