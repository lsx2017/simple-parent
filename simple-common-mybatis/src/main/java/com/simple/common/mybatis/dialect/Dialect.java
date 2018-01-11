package com.simple.common.mybatis.dialect;
/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * @author 
 *
 */
public interface Dialect {

	/**
     * 数据库本身是否支持分页当前的分页查询方式
     * 如果数据库不支持的话，则不进行数据库分页
     *
     * @return true：支持当前的分页查询方式
     */
    public boolean supportsLimit();
    
    /**
     * 将sql转换为分页sql
     * @param sql sql语句
     * @return
     */
    public String getCountString(String sql);

    /**
     * 将sql转换为分页sql
     * @param sql sql语句
     * @param offset 开始条数 
     * @param limit 结束条数
     * @return
     */
    public String getLimitString(String sql, int offset, int limit);

}
