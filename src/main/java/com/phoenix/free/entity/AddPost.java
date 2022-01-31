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
@ApiModel("AddPost 添加搭档帖子")
public class AddPost {
    @ApiModelProperty("帖子id")
    private Long postId;

    @ApiModelProperty("被请求者id")
    private Long userId2;
}
