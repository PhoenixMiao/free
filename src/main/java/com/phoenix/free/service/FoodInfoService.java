package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.FoodInfo;

public interface FoodInfoService {

    FoodInfo getFoodInfoById(Long id);
    FoodInfo getFoodInfoByName(String name);

    int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest);
    void deleteFoodInfoById(Long id);
    void deleteFoodInfoByName(String name);
}
