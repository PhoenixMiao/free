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
@ApiModel("ExerciseSearchTerm 运动搜索推荐")
public class ExerciseSearchTerm extends SearchTerm{
    @ApiModelProperty("推荐运动名")
    private String recName;
}
