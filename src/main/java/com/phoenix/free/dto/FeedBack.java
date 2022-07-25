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
@ApiModel("FeedBack 反馈")
public class FeedBack extends Post {
    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("状态")
    private int status;
}
