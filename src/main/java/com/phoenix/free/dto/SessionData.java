package com.phoenix.free.dto;

import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.entity.User;
import com.phoenix.free.util.AssertUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * session缓存实体
 * @author yan on 2020-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("SessionData 会话实体")
public class SessionData implements Serializable {

    /**
     * {@link User}
     */
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("会话id")
    private String sessionId;

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

    @ApiModelProperty("用户类型")
    private Integer isAdmin;


    public SessionData(User user){
        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        id = user.getId();
        createTime = user.getCreateTime();
        nickname = user.getNickname();
        gender = user.getGender();
        portrait = user.getPortrait();
        age = user.getAge();
        phone = user.getPhone();
        school = user.getSchool();
        introduction = user.getIntroduction();
        sessionId = user.getSessionId();
        isAdmin = user.getIsAdmin();
    }
}
