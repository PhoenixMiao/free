package com.phoenix.free.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("Plan 每日计划")
public class Plan {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("状态")
    private int status;

    //运动计划
    @ApiModelProperty("目标消耗卡路里")
    private double calories;

    @ApiModelProperty("目标步数")
    private int path;

    //饮食计划
    @ApiModelProperty("目标热量")
    private double energy;

    @ApiModelProperty("目标糖分")
    private double sugar;

    @ApiModelProperty("目标脂肪")
    private double fat;

    @ApiModelProperty("目标蛋白质")
    private double protein;

    @ApiModelProperty("目标纤维素")
    private double cellulose;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("收藏乐观锁组件")
    private Integer version;
}
