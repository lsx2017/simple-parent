package com.simple.common.mybatis.page;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.validation.support.BindingAwareModelMap;

import com.simple.common.entity.Page;
import com.simple.common.mybatis.dialect.MySQLDialect;
import com.simple.common.utils.StringUtils;




/**
 * 数据库分页插件，只拦截查询语句.
 * @author 
 * 
 */
@Intercepts({ 
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,ResultHandler.class }) 
})
public class PageInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		if(dialect == null) dialect = new MySQLDialect();
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Object parameterObject = boundSql.getParameterObject();

		// 获取分页参数对象
		Page<?> page = null;
		if (parameterObject != null) {
			page = findPageObject(parameter);
//			System.out.println(parameter.getClass());
//			BindingAwareModelMap map = null;
//			       
//			Method removeBindingResultIfNecessary = parameter.getClass().getDeclaredMethod("removeBindingResultIfNecessary");
//			removeBindingResultIfNecessary.setAccessible(true);
//	        removeBindingResultIfNecessary.invoke(parameter, PAGE, page);
			((Map<?, ?>) parameter).remove(PAGE);
		}

		// 如果设置了分页对象，则进行分页
		if (page != null && page.getPageSize() != -1) {

			if (StringUtils.isEmpty(boundSql.getSql())) {
				return null;
			}
			//原始SQL语句
			String originalSql = boundSql.getSql().trim();
			String countSql = dialect.getCountString(originalSql);
			// 得到总记录数
			page.setTotal(getCount(countSql, null,mappedStatement, parameterObject, boundSql, log));
			// 分页查询 本地化对象 修改数据库注意修改实现
			
			String pageSql = dialect.getLimitString(originalSql, page.getOffset(), page.getLimit());
			invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
			BoundSql newBoundSql = new BoundSql(
					mappedStatement.getConfiguration(), pageSql,
					boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			// 解决MyBatis 分页foreach 参数失效 start
			if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
				MetaObject mo = (MetaObject) Reflections.getFieldValue(
						boundSql, "metaParameters");
				Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
			}
			// 解决MyBatis 分页foreach 参数失效 end
			MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));

			invocation.getArgs()[0] = newMs;
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		//设置数据库方言
        String dbType = "mysql";
        if("mysql".equals(dbType)){
        	dialect = new MySQLDialect();
        }
        if (dialect == null) {
            throw new RuntimeException("mybatis dialect error.");
        }
        
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(
				ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		@Override
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
	
	 /**
     * 查询总纪录数
     * @param sql             SQL语句
     * @param connection      数据库连接
     * @param mappedStatement mapped
     * @param parameterObject 参数
     * @param boundSql        boundSql
     * @return 总记录数
     * @throws SQLException sql查询错误
     */
    private int getCount(final String countSql, final Connection connection,
    							final MappedStatement mappedStatement, final Object parameterObject,
    							final BoundSql boundSql, Log log) throws SQLException {
 
        Connection conn = connection;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
        	if (log.isDebugEnabled()) {
                log.debug("COUNT SQL: " + countSql);
            }
        	if (conn == null){
        		conn = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
            }
        	ps = conn.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), parameterObject);
            //解决MyBatis 分页foreach 参数失效 start
   
			if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
				MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
				Reflections.setFieldValue(countBS, "metaParameters", mo);
			}
			//解决MyBatis 分页foreach 参数失效 end 
			setParameters(ps, mappedStatement, countBS, parameterObject);
            rs = ps.executeQuery();
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
            	ps.close();
            }
            if (conn != null) {
            	conn.close();
            }
        }
    }

}
