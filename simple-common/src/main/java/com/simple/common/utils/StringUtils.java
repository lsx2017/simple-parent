package com.simple.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串处理工具类
 * org.springframework.util.StringUtils
 * @author liushx
 * @date 2017-10-17
 */
public class StringUtils  {


	/**
	 * 判断字符串是否为空，如果为nul或者""则返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return null == str || "".equals(str);
	}
	
	/**
	 * 字符串非空判断
	 * @param str
	 * @return
	 */
    public static boolean isNotEmpty(CharSequence str) {
        return (str == null || str.length() == 0) == false;

    }


	/**
	 * 判断字符串是否有长度，不等于null同时长度大于0，则为true
	 * 这里重载两个hasLength方法，其中CharSequence是String的父类，是接口
	 * @param str
	 * @return
	 */
	public static boolean hasLength(CharSequence str) {
		return str != null && str.length() > 0;
	}

	public static boolean hasLength(String str) {
		return hasLength(((CharSequence) (str)));
	}
	    
	/**
	 * 将字Clob转成String类型
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static String clobToString(Clob clob) throws SQLException, IOException {
		String reString = "";
		//得到流
		Reader is = clob.getCharacterStream();
		BufferedReader br = new BufferedReader(is);
		String str = br.readLine();
		StringBuffer sb = new StringBuffer();
		//执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
		while (null != str) {
			sb.append(str);
			str = br.readLine();
		}

		return sb.toString();
	}
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String captureName(String str) {
		return formatFirstLetter(str, true);
    }
	
	public static String firstLowerCase(String str){
		return formatFirstLetter(str, false);
	}
	
	public static String formatFirstLetter(String str, boolean isUpperCase){
		if(isEmpty(str)) return "";
		if(isUpperCase) {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}
	
	public static boolean in(String str, String[] arrStr) {
		return Arrays.asList(arrStr).contains(str);
	}
	
	public static boolean notIn(String str, String[] arrStr) {
		return !in(str, arrStr);
	}
	
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = null;
		
		System.out.println(isEmpty(str1));
		List<String> list = new ArrayList<>(100);
		for (int i = 0; i < 110; i++) {
			list.add("hello"+i);
		}
		List<String> newList = list.subList(10, 20);
		
		
		System.out.println(list.size());
	}
}
