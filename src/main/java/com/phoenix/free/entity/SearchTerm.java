package com.phoenix.free.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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
