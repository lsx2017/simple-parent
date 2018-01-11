package com.simple.generator.entity;

import java.util.List;
import java.util.Set;


public class TableInfo {

	/** 表名称 */
	private String tableName;
	/** 实体名称 */
    private String entityName;
    /** 注释 */
    private String comment;
    /** 作者 */
    private String author;
    /** 创建时间 */
    private String createDate;
    /** spring链接、变量名称 */
    private String varName;
    /** 要导入的包 */
	private Set<String> pkgs;
	
	private FieldInfo pkFieldInfo;
	
	private List<FieldInfo> fieldInfos;
	/** mybatis xml使用 */
	private String columnNames;
//	/** 主键Java变量名称 
//	private String pkVarName;
//	private String pkGetMethodName;
//	/** 主键数据库字段名称 */
//	private String pkColumnName;
//	/** 主键Java数据类型含包路径(java.lang.String) */
//	private String pkFieldType;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<FieldInfo> getFieldInfos() {
		return fieldInfos;
	}

	public void setFieldInfos(List<FieldInfo> fieldInfos) {
		this.fieldInfos = fieldInfos;
	}

	public Set<String> getPkgs() {
		return pkgs;
	}

	public void setPkgs(Set<String> pkgs) {
		this.pkgs = pkgs;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getMapperName() {
		return this.entityName+"Mapper";
	}
	
	public String getServiceName() {
		return this.entityName+"Service";
	}

	public String getControllerName() {
		return this.entityName+"Controller";
	}

	public FieldInfo getPkFieldInfo() {
		return pkFieldInfo;
	}

	public void setPkFieldInfo(FieldInfo pkFieldInfo) {
		this.pkFieldInfo = pkFieldInfo;
	}
	
	
}
