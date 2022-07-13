package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import com.phoenix.free.mapper.FoodMapper;
import com.phoenix.free.service.FoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {
    @Autowired
    private FoodMapper foodMapper;

    public Food getFoodInfoById(Long id) {
        return foodMapper.selectById(id);
    }

    public Food getFoodInfoByName(String name) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("name", name);
        return foodMapper.selectOne(wrapper);
    }

    @Override
    public List<Food> searchFoodInfo(String name, int page) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .like("name", name)
                .last("LIMIT 15 OFFSET " + offset);
        return foodMapper.selectList(wrapper);
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
        return foodMapper.insert(food);
    }

    public int deleteFoodInfoById(Long id) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return foodMapper.delete(wrapper);
    }

    public int deleteFoodInfoByName(String name) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return foodMapper.delete(wrapper);
    }
}
