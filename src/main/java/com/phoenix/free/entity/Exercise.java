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
@ApiModel("Exercise 运动")
public class Exercise {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类")
    private int category;

    @ApiModelProperty("消耗比")
    private double ratio;

    @ApiModelProperty("运动量")
    private int exerciseAmount;

    @ApiModelProperty("消耗量")
    private double consume;

    @ApiModelProperty("状态")
    private int state;
}
