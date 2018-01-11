package com.simple.common.mybatis.page;

import java.lang.reflect.Field;

public class Reflections {

	/** 
     * 获取当前类声明的private/protected变量 
     */  
	public static  Object getFieldValue(Object object, String propertyName)  {  
		try {

	        System.out.println("propertyName=="+propertyName);
	        Field field = object.getClass().getDeclaredField(propertyName);  
	        field.setAccessible(true);  
	        
	        return field.get(object); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
    } 
    
    public static void setFieldValue(Object obj, String propertyName, Object value) {

        try {
        	Field field = obj.getClass().getDeclaredField(propertyName);  
        	field.setAccessible(true);  
//          String fieldType = field.getType().getSimpleName();
//           if (fieldType.equals("int"))
//               field.setInt(obj, new Integer(value));
//           else if (fieldType.equals("float"))
//               field.setFloat(obj, new Float(value));
//           else if (fieldType.equals("boolean"))
//               field.setBoolean(obj, new Boolean(value));
//           else if (fieldType.equals("char"))
//               field.setChar(obj, value.charAt(0));
//           else if (fieldType.equals("double"))
//               field.setDouble(obj, new Double(value));
//           else if (fieldType.equals("long"))
//               field.setLong(obj, new Long(value));
//           else
               field.set(obj, value);
        } catch (Exception e) {
           e.printStackTrace();
        } 
    }
}
