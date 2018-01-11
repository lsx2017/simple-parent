package com.simple.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simple.common.mybatis.BaseMapper;
import com.simple.common.mybatis.BaseService;
import com.simple.pm.entity.Bugs;
import com.simple.pm.mapper.BugsMapper;



@Service
@Transactional
public class BugsService extends BaseService<Bugs, Long> {
	
	@Autowired 
	private BugsMapper bugsMapper;
	
	@Override
	protected BaseMapper<Bugs, Long> getBaseMapper() {
		
		return bugsMapper;
	}
	
	
		
	

}
