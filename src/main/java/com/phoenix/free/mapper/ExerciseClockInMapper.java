package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.ExerciseClockIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExerciseClockInMapper extends BaseMapper<ExerciseClockIn> {
    @Insert("INSERT INTO exercise_clock_in(userId,recordTime,content,pic,time,currentCalories,exerciseInfoId) VALUES (#{userId},#{recordTime},#{content},#{pic},#{time},#{currentCalories},#{exerciseInfoId}); ")
    int insertExerciseClockIn(ExerciseClockIn exerciseClockIn);

    @ResultType(ExerciseClockIn.class)
    @Select("SELECT * FROM exercise_clock_in WHERE id=#{id};")
    ExerciseClockIn getExerciseClockInById(@Param("id") Long id);

    @Results(
            id = "exerciseClockInList",value = {
            @Result(property="userId", column="userId"),
            @Result(property="recordTime", column="recordTime"),
            @Result(property="content", column="content"),
            @Result(property="pic", column="pic"),
            @Result(property="time", column="time"),
            @Result(property="currentCalories", column="currentCalories"),
            @Result(property="exerciseInfoId", column="exerciseInfoId")
    })
    @Select("SELECT * FROM exercise_clock_in WHERE userId=#{userId};")
    List<ExerciseClockIn> getExerciseClockInByUserId(@Param("userId") Long userId);
}
