package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.FoodInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FoodInfoMapper extends MyMapper<FoodInfo> {
    @Insert("INSERT INTO food(name,category,heat,sugar,fat,protein,cellulose,state) VALUES (#{name},#{category},#{heat},#{sugar},#{fat},#{protein},#{cellulose},#{state}); ")
    int insertFoodInfo(FoodInfo food);

    @Delete("DELETE FROM food WHERE id=#{id};")
    int deleteFoodInfoById(Long id);

    @Delete("DELETE FROM food WHERE name=#{name};")
    int deleteFoodInfoByName(String name);

    @ResultType(FoodInfo.class)
    @Select("SELECT * FROM food WHERE id=#{id};")
    FoodInfo getFoodInfoById(@Param("id") Long id);

    @Results(
            id = "foodClockInList",value = {
            @Result(property="userId", column="userId"),
            @Result(property="recordTime", column="recordTime"),
            @Result(property="content", column="content"),
            @Result(property="pic", column="pic"),
            @Result(property="totalHeat", column="totalHeat"),
            @Result(property="totalSugar", column="totalSugar"),
            @Result(property="totalFat", column="totalFat"),
            @Result(property="totalProtein", column="totalProtein"),
            @Result(property="totalCellulose", column="totalCellulose"),
            @Result(property="foodInfoId", column="foodInfoId")
    })
    @Select("SELECT * FROM food WHERE name=#{name};")
    FoodInfo getFoodInfoByName(@Param("name") String name);

}
