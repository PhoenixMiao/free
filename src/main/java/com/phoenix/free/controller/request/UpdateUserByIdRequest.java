package com.phoenix.free.controller.request;

import com.phoenix.free.entity.User;
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
@ApiModel("UpdateUserByIdRequest 更新用户信息")
public class UpdateUserByIdRequest {
    /**
     * {@link User}
     */
    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("年龄")
    private int age;

    @ApiModelProperty("手机号")
    private int phone;

    @ApiModelProperty("学校")
    private String school;

    @ApiModelProperty("个人简介")
    private String introduction;
}
