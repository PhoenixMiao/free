package com.phoenix.free.controller.request;

import com.phoenix.free.entity.ExerciseClockIn;
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
     * {@link ExerciseClockIn}
     */

    @ApiModelProperty("文字描述")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("运动时间")
    private int time;

    @ApiModelProperty("运动量")
    private double amount;

    @ApiModelProperty("详细的运动")
    private Long exerciseInfoId;
}
