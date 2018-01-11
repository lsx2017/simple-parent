package com.simple.common.utils;

import java.util.List;
import java.util.Map;

public class ObjectUtils {

	public static <T> boolean isEmpty(List<T> list) {
		return list == null || list.size() == 0 ;
	}
	
	public static <T> boolean isNotEmpty(List<T> list) {
		return list != null && list.size() > 0;
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.size() == 0 ;
	}
	
	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return map != null && map.size() > 0;
	}
}
