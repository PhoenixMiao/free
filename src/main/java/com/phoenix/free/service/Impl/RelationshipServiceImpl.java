package com.phoenix.free.service.Impl;

import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.controller.response.UserBriefInfoResponse;
import com.phoenix.free.entity.Relationship;
import com.phoenix.free.entity.User;
import com.phoenix.free.mapper.RelationshipMapper;
import com.phoenix.free.mapper.UserMapper;
import com.phoenix.free.service.RelationshipService;
import com.phoenix.free.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RelationshipServiceImpl implements RelationshipService {
    @Autowired
    private RelationshipMapper relationshipMapper;

    @Autowired
    private UserMapper userMapper;

    public Long addNewRelationship(Long userId1, Long userId2) {
        AssertUtil.isTrue(relationshipMapper.getExactRelationship(userId1, userId2) == null, CommonErrorCode.DUPLICATE_DATABASE_INFORMATION, "请勿重复添加搭档关系");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());
        Relationship relationship = Relationship.builder()
                .userId1(userId1)
                .userId2(userId2)
                .recordTime(now)
                .build();

        relationshipMapper.insert(relationship);
        return relationship.getId();
    }

    public int deleteRelationship(Long userId1, Long userId2) {
        Long id = relationshipMapper.getExactRelationship(userId1, userId2).getId();
        return relationshipMapper.deleteById(id);
    }

    public List<Relationship> getUserRelationship(Long userId) {
        return relationshipMapper.getUserRelationship(userId);
    }

    public List<UserBriefInfoResponse> getUserPartner(Long userId){
        List<Relationship> relationshipList = getUserRelationship(userId);
        List<UserBriefInfoResponse> result = new ArrayList<>();
        for(Relationship r : relationshipList)
            if(r.getUserId1().equals(userId)){
                User user = userMapper.selectById(r.getUserId2());
                result.add(new UserBriefInfoResponse(r.getUserId2(), user.getPortrait(), user.getNickname()));
            }
            else{
                User user = userMapper.selectById(r.getUserId1());
                result.add(new UserBriefInfoResponse(r.getUserId1(), user.getPortrait(), user.getNickname()));
            }

        return result;
    }
}
