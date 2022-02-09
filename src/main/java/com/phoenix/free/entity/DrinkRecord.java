package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("DrinkRecord 饮水记录")
public class DrinkRecord extends Record{
    @ApiModelProperty("目标饮水量")
    private int amount;

    @ApiModelProperty("饮水量")
    private int currentAmount;

    @ApiModelProperty("用户水杯大小")
    private int bottleSize;
}
