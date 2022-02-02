package com.phoenix.free.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("GetUserByIdResponse 获取用户信息")
public class UserResponse {
    /**
     * {@link com.phoenix.free.entity.User}
     */
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

    @ApiModelProperty("管理员表示符")
    private int isAdmin;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("个人简介")
    private String introduction;
}
