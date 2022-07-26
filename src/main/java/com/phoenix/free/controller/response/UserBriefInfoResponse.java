package com.phoenix.free.controller.response;

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
@ApiModel("UserBriefInfoResponse 用户简要信息")
public class UserBriefInfoResponse {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("昵称")
    private String nickname;
}
