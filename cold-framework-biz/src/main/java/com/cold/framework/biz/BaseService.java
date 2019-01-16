package com.cold.framework.biz;

import java.io.Serializable;
import java.util.List;

/**
 * @author cuipeng
 * @date 2019/1/10 20:37
 */
public interface BaseService<T,ID extends Serializable> {

    int save(T entity);

    int update(T entity);

    T get(ID id);

    List<T> getList(T entity);

    long getCount(T entity);

    void delete(ID id);
}
