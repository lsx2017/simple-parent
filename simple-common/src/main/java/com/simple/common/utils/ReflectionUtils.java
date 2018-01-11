package com.simple.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author Echoice-java_oo@163.com
 *
 */
public class ReflectionUtils {
	
//	private final static Logger logger = Logger.getLogger(ReflectionUtils.class);
	
	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
	 *
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be determined
	 */
	public static Class getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User,Long>
	 *
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be determined
	 */
	public static Class getSuperClassGenricType(final Class clazz, final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	// 向上获得类的声明字段
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(final Class clazz, final String fieldName) {
//		Assert.notNull(clazz, "clazz不能为空");
//		Assert.hasText(fieldName, "fieldName");
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	// 调用对象的set方法
	public static void invokeSetMethod(Object instance, Field field,
			Object fieldValue) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String fieldName = field.getName();

		String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		Method setMethod = instance.getClass().getMethod(setMethodName,
				field.getType());
		setMethod.invoke(instance, fieldValue);
	}

	// 调用对象的get方法
	public static Object invokeGetMethod(Object instance, Field field)
			throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		String fieldName = field.getName();

		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);

		Method getMethod = instance.getClass().getMethod(getMethodName, null);
		return getMethod.invoke(instance, null);
	}

	// 获得指定变量的值
	public static Object getFieldValue(Object instance, String fieldName)
			throws IllegalArgumentException, IllegalAccessException {

		Field field = getDeclaredField(instance.getClass(), fieldName);
		return getFieldValue(instance, field);
	}

	// 获得指定变量的值
	public static Object getFieldValue(Object instance, Field field)
			throws IllegalArgumentException, IllegalAccessException {

		boolean accessible = field.isAccessible();
		// 参数值为true，禁用访问控制检查
		field.setAccessible(true);
		Object result = field.get(instance);
		field.setAccessible(accessible);
		return result;
	}
	
	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object, final String fieldName, final Object value) {
		Field field = getDeclaredField(object.getClass(), fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");

		// 参数值为true，禁用访问控制检查
		field.setAccessible(true);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			
		}
	}
	
	//设置对象属性值
	public static void setProperty(Object instance, String fieldName,
			String fieldValue) throws InstantiationException,
			IllegalAccessException, SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException {
		
//		Assert.notNull(instance, "instance不能为空");
		fieldValue = fieldValue.trim(); //去左右空格
		int index = fieldName.indexOf(".");
		
		if (index != -1) { // 要设置的是一个实体对象

			String entityName = fieldName.substring(0, index);
			String subString = fieldName.substring(index + 1);

			Field field = getDeclaredField(instance.getClass(), entityName);

			if (field != null && field.getAnnotation(ReflectIgnore.class) == null) {
				Object entity = getFieldValue(instance, field);
				if (entity == null) { // 不存在的话就创建实体对象
					entity = field.getType().newInstance();
				}
				// 递归
				setProperty(entity, subString, fieldValue);
				// 把实体对象设置到父对象中
				invokeSetMethod(instance, field, entity);
			}

		} else { // 要设置的是一个简单类型对象
			Field field = ReflectionUtils.getDeclaredField(instance.getClass(), fieldName);

			if (field != null && field.getAnnotation(ReflectIgnore.class) == null) {
				
				if(StringUtils.isEmpty(fieldValue)){ //
					invokeSetMethod(instance, field, null);
					return;
				}
				
				Object value = null;
				//对时间类型进行特殊处理
				//可以处理请求名称形如"startDate_yyyy-HH-dd HH:mm"格式的参数
				try{
					if(field.getType().equals(Date.class) && fieldName.split("_").length > 1){
						String pattern = fieldName.split(",")[1];
						DateFormat format = new SimpleDateFormat(pattern);
						value = format.parse(fieldValue);
					}
				}catch(Exception e){
					//logger.error(e);
				}
				
				if(value == null){
					value = BeanUtils.convertUtilsBean.convert(fieldValue, field.getType()); // 把值转换为相应的类型
				}
				invokeSetMethod(instance, field, value);
			}
		}

	}
	
}
