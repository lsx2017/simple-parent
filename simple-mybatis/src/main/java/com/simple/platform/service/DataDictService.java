package com.simple.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simple.common.mybatis.BaseMapper;
import com.simple.common.mybatis.BaseService;
import com.simple.platform.entity.DataDict;
import com.simple.platform.mapper.DataDictMapper;



@Service
@Transactional
public class DataDictService extends BaseService<DataDict, Long> {
	
	@Autowired 
	private DataDictMapper dataDictMapper;
	
	@Override
	protected BaseMapper<DataDict, Long> getBaseMapper() {
		
		return dataDictMapper;
	}
	
	
		
	

}
