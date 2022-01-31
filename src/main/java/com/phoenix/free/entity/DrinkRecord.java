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
@ApiModel("DrinkRecord 饮水记录")
public class DrinkRecord {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("目标饮水量")
    private int amount;

    @ApiModelProperty("饮水量")
    private int currentAmount;

    @ApiModelProperty("用户水杯大小")
    private int bottleSize;

    @ApiModelProperty("状态")
    private int status;
}
