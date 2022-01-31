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
@ApiModel("Assess 评估信息")
public class Assess {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("身高")
    private int height;

    @ApiModelProperty("体重")
    private int weight;

    @ApiModelProperty("目标")
    private String target;

    @ApiModelProperty("健康状况")
    private String condition;
}
