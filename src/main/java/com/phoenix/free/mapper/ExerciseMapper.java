package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.Exercise;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ExerciseMapper extends BaseMapper<Exercise> {
    @Insert("INSERT INTO exercise(name,category,ratio) VALUES (#{name},#{category},#{ratio}); ")
    int insertExerciseInfo(Exercise exercise);

    @Delete("DELETE FROM exercise WHERE id=#{id};")
    void deleteExerciseById(Long id);

    @Delete("DELETE FROM exercise WHERE name=#{name};")
    void deleteExerciseByName(String name);

    @ResultType(Exercise.class)
    @Select("SELECT * FROM exercise WHERE id=#{id};")
    Exercise getExerciseById(@Param("id") Long id);

    @ResultType(Exercise.class)
    @Select("SELECT * FROM exercise WHERE name=#{name};")
    Exercise getExerciseByName(@Param("name") String name);
}
