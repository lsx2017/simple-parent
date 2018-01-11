package com.simple.common.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.simple.common.en.Size;

public class TestMain {

	public static void main(String[] args) throws Exception {
		/**
		Class<Size> cl = Size.class;
		System.out.println(cl.getName());
	
		System.out.println(BagTest.class.getName());
		BagTest test = new BagTest();
		System.out.println(test.getClass().newInstance());
		
		Class date = Class.forName("java.util.Date");
		
		System.out.println(date.newInstance());
		
//		Field 域  方法 构造器
//		Method
		Field[] fields = Double.class.getFields();
		for (Field field : fields) {
			System.out.println("name=="+field.getType());
		}
		
		Field[] fieldss = Double.class.getDeclaredFields();
		for (Field field : fieldss) {
			System.out.println("name1=="+field.getName());
		}
		
		Method[] methods = Double.class.getMethods();
		for (Method method : methods) {
			System.out.println(method.getParameterTypes());
			System.out.println(method.getReturnType());
			Modifier.isStatic(method.getModifiers());
		}
		
		Field age = User.class.getField("name");
		System.out.println(age.getType());
		
		
		try {
			Set<User> userSet = new HashSet<>();
			User user1 = new User("admin1");
			User user2 = new User("admin2");
			User user3 = new User("admin1");
			userSet.add(user1);
			userSet.add(user2);
			userSet.add(user3);
			for (User user : userSet) {
				System.out.println("name==" + user.getName());
			}
		} catch (Exception e) {
			StackTraceElement[] ss = e.getStackTrace();
			for (StackTraceElement stackTraceElement : ss) {
//				stackTraceElement.
			}
		}
		
		String str = null;
		System.out.println(str.endsWith("hello"));;
		*/
		System.out.println(textVal());
		
		List<Integer> list = Arrays.asList(1,2,3);
	
		Integer[] list1 =new Integer[]{1,2,3};
	}
	
	public static String textVal(){
		for (int i = 0; i < 10; i++) {
			//System.out.println("iii=="+i);
			for (int j = 0; j < 10; j++) {
				//System.out.println("j=="+j);
				if(i== 3 && j==3) {
					return "N";
				}
			}
		}
		
		return "Y";
	}
}
