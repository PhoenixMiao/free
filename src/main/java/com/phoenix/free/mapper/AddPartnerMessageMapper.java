package com.phoenix.free.mapper;

import com.phoenix.free.MyMapper;
import com.phoenix.free.entity.AddPartnerMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddPartnerMessageMapper extends MyMapper<AddPartnerMessage> {
    @Insert("INSERT INTO add_partner_message(id,userId,content,pic,createTime,userId2) VALUES (#{id},#{userId},#{content},#{pic},#{createTime},#{userId2});")
    int insertAddPartnerMessage(AddPartnerMessage addPartnerMessage);

    @Delete("DELETE FROM add_partner_message WHERE id=#{id};")
    void deleteAddPartnerMessage(Long id);

    @ResultType(AddPartnerMessage.class)
    @Select("SELECT * FROM add_partner_message WHERE WHERE id=#{id};")
    AddPartnerMessage getAddPartnerMessageById(Long id);

    @Results(
            id = "AddPartnerMessageList",value = {
            @Result(property="userId", column="userId"),
            @Result(property="content", column="content"),
            @Result(property="pic", column="pic"),
            @Result(property="createTime", column="createTime"),
            @Result(property="userId2", column="userId2")
    })
    @Select("SELECT * FROM add_partner_message WHERE userId2=#{userId2};")
    List<AddPartnerMessage> getAddPartnerMessageByUserId(Long userId);
}
