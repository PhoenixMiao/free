package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.service.FoodClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FoodClockInServiceImpl implements FoodClockInService {
    @Autowired
    private FoodClockInMapper foodClockInMapper;

    public int addFoodClockIn(FoodClockInRequest foodClockInRequest, Long userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = simpleDateFormat.format(new Date());

        FoodClockIn foodClockIn = FoodClockIn.builder()
                .userId(userId)
                .recordTime(now)
                .content(foodClockInRequest.getContent())
                .pic(foodClockInRequest.getPic())
                .totalEnergy(foodClockInRequest.getTotalEnergy())
                .totalSugar(foodClockInRequest.getTotalSugar())
                .totalFat(foodClockInRequest.getTotalFat())
                .totalProtein(foodClockInRequest.getTotalProtein())
                .totalCellulose(foodClockInRequest.getTotalCellulose())
                .foodInfoId(foodClockInRequest.getFoodInfoId())
                .build();

        return foodClockInMapper.insertFoodClockIn(foodClockIn);
    }

    public FoodClockIn getFoodClockInById(Long id) {
        return foodClockInMapper.getFoodClockInById(id);
    }

    public List<FoodClockIn> getFoodClockInByUserId(Long userId) {
        return foodClockInMapper.getFoodClockInByUserId(userId);
    }
}
