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
@ApiModel("Message 用户私信")
public class Message {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("发送者id")
    private Long senderId;

    @ApiModelProperty("接收者id")
    private Long receiverId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("创建时间")
    private String createTime;
}
