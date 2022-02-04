package com.phoenix.free.controller.request;

import com.phoenix.free.entity.ExerciseInfo;
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
@ApiModel("AddExerciseInfoRequest 添加运动信息")
public class AddExerciseInfoRequest {
    /**
     * {@link ExerciseInfo}
     */

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分类")
    private int category;

    @ApiModelProperty("消耗比")
    private double ratio;

}
