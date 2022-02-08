package com.phoenix.free.controller.request;

import com.phoenix.free.entity.FoodInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AddFoodInfoRequest 添加食物信息")
public class AddFoodInfoRequest {
    /**
     * {@link FoodInfo}
     */

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
    private double cellulose;

    @ApiModelProperty("状态")
    private String state;
}
