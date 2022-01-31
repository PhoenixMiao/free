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
@ApiModel("Relationship 搭档关系")
public class Relationship {
    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id1")
    private Long userId1;

    @ApiModelProperty("用户id2")
    private Long userId2;

    @ApiModelProperty("确定时间")
    private String recordTime;
}
