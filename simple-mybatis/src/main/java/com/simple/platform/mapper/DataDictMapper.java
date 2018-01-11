package com.simple.platform.mapper;

import org.springframework.stereotype.Repository;

import com.simple.platform.entity.DataDict;
import com.simple.common.mybatis.BaseMapper;

/**
 * <p> Mapper </p>
 * @author liushx
 * @date 2017-12-23
 */
@Repository
public interface DataDictMapper extends BaseMapper<DataDict, Long> {

}