package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.ExerciseMapper;
import com.phoenix.free.mapper.FoodMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.FileService;
import com.phoenix.free.service.FoodInfoService;
import com.phoenix.free.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class FoodInfoServiceImpl implements FoodInfoService {
    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileService fileService;

    public Food getFoodInfoById(Long id) {
        return foodMapper.selectById(id);
    }

    public Food getFoodInfoByName(String name) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .eq("name", name);
        return foodMapper.selectOne(wrapper);
    }

    @Override
    public List<Food> searchFoodInfo(String name, int page) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .like("name", name)
                .last("LIMIT 15 OFFSET " + offset);
        return foodMapper.selectList(wrapper);
    }

    public int addFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long id) {
        String url = null;
        User user = userMapper.selectById(id);
        AssertUtil.notNull(user, CommonErrorCode.USER_NOT_EXIST);
        if(!Objects.isNull(addFoodInfoRequest.getPic())){
            String type = "/" + addFoodInfoRequest.getName();
            MultipartFile file = addFoodInfoRequest.getPic();
            url = fileService.uploadPicture(file, id, type);
        }
        Food food = Food.builder()
                .name(addFoodInfoRequest.getName())
                .category(addFoodInfoRequest.getCategory())
                .pic(url)
                .energy(addFoodInfoRequest.getEnergy())
                .fat(addFoodInfoRequest.getFat())
                .sugar(addFoodInfoRequest.getSugar())
                .protein(addFoodInfoRequest.getProtein())
                .cellulose(addFoodInfoRequest.getCellulose())
                .state(addFoodInfoRequest.getState())
                .build();
        return foodMapper.insert(food);
    }

    public int deleteFoodInfoById(Long id) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return foodMapper.delete(wrapper);
    }

    public int deleteFoodInfoByName(String name) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return foodMapper.delete(wrapper);
    }
}
