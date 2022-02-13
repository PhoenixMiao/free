package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.entity.UserAssessInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserAssessInfoMapper extends MyMapper<UserAssessInfo> {
    @Insert("INSERT INTO assess(userId,height,weight,target,`condition`) VALUES (#{userId},#{height},#{weight},#{target},#{condition}); ")
    int insertAssessInfo(UpdateUserAssessInfoRequest updateUserAssessInfoRequest);

    @Delete("DELETE FROM assess WHERE id=#{id};")
    int deleteAssessById(Long id);

    @ResultType(UserAssessInfo.class)
    @Select("SELECT * FROM assess WHERE userId=#{userId};")
    UserAssessInfo getAssessByUserId(@Param("userId") Long userId);

    @Update("UPDATE assess SET height=#{height},weight=#{weight},target=#{target},`condition`=#{condition} WHERE userId=#{userId};")
    void updateAssessByUserId(@Param("height")double height,
                              @Param("weight")double weight,
                              @Param("target")String target,
                              @Param("condition")String condition,
                              @Param("userId")Long userId);
}
