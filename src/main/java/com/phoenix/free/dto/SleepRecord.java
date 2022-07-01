package com.phoenix.free.dto;

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
@ApiModel("SleepRecord 睡眠记录")
public class SleepRecord extends Record{
    @ApiModelProperty("目标睡眠时长")
    private int sleepTime;

    @ApiModelProperty("睡眠时长")
    private int currentSleepTime;
}
