package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.Relationship;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RelationshipMapper extends MyMapper<Relationship> {
    @Insert("INSERT INTO relationship(userId1,userId2,recordTime) VALUES (#{userId1},#{userId2},#{recordTime}); ")
    int insertRelationship(Relationship relationship);

    @Delete("DELETE FROM relationship WHERE id=#{id};")
    void deleteRelationship(Long id);

    @Results(
            id = "relationshipList",value = {
            @Result(property="userId1", column="userId1"),
            @Result(property="userId2", column="userId2"),
            @Result(property="recordTime", column="recordTime")
    })
    @Select("SELECT * FROM relationship WHERE userId1=#{userId} OR userId2=#{userId};")
    List<Relationship> getUserRelationship(Long userId);

    @ResultType(Relationship.class)
    @Select("SELECT * FROM relationship WHERE (userId1=#{userId1} AND userId2=#{userId2}) OR (userId1=#{userId2} AND userId2=#{userId1});")
    Relationship getExactRelationship(Long userId1, Long userId2);
}
