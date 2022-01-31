package com.phoenix.free.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;

public class SearchTerm {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户Id")
    private Long userId;

    @ApiModelProperty("点击量")
    private int hits;

    @ApiModelProperty("历史搜索名")
    private String historyName;

    @ApiModelProperty("生命期")
    private int lifeSpan;
}
