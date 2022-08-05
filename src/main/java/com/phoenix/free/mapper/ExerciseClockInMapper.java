package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.ExerciseClockIn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExerciseClockInMapper extends BaseMapper<ExerciseClockIn> {
    @Select("SELECT COUNT(DISTINCT LEFT(record_time, 10)) FROM exercise_clock_in WHERE user_id = #{userId} AND record_time >= #{begin};")
    int calculateClockInDays(@Param("userId") Long userId, @Param("begin") String begin);

    @Select("SELECT COUNT(DISTINCT user_id) FROM exercise_clock_in WHERE record_time BETWEEN #{begin} AND #{end};")
    int calculateClockInUsers(@Param("begin") String begin, @Param("end") String end);
}
