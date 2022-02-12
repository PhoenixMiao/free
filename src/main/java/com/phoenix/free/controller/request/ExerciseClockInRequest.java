package com.phoenix.free.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("ExerciseClockInRequest 运动打卡")
public class ExerciseClockInRequest {
    /**
     * {@link com.phoenix.free.entity.ExerciseClockIn}
     */

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("文字描述")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("运动量")
    private double amount;

    @ApiModelProperty("详细的运动")
    private Long exerciseInfoId;
}
