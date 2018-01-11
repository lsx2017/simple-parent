package com.simple.generator.contverts;

public class DataFieldType {

	/** 对应数据库类型 */
	private String dataType;
	/** 对应属性前面显示Java数据类型 */
	private String fieldType;
	/** 对应Java类导入类型 */
	private String importType;
	
	public DataFieldType(String dataType, String fieldType, String importType) {
		this.dataType = dataType;
		this.fieldType = fieldType;
		this.importType = importType;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	// STRING("String", null),
	// LONG("Long", null),
	// INTEGER("Integer", null),
	// FLOAT("Float", null),
	// DOUBLE("Double", null),
	// BOOLEAN("Boolean", null),
	// BYTE_ARRAY("byte[]", null),
	// CHARACTER("Character", null),
	// OBJECT("Object", null),
	// DATE("Date", "java.util.Date"),
	// TIME("Time", "java.sql.Time"),
	// BLOB("Blob", "java.sql.Blob"),
	// CLOB("Clob", "java.sql.Clob"),
	// TIMESTAMP("Timestamp", "java.sql.Timestamp"),
	// BIG_INTEGER("BigInteger", "java.math.BigInteger"),
	// BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
	// LOCAL_DATE("LocalDate", "java.time.LocalDate"),
	// LOCAL_TIME("LocalTime", "java.time.LocalTime"),
	// LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime");
	// /**
	// * 类型
	// */
	// private final String type;
	//
	// /**
	// * 包路径
	// */
	// private final String pkg;
	//
	// FieldColumnType(final String type, final String pkg) {
	// this.type = type;
	// this.pkg = pkg;
	// }
	//
	// public String getType() {
	// return this.type;
	// }
	//
	// public static String getTypeByPkg(String pkg){
	// for (FieldColumnType fcType : FieldColumnType.values()) {
	// System.out.println("pkg=="+pkg);
	// if(fcType.pkg.equals(pkg)) {
	// return fcType.type;
	// }
	// }
	//
	// return FieldColumnType.STRING.getType();
	// }
	//
	// public String getPkg() {
	// return this.pkg;
	// }

}
