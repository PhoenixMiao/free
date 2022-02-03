package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;

public interface FoodInfoService {

    Food getFoodById(Long id);
    Food getFoodByName(String name);

    int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest);
    void deleteFoodById(Long id);
    void deleteFoodByName(String name);
}
