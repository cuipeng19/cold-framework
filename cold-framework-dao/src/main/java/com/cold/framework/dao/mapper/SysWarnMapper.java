package com.cold.framework.dao.mapper;

import com.cold.framework.dao.model.SysWarn;
import com.cold.framework.dao.util.ColdMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysWarnMapper extends ColdMapper<SysWarn> {

    @Select("SELECT * FROM sys_warn WHERE state=1 AND email_state=1")
    List<SysWarn> getEmail();

    @Select("SELECT * FROM sys_warn WHERE state=1 AND phone_state=1")
    List<SysWarn> getPhone();
}