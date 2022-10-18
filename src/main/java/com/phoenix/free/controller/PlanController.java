package com.phoenix.free.controller;

import com.phoenix.free.annotation.Auth;
import com.phoenix.free.controller.request.AddOrUpdatePlanRequest;
import com.phoenix.free.controller.request.GeneratePlanRequest;
import com.phoenix.free.entity.Plan;
import com.phoenix.free.service.PlanService;
import com.phoenix.free.util.SessionUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/plan")
@RestController
public class PlanController {
    @Autowired
    private SessionUtils sessionUtils;

    @Autowired
    private PlanService planService;

    @Auth
    @PostMapping("/add")
    @ApiOperation(value = "添加计划",response = Long.class)
    public Object addPlan(@RequestBody AddOrUpdatePlanRequest request){
        Long id = sessionUtils.getUserId();
        return planService.addPlan(request, id);
    }

    @Auth
    @PostMapping("/update")
    @ApiOperation(value = "修改计划",response = Long.class)
    public Object updatePlan(@RequestBody AddOrUpdatePlanRequest request){
        Long id = sessionUtils.getUserId();
        return planService.updatePlan(request, id);
    }

    @Auth
    @PostMapping("/generate")
    @ApiOperation(value = "生成计划",response = Plan.class)
    public Object generatePlan(@RequestBody GeneratePlanRequest request){
        return planService.generatePlan(request);
    }
}
