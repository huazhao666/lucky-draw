package com.huazhao.mapper;

import com.huazhao.base.BaseMapper;
import com.huazhao.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);

}