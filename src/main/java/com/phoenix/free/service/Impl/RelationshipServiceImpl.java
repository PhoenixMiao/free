package com.phoenix.free.service.Impl;

import com.phoenix.free.common.CommonErrorCode;
import com.phoenix.free.entity.Relationship;
import com.phoenix.free.mapper.RelationshipMapper;
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

    public int addNewRelationship(Long userId1, Long userId2) {
        AssertUtil.isTrue(relationshipMapper.getExactRelationship(userId1, userId2) == null, CommonErrorCode.DUPLICATE_DATABASE_INFORMATION, "请勿重复添加搭档关系");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = simpleDateFormat.format(new Date());

        Relationship relationship = Relationship.builder()
                .userId1(userId1)
                .userId2(userId2)
                .recordTime(now)
                .build();
        return relationshipMapper.insertRelationship(relationship);
    }

    public void deleteRelationship(Long userId1, Long userId2) {
        Long id = relationshipMapper.getExactRelationship(userId1, userId2).getId();
        relationshipMapper.deleteRelationship(id);
    }

    public List<Relationship> getUserRelationship(Long userId) {
        return relationshipMapper.getUserRelationship(userId);
    }

    public List<Long> getUserPartner(Long userId){
        List<Relationship> relationshipList = getUserRelationship(userId);
        List<Long> result = new ArrayList<>();
        for(Relationship r : relationshipList)
            result.add((r.getUserId1().equals(userId))? r.getUserId2() : r.getUserId1());

        return result;
    }
}
