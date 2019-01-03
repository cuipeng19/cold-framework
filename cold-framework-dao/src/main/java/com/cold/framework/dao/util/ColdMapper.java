package com.cold.framework.dao.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * This interface cannot be scanned.
 *
 * @author cuipeng
 * @date 2019/1/3 16:01
 */
public interface ColdMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
