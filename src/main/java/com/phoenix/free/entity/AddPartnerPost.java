package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("AddPartnerPost 添加搭档帖子")
public class AddPartnerPost extends Post {
    @ApiModelProperty("帖子id")
    private Long postId;

    @ApiModelProperty("被请求者id")
    private Long userId2;
}
