package com.phoenix.free.service;

import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.controller.response.ClockInGraphResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExerciseClockInService {
    Long addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId);
    void addPic(Long userId, Long clockInId, MultipartFile file, int sequence);

    ExerciseClockIn getExerciseClockInById(Long id);
    List<ExerciseClockIn> getExerciseClockInByUserId(Long userId, int page);

    ClockInGraphResponse getExerciseClockInGraph();
}
