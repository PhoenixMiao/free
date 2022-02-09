package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.FoodClockIn;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FoodClockInMapper extends MyMapper<FoodClockIn> {
    @Insert("INSERT INTO food_clock_in(userId,recordTime,content,pic,totalHeat,totalSugar,totalFat,totalProtein,totalCellulose,foodInfoId) VALUES (#{userId},#{recordTime},#{content},#{pic},#{totalHeat},#{totalSugar},#{totalFat},#{totalProtein},#{totalCellulose},#{foodInfoId}); ")
    int insertFoodClockIn(FoodClockIn foodClockIn);

    @ResultType(FoodClockIn.class)
    @Select("SELECT * FROM food_clock_in WHERE id=#{id};")
    FoodClockIn getFoodClockInById(@Param("id") Long id);

    @ResultType(FoodClockIn.class)
    @Select("SELECT * FROM food_clock_in WHERE userId=#{userId};")
    FoodClockIn getFoodClockInByUserId(@Param("userId") Long userId);
}
