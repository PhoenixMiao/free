package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.controller.response.UserResponse;
import com.phoenix.free.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends MyMapper<User> {
    @Select("SELECT nickname,gender,age,phone,portrait,isAdmin,school,introduction FROM user WHERE id=#{id};")
    UserResponse getUserById(@Param("id")Long id);

    @Update("UPDATE user SET nickname = #{nickname},gender = #{gender},phone = #{phone},portrait = #{portrait},isAdmin = #{isAdmin},school = #{school},introduction = #{introduction} WHERE id=#{id};")
    void updateUserById(@Param("nickname")String nickname,
                        @Param("gender")int gender,
                        @Param("age")int age,
                        @Param("phone")int phone,
                        @Param("portrait")String portrait,
                        @Param("isAdmin")int isAdmin,
                        @Param("school")String school,
                        @Param("introduction")String introduction,
                        @Param("id")Long id);
}
