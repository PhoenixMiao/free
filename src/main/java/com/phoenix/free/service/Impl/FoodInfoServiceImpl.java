package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.request.AddFoodInfoRequest;
import com.phoenix.free.entity.Food;
import com.phoenix.free.entity.User;
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
    public Food updateFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long id) {
        Food food = foodMapper.selectById(id);
        AssertUtil.isNotNull(food, CommonErrorCode.DATA_NOT_EXISTS);

        food.setName(addFoodInfoRequest.getName());
        food.setCategory(addFoodInfoRequest.getCategory());
        food.setEnergy(addFoodInfoRequest.getEnergy());
        food.setSugar(addFoodInfoRequest.getSugar());
        food.setFat(addFoodInfoRequest.getFat());
        food.setProtein(addFoodInfoRequest.getProtein());
        food.setCellulose(addFoodInfoRequest.getCellulose());
        food.setState(addFoodInfoRequest.getState());

        AssertUtil.isFalse(foodMapper.updateById(food) == 0, CommonErrorCode.UPDATE_FAIL);

        return food;
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

    @Override
    public List<Food> getNewlyAddedFood() {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.select("*")
                .orderByDesc("id")
                .last("LIMIT 5");
        return foodMapper.selectList(wrapper);
    }

    @Override
    public void addPic(Long userId, Long id, MultipartFile file) {
        Food food = foodMapper.selectById(id);
        AssertUtil.isNotNull(food, CommonErrorCode.DATA_NOT_EXISTS);
        String url = null;
        if(!Objects.isNull(file)){
            String type = "/" + food.getName();
            url = fileService.uploadPicture(file, userId, type);
        }
        food.setPic(url);
        AssertUtil.isFalse(foodMapper.updateById(food) == 0, CommonErrorCode.UPDATE_FAIL);
        return ;
    }

    @Override
    public Long addFoodInfo(AddFoodInfoRequest addFoodInfoRequest, Long userId) {
        String url = null;
        User user = userMapper.selectById(userId);
        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        if(!Objects.isNull(addFoodInfoRequest.getPic())){
            String type = "/" + addFoodInfoRequest.getName();
            MultipartFile file = addFoodInfoRequest.getPic();
            url = fileService.uploadPicture(file, userId, type);
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
        if(foodMapper.insert(food) == 1){
            return food.getId();
        }
        else return -1l;
    }

    @Override
    public int deleteFoodInfoById(Long id) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return foodMapper.delete(wrapper);
    }

    @Override
    public int deleteFoodInfoByName(String name) {
        QueryWrapper<Food> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return foodMapper.delete(wrapper);
    }


}
