package com.phoenix.free.controller.response;

import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("DailyHealthInfoResponse 用户今日健康信息")
public class DailyHealthInfoResponse {
    @ApiModelProperty("坚持运动打卡天数")
    private int exerciseClockInDays;

    @ApiModelProperty("坚持饮食打卡天数")
    private int foodClockInDays;

    @ApiModelProperty("今日运动（打卡）")
    private List<ExerciseClockIn> exerciseClockInList;

    @ApiModelProperty("今日饮食（打卡）")
    private List<FoodClockIn> foodClockInList;

    @ApiModelProperty("今日步数")
    private int stepCount;

    @ApiModelProperty("今日消耗热量")
    private double totalConsumption;

    @ApiModelProperty("今日摄入热量")
    private double totalEnergyIngestion;

    @ApiModelProperty("今日摄入糖分")
    private double totalSugarIngestion;

    @ApiModelProperty("今日摄入脂肪")
    private double totalFatIngestion;

    @ApiModelProperty("今日摄入蛋白质")
    private double totalProteinIngestion;

    @ApiModelProperty("今日摄入纤维素")
    private double totalCelluloseIngestion;
}
