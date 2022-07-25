package com.phoenix.free.dto;

import com.phoenix.free.entity.Post;
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
@ApiModel("AddPartnerPost 添加搭档帖子")
public class AddPartnerPost extends Post {
    @ApiModelProperty("帖子id")
    private Long postId;

    @ApiModelProperty("被请求者id")
    private Long userId2;
}
