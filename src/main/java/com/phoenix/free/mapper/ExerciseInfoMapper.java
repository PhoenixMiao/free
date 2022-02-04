package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.ExerciseInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExerciseInfoMapper extends MyMapper<ExerciseInfo> {
    @Insert("INSERT INTO exercise(name,category,ratio) VALUES (#{name},#{category},#{ratio}); ")
    int insertExerciseInfo(ExerciseInfo exerciseInfo);

    @Delete("DELETE FROM exercise WHERE id=#{id};")
    int deleteExerciseById(Long id);

    @Delete("DELETE FROM exercise WHERE name=#{name};")
    int deleteExerciseByName(String name);

    @ResultType(ExerciseInfo.class)
    @Select("SELECT * FROM exercise WHERE id=#{id};")
    ExerciseInfo getExerciseById(@Param("id") Long id);

    @ResultType(ExerciseInfo.class)
    @Select("SELECT * FROM exercise WHERE name=#{name};")
    ExerciseInfo getExerciseByName(@Param("name") String name);
}
