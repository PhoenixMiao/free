package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.FoodClockIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodClockInMapper extends BaseMapper<FoodClockIn> {
    @Select("SELECT COUNT(DISTINCT LEFT(record_time, 10)) FROM food_clock_in WHERE user_id = #{userId} AND record_time >= #{begin};")
    int calculateClockInDays(@Param("userId") Long userId, @Param("begin") String begin);
}
