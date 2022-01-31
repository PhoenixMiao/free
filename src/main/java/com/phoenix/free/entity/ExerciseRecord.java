package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("ExerciseRecord 运动记录")
public class ExerciseRecord {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("本周坚持时间")
    private String persistenceTime;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("目标消耗卡路里")
    private double calories;

    @ApiModelProperty("目标步数")
    private int path;

    @ApiModelProperty("消耗卡路里")
    private double currentCalories;

    @ApiModelProperty("步数")
    private int currentPath;

    @ApiModelProperty("状态")
    private int status;
}
