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
@ApiModel("DrinkRecord 饮水记录")
public class DrinkRecord extends Record{
    @ApiModelProperty("目标饮水量")
    private int amount;

    @ApiModelProperty("饮水量")
    private int currentAmount;

    @ApiModelProperty("用户水杯大小")
    private int bottleSize;
}
