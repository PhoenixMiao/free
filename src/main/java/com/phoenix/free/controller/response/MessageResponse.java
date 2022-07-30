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
@ApiModel("MessageResponse 用于显示添加搭档私信")
public class MessageResponse {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("发送者信息")
    private UserBriefInfoResponse sender;
}
