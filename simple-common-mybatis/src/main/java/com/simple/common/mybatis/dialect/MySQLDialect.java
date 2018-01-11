package com.simple.common.mybatis.dialect;

/**
 * Mysql方言的实现
 * @author 
 *
 */
public class MySQLDialect extends MybatisDialect {

	@Override
	public boolean supportsLimit() {
		return true;
	}
	
	@Override
	public String getCountString(String sql) {
		StringBuffer countSql = new StringBuffer();
		//countSql = "select count(1) from (" + sql + ") tmp_count";
		countSql.append("select count(1) from (").append(sql).append(") tmp_count");
		return countSql.toString();
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		StringBuffer limltSql = new StringBuffer(sql);
		limltSql.append(" limit ").append(offset).append(", ").append(limit);
		return limltSql.toString();
	}
	



}

