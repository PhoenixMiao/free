package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.controller.response.RecordResponse;
import com.phoenix.free.controller.response.UserHealthInfoResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.Plan;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.AssessMapper;
import com.phoenix.free.mapper.PlanMapper;
import com.phoenix.free.service.UserHealthInfoService;
import com.phoenix.free.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class UserHealthInfoServiceImpl implements UserHealthInfoService {
    @Autowired
    private AssessMapper assessMapper;

    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private FoodClockInMapper foodClockInMapper;

    @Autowired
    private PlanMapper planMapper;

    @Override
    public UserHealthInfoResponse getUserHealthInfo(Long userId) {
//        Assess assessInfo = assessMapper.getAssessByUserId(userId);
//
//        UserHealthInfoResponse response = UserHealthInfoResponse.builder()
//                .height(assessInfo.getHeight())
//                .weight(assessInfo.getWeight())
//                .bmi(assessInfo.getWeight() / (assessInfo.getHeight() * assessInfo.getHeight()))
//                .target(assessInfo.getTarget())
//                .calories(calculateCaloriesOfOneDay(userId))
//                .build();
//
//        return response;
        return null;
    }

    @Override
    public RecordResponse getRecord(Long userId, boolean isOneDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String beginDay;
        if(isOneDay){
            beginDay = simpleDateFormat.format(DatesUtil.getDayBegin());
        }
        else {
            beginDay = simpleDateFormat.format(DatesUtil.getBeginDayOfWeek());
        }
        String endDay = simpleDateFormat.format(DatesUtil.getBeginDayOfTomorrow());

        QueryWrapper<ExerciseClockIn> exerciseClockInWrapper = new QueryWrapper<>();
        exerciseClockInWrapper.select("*")
                .eq("user_id", userId)
                .between("record_time", beginDay, endDay);
        QueryWrapper<FoodClockIn> foodClockInWrapper = new QueryWrapper<>();
        foodClockInWrapper.select("*")
                .eq("user_id", userId)
                .between("record_time", beginDay, endDay);

        List<ExerciseClockIn> exerciseClockInList;
        exerciseClockInList = exerciseClockInMapper.selectList(exerciseClockInWrapper);
        List<FoodClockIn> foodClockInList;
        foodClockInList = foodClockInMapper.selectList(foodClockInWrapper);
        int exerciseClockInDays = exerciseClockInMapper.calculateClockInDays(userId, simpleDateFormat.format(DatesUtil.getBeginDayOfWeek()));
        int foodClockInDays = foodClockInMapper.calculateClockInDays(userId, simpleDateFormat.format(DatesUtil.getBeginDayOfWeek()));;
        int totalTime = 0;
        double totalConsumption = 0;
        double totalEnergyIngestion = 0;
        double totalSugarIngestion = 0;
        double totalFatIngestion = 0;
        double totalProteinIngestion = 0;
        double totalCelluloseIngestion = 0;

        for(ExerciseClockIn e : exerciseClockInList) {
            totalTime += e.getTime();
            totalConsumption += e.getCurrentCalories();
        }
        for(FoodClockIn f : foodClockInList) {
            totalEnergyIngestion += f.getTotalEnergy();
            totalSugarIngestion += f.getTotalSugar();
            totalFatIngestion += f.getTotalFat();
            totalProteinIngestion += f.getTotalProtein();
            totalCelluloseIngestion += f.getTotalCellulose();
        }

        //TODO 统计今日步数
        QueryWrapper<Plan> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("user_id", userId);

        RecordResponse response = RecordResponse.builder()
                .exerciseClockInDays(exerciseClockInDays)
                .foodClockInDays(foodClockInDays)
                .totalTime(totalTime)
                .totalConsumption(totalConsumption)
                .totalEnergyIngestion(totalEnergyIngestion)
                .totalSugarIngestion(totalSugarIngestion)
                .totalFatIngestion(totalFatIngestion)
                .totalProteinIngestion(totalProteinIngestion)
                .totalCelluloseIngestion(totalCelluloseIngestion)
                .plan(planMapper.selectOne(wrapper))
                .build();

        return response;
    }

    private double calculateCaloriesOfOneDay(Long userId){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String today = simpleDateFormat.format(DatesUtil.getDayBegin());
//
//        List<ExerciseClockIn> exerciseClockInList;
//        exerciseClockInList = exerciseClockInMapper.getExerciseClockInByUserId(userId);
//        List<FoodClockIn> foodClockInList;
//        foodClockInList = foodClockInMapper.getFoodClockInByUserId(userId);
//
//        double calories = 0;
//        for(ExerciseClockIn e : exerciseClockInList) {
//            if(0 == DatesUtil.daysBetween(e.getRecordTime() , today))
//                calories -= e.getCurrentCalories();
//        }
//        for(FoodClockIn f : foodClockInList) {
//            if(0 == DatesUtil.daysBetween(f.getRecordTime() , today))
//                calories += f.getTotalEnergy();
//        }
//        return calories;
        return 0;
    }
}
