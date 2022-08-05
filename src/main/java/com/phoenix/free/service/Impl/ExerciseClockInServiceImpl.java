package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.controller.request.ExerciseClockInRequest;
import com.phoenix.free.controller.response.ClockInGraphResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.ExerciseClockInMapper;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.ExerciseClockInService;
import com.phoenix.free.service.FileService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ExerciseClockInServiceImpl implements ExerciseClockInService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExerciseClockInMapper exerciseClockInMapper;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Autowired
    private FileService fileService;

    @Override
    public Long addExerciseClockIn(ExerciseClockInRequest exerciseClockInRequest, Long userId) {
        User user = userMapper.selectById(userId);
        String url = null;
        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        // 如果需要上传图片
        if(!Objects.isNull(exerciseClockInRequest.getPic())){
            String type = "/exerciseClockIn/" + now.replace(" ", "_").replace(":", "_");
            MultipartFile file = exerciseClockInRequest.getPic();
            url = fileService.uploadPicture(file, userId, type);
        }

        double currentCalories;
        try{
            currentCalories = exerciseMapper.selectById(exerciseClockInRequest.getExerciseInfoId()).getRatio() * exerciseClockInRequest.getAmount();
        } catch (Exception e) {
            throw new CommonException(CommonErrorCode.DATA_NOT_EXISTS);
        }
        ExerciseClockIn exerciseClockIn = ExerciseClockIn.builder()
                .userId(userId)
                .recordTime(now)
                .content(exerciseClockInRequest.getContent())
                .pic(url)
                .time(exerciseClockInRequest.getTime())
                .currentCalories(currentCalories)
                .exerciseInfoId(exerciseClockInRequest.getExerciseInfoId())
                .build();

        if(1 == exerciseClockInMapper.insert(exerciseClockIn)){
            QueryWrapper<ExerciseClockIn> wrapper = new QueryWrapper<>();
            wrapper.select("*")
                    .eq("record_time", now)
                    .eq("user_id", userId);
            return exerciseClockInMapper.selectOne(wrapper).getId();
        }
        else return -1l;
    }

    @Override
    public ExerciseClockIn getExerciseClockInById(Long id) {
        return exerciseClockInMapper.selectById(id);
    }

    @Override
    public List<ExerciseClockIn> getExerciseClockInByUserId(Long userId, int page) {
        QueryWrapper<ExerciseClockIn> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .eq("user_id", userId)
                .last("LIMIT 15 OFFSET " + offset);
        return exerciseClockInMapper.selectList(wrapper);
    }

    @Override
    public ClockInGraphResponse getExerciseClockInGraph() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDay, beginDay;
        List<Integer> clockInTimes = new ArrayList<>();
        List<Integer> clockInUsers = new ArrayList<>();
        List<String> date = new ArrayList<>();

        for (int i = 6; i >= 0; i--){
            beginDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getDayBegin(), i));
            date.add(beginDay);
            endDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getBeginDayOfTomorrow(), i));

            QueryWrapper<ExerciseClockIn> wrapper = new QueryWrapper<>();
            wrapper.between("record_time", beginDay, endDay);
            clockInTimes.add(exerciseClockInMapper.selectCount(wrapper));
            clockInUsers.add(exerciseClockInMapper.calculateClockInUsers(beginDay, endDay));
        }

        return new ClockInGraphResponse(clockInTimes, clockInUsers, date);
    }
}
