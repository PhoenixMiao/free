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
@ApiModel("FoodRecord 饮食记录")
public class FoodRecord {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("本周坚持时间")
    private String persistenceTime;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("目标热量")
    private double targetHeat;

    @ApiModelProperty("目标糖分")
    private double targetSugar;

    @ApiModelProperty("目标脂肪")
    private double targetFat;

    @ApiModelProperty("目标蛋白质")
    private double targetProtein;

    @ApiModelProperty("目标纤维素")
    private double targetCellulose;

    @ApiModelProperty("目标饮水量")
    private double targetWater;

    @ApiModelProperty("总热量")
    private double totalHeat;

    @ApiModelProperty("总糖分")
    private double totalSugar;

    @ApiModelProperty("总脂肪")
    private double totalFat;

    @ApiModelProperty("总蛋白质")
    private double totalProtein;

    @ApiModelProperty("总纤维素")
    private double totalCellulose;

    @ApiModelProperty("状态")
    private int status;
}
