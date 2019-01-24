package com.cold.framework.dao.mapper;

import com.cold.framework.dao.model.User;
import com.cold.framework.dao.util.ColdMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends ColdMapper<User> {

    @Select("SELECT * FROM user WHERE phone_number=#{phoneNumber}")
    User getByPhone(@Param("phoneNumber") String phoneNumber);
}