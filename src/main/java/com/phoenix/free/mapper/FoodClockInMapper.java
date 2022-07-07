package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.FoodClockIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodClockInMapper extends BaseMapper<FoodClockIn> {
    @Insert("INSERT INTO food_clock_in(userId,recordTime,content,pic,totalEnergy,totalSugar,totalFat,totalProtein,totalCellulose,foodInfoId) VALUES (#{userId},#{recordTime},#{content},#{pic},#{totalEnergy},#{totalSugar},#{totalFat},#{totalProtein},#{totalCellulose},#{foodInfoId}); ")
    int insertFoodClockIn(FoodClockIn foodClockIn);

    @ResultType(FoodClockIn.class)
    @Select("SELECT * FROM food_clock_in WHERE id=#{id};")
    FoodClockIn getFoodClockInById(@Param("id") Long id);

    @Results(
            id = "foodClockInList",value = {
            @Result(property="userId", column="userId"),
            @Result(property="recordTime", column="recordTime"),
            @Result(property="content", column="content"),
            @Result(property="pic", column="pic"),
            @Result(property="totalEnergy", column="totalEnergy"),
            @Result(property="totalSugar", column="totalSugar"),
            @Result(property="totalFat", column="totalFat"),
            @Result(property="totalProtein", column="totalProtein"),
            @Result(property="totalCellulose", column="totalCellulose"),
            @Result(property="foodInfoId", column="foodInfoId")
    })
    @Select("SELECT * FROM food_clock_in WHERE userId=#{userId};")
    List<FoodClockIn> getFoodClockInByUserId(@Param("userId") Long userId);
}
