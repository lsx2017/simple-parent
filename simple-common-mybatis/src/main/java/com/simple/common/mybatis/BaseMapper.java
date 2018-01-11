package com.simple.common.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.simple.common.entity.BaseEntity;

/**
 * mybatis基础DAO类
 * @author liushx
 * @date 2017年12月22日
 * @param <T> 实体类
 * @param <ID> 主键
 */
public interface BaseMapper<T extends BaseEntity, PK extends Serializable>  {

    void create(T t);
    void update(T t);
    void delete(T t);
    void deleteById(PK id);
    T get(PK id);
    List<T> getAll();
    List<T> queryList(Map<?,?> params);
    Long count(Map<?,?> params);
	List<T> queryPageList(Map<?,?> params);
}
