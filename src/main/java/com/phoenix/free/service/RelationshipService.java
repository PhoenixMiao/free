package com.phoenix.free.service;

import com.phoenix.free.entity.Relationship;

import java.util.List;

public interface RelationshipService {
    int addNewRelationship(Long userId1, Long userId2);
    void deleteRelationship(Long userId1, Long userId2);

    List<Relationship> getUserRelationship(Long userId);
    List<Long> getUserPartner(Long userId);
}
