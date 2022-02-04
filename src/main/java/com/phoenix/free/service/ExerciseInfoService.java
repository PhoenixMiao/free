package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.ExerciseInfo;

public interface ExerciseInfoService {

    ExerciseInfo getExerciseInfoById(Long id);
    ExerciseInfo getExerciseInfoByName(String name);

    int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest);
    void deleteExerciseInfoById(Long id);
    void deleteExerciseInfoByName(String name);
}
