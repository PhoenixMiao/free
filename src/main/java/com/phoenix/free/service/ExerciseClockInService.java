package com.phoenix.free.service;

import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.entity.ExerciseClockIn;

public interface ExerciseClockInService {
    int addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId);

    ExerciseClockIn getExerciseClockInById(Long id);
    ExerciseClockIn getExerciseClockInByUserId(Long userId);
}
