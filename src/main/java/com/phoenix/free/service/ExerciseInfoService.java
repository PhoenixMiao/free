package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;

public interface ExerciseInfoService {

    Exercise getExerciseInfoById(Long id);
    Exercise getExerciseInfoByName(String name);

    int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest);
    int deleteExerciseInfoById(Long id);
    int deleteExerciseInfoByName(String name);
}
