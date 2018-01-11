package cn.simple.common.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.springframework.util.Assert;

import cn.simple.common.utils.BeanUtils;
import cn.simple.common.utils.EasyuiPage;
import cn.simple.common.utils.EasyuiPage.OrderType;
import cn.simple.common.utils.PropertyFilter;
import cn.simple.common.utils.PropertyFilter.MatchType;
import cn.simple.common.utils.ReflectionUtils;
import cn.simple.common.utils.StringUtils;





/**
 * 用于分页查询的DAO
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 */
public class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDao<T, PK> {
	
	
	/**
	 * 用于扩展的DAO子类使用的构造函数.
	 * 通过子类的泛型定义取得对象类型Class.
	 * eg. public class UserDao extends HibernateDao<User, Long>{
	 * }
	 */
	public HibernateDao() {
		super();
	}
	
	/**
	 * 用于Service层直接使用HibernateDAO的构造函数.
	 * eg.
	 * HibernateDao<User, Long> userDao = new HibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}

	// 分页查询函数 //
	/**
	 * 按HQL分页查询.
	 * 
	 * @param page 分页参数
	 * @param queryHql 查询记录集的hql语句
	 * @param countHql 查询总数的hql语句
	 * @param values 数量可变的查询参数
	 * 
	 * @return 分页查询结果
	 */
	@SuppressWarnings("unchecked")
	public EasyuiPage<T> findByHql(final EasyuiPage<T> page, final String queryHql, final String countHql, final Object... values) {
		Assert.notNull(page, "page不能为空");
		Query query = createQuery(queryHql, values);
		
		int totalCount = findInt(countHql, values);
		page.setTotal(totalCount);
		
		if(totalCount > 0){
			setPageParameter(query, page);
			page.setDataList(query.list());
		}
		
		return page;
	}

	/**
	 * 按Criteria分页查询
	 * @param page 分页参数      
	 * @param criterions 数量可变的Criterion
	 * @return 分页查询结果
	 */
	@SuppressWarnings("unchecked")
	public EasyuiPage<T> findByCriteria(final EasyuiPage<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);
		return findByCriteria(page, c);
	}

	public EasyuiPage<T> findByCriteria(final EasyuiPage<T> page, Criteria c) {
		int totalCount = countCriteriaResult(c);
		page.setTotal(totalCount);
		
		if(totalCount > 0){
			c = setPageParameter(c, page);
			page.setDataList(c.list());

		}
		
		return page;
	}

	/**
	 * 按属性过滤条件列表分页查找对象.
	 * 
	 * @param page 分页参数      
	 * @param filters PropertyFilter列表
	 * 
	 * @return 分页查询结果
	 */
	public EasyuiPage<T> findByPropertyFilters(final EasyuiPage<T> page, final List<PropertyFilter> filters) {
		Criterion[] criterions = buildFilterCriterions(filters);
		return findByCriteria(page, criterions);
	}
	
	/**
	 * 按属性过滤条件列表查找对象列表.
	 */
	public List<T> findByPropertyFilters(final List<PropertyFilter> filters) {
		Criterion[] criterions = buildFilterCriterions(filters);
		return find(criterions);
	}
	
	/**
	* 设置分页参数到Query对象,辅助函数.
	*/
	protected Query setPageParameter(final Query q, final EasyuiPage<T> page) {
		if(page.getPageSize() != 0){
			int start = page.getCurPage() * page.getPageSize();
			q.setFirstResult(start);
			q.setMaxResults(page.getPageSize());
		}
		return q;
	}
	
	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final EasyuiPage<T> page) {
		
		if(page.getPageSize() != 0){
			int start = page.getCurPage() * page.getPageSize();
			c.setFirstResult(start);
			c.setMaxResults(page.getPageSize());
		}
		
		Set<String> orderBys = page.getOrderMap().keySet();
		for(String orderBy : orderBys){
			OrderType orderType = page.getOrderMap().get(orderBy);
			if(OrderType.ASC.equals(orderType)){
				c.addOrder(Order.asc(orderBy));
			}else{
				c.addOrder(Order.desc(orderBy));
			}
		}

		return c;
	}
	
	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数.
	 */
	@SuppressWarnings("unchecked")
	protected int countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("不可能抛出的异常:", e);
		}

		// 执行Count查询
		int totalCount = ((Long) c.setProjection(Projections.rowCount()).uniqueResult()).intValue();

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			//logger.error("不可能抛出的异常:", e);
		}

		return totalCount;
	}
	
	/**
	 * 按属性条件列表创建Criterion数组,辅助函数.
	 */
	protected Criterion[] buildFilterCriterions(final List<PropertyFilter> filters) {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		for (PropertyFilter filter : filters) {
			String propertyName = filter.getPropertyName();

			boolean multiProperty = StringUtils.contains(propertyName, PropertyFilter.OR_SEPARATOR);
			if (!multiProperty) { //properNameName中只有一个属性的情况.
				Criterion criterion = buildPropertyCriterion(propertyName, filter.getMatchType().equals(MatchType.IN)?filter.getValue():filter.getValue().toString(), filter.getMatchType());
				criterionList.add(criterion);
			} else {//properName中包含多个属性的情况,进行or处理.
				Disjunction disjunction = Restrictions.disjunction();
				String[] params = StringUtils.split(propertyName, PropertyFilter.OR_SEPARATOR);

				for (String param : params) {
					Criterion criterion = buildPropertyCriterion(param, filter.getValue(), filter.getMatchType());
					disjunction.add(criterion);
				}
				criterionList.add(disjunction);
			}
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 按属性条件参数创建Criterion,辅助函数.
	 */
	public Criterion buildPropertyCriterion(final String propertyName, final Object value, final MatchType matchType) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = null;

		//数据类型转换
		Type propertyType = sessionFactory.getClassMetadata(entityClass).getPropertyType(propertyName);

		Object propertyValue = value;
		if(value instanceof String){
			propertyValue = BeanUtils.convertUtilsBean.convert((String) value, propertyType.getReturnedClass());
		}
		
		if (MatchType.EQ.equals(matchType)) {
			criterion = Restrictions.eq(propertyName, propertyValue);
		}
		if (MatchType.NE.equals(matchType)) {
			criterion = Restrictions.ne(propertyName, propertyValue);
		}
		if (MatchType.LE.equals(matchType)) {
			criterion = Restrictions.le(propertyName, propertyValue);
		}
		if (MatchType.LT.equals(matchType)) {
			criterion = Restrictions.lt(propertyName, propertyValue);
		}
		if (MatchType.GE.equals(matchType)) {
			criterion = Restrictions.ge(propertyName, propertyValue);
		}
		if (MatchType.GT.equals(matchType)) {
			criterion = Restrictions.gt(propertyName, propertyValue);
		}
		if (MatchType.LIKE.equals(matchType)) {
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.ANYWHERE);
		}
		if (MatchType.LIKEEND.equals(matchType)) {
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.END);
		}
		if (MatchType.LIKESTART.equals(matchType)) {
			criterion = Restrictions.like(propertyName, (String) propertyValue, MatchMode.START);
		}
		if (MatchType.IN.equals(matchType)) {
			if(value instanceof String[]){
				List<Object> c = new ArrayList<Object>();
				for (String s : (String[])value) {
					Object o =  BeanUtils.convertUtilsBean.convert(s, propertyType.getReturnedClass());
					c.add(o);
				}
				criterion = Restrictions.in(propertyName, c);
			}
		}
		return criterion;
	}

	
	public EasyuiPage<Map<String, Object>> findBySql(final EasyuiPage<Map<String, Object>> page, final String querySql, final Object... values) {
		final String countSql = "SELECT COUNT(*) FROM ("+querySql+") as total";
		Assert.notNull(page, "page不能为空");
		String qSql = "SELECT * FROM ("+querySql+") as datalist";
		Query q = createSqlQuery(qSql, values);
		int totalCount = findSqlInt(countSql, values);
		page.setTotal(totalCount);
		
		if(totalCount > 0){
			Object o = q.list();
			setPageSqlParameter(q, page);
			
			List result = q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
			page.setDataList(result);
		}
		
		return page;
	}
	
	protected Query setPageSqlParameter(final Query q, final EasyuiPage<Map<String, Object>> page) {
		if(page.getPageSize() != 0){
			int start = page.getCurPage() * page.getPageSize();
			q.setFirstResult(start);
			q.setMaxResults(page.getPageSize());
		}
		return q;
	}
	
    @SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryList(String querySql, final Object... values) {
    	Query query = getSession().createSQLQuery(querySql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
    	return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
}
