package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("ExerciseSearchTerm 运动搜索推荐")
public class ExerciseSearchTerm extends SearchTerm{
    @ApiModelProperty("推荐运动名")
    private String recName;
}
