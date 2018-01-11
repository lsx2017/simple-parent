package com.simple.common.utils;

import java.util.HashMap;

public class HashMapExt extends HashMap {

	
	@Override
	public Object get(Object key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getObj(Object object) {
		if (object == null) {
			return null;
		}
		return (T) object;
	}
}
