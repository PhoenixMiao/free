package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;

import java.util.List;

public interface FoodInfoService {

    Food getFoodInfoById(Long id);
    Food getFoodInfoByName(String name);
    List<Food> searchFoodInfo(String name, int page);

    int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long id);
    int deleteFoodInfoById(Long id);
    int deleteFoodInfoByName(String name);

}
