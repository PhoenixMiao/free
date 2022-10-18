package com.phoenix.free.service;

import com.phoenix.free.controller.request.AddOrUpdatePlanRequest;
import com.phoenix.free.controller.request.GeneratePlanRequest;
import com.phoenix.free.entity.Plan;

public interface PlanService {
    Long addPlan(AddOrUpdatePlanRequest request, Long id);
    Plan updatePlan(AddOrUpdatePlanRequest request, Long id);
    Plan generatePlan(GeneratePlanRequest request);
}
