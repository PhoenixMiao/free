package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddOrUpdatePlanRequest;
import com.phoenix.free.entity.Plan;
import com.phoenix.free.mapper.PlanMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.PlanService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
