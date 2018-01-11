package com.simple.generator.entity;

import java.util.List;

import com.simple.generator.contverts.ITableParser;

/**
 * 配置信息从classpath:generator.xml读取
 * @author liushx
 * @date 2017年12月17日
 */
public class Generator {
	
	private String dbType;
    
	private ITableParser tableParser;
	
	private String driverName;
	
	private String url;
	
	private String username;
	
	private String password;
	
	private List<Module> modules;



	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public ITableParser getTableParser() {
		return tableParser;
	}

	public void setTableParser(ITableParser tableParser) {
		this.tableParser = tableParser;
	}


	
	
}
