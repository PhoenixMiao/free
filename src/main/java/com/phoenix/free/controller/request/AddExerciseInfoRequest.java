package com.phoenix.free.controller.request;

import com.phoenix.free.entity.Exercise;
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
@ApiModel("AddExerciseInfoRequest 添加运动信息")
public class AddExerciseInfoRequest {
    /**
     * {@link Exercise}
     */

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图片文件(上传时留空)")
    private MultipartFile pic;

    @ApiModelProperty("分类")
    private int category;

    @ApiModelProperty("消耗比")
    private double ratio;

    @ApiModelProperty("状态")
    private String state;
}
