package com.phoenix.free.service.Impl;

import com.phoenix.free.controller.request.UpdateUserAssessInfoRequest;
import com.phoenix.free.controller.response.UserAssessInfoResponse;
import com.phoenix.free.entity.Assess;
import com.phoenix.free.mapper.AssessMapper;
import com.phoenix.free.service.UserAssessInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAssessInfoServiceImpl implements UserAssessInfoService {
    @Autowired
    private AssessMapper assessMapper;

    public void updateAssessByUserId(UpdateUserAssessInfoRequest updateUserAssessInfoRequest) {
        if(assessMapper.getAssessByUserId(updateUserAssessInfoRequest.getUserId()) == null){
            //generateNewAssessInfo();
            assessMapper.insertAssessInfo(updateUserAssessInfoRequest);
            return ;
        }
        assessMapper.updateAssessByUserId(
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
        Assess info =  assessMapper.getAssessByUserId(userId);
        return UserAssessInfoResponse.builder()
                .height(info.getHeight())
                .weight(info.getWeight())
                .target(info.getTarget())
                .condition(info.getCondition())
                .build();
    }
}
