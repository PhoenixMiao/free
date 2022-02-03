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

    public Food getFoodById(Long id) {
        return foodMapper.getFoodById(id);
    }

    public Food getFoodByName(String name) {
        return foodMapper.getFoodByName(name);
    }

    public int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest) {
        Food food = Food.builder()
                .name(addFoodInfoRequest.getName())
                .category(addFoodInfoRequest.getCategory())
                .pic(addFoodInfoRequest.getPic())
                .heat(addFoodInfoRequest.getHeat())
                .fat(addFoodInfoRequest.getFat())
                .sugar(addFoodInfoRequest.getSugar())
                .protein(addFoodInfoRequest.getProtein())
                .cellulose(addFoodInfoRequest.getCellulose())
                .state(addFoodInfoRequest.getState())
                .build();
        return foodMapper.insertFood(food);
    }

    public void deleteFoodById(Long id) {
        foodMapper.deleteFoodById(id);
    }

    public void deleteFoodByName(String name) {
        foodMapper.deleteFoodByName(name);
    }
}
