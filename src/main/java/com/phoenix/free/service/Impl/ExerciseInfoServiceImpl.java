package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddExerciseInfoRequest;
import com.phoenix.free.entity.Exercise;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.ExerciseInfoService;
import com.phoenix.free.service.FileService;
import com.phoenix.free.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class ExerciseInfoServiceImpl implements ExerciseInfoService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Autowired
    private FileService fileService;

    public Exercise getExerciseInfoById(Long id) {
        return exerciseMapper.selectById(id);
    }

    public Exercise getExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("name", name);
        return exerciseMapper.selectOne(wrapper);
    }

    @Override
    public List<Exercise> searchExerciseInfo(String name, int page) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .like("name", name)
                .last("LIMIT 15 OFFSET " + offset);
        return exerciseMapper.selectList(wrapper);
    }

    public int addExerciseInfo(AddExerciseInfoRequest addExerciseInfoRequest, Long id) {
        String url = null;
        User user = userMapper.selectById(id);
        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
        if(!Objects.isNull(addExerciseInfoRequest.getPic())){
            String type = "/" + addExerciseInfoRequest.getName();
            MultipartFile file = addExerciseInfoRequest.getPic();
            url = fileService.uploadPicture(file, id, type);
        }
        Exercise exercise = Exercise.builder()
                .name(addExerciseInfoRequest.getName())
                .pic(url)
                .category(addExerciseInfoRequest.getCategory())
                .ratio(addExerciseInfoRequest.getRatio())
                .state(addExerciseInfoRequest.getState())
                .build();
        return exerciseMapper.insert(exercise);
    }

    public int deleteExerciseInfoById(Long id) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return exerciseMapper.delete(wrapper);
    }

    public int deleteExerciseInfoByName(String name) {
        QueryWrapper<Exercise> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return exerciseMapper.delete(wrapper);
    }
}
