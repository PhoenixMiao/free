package com.phoenix.free.controller.request;

import com.phoenix.free.entity.AddPartnerMessage;
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
@ApiModel("AddPartnerPostRequest 发送好友申请")
public class AddPartnerMessageRequest {
    /**
     * {@link AddPartnerMessage}
     */

    @ApiModelProperty("发布者id")
    private Long userId;

    @ApiModelProperty("正文")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("发布时间")
    private String createTime;

    @ApiModelProperty("被请求者id")
    private Long userId2;
}
