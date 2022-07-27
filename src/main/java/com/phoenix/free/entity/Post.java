package com.phoenix.free.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel("Post 贴子")
public class Post {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("发布者id")
    private Long userId;

    @ApiModelProperty("主标题")
    private String title;

    @TableField("content")
    @ApiModelProperty("正文")
    private String desc;

    @TableField("pic")
    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("发布时间")
    private String createTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("收藏乐观锁组件")
    private Integer version;
}
