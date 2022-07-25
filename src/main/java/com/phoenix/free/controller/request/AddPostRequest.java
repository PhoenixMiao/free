package com.phoenix.free.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("AddPostRequest 发贴")
public class AddPostRequest {
    @ApiModelProperty("主标题")
    private String title;

    @ApiModelProperty("正文")
    private String content;

    @ApiModelProperty("图片")
    private MultipartFile pic;
}
