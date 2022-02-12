package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.ExerciseInfoMapper;
import com.phoenix.free.service.ExerciseClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseClockInServiceImpl implements ExerciseClockInService {
    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private ExerciseInfoMapper exerciseInfoMapper;

    public int addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId) {
        double currentCalories;
        currentCalories = exerciseInfoMapper.getExerciseById(exerciseClockInRequest.getExerciseInfoId()).getRatio() * exerciseClockInRequest.getAmount();
        ExerciseClockIn exerciseClockIn = ExerciseClockIn.builder()
                .userId(userId)
                .recordTime(exerciseClockInRequest.getRecordTime())
                .content(exerciseClockInRequest.getContent())
                .pic(exerciseClockInRequest.getPic())
                .currentCalories(currentCalories)
                .exerciseInfoId(exerciseClockInRequest.getExerciseInfoId())
                .build();
        return exerciseClockInMapper.insertExerciseClockIn(exerciseClockIn);
    }

    public ExerciseClockIn getExerciseClockInById(Long id) {
        return exerciseClockInMapper.getExerciseClockInById(id);
    }

    public ExerciseClockIn getExerciseClockInByUserId(Long userId) {
        return exerciseClockInMapper.getExerciseClockInByUserId(userId);
    }
}
