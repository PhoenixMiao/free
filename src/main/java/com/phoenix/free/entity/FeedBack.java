package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ApiModel("FeedBack 反馈")
public class FeedBack extends Post{
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("状态")
    private int status;
}
