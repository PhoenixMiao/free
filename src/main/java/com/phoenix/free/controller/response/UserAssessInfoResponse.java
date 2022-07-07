package com.phoenix.free.controller.response;

import com.phoenix.free.entity.Assess;
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
@ApiModel("UserAssessInfoResponse 获取用户评估信息")
public class UserAssessInfoResponse {
    /**
     * {@link Assess}
     */

    @ApiModelProperty("身高")
    private double height;

    @ApiModelProperty("体重")
    private double weight;

    @ApiModelProperty("目标")
    private String target;

    @ApiModelProperty("健康状况")
    private String condition;
}
