package com.simple.common.mybatis.dialect;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class MybatisDialect implements Dialect {

    /** 
     * 去除hql的orderBy子句。 
     * @param hql 
     * @return 
     */ 
   	protected String removeOrders(String qlString) {  
        Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);  
        Matcher m = p.matcher(qlString);  
        StringBuffer sb = new StringBuffer();  
        while (m.find()) {  
            m.appendReplacement(sb, "");  
        }
        m.appendTail(sb);
        return sb.toString();  
    } 
}
