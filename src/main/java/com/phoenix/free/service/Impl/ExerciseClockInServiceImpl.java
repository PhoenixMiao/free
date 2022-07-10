package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.service.ExerciseClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ExerciseClockInServiceImpl implements ExerciseClockInService {
    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private ExerciseMapper exerciseMapper;

    public int addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        double currentCalories = 0;
//        currentCalories = exerciseMapper.getExerciseById(exerciseClockInRequest.getExerciseInfoId()).getRatio() * exerciseClockInRequest.getAmount();
        ExerciseClockIn exerciseClockIn = ExerciseClockIn.builder()
                .userId(userId)
                .recordTime(now)
                .content(exerciseClockInRequest.getContent())
                .pic(exerciseClockInRequest.getPic())
                .time(exerciseClockInRequest.getTime())
                .currentCalories(currentCalories)
                .exerciseInfoId(exerciseClockInRequest.getExerciseInfoId())
                .build();
        return exerciseClockInMapper.insertExerciseClockIn(exerciseClockIn);
    }

    public ExerciseClockIn getExerciseClockInById(Long id) {
        return exerciseClockInMapper.getExerciseClockInById(id);
    }

    public List<ExerciseClockIn> getExerciseClockInByUserId(Long userId) {
        return exerciseClockInMapper.getExerciseClockInByUserId(userId);
    }
}
