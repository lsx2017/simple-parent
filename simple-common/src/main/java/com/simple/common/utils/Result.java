package com.simple.common.utils;

/**
 * 操作结果
 * 
 * @author liushx
 *
 */
public class Result {
	
	private Object data;  		//操作结果
	private boolean success;  	//操作是否成功
	private String message;		//信息
	
	public static final Result SUCCESS = new Result(true, true);
	public static final Result FAILURE = new Result(false, true);
	
	public Result(){
		
	}
	
	private Result(boolean success, boolean unique){
		this.success = success;
	}
	
	public Result(Object data){
		this.success = true;
		this.data = data;
	}
	
	public Result(boolean success){
		this.success = success;
	}
	
	public Result(Object data, boolean success, String message){
		this.data = data;
		this.success = success;
		this.message = message;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
