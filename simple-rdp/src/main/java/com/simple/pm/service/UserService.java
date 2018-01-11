package com.simple.pm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.simple.common.mybatis.BaseMapper;
import com.simple.common.mybatis.BaseService;
import com.simple.pm.entity.User;
import com.simple.pm.mapper.UserMapper;



@Service
@Transactional
public class UserService extends BaseService<User, Integer> {
	
	@Autowired 
	private UserMapper userMapper;
	
	@Override
	protected BaseMapper<User, Integer> getBaseMapper() {
		
		return userMapper;
	}
	
	
		
	

}
