package com.phoenix.free.service;

import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.entity.ExerciseClockIn;

import java.util.List;

public interface ExerciseClockInService {
    Long addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId);

    ExerciseClockIn getExerciseClockInById(Long id);
    List<ExerciseClockIn> getExerciseClockInByUserId(Long userId, int page);
}
