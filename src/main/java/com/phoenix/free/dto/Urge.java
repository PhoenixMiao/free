package com.phoenix.free.dto;

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
@ApiModel("Urge 督促消息")
public class Urge {

    @Id
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("发送者id")
    private Long senderId;

    @ApiModelProperty("接收者id")
    private Long receiverId;

    @ApiModelProperty("关系id")
    private Long relationshipId;

    @ApiModelProperty("督促时间")
    private String urgeTime;
}
