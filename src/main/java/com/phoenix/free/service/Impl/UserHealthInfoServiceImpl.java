package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.response.DailyHealthInfoResponse;
import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.controller.response.WeeklyHealthInfoResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.Assess;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.AssessMapper;
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
    private AssessMapper assessMapper;

    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private FoodClockInMapper foodClockInMapper;

    public UserHealthInfoResponse getUserHealthInfo(Long userId) {
        Assess assessInfo = assessMapper.getAssessByUserId(userId);
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
        int totalTime = 0;
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
                totalTime += e.getTime();
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
                .totalTime(totalTime)
                .totalConsumption(totalConsumption)
                .totalEnergyIngestion(totalEnergyIngestion)
                .totalSugarIngestion(totalSugarIngestion)
                .totalFatIngestion(totalFatIngestion)
                .totalProteinIngestion(totalProteinIngestion)
                .totalCelluloseIngestion(totalCelluloseIngestion)
                .build();

        return response;
    }

    public DailyHealthInfoResponse getDailyHealthInfo(Long userId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(DatesUtil.getDayBegin());

        List<ExerciseClockIn> exerciseClockInList,dailyExerciseClockInList;
        exerciseClockInList = exerciseClockInMapper.getExerciseClockInByUserId(userId);
        dailyExerciseClockInList = new ArrayList<ExerciseClockIn>();
        List<FoodClockIn> foodClockInList,dailyFoodClockInList;
        foodClockInList = foodClockInMapper.getFoodClockInByUserId(userId);
        dailyFoodClockInList = new ArrayList<FoodClockIn>();
        int exerciseClockInDays = 0;
        int foodClockInDays = 0;
        int totalTime = 0;
        double totalConsumption = 0;
        double totalEnergyIngestion = 0;
        double totalSugarIngestion = 0;
        double totalFatIngestion = 0;
        double totalProteinIngestion = 0;
        double totalCelluloseIngestion = 0;

        for(ExerciseClockIn e : exerciseClockInList) {
            exerciseClockInDays += 1;
            if(0 == DatesUtil.daysBetween(today, e.getRecordTime())){
                dailyExerciseClockInList.add(e);
                totalTime += e.getTime();
                totalConsumption += e.getCurrentCalories();
            }
        }
        for(FoodClockIn f : foodClockInList) {
            foodClockInDays += 1;
            if(0 == DatesUtil.daysBetween(today, f.getRecordTime())) {
                dailyFoodClockInList.add(f);
                totalEnergyIngestion += f.getTotalEnergy();
                totalSugarIngestion += f.getTotalSugar();
                totalFatIngestion += f.getTotalFat();
                totalProteinIngestion += f.getTotalProtein();
                totalCelluloseIngestion += f.getTotalCellulose();
            }
        }

        //TODO 统计今日步数

        DailyHealthInfoResponse response = DailyHealthInfoResponse.builder()
                .exerciseClockInDays(exerciseClockInDays)
                .foodClockInDays(foodClockInDays)
                .exerciseClockInList(dailyExerciseClockInList)
                .foodClockInList(dailyFoodClockInList)
                .totalTime(totalTime)
                .totalConsumption(totalConsumption)
                .totalEnergyIngestion(totalEnergyIngestion)
                .totalSugarIngestion(totalSugarIngestion)
                .totalFatIngestion(totalFatIngestion)
                .totalProteinIngestion(totalProteinIngestion)
                .totalCelluloseIngestion(totalCelluloseIngestion)
                .build();

        return response;
    }

    private double calculateCaloriesOfOneDay(Long userId){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(DatesUtil.getDayBegin());

        List<ExerciseClockIn> exerciseClockInList;
        exerciseClockInList = exerciseClockInMapper.getExerciseClockInByUserId(userId);
        List<FoodClockIn> foodClockInList;
        foodClockInList = foodClockInMapper.getFoodClockInByUserId(userId);

        double calories = 0;
        for(ExerciseClockIn e : exerciseClockInList) {
            if(0 == DatesUtil.daysBetween(e.getRecordTime() , today))
                calories -= e.getCurrentCalories();
        }
        for(FoodClockIn f : foodClockInList) {
            if(0 == DatesUtil.daysBetween(f.getRecordTime() , today))
                calories += f.getTotalEnergy();
        }
        return calories;
    }
}
