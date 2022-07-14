package com.phoenix.free.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("User 用户")
public class User implements Serializable {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("会话id")
    private String sessionId;

    @ApiModelProperty("用户唯一标识")
    private String openId;

    @ApiModelProperty("unionid")
    private String unionId;

    @ApiModelProperty("会话密钥")
    private String sessionKey;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别")
    private int gender;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("手机号")
    private int phone;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("个人简介")
    private String introduction;

    @ApiModelProperty("用户类型（0为普通用户，1为管理员）")
    private Integer type;

    @Version
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("收藏乐观锁组件")
    private Integer version;
}
