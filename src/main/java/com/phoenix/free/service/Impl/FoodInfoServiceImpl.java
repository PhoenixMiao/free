package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.FoodInfo;
import com.phoenix.free.mapper.FoodInfoMapper;
import com.phoenix.free.service.FoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {
    @Autowired
    private FoodInfoMapper foodInfoMapper;

    public FoodInfo getFoodInfoById(Long id) {
        return foodInfoMapper.getFoodInfoById(id);
    }

    public FoodInfo getFoodInfoByName(String name) {
        return foodInfoMapper.getFoodInfoByName(name);
    }

    public int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest) {
        FoodInfo food = FoodInfo.builder()
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
        return foodInfoMapper.insertFoodInfo(food);
    }

    public void deleteFoodInfoById(Long id) {
        foodInfoMapper.deleteFoodInfoById(id);
    }

    public void deleteFoodInfoByName(String name) {
        foodInfoMapper.deleteFoodInfoByName(name);
    }
}
