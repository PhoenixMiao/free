package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import com.phoenix.free.mapper.FoodMapper;
import com.phoenix.free.service.FoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {
    @Autowired
    private FoodMapper foodMapper;

    public Food getFoodInfoById(Long id) {
        return foodMapper.getFoodInfoById(id);
    }

    public Food getFoodInfoByName(String name) {
        return foodMapper.getFoodInfoByName(name);
    }

    public int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest) {
        Food food = Food.builder()
                .name(addFoodInfoRequest.getName())
                .category(addFoodInfoRequest.getCategory())
                .pic(addFoodInfoRequest.getPic())
                .energy(addFoodInfoRequest.getEnergy())
                .fat(addFoodInfoRequest.getFat())
                .sugar(addFoodInfoRequest.getSugar())
                .protein(addFoodInfoRequest.getProtein())
                .cellulose(addFoodInfoRequest.getCellulose())
                .state(addFoodInfoRequest.getState())
                .build();
        return foodMapper.insertFoodInfo(food);
    }

    public void deleteFoodInfoById(Long id) {
        foodMapper.deleteFoodInfoById(id);
    }

    public void deleteFoodInfoByName(String name) {
        foodMapper.deleteFoodInfoByName(name);
    }
}
