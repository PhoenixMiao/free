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
@ApiModel("ExerciseClockIn 运动打卡")
public class ExerciseClockIn {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("文字描述")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("总计消耗卡路里")
    private double currentCalories;

    @ApiModelProperty("详细的运动")
    private String exerciseList;
}
