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
@ApiModel("FoodSearchTerm 食物搜索推荐")
public class FoodSearchTerm {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户Id")
    private Long userId;

    @ApiModelProperty("推荐食物名")
    private String recName;

    @ApiModelProperty("点击量")
    private int hits;

    @ApiModelProperty("历史搜索名")
    private String historyName;

    @ApiModelProperty("生命期")
    private int lifeSpan;
}
