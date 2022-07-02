package com.phoenix.free.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.phoenix.free.dto.ClockIn;
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
@ApiModel("FoodClockIn 饮食打卡")
public class FoodClockIn {

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

    @ApiModelProperty("总热量")
    private double totalEnergy;

    @ApiModelProperty("总糖分")
    private double totalSugar;

    @ApiModelProperty("总脂肪")
    private double totalFat;

    @ApiModelProperty("总蛋白质")
    private double totalProtein;

    @ApiModelProperty("总纤维素")
    private double totalCellulose;

    @ApiModelProperty("详细的食物")
    private Long foodInfoId;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("收藏乐观锁组件")
    private Integer version;
}
