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
@ApiModel("ExerciseClockIn 运动打卡")
public class ExerciseClockIn {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("记录时间")
    private String recordTime;

    @ApiModelProperty("文字描述")
    private String content;

    @ApiModelProperty("图片")
    private String pic;

    @ApiModelProperty("运动时间")
    private int time;

    @ApiModelProperty("总计消耗卡路里")
    private double currentCalories;

    @ApiModelProperty("详细的运动")
    private Long exerciseInfoId;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("收藏乐观锁组件")
    private Integer version;
}
