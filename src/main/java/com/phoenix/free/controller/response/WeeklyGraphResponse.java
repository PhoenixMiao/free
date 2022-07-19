package com.phoenix.free.controller.response;

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
@ApiModel("WeeklyGraphResponse 一周健康记录统计图")
public class WeeklyGraphResponse {
    @ApiModelProperty("七天摄入能量")
    List<Double> ingestion;

    @ApiModelProperty("七天消耗能量")
    List<Double> consumption;

    @ApiModelProperty("七天日期")
    List<String> date;
}
