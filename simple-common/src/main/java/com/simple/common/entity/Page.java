package com.simple.common.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("serial")
public class Page<T> implements Serializable {
	
	/** 当前页  */
	private int page = 1;//当前页
	/** 每页显示记录数  */
	private int rows;//每页显示记录数
	/** 总记录数 */
	private int total;//总记录数
	
	private List<Map<String, Object>> footer;

	public static final int PAGE_SIZE = 15;
	
	private List<T> dataList = Collections.emptyList();  	//页面记录集
	
	private String orderBy;	

	private Map<String, OrderType> orderMap = new LinkedHashMap<String, OrderType>();  //页面排序
	/**
	 * 排序方式
	 */
	public enum OrderType{
		ASC, DESC
	}

	public int getPage() {
		return page;
	}

	public int getCurPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		if (rows > 0) {
			this.rows = rows;
		} else {
			this.rows = PAGE_SIZE;
		}
	}

	public int getPageSize() {
		if (rows > 0) {
			return this.rows;
		} else {
			return PAGE_SIZE;
		}
	}

	public int getRows() {
		return rows;
	}
	
	public int getTotalPages() {
		//iPageCount = (iRowCount + iPageSize - 1) / iPageSize;
		if(this.total < 3 || this.rows == 0) {
			return 1;
		}
		return (this.total + this.rows - 1) / this.rows;
	}

	public void setTotalPages(int totalPages) {
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public Map<String, OrderType> getOrderMap() {
		return orderMap;
	}

	public Page<T> setOrderMap(Map<String, OrderType> orderMap) {
		this.orderMap = orderMap;
		return this;
	}
	
	public String getOrderBy(){
		return orderBy;
	}
	
	
	public List<Map<String, Object>> getFooter() {
		return footer;
	}

	public void setFooter(List<Map<String, Object>> footer) {
		this.footer = footer;
	}

	public Page<T> setOrderBy(String orderBy){
		String[] orderArray = orderBy.split(",");
		for(String orderStr : orderArray){
			String[] orders = orderStr.split("_");
			if("asc".equals(orders[0].toLowerCase().trim())){
				addOrder(orders[1], OrderType.ASC);
			}else if("desc".equals(orders[0].toLowerCase().trim())){
				addOrder(orders[1], OrderType.DESC);
			}else{
				throw new IllegalArgumentException("orderBy字段格式错误: " + orderBy);
			}
		}
		this.orderBy = orderBy;
		return this;
	}
	
	/**
	 *  添加排序方式
	 * @param orderBy 排序方式
	 * @param orderType 根据哪个字段排序
	 * @return
	 */
	public Page<T> addOrder(String orderBy, OrderType orderType){
		this.orderMap.put(orderBy, orderType);
		return this;
	}
	
	public Map<String, Object> toPageMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", this.getTotal());
		map.put("rows", this.getDataList());
		map.put("footer", this.getFooter());
	
		return map;
	}
	
	
	public int getOffset(){
		return ((this.getCurPage()-1)*getPageSize()+1);
	}
	
	public int getLimit(){
		return (this.getCurPage()*getPageSize());
	}
	
}
