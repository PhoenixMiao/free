package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.Food;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FoodMapper extends MyMapper<Food> {
    @Insert("INSERT INTO food(name,category,heat,sugar,fat,protein,cellulose,state) VALUES (#{name},#{category},#{heat},#{sugar},#{fat},#{protein},#{cellulose},#{state}); ")
    int insertFood(Food food);

    @Delete("DELETE FROM food WHERE id=#{id};")
    int deleteFoodById(Long id);

    @Delete("DELETE FROM food WHERE name=#{name};")
    int deleteFoodByName(String name);

    @ResultType(Food.class)
    @Select("SELECT * FROM food WHERE id=#{id};")
    Food getFoodById(@Param("id") Long id);

    @ResultType(Food.class)
    @Select("SELECT * FROM food WHERE name=#{name};")
    Food getFoodByName(@Param("name") String name);

}
