package com.simple.generator.entity;


/**
 * 列表要显示的列
 * @author liushx
 * @date 2016年10月5日
 */
public class FieldInfo {

	/** 实体类属性数据类型 */
	private String fieldType;
	/** 实体类属性数据类型(带包路径) */
	private String importType;
	/** 实体类属性名称 */
	private String fieldName;
	//对应数据库的
	/** 对应数据库字段类型 */
	private String dataType;
	/** 对应数据库字段名称 */
	private String columnName;
	/** 注释 */
	private String comment;
	/** get方法名称 */
	private String getMethodName;
	/** set方法名称 */
	private String setMethodName;
	/** 判断是否主键 */
	private String keyName;	

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGetMethodName() {
		return getMethodName;
	}

	public void setGetMethodName(String getMethodName) {
		this.getMethodName = getMethodName;
	}

	public String getSetMethodName() {
		return setMethodName;
	}

	public void setSetMethodName(String setMethodName) {
		this.setMethodName = setMethodName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	
}
