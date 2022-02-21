package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel("FoodClockIn 饮食打卡")
public class FoodClockIn extends ClockIn{
    @ApiModelProperty("总热量")
    private double totalEnergy;

    @ApiModelProperty("总糖分")
    private double totalSugar;

    @ApiModelProperty("总脂肪")
    private double totalFat;

    @ApiModelProperty("总蛋白质")
    private double totalProtein;

    @ApiModelProperty("总纤维素")
    private double totalCellulose;

    @ApiModelProperty("详细的食物")
    private Long foodInfoId;
}
