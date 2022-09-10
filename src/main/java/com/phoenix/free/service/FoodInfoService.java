package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodInfoService {

    Food getFoodInfoById(Long id);
    Food getFoodInfoByName(String name);
    Food updateFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long id);
    List<Food> searchFoodInfo(String name, int page);
    List<Food> getNewlyAddedFood();

    void addPic(Long userId, Long id, MultipartFile file);

    Long addFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long userId);
    int deleteFoodInfoById(Long id);
    int deleteFoodInfoByName(String name);

}
