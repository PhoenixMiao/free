package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExerciseInfoService {

    Exercise getExerciseInfoById(Long id);
    Exercise getExerciseInfoByName(String name);
    Exercise updateExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long id);
    List<Exercise> searchExerciseInfo(String name, int page);
    List<Exercise> getNewlyAddedExercise();

    void addPic(Long userId, Long id, MultipartFile file);

    Long addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long userId);
    int deleteExerciseInfoById(Long id);
    int deleteExerciseInfoByName(String name);
}
