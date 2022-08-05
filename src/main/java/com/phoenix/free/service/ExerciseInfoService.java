package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;

import java.util.List;

public interface ExerciseInfoService {

    Exercise getExerciseInfoById(Long id);
    Exercise getExerciseInfoByName(String name);
    List<Exercise> searchExerciseInfo(String name, int page);

    int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long id);
    int deleteExerciseInfoById(Long id);
    int deleteExerciseInfoByName(String name);
}
