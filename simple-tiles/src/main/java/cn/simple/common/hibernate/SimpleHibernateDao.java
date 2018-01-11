package cn.simple.common.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.simple.common.utils.ReflectionUtils;


/**
 * 封装Hibernate原生API的CRUD泛型基类.
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 * 
 */
@SuppressWarnings("unchecked")
public class SimpleHibernateDao<T, PK extends Serializable> {

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 用于扩展的DAO子类使用的构造函数.
	 * 通过子类的泛型定义取得对象类型Class.
	 * eg.
	 * public class UserDao extends SimpleHibernateDao<User, Long>
	 */
	public SimpleHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于Service层直接使用SimpleHibernateDAO的构造函数.
	 * eg.
	 * SimpleHibernateDao<User, Long> userDao = new SimpleHibernateDao<User, Long>(sessionFactory, User.class);
	 */
	public SimpleHibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 采用@Autowired按类型注入SessionFactory,当有多个SesionFactory的时候Override本函数.
	 * @param sessionFactory
	 */
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 保存新增或修改的对象.
	 */
	public void save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
	}
	
	public void update(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
	}

	/**
	 * 删除对象.
	 * @param entity 对象必须是session中的对象或含id属性的transient对象.
	 */
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
	}
	
	/**
	 * 按id删除对象.
	 */
	public void delete(final PK... ids) {
		StringBuilder builder = new StringBuilder();
		builder.append("delete ").append(entityClass.getSimpleName())
			.append(" where id in(");
		for(int i = 0; i < ids.length; i ++){
			builder.append("?,");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		executeHql(builder.toString(), ids);
	}
	
	public T load(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}
	/**
	 * 按id获取对象.
	 */
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}
	
	/**
	 * 初始化大数据对象或级联对象
	 */
	public void init(Object proxy){
		Assert.notNull(proxy, "proxy不能为空");
		Hibernate.initialize(proxy);
	}

	/**
	 *	获取全部对象. 
	 */
	public List<T> getAll() {
		return find();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T findByUnique(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T findUniqueByCriteria(Criterion... criterions) {
		Criteria criteria = this.createCriteria(criterions);
		return (T) criteria.uniqueResult();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values 数量可变的参数
	 */
	public List<T> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique(final String hql, final Object... values) {
		return createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询Integer类型结果. 
	 */
	public Integer findInt(final String hql, final Object... values) {
		long l  = (Long)findUnique(hql, values);
		return (int)l ;
	}

	/**
	 * 按HQL查询Long类型结果. 
	 */
	public Long findLong(final String hql, final Object... values) {
		return (Long) findUnique(hql, values);
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * 
	 * @param values 数量可变的参数
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	public Integer findSqlInt(final String sql, final Object... values) {
		String count = String.valueOf(findUniqueBySql(sql, values));
		return Integer.valueOf(count) ;
	}
	
	public Object findUniqueBySql(final String sql, final Object... values) {
		return createSqlQuery(sql, values).uniqueResult();
	}
	
	public Query createSqlQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	/**
	 * 根据HQL执行修改和删除操作
	 * @param updateString 可执行的hql
	 * @param values 数量可变的参数
	 */
	public void executeHql(final String updateString, final Object... values){
		Assert.hasText(updateString, "queryString不能为空");
		Query query = getSession().createQuery(updateString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		query.executeUpdate();
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * 返回对象类型不是Entity时可用此函数灵活查询.
	 * 
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if(criterions != null){
			for (Criterion c : criterions) {
				if(c != null){
					criteria.add(c);
				}
			}
		}
		return criteria;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object orgValue) {
		if (newValue == null || newValue.equals(orgValue))
			return true;
		Object object = findByUnique(propertyName, newValue);
		return (object == null);
	}
	
	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 */
	public boolean isPropertyUnique(final String propertyName, final Object propertyValue, final PK id) {
		
		Criteria c = null;
		Criterion c1 = Restrictions.eq(propertyName, propertyValue);
		if(id != null){
			Criterion c2 = Restrictions.ne(getIdName(), id);
			c = createCriteria(c1, c2);
		}else{
			c = createCriteria(c1);
		}
		List list = find(c1);
		return list == null || list.size() < 2;
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass.getSimpleName() + " not define in HibernateSessionFactory.");
		return meta.getIdentifierPropertyName();
	}
}