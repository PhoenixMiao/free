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
@ApiModel("WeeklyHealthInfoResponse 用户每周健康信息")
public class WeeklyHealthInfoResponse {
    @ApiModelProperty("一周运动打卡列表")
    private List<ExerciseClockIn> exerciseClockInList;

    @ApiModelProperty("一周饮食打卡列表")
    private List<FoodClockIn> foodClockInList;

    @ApiModelProperty("坚持运动打卡天数（本周）")
    private int exerciseClockInDays;

    @ApiModelProperty("坚持饮食打卡天数（本周）")
    private int foodClockInDays;

    @ApiModelProperty("总计运动时长")
    private double totalTime;

    @ApiModelProperty("总计消耗热量")
    private double totalConsumption;

    @ApiModelProperty("总计摄入热量")
    private double totalEnergyIngestion;

    @ApiModelProperty("总计摄入糖分")
    private double totalSugarIngestion;

    @ApiModelProperty("总计摄入脂肪")
    private double totalFatIngestion;

    @ApiModelProperty("总计摄入蛋白质")
    private double totalProteinIngestion;

    @ApiModelProperty("总计摄入纤维素")
    private double totalCelluloseIngestion;
}
