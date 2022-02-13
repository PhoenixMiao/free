package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.UserAssessInfo;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.UserAssessInfoMapper;
import com.phoenix.free.service.UserHealthInfoService;
import com.phoenix.free.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserHealthInfoServiceImpl implements UserHealthInfoService {
    @Autowired
    private UserAssessInfoMapper userAssessInfoMapper;

    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private FoodClockInMapper foodClockInMapper;

    public UserHealthInfoResponse getUserHealthInfo(Long userId) {
        UserAssessInfo assessInfo = userAssessInfoMapper.getAssessByUserId(userId);
        //TODO 查看步数
        UserHealthInfoResponse response = UserHealthInfoResponse.builder()
                .height(assessInfo.getHeight())
                .weight(assessInfo.getWeight())
                .BMI(assessInfo.getWeight() / (assessInfo.getHeight() * assessInfo.getHeight()))
                .target(assessInfo.getTarget())
                .calories(calculateCaloriesOfOneDay(userId))
                .build();

        return response;
    }

    //计算一日卡路里
    private double calculateCaloriesOfOneDay(Long userId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = new String(simpleDateFormat.format(DatesUtil.getDayBegin()));

        List<ExerciseClockIn> exerciseClockInList;
        exerciseClockInList = exerciseClockInMapper.getExerciseClockInByUserId(userId);
        /*TODO 计算进食所摄取的能量
        List<FoodClockIn> foodClockInList;
        foodClockInList = foodClockInMapper.getFoodClockInByUserId(userId);
         */
        double calories = 0;
        for(ExerciseClockIn e : exerciseClockInList) {
            if(0 == DatesUtil.daysBetween(e.getRecordTime() , today))
                calories += e.getCurrentCalories();
        }
        return calories;
    }
}
