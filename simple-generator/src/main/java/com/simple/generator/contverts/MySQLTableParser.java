package com.simple.generator.contverts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.simple.common.utils.DateUtils;
import com.simple.common.utils.StringUtils;
import com.simple.generator.entity.FieldInfo;
import com.simple.generator.entity.Generator;
import com.simple.generator.entity.TableInfo;
import com.simple.generator.global.ConstVal;
import com.simple.generator.utils.JdbcUtils;

public class MySQLTableParser implements ITableParser {
	
	private static List<DataFieldType> dataFieldTypes = new ArrayList<>();
			
	static {
		dataFieldTypes.add(new DataFieldType("int", "Integer", "java.lang.Integer"));
		dataFieldTypes.add(new DataFieldType("bigint", "Long", "java.lang.Long"));
		dataFieldTypes.add(new DataFieldType("float", "Float", "java.lang.Float"));
		dataFieldTypes.add(new DataFieldType("double", "Double", "java.lang.Double"));
		dataFieldTypes.add(new DataFieldType("decimal", "BigDecimal", "	java.math.BigDecimal"));
		dataFieldTypes.add(new DataFieldType("char",   "String", "java.lang.String"));
		dataFieldTypes.add(new DataFieldType("varchar", "String", "java.lang.String"));
		dataFieldTypes.add(new DataFieldType("text", "String", "java.lang.String"));
		dataFieldTypes.add(new DataFieldType("date", "Date", "java.util.Date"));
		dataFieldTypes.add(new DataFieldType("datetime", "Timestamp", "java.sql.Timestamp"));
		dataFieldTypes.add(new DataFieldType("timestamp", "Timestamp", "java.sql.Timestamp"));
		dataFieldTypes.add(new DataFieldType("bit", "Boolean", "java.lang.Boolean"));
	}


	@Override
	public DataFieldType dataTypeConvert(String dataType) {
//		System.out.println("dataType==="+dataType);
		for (DataFieldType dataFieldType : dataFieldTypes) {
			if(dataType.equals(dataFieldType.getDataType())) {
				return dataFieldType;
			}
		}
        return new DataFieldType("java.lang.String", "String", null);
	}
	
	@Override
	public TableInfo buildTableInfo(Generator generator, TableInfo tableInfo){
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String tableName = tableInfo.getTableName();
		try{
			conn = JdbcUtils.getConn(generator.getDriverName(), generator.getUrl(), 
						generator.getUsername(), generator.getPassword());
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show full columns from " + tableName);  
            System.out.println("构建【"+tableName+"】信息!!!");  

			List<FieldInfo> fieldInfos = new ArrayList<>();
			Set<String> pkgs = new HashSet<>();
			FieldInfo field = null;
			StringBuffer columnNames = new StringBuffer();
            while (rs.next()) {     
            	String columnName = rs.getString("Field");
            	String dataType = rs.getString("Type");
            	int len = dataType.indexOf("(");
            	if(len > 0) {
            		dataType = dataType.substring(0, len);
            	}
            	String comment = rs.getString("Comment");
				field = new FieldInfo();
				field.setColumnName(columnName);
				columnNames.append(columnName).append(",");
				field.setDataType(dataType);
				String fieldName =JdbcUtils.columnToField(columnName);
				field.setFieldName(fieldName);
				DataFieldType dataFieldType = dataTypeConvert(dataType);
				field.setFieldType(dataFieldType.getFieldType());
				field.setComment(comment);
				field.setGetMethodName(ConstVal.GET_METHOD_PREFIX + StringUtils.captureName(fieldName));
				field.setSetMethodName(ConstVal.SET_METHOD_PREFIX + StringUtils.captureName(fieldName));
				//System.out.println("columnName===="+columnName);
				if(!dataFieldType.getImportType().contains("java.lang")) {
					pkgs.add(dataFieldType.getImportType());
				}
				//是否主键
				String keyName = rs.getString("Key");
				field.setKeyName(keyName);
				
				if(keyName != null && "PRI".equals(keyName)) {
					tableInfo.setPkFieldInfo(field);
					//field.setPk(true);
//					tableInfo.setPkFieldType(dataFieldType.getImportType());
//					tableInfo.setPkGetMethodName(ConstVal.GET_METHOD_PREFIX + StringUtils.captureName(fieldName));
//					tableInfo.setPkColumnName(columnName);
//					tableInfo.setPkVarName(StringUtils.firstLowerCase(fieldName));
				} else {
					//field.setPk(false);
				}
				fieldInfos.add(field);
            }  
            
			tableInfo.setEntityName(tableInfo.getEntityName());
			tableInfo.setFieldInfos(fieldInfos);
			tableInfo.setPkgs(pkgs);
			tableInfo.setComment(getTableComment(conn, tableName));
			tableInfo.setCreateDate(DateUtils.getToday());
			tableInfo.setVarName(StringUtils.firstLowerCase(tableInfo.getEntityName()));
			String colNames = columnNames.toString();
			tableInfo.setColumnNames(colNames.substring(0, colNames.length()-1));
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			JdbcUtils.closeJdbcRes(conn, stmt, rs);
		}

		return tableInfo;
	}
	
    /** 
     * 获得某表的建表语句 
     * @param tableName 
     * @return 
     * @throws Exception 
     */  
    public static String getTableComment(Connection conn, String tableName){  
    	String comment = "";
//        Connection conn = JdbcUtils.getConn();  
        Statement stmt = null;
        ResultSet rs = null;
		try {
			stmt = conn.createStatement();
	        rs = stmt.executeQuery("SHOW CREATE TABLE " + tableName);  
	        if (rs != null && rs.next()) {  
	            String createDDL = rs.getString(2);  
	            comment = commentParse(createDDL);  
	        }  
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
	       //JdbcUtils.closeJdbcRes(conn, stmt, rs); 
		}
        return comment;  
    }  
    
    /** 
     * 返回注释信息 
     * @param all 
     * @return 
     */  
      
    public static String commentParse(String all) {  
        String comment = null;  
        int index = all.indexOf("COMMENT='");  
        if (index < 0) {  
            return "";  
        }  
        comment = all.substring(index + 9, (all.length() - 1));  
        return comment;  
    } 
}
