package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ExerciseRecord 运动记录")
public class ExerciseRecord extends Record{
    @ApiModelProperty("本周坚持时间")
    private String persistenceTime;

    @ApiModelProperty("目标消耗卡路里")
    private double calories;

    @ApiModelProperty("目标步数")
    private int path;

    @ApiModelProperty("消耗卡路里")
    private double currentCalories;

    @ApiModelProperty("步数")
    private int currentPath;
}
