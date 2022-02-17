package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.controller.response.WeeklyHealthInfoResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.UserAssessInfo;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.UserAssessInfoMapper;
import com.phoenix.free.service.UserHealthInfoService;
import com.phoenix.free.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                .bmi(assessInfo.getWeight() / (assessInfo.getHeight() * assessInfo.getHeight()))
                .target(assessInfo.getTarget())
                .calories(calculateCaloriesOfOneDay(userId))
                .build();

        return response;
    }

    public WeeklyHealthInfoResponse getWeeklyHealthInfo(Long userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beginDay = simpleDateFormat.format(DatesUtil.getBeginDayOfWeek());

        List<ExerciseClockIn> exerciseClockInList,weeklyExerciseClockInList;
        exerciseClockInList = exerciseClockInMapper.getExerciseClockInByUserId(userId);
        weeklyExerciseClockInList = new ArrayList<ExerciseClockIn>();
        List<FoodClockIn> foodClockInList,weeklyFoodClockInList;
        foodClockInList = foodClockInMapper.getFoodClockInByUserId(userId);
        weeklyFoodClockInList = new ArrayList<FoodClockIn>();
        int exerciseClockInDays = 0;
        int foodClockInDays = 0;
        //TODO 计算运动时长（不同种运动如何相加？） double totalAmount = 0;
        double totalConsumption = 0;
        double totalEnergyIngestion = 0;
        double totalSugarIngestion = 0;
        double totalFatIngestion = 0;
        double totalProteinIngestion = 0;
        double totalCelluloseIngestion = 0;

        for(ExerciseClockIn e : exerciseClockInList) {
            if(0 <= DatesUtil.daysBetween(beginDay, e.getRecordTime())){
                weeklyExerciseClockInList.add(e);
                exerciseClockInDays += 1;
                totalConsumption += e.getCurrentCalories();
            }
        }
        for(FoodClockIn f : foodClockInList) {
            if(0 <= DatesUtil.daysBetween(beginDay, f.getRecordTime())) {
                weeklyFoodClockInList.add(f);
                foodClockInDays += 1;
                totalEnergyIngestion += f.getTotalEnergy();
                totalSugarIngestion += f.getTotalSugar();
                totalFatIngestion += f.getTotalFat();
                totalProteinIngestion += f.getTotalProtein();
                totalCelluloseIngestion += f.getTotalCellulose();
            }
        }

        WeeklyHealthInfoResponse response = WeeklyHealthInfoResponse.builder()
                .exerciseClockInList(weeklyExerciseClockInList)
                .foodClockInList(weeklyFoodClockInList)
                .exerciseClockInDays(exerciseClockInDays)
                .foodClockInDays(foodClockInDays)
                .totalConsumption(totalConsumption)
                .totalEnergyIngestion(totalEnergyIngestion)
                .totalSugarIngestion(totalSugarIngestion)
                .totalFatIngestion(totalFatIngestion)
                .totalProteinIngestion(totalProteinIngestion)
                .totalCelluloseIngestion(totalCelluloseIngestion)
                .build();

        return response;
    }

    //计算一日卡路里
    private double calculateCaloriesOfOneDay(Long userId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(DatesUtil.getDayBegin());

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
