package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.ExerciseClockIn;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExerciseClockInMapper extends MyMapper<ExerciseClockIn> {
    @Insert("INSERT INTO exercise_clock_in(userId,recordTime,content,pic,currentCalories,exerciseInfoId) VALUES (#{userId},#{recordTime},#{content},#{pic},#{currentCalories},#{exerciseInfoId}); ")
    int insertExerciseClockIn(ExerciseClockIn exerciseClockIn);

    @ResultType(ExerciseClockIn.class)
    @Select("SELECT * FROM exercise_clock_in WHERE id=#{id};")
    ExerciseClockIn getExerciseClockInById(@Param("id") Long id);

    @ResultType(ExerciseClockIn.class)
    @Select("SELECT * FROM exercise_clock_in WHERE userId=#{userId};")
    ExerciseClockIn getExerciseClockInByUserId(@Param("userId") Long userId);
}
