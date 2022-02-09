package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("ExerciseClockIn 运动打卡")
public class ExerciseClockIn extends ClockIn{
    @ApiModelProperty("总计消耗卡路里")
    private double currentCalories;

    @ApiModelProperty("详细的运动")
    private Long exerciseInfoId;
}
