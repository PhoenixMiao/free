package com.phoenix.free.entity;

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
    @ApiModelProperty("用户id")
    private Long id;

    private String sessionId;
    private String openId;
    private String unionId;
    private String sessionKey;
    private String createTime;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("性别")
    private int gender;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("手机号")
    private int phone;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("管理员表示符")
    private int isAdmin;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("个人简介")
    private String introduction;
}
