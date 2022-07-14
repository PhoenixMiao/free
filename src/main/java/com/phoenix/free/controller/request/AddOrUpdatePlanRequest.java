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
@ApiModel("AddOrUpdatePlanRequest 添加或修改计划")
public class AddOrUpdatePlanRequest {
    @ApiModelProperty("状态")
    private int status;

    @ApiModelProperty("目标消耗卡路里")
    private double calories;

    @ApiModelProperty("目标步数")
    private int path;

    @ApiModelProperty("目标热量")
    private double energy;

    @ApiModelProperty("目标糖分")
    private double sugar;

    @ApiModelProperty("目标脂肪")
    private double fat;

    @ApiModelProperty("目标蛋白质")
    private double protein;

    @ApiModelProperty("目标纤维素")
    private double cellulose;
}
