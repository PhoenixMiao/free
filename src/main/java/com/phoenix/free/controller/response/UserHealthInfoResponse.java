package com.phoenix.free.controller.response;

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
@ApiModel("UserHealthInfoResponse 获取用户健康信息（用于填充健康空间内容）")
public class UserHealthInfoResponse {
    @ApiModelProperty("身高")
    private double height;

    @ApiModelProperty("体重")
    private double weight;

    @ApiModelProperty("BMI指数")
    private double bmi;

    @ApiModelProperty("目标")
    private String target;

    @ApiModelProperty("今日消耗的卡路里")
    private double calories;

    @ApiModelProperty("今日步数")
    private int stepCount;

}
