package com.phoenix.free.controller.response;

import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.Plan;
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
@ApiModel("RecordResponse 用户健康记录")
public class RecordResponse {
    @ApiModelProperty("一周坚持运动打卡天数")
    private int exerciseClockInDays;

    @ApiModelProperty("运动打卡列表")
    private List<ExerciseClockIn> exerciseClockInList;

    @ApiModelProperty("一周坚持饮食打卡天数")
    private int foodClockInDays;

    @ApiModelProperty("饮食打卡列表")
    private List<FoodClockIn> foodClockInList;

    @ApiModelProperty("步数")
    private int stepCount;

    @ApiModelProperty("运动时长")
    private int totalTime;

    @ApiModelProperty("消耗热量")
    private double totalConsumption;

    @ApiModelProperty("摄入热量")
    private double totalEnergyIngestion;

    @ApiModelProperty("摄入糖分")
    private double totalSugarIngestion;

    @ApiModelProperty("摄入脂肪")
    private double totalFatIngestion;

    @ApiModelProperty("摄入蛋白质")
    private double totalProteinIngestion;

    @ApiModelProperty("摄入纤维素")
    private double totalCelluloseIngestion;

    @ApiModelProperty("计划目标")
    private Plan plan;
}
