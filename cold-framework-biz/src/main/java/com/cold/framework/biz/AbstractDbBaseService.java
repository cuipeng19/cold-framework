package com.cold.framework.biz;

import com.cold.framework.dao.util.ColdMapper;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @author cuipeng
 * @date 2019/1/10 20:38
 */
public abstract class AbstractDbBaseService<T,ID extends Serializable> implements BaseService<T,ID> {

    protected ColdMapper<T> mapper;

    public AbstractDbBaseService(ColdMapper<T> mapper) {
        this.mapper = mapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public T get(ID id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> getList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public long getCount(T entity) {
        return mapper.selectCount(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(ID id) {
        mapper.deleteByPrimaryKey(id);
    }
}
