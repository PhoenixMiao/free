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
@ApiModel("Food 食物")
public class Food {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("分类")
    private int category;

    @ApiModelProperty("热量")
    private double heat;

    @ApiModelProperty("糖分")
    private double sugar;

    @ApiModelProperty("脂肪")
    private double fat;

    @ApiModelProperty("蛋白质")
    private double protein;

    @ApiModelProperty("纤维素")
    private double  cellulose;

    @ApiModelProperty("状态")
    private String state;
}
