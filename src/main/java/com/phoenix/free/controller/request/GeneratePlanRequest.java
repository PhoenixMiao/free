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
@ApiModel("GeneratePlanRequest 提交调查问卷生成计划")
public class GeneratePlanRequest {
    @ApiModelProperty("性别")
    private int gender;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("体重")
    private double weight;

    @ApiModelProperty("身高")
    private double height;

    @ApiModelProperty("目标")
    private int target;

    @ApiModelProperty("运动强度")
    private int intensity;

    @ApiModelProperty("饮食结构")
    private int diet;

    @ApiModelProperty("蛋奶类摄入情况")
    private int protein;

    @ApiModelProperty("对现在身体状况满意度")
    private int satisfaction;

    @ApiModelProperty("偏好方式")
    private int preference;

}
