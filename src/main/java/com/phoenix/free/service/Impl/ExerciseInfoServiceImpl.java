package com.phoenix.free.service.Impl;

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
        return exerciseMapper.getExerciseById(id);
    }

    public Exercise getExerciseInfoByName(String name) {
        return exerciseMapper.getExerciseByName(name);
    }

    public int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest) {
        Exercise exercise = Exercise.builder()
                .name(addExerciseInfoRequest.getName())
                .category(addExerciseInfoRequest.getCategory())
                .ratio(addExerciseInfoRequest.getRatio())
                .build();
        return exerciseMapper.insertExerciseInfo(exercise);
    }

    public void deleteExerciseInfoById(Long id) {
        exerciseMapper.deleteExerciseById(id);
    }

    public void deleteExerciseInfoByName(String name) {
        exerciseMapper.deleteExerciseByName(name);
    }
}
