package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.Relationship;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RelationshipMapper extends BaseMapper<Relationship> {

    @Results(
            id = "relationshipList",value = {
            @Result(property="userId1", column="user_id1"),
            @Result(property="userId2", column="user_id2"),
            @Result(property="record_time", column="recordTime")
    })
    @Select("SELECT * FROM relationship WHERE user_id1=#{userId} OR user_id2=#{userId};")
    List<Relationship> getUserRelationship(Long userId);

    @ResultType(Relationship.class)
    @Select("SELECT * FROM relationship WHERE (user_id1=#{userId1} AND user_id2=#{userId2}) OR (user_id1=#{userId2} AND user_id2=#{userId1});")
    Relationship getExactRelationship(Long userId1, Long userId2);
}
