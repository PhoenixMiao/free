package com.phoenix.free.entity;

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
@ApiModel("Message 用户私信")
public class Message extends Post{
    @ApiModelProperty("接收者id")
    private Long receiverId;
}