package cn.simple.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class StringUtils extends org.apache.commons.lang.StringUtils  {
	
	
	public static final String[] parsePatterns = { "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss" };
	private static final String[] BOOLEAN_TRUE_STRINGS = { "true", "t", "o", "on", "y", "yes", "1", "是" };

	
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
  
    public static boolean isNotEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return false;
        else
            return true;
    }
    

    
	public static String nvl(String s) {
		return s == null ? "" : s;
	}

	public static long parseLong(String s, long defValue) {
		long v = defValue;
		try {
			v = Long.parseLong(s);
		} catch (Exception e) {

		}
		return v;
	}

	public static boolean parseBoolean(String s) {
		boolean isBoolean = false;
		if (s != null && s.length() > 0) {
			for (String v : BOOLEAN_TRUE_STRINGS) {
				if (v.equalsIgnoreCase(s.trim())) {
					isBoolean = true;
					break;
				}
			}
		}
		return isBoolean;
	}

	public static boolean checkMap(Map<String, String> map, List<String> fields) {
		boolean isOk = false;
		for (String key : map.keySet()) {
			String value = map.get(key);
			if (fields.contains(key)) {
				if (value == null || value.length() == 0) {
					isOk = false;
					break;
				} else
					isOk = true;
			} else if (value != null && value.trim().length() > 0) {
				isOk = true;
			}
		}
		return isOk;
	}

	public static String nullValue(Object value) {
		return nullValue(value, "");
	}

	public static String nullValue(Object object, String defaultString) {
		return object == null ? defaultString
				: ((object instanceof String) ? (String) object : object
						.toString());
	}

	public static boolean booleanValue(String str) {
		return str != null
				&& (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str
						.equalsIgnoreCase("on"));
	}

	public static double doubleValue(String str) {
		return doubleValue(str, 0);
	}

	public static Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		for (String pattern : parsePatterns) {
			try {
				sdf.applyPattern(pattern);
				return sdf.parse(date);
			} catch (Exception e) {

			}
		}
		return null;
	}

	public static Date parseDate(String date,String pattern) {
		if(pattern==null || pattern.length()==0){
			return parseDate(date);
		}
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			sdf.applyPattern(pattern);
			return sdf.parse(date);
		} catch (Exception e) {
		}
		return null;
	}
	public static double doubleValue(String str, double defaultValue) {
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String jsonStrFilter(String str) {
		String jsonStr = "";
		if (str != null && str.length() > 0) {
			StringBuilder stringBuilder = new StringBuilder(str.length() * 2);
			char cht;
			for (int index = 0; index < str.length(); index++) {
				cht = str.charAt(index);
				switch (cht) {
				case '\"':
					stringBuilder.append("\\\"");
					break;
				case '\\':
					stringBuilder.append("\\\\");
					break;
				case '\b':
					stringBuilder.append("\\b");
					break;
				case '\n':
					stringBuilder.append("\\n");
					break;
				case '/':
					stringBuilder.append("\\/");
					break;
				case '\f':
					stringBuilder.append("\\f");
					break;
				case '\r':
					stringBuilder.append("\\r");
					break;
				case '\t':
					stringBuilder.append("\\t");
					break;
				default:
					stringBuilder.append(cht);
				}
			}
			jsonStr = stringBuilder.toString();
		}
		return jsonStr;
	}
	
	public static String arrayToString(String[] arrs){
		return arrayToString(arrs, ",");
	}
	
	/**
	 * 将字符串数组转换成分隔符为(逗号隔开?)字符串
	 * @param arrs
	 * @param separator
	 * @return
	 */
	public static String arrayToString(String[] arrs, String separator){
		StringBuffer result = new StringBuffer(arrs[0]);
		for (int i = 1; i < arrs.length; i++) {
			result.append(separator).append(arrs[i]);
		}
		return result.toString();
	}
	
	public static String longToString(Long obj) {
		if(obj == null) 
			return "";
		return String.valueOf(obj);
		
	}
	
	public static Long[] strArrToLongArr(String[] strs){
		 Long[] num = new Long[strs.length];
	     for(int i = 0; i < num.length; i++){
	         num[i] = Long.valueOf(strs[i]);
	     }
	     return num;
	}

    
}
