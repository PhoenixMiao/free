package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
//    @Insert("INSERT INTO food(name,category,energy,sugar,fat,protein,cellulose,state) VALUES (#{name},#{category},#{energy},#{sugar},#{fat},#{protein},#{cellulose},#{state}); ")
//    int insertFoodInfo(Food food);
//
//    @Delete("DELETE FROM food WHERE id=#{id};")
//    int deleteFoodInfoById(Long id);
//
//    @Delete("DELETE FROM food WHERE name=#{name};")
//    int deleteFoodInfoByName(String name);
//
//    @ResultType(Food.class)
//    @Select("SELECT * FROM food WHERE id=#{id};")
//    Food getFoodInfoById(@Param("id") Long id);
//
//    @ResultType(Food.class)
//    @Select("SELECT * FROM food WHERE name=#{name};")
//    Food getFoodInfoByName(@Param("name") String name);

}
