package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SleepRecord 睡眠记录")
public class SleepRecord extends Record{
    @ApiModelProperty("目标睡眠时长")
    private int sleepTime;

    @ApiModelProperty("睡眠时长")
    private int currentSleepTime;
}
