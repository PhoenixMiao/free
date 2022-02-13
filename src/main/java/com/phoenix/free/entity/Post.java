package com.phoenix.free.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;

@Data
@SuperBuilder
public class Post {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("发布者id")
    private Long userId;

    @ApiModelProperty("正文")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("发布时间")
    private String createTime;
}
