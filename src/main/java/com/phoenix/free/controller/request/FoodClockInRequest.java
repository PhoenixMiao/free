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
@ApiModel("FoodClockInRequest 饮食打卡")
public class FoodClockInRequest {
    /**
     * {@link com.phoenix.free.entity.FoodClockIn}
     */

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("文字描述")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

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

    @ApiModelProperty("详细的食物")
    private Long foodInfoId;
}
