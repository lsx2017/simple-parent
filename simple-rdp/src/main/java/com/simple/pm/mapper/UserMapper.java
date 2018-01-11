package com.simple.pm.mapper;

import com.simple.pm.entity.User;
import com.simple.common.mybatis.BaseMapper;
import org.springframework.stereotype.Repository;
/**
 * <p> 这是表的注释Mapper </p>
 * @author liushx
 * @date 2018-01-10
 */
@Repository
public interface UserMapper extends BaseMapper<User, Integer> {

}