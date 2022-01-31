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
@ApiModel("SleepRecord 睡眠记录")
public class SleepRecord {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("目标睡眠时长")
    private int sleepTime;

    @ApiModelProperty("睡眠时长")
    private int currentSleepTime;

    @ApiModelProperty("状态")
    private int status;
}
