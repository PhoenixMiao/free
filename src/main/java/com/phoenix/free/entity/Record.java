package com.phoenix.free.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;

@SuperBuilder
public class Record {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("状态")
    private int status;
}
