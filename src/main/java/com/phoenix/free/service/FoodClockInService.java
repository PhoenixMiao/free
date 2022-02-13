package com.phoenix.free.service;

import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.entity.FoodClockIn;

import java.util.List;

public interface FoodClockInService {
    int addFoodClockIn(FoodClockInRequest foodClockInRequest, Long userId);

    FoodClockIn getFoodClockInById(Long id);
    List<FoodClockIn> getFoodClockInByUserId(Long userId);
}
