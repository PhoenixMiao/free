package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.controller.response.UserAssessInfoResponse;
import com.phoenix.free.entity.UserAssessInfo;
import com.phoenix.free.mapper.UserAssessInfoMapper;
import com.phoenix.free.service.UserAssessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAssessInfoServiceImpl implements UserAssessInfoService {
    @Autowired
    private UserAssessInfoMapper userAssessInfoMapper;

    public void updateAssessByUserId(UpdateUserAssessInfoRequest updateUserAssessInfoRequest) {
        if(userAssessInfoMapper.getAssessByUserId(updateUserAssessInfoRequest.getUserId()) == null){
            //generateNewAssessInfo();
            userAssessInfoMapper.insertAssessInfo(updateUserAssessInfoRequest);
            return ;
        }
        userAssessInfoMapper.updateAssessByUserId(
                updateUserAssessInfoRequest.getHeight(),
                updateUserAssessInfoRequest.getWeight(),
                updateUserAssessInfoRequest.getTarget(),
                updateUserAssessInfoRequest.getCondition(),
                updateUserAssessInfoRequest.getUserId()
        );
    }

    public void generateNewAssessInfo() {
        //TODO 生成评估信息
    }

    public UserAssessInfoResponse getAssessByUserId(Long userId) {
        UserAssessInfo info =  userAssessInfoMapper.getAssessByUserId(userId);
        return UserAssessInfoResponse.builder()
                .height(info.getHeight())
                .weight(info.getWeight())
                .target(info.getTarget())
                .condition(info.getCondition())
                .build();
    }
}
