package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.entity.Assess;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AssessMapper extends BaseMapper<Assess> {
    @Insert("INSERT INTO assess(userId,height,weight,target,`condition`) VALUES (#{userId},#{height},#{weight},#{target},#{condition}); ")
    int insertAssessInfo(UpdateUserAssessInfoRequest updateUserAssessInfoRequest);

    @Delete("DELETE FROM assess WHERE id=#{id};")
    int deleteAssessById(Long id);

    @ResultType(Assess.class)
    @Select("SELECT * FROM assess WHERE userId=#{userId};")
    Assess getAssessByUserId(@Param("userId") Long userId);

    @Update("UPDATE assess SET height=#{height},weight=#{weight},target=#{target},`condition`=#{condition} WHERE userId=#{userId};")
    void updateAssessByUserId(@Param("height")double height,
                              @Param("weight")double weight,
                              @Param("target")String target,
                              @Param("condition")String condition,
                              @Param("userId")Long userId);
}
