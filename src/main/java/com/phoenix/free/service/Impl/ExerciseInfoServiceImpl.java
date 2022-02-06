package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.ExerciseInfo;
import com.phoenix.free.mapper.ExerciseInfoMapper;
import com.phoenix.free.service.ExerciseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseInfoServiceImpl implements ExerciseInfoService {
    @Autowired
    private ExerciseInfoMapper exerciseInfoMapper;

    public ExerciseInfo getExerciseInfoById(Long id) {
        return exerciseInfoMapper.getExerciseById(id);
    }

    public ExerciseInfo getExerciseInfoByName(String name) {
        return exerciseInfoMapper.getExerciseByName(name);
    }

    public int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest) {
        ExerciseInfo exerciseInfo = ExerciseInfo.builder()
                .name(addExerciseInfoRequest.getName())
                .category(addExerciseInfoRequest.getCategory())
                .ratio(addExerciseInfoRequest.getRatio())
                .build();
        return exerciseInfoMapper.insertExerciseInfo(exerciseInfo);
    }

    public void deleteExerciseInfoById(Long id) {
        exerciseInfoMapper.deleteExerciseById(id);
    }

    public void deleteExerciseInfoByName(String name) {
        exerciseInfoMapper.deleteExerciseByName(name);
    }
}
