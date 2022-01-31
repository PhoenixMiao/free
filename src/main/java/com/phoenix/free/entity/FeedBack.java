package com.phoenix.free.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("FeedBack 反馈")
public class FeedBack extends Post{
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("状态")
    private int status;
}
