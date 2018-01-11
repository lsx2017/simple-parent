package com.simple.generator.contverts;

import com.simple.generator.entity.Generator;
import com.simple.generator.entity.TableInfo;

public interface ITableParser {
	
	public static final String SELECT_SQL_PREFIX = "SELECT * FROM ";// 数据库操作

    /**
     * 数据库类型转换Java数据类型
     * @param dataType 字段类型
     * @return
     */
    DataFieldType dataTypeConvert(String dataType);
    
    /**
     * 
     * @param tableInfo
     * @return
     */
    public TableInfo buildTableInfo(Generator generator, TableInfo tableInfo);
    
}
