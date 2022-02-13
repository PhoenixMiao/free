package com.phoenix.free.entity;

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
@ApiModel("FoodSearchTerm 食物搜索推荐")
public class FoodSearchTerm extends SearchTerm{
    @ApiModelProperty("推荐食物名")
    private String recName;
}
