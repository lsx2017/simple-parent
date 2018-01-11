package cn.simple.common.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.simple.common.utils.PropertyFilter.MatchType;



public class PropertyUtils {

	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request) {
		return buildPropertyFilters(request, "filter_");
	}
	
	/**
	 * 根据按PropertyFilter命名规则的Request参数,创建PropertyFilter列表.
	 * PropertyFilter命名规则为Filter属性前缀_比较类型_属性名.
	 * 
	 * eg.
	 * filter_EQ_name
	 * filter_LIKE_name|email	
	
	public static List<PropertyFilter> buildPropertyFilters(final ServletRequest request, final String filterPrefix) {
		return buildPropertyFilters(new ServletWebRequest((HttpServletRequest)request), filterPrefix);
	}
	 */
	
	public static List<PropertyFilter> buildPropertyFilters(final HttpServletRequest request, final String filterPrefix) {
		
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

		//从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, String> filterParamMap = new IdentityHashMap<String, String>();
		Enumeration<String> e = request.getParameterNames();
		String key = null;
		while(e.hasMoreElements()) {
			key = e.nextElement();
			if(key.startsWith(filterPrefix)){
				String[] values = request.getParameterValues(key);
				for(String value : values){
					filterParamMap.put(new String(key.substring(filterPrefix.length())), value);
				}
			}
		}	

		//分析参数Map,构造PropertyFilter列表
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = entry.getValue();
			//如果value值为空,则忽略此filter.
			boolean omit = StringUtils.isBlank(value);
			if (!omit) {
				value=value.trim();

				//分析filterName,获取matchType与propertyName
				MatchType matchType;
				String matchTypeCode = StringUtils.substringBefore(filterName, "_");
				try {
					matchType = Enum.valueOf(MatchType.class, matchTypeCode);
				} catch (RuntimeException ex) {
					throw new IllegalArgumentException("filter名称没有按规则编写,无法得到属性比较类型.", ex);
				}
				String propertyName = StringUtils.substringAfter(filterName, "_");

				PropertyFilter filter = new PropertyFilter(propertyName, value, matchType);
				filterList.add(filter);
			}
		}
		return filterList;
	}
	/**********************************************************************/
	
	public static void buildFilters(List<PropertyFilter> filters, StringBuffer sql, List<Object> objList){
		if(filters != null && filters.size() > 0) {
			for (PropertyFilter filter : filters) {
				if(filter.getValue() != null) {
					buildSqlAndValus(sql, objList, filter);
				}
			}
		}

	}
	
	
	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	protected static void buildSqlAndValus(StringBuffer sql, List<Object> objList, PropertyFilter filter) {
		
		sql.append(" AND ").append(filter.getPropertyName());
		MatchType matchType = filter.getMatchType();
		if (MatchType.EQ.equals(matchType)) {
			sql.append("=? ");
			objList.add(filter.getValue());
		}
		
		if (MatchType.NE.equals(matchType)) {
			sql.append("<>? ");
			objList.add(filter.getValue());
		}
		if (MatchType.LE.equals(matchType)) {
			sql.append("<=? ");
			objList.add(filter.getValue());
		}
		if (MatchType.LT.equals(matchType)) {
			sql.append("<? ");
			objList.add(filter.getValue());
		}
		if (MatchType.GE.equals(matchType)) {
			sql.append(">=? ");
			objList.add(filter.getValue());
		}
		if (MatchType.GT.equals(matchType)) {
			sql.append(">? ");
			objList.add(filter.getValue());
		}
		if (MatchType.LIKE.equals(matchType)) {
			sql.append(" LIKE ? ");
			objList.add("%"+filter.getValue()+"%");
		}

	}
}
