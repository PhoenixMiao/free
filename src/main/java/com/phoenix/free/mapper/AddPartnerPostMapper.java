package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.AddPartnerPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddPartnerPostMapper extends MyMapper<AddPartnerPost> {
    @Insert("INSERT INTO add_partner_post(id,userId,content,pic,createTime,userId2) VALUES (#{id},#{userId},#{content},#{pic},#{createTime},#{userId2});")
    int insertAddPartnerPost(AddPartnerPost addPartnerPost);

    @Delete("DELETE FROM add_partner_post WHERE id=#{id};")
    void deleteAddPartnerPost(Long id);

    @ResultType(AddPartnerPost.class)
    @Select("SELECT * FROM add_partner_post WHERE WHERE id=#{id};")
    AddPartnerPost getAddPartnerPostById(Long id);

    @Results(
            id = "AddPartnerPostList",value = {
            @Result(property="userId", column="userId"),
            @Result(property="content", column="content"),
            @Result(property="pic", column="pic"),
            @Result(property="createTime", column="createTime"),
            @Result(property="userId2", column="userId2")
    })
    @Select("SELECT * FROM add_partner_post WHERE userId2=#{userId2};")
    List<AddPartnerPost> getAddPartnerPostByUserId(Long userId);
}
