package com.phoenix.free.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.common.CommonException;
import com.phoenix.free.controller.request.FoodClockInRequest;
import com.phoenix.free.controller.response.ClockInGraphResponse;
import com.phoenix.free.entity.ExerciseClockIn;
import com.phoenix.free.entity.FoodClockIn;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.FoodClockInMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.FileService;
import com.phoenix.free.service.FoodClockInService;
import com.phoenix.free.util.AssertUtil;
import com.phoenix.free.util.DatesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FoodClockInServiceImpl implements FoodClockInService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FoodClockInMapper foodClockInMapper;

    @Autowired
    private FileService fileService;

    @Override
    public Long addFoodClockIn(FoodClockInRequest foodClockInRequest, Long userId) {
        User user = userMapper.selectById(userId);
        String url = null;
        AssertUtil.isNotNull(user, CommonErrorCode.USER_NOT_EXIST);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());

        // 如果需要上传图片
        if(!Objects.isNull(foodClockInRequest.getPic())){
            String type = "/foodClockIn/" + now.replace(" ", "_").replace(":", "_");
            MultipartFile file = foodClockInRequest.getPic();
            try {
                url = fileService.uploadPicture(file, userId, type);
            } catch (Exception e) {
                throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
            }
        }

        FoodClockIn foodClockIn = FoodClockIn.builder()
                .userId(userId)
                .recordTime(now)
                .content(foodClockInRequest.getContent())
                .pic(url)
                .totalEnergy(foodClockInRequest.getTotalEnergy())
                .totalSugar(foodClockInRequest.getTotalSugar())
                .totalFat(foodClockInRequest.getTotalFat())
                .totalProtein(foodClockInRequest.getTotalProtein())
                .totalCellulose(foodClockInRequest.getTotalCellulose())
                .foodInfoId(foodClockInRequest.getFoodInfoId())
                .build();

        if(1 == foodClockInMapper.insert(foodClockIn)){
            QueryWrapper<FoodClockIn> wrapper = new QueryWrapper<>();
            wrapper.select("*")
                    .eq("record_time", now)
                    .eq("user_id", userId);
            return foodClockInMapper.selectOne(wrapper).getId();
        }
        else return -1l;
    }

    @Override
    public void addPic(Long userId, Long clockInId, MultipartFile file, int sequence) {
        FoodClockIn clockIn = foodClockInMapper.selectById(clockInId);
        AssertUtil.isNotNull(clockIn, CommonErrorCode.DATA_NOT_EXISTS);
        AssertUtil.isEqual(userId, clockIn.getUserId(), CommonErrorCode.DATA_NOT_EXISTS, null);

        String type = "/foodClockIn/" + clockInId + "/" + sequence;
        String url = StringUtils.hasText(clockIn.getPic()) ? "," : "";
        try{
            url += fileService.uploadPicture(file, userId, type);
        } catch (Exception e) {
            throw new CommonException(CommonErrorCode.UPLOAD_FILE_FAIL);
        }
        clockIn.setPic(clockIn.getPic() + url);
        AssertUtil.isTrue(1 == foodClockInMapper.updateById(clockIn), CommonErrorCode.UPLOAD_FILE_FAIL);
    }

    @Override
    public FoodClockIn getFoodClockInById(Long id) {
        return foodClockInMapper.selectById(id);
    }

    @Override
    public List<FoodClockIn> getFoodClockInByUserId(Long userId, int page) {
        QueryWrapper<FoodClockIn> wrapper = new QueryWrapper<>();
        String offset = String.valueOf(page * 15);
        wrapper.select("*")
                .eq("user_id", userId)
                .last("LIMIT 15 OFFSET " + offset);
        return foodClockInMapper.selectList(wrapper);
    }

    @Override
    public ClockInGraphResponse getFoodClockInGraph() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endDay, beginDay;
        List<Integer> clockInTimes = new ArrayList<>();
        List<Integer> clockInUsers = new ArrayList<>();
        List<String> date = new ArrayList<>();

        for (int i = 6; i >= 0; i--){
            beginDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getDayBegin(), i));
            date.add(beginDay);
            endDay = simpleDateFormat.format(DatesUtil.getFrontDay(DatesUtil.getBeginDayOfTomorrow(), i));

            QueryWrapper<FoodClockIn> wrapper = new QueryWrapper<>();
            wrapper.between("record_time", beginDay, endDay);
            clockInTimes.add(foodClockInMapper.selectCount(wrapper));
            clockInUsers.add(foodClockInMapper.calculateClockInUsers(beginDay, endDay));
        }

        return new ClockInGraphResponse(clockInTimes, clockInUsers, date);
    }
}
