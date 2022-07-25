package com.phoenix.free.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phoenix.free.entity.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

}
