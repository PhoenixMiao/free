package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("RequestPost 请求搭档帖子")
public class RequestPost extends Post{
    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("浏览次数")
    private int browseTimes;

    @ApiModelProperty("状态")
    private int status;
}
