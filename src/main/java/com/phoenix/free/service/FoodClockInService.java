package com.phoenix.free.service;

import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.controller.response.ClockInGraphResponse;
import com.phoenix.free.entity.FoodClockIn;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodClockInService {
    Long addFoodClockIn(FoodClockInRequest foodClockInRequest, Long userId);
    void addPic(Long userId, Long clockInId, MultipartFile file, int sequence);

    FoodClockIn getFoodClockInById(Long id);

    List<FoodClockIn> getFoodClockInByUserId(Long userId, int page);
    ClockInGraphResponse getFoodClockInGraph();
}
