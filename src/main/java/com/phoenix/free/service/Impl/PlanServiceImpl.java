package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddOrUpdatePlanRequest;
import com.phoenix.free.controller.request.GeneratePlanRequest;
import com.phoenix.free.entity.Plan;
import com.phoenix.free.mapper.PlanMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.PlanService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlanMapper planMapper;

    @Override
    public Long addPlan(AddOrUpdatePlanRequest request, Long id) {
        QueryWrapper<Plan> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("user_id", id);
        AssertUtil.isNull(planMapper.selectOne(wrapper), CommonErrorCode.DUPLICATE_DATABASE_INFORMATION);

        Plan plan = Plan.builder()
                .userId(id)
                .status(request.getStatus())
                .calories(request.getCalories())
                .path(request.getPath())
                .energy(request.getEnergy())
                .sugar(request.getSugar())
                .fat(request.getFat())
                .protein(request.getProtein())
                .cellulose(request.getCellulose())
                .build();
        planMapper.insert(plan);

        plan = planMapper.selectOne(wrapper);
        AssertUtil.isFalse(Objects.isNull(plan), CommonErrorCode.DATA_NOT_EXISTS);
        return plan.getId();
    }

    @Override
    public Plan updatePlan(AddOrUpdatePlanRequest request, Long id) {
        QueryWrapper<Plan> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("user_id", id);
        Plan plan = planMapper.selectOne(wrapper);
        AssertUtil.isFalse(Objects.isNull(plan), CommonErrorCode.DATA_NOT_EXISTS);

        plan.setStatus(request.getStatus());
        plan.setCalories(request.getCalories());
        plan.setPath(request.getPath());
        plan.setEnergy(request.getEnergy());
        plan.setSugar(request.getSugar());
        plan.setFat(request.getFat());
        plan.setProtein(request.getProtein());
        plan.setCellulose(request.getCellulose());

        AssertUtil.isTrue(1 == planMapper.updateById(plan), CommonErrorCode.UPDATE_FAIL);
        return plan;
    }

    @Override
    public Plan generatePlan(GeneratePlanRequest request) {
        DecimalFormat df = new DecimalFormat("#.0");
        double bmr = 0.0;
        double tdee = 0.0;
        // calculate BMR
        if(request.getGender() == 1){
            bmr += 661.0 + 9.6 * request.getWeight() + 1.72 * request.getHeight() - 4.7 * request.getAge();
        }
        else {
            bmr += 67.0 + 13.7 * request.getWeight() + 5.0 * request.getHeight() - 6.8 * request.getAge();
        }
        // calculate basic TDEE
        switch (request.getIntensity()){
            case 0:{
                tdee = bmr * 1.38;
                break;
            }
            case 1:{
                tdee = bmr * 1.55;
                break;
            }
            case 2:{
                tdee = bmr * 1.725;
                break;
            }
            default : break;
        }
        //calculate plan data
        tdee = Double.parseDouble(df.format(tdee * (Math.sqrt(bmr / 4000) * (new Random()).nextGaussian() + 100) / 100));
        double calories = tdee - bmr;
        double energy = 0.0;
        int path = 0;
        double sugar = 0.0;
        double fat = 0.0;
        double protein = 0.0;
        double cellulose = 25.0;
        switch (request.getTarget()){
            case 0:{
                energy = tdee - (400 - 100 * request.getIntensity());
                path = 5000;
                calories -= 150;
                fat = energy * 0.2 / 9;
                protein = request.getWeight() * 1.5;
                sugar = (energy - fat * 9 - protein * 4) / 4;
                break;
            }
            case 1:{
                energy = tdee + (400 - 100 * request.getIntensity());
                path = 10000;
                calories -= 300;
                fat = energy * 0.2 / 9;
                protein = request.getWeight() * 1.55;
                sugar = (energy - fat * 9 - protein * 4) / 4;
                break;
            }
            case 2:{
                energy = tdee;
                path = 8000;
                calories -= 240;
                fat = energy * 0.25 / 9;
                protein = request.getWeight() * 1.5;
                sugar = (energy - fat * 9 - protein * 4) / 4;
                break;
            }
            default : break;
        }
        if(request.getDiet() == 1){
            cellulose += Double.parseDouble(df.format(Math.sqrt(1) * (new Random()).nextGaussian() + 3));
        }
        if(request.getProtein() == 3 || request.getProtein() == 2){
            protein += Double.parseDouble(df.format(Math.sqrt(1) * (new Random()).nextGaussian() + 6));
        }

        Plan plan = Plan.builder()
                .calories(Double.parseDouble(df.format(calories)))
                .energy(Double.parseDouble(df.format(energy)))
                .protein(Double.parseDouble(df.format(protein)))
                .sugar(Double.parseDouble(df.format(sugar)))
                .fat(Double.parseDouble(df.format(fat)))
                .cellulose(Double.parseDouble(df.format(cellulose)))
                .path(path)
                .build();

        return plan;
    }
}
