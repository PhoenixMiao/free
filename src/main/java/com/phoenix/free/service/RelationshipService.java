package com.phoenix.free.service;

import com.phoenix.free.controller.response.UserBriefInfoResponse;
import com.phoenix.free.entity.Relationship;

import java.util.List;

public interface RelationshipService {
    Long addNewRelationship(Long userId1, Long userId2);
    int deleteRelationship(Long userId1, Long userId2);

    List<Relationship> getUserRelationship(Long userId);
    List<UserBriefInfoResponse> getUserPartner(Long userId);
}
