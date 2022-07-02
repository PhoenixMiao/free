package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;

public interface FoodInfoService {

    Food getFoodInfoById(Long id);
    Food getFoodInfoByName(String name);

    int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest);
    void deleteFoodInfoById(Long id);
    void deleteFoodInfoByName(String name);

}
