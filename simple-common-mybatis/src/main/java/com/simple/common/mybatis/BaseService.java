package com.simple.common.mybatis;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.common.entity.BaseEntity;
import com.simple.common.entity.Page;

/**
 * Mybatis 基本Service
 * @author liushx
 * @date 2017年12月22日
 * @param <T> 实体类
 * @param <PK> 主键ID
 */
@Transactional
public abstract class BaseService<T extends BaseEntity, PK extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected abstract BaseMapper<T, PK> getBaseMapper(); 
	
	public void create(T t) {
		getBaseMapper().create(t);
	}
	
	public void update(T t) {
		getBaseMapper().update(t);
	}
	
	public void delete(T t) {
		getBaseMapper().delete(t);
	}
	
	public void deleteById(PK id) {
		getBaseMapper().deleteById(id);
	}
	
	public T get(PK id) {
		return getBaseMapper().get(id);
	}
	
	public List<T> getAll() {
		return getBaseMapper().getAll();
	}
	
	public List<T> queryList(Map<?, ?> params) {
		return getBaseMapper().queryList(params);
	}
	
	public void queryList(Map<String, Object> params, Page<T> page) {
//		PagingBounds bounds = new PagingBounds(2, 4);
		params.put("page", page);
		List<T> dataList = getBaseMapper().queryPageList(params);
		page.setDataList(dataList);
		System.out.println("page==");
	}
	
}
