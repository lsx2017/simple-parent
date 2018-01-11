package com.simple.generator.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.CharUtils;

import com.simple.generator.entity.Generator;
import com.simple.generator.global.ConstVal;

public class JdbcUtils {

	
    /**
     * 创建数据库连接对象
     * @return
     */
	public static Connection getConn(String driverName, String url, String username, String password) {

		Connection conn = null;
		try {
			if(conn == null) {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeJdbcRes(Connection conn, Statement stmt, ResultSet rs){
		try{
			if(rs   != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
    
//	public static ResultSetMetaData getResultSetMetaData(String tableName){
//		Connection conn = getConn();
//		PreparedStatement pstmt = null;
//		ResultSetMetaData data = null;
//		try{
//			
//			pstmt = conn.prepareStatement("select * from "+tableName);
//			ResultSet rs = pstmt.executeQuery();
//			data = rs.getMetaData();
//		}catch(SQLException ex){
//			ex.printStackTrace();
//		}finally{
//			try{
//				pstmt.close();
//				conn.close();
//			}catch(SQLException ex){
//				ex.printStackTrace();
//			}
//		}
//		return data;
//	}
    
	/** 
     * 对象属性转换为字段  例如：userName to user_name 
     * @param property 字段名 
     * @return 
     */  
    public static String propertyToField(String property) {  
        if (null == property) {  
            return "";  
        }  
        char[] chars = property.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (char c : chars) {  
            if (CharUtils.isAsciiAlphaUpper(c)) {  
                sb.append("_" + CharUtils.toString(c).toUpperCase());  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    } 
	
	/** 
	 * 字段转换成对象属性 例如：user_name to userName 
	 * @param field
	 * @return
	 */
    public static String columnToField(String field) {  
        if (null == field) {  
            return "";  
        }  
        char[] chars = field.toLowerCase().toCharArray();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < chars.length; i++) {  
            char c = chars[i];  
            if (c == '_') {  
                int j = i + 1;  
                if (j < chars.length) {  
                    sb.append(CharUtils.toString(chars[j]).toUpperCase());  
                    i++;  
                }  
            } else {  
                sb.append(c);  
            }  
        }  
        return sb.toString();  
    }  
    
    public static String formatColumnType(String columnType){
        if (null == columnType) {  
            return "";  
        }  
        char[] chars = columnType.toCharArray();  
        StringBuffer sb = new StringBuffer();  
        sb.append(CharUtils.toString(chars[0]).toUpperCase());
        for (int i = 1; i < chars.length; i++) {  
            char c = chars[i];  
            sb.append(CharUtils.toString(c).toLowerCase());  
        }  
        return sb.toString();
    }
    

    /** 
     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释 
     * @param driver 数据库连接驱动 
     * @param url 数据库连接url 
     * @param user  数据库登陆用户名 
     * @param pwd 数据库登陆密码 
     * @param table 表名 
     * @return Map集合 
     
    public static List getTableInfo(String table){  
        List result = new ArrayList();  
          
        Connection conn = null;       
        DatabaseMetaData dbmd = null;  
          
        try {  
            conn = getConn();
              
            dbmd = conn.getMetaData();  
            ResultSet resultSet = dbmd.getTables(null, "%", table, new String[] { "TABLE" });  
              
            while (resultSet.next()) {  
                String tableName=resultSet.getString("TABLE_NAME");  
                System.out.println(tableName);  
                  
                if(tableName.equals(table)){  
                    ResultSet rs = conn.getMetaData().getColumns(conn.getCatalog(), "%",tableName.toUpperCase(), "%");  
  
                    while(rs.next()){  
                        //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));  
                        Map map = new HashMap();  
                        String colName = rs.getString("COLUMN_NAME");  
                        map.put("code", colName);  
                          
                        String remarks = rs.getString("REMARKS");  
                        if(remarks == null || remarks.equals("")){  
                            remarks = colName;  
                        }  
                        map.put("name",remarks);  
                          
                        String dbType = rs.getString("TYPE_NAME");  
                        map.put("dbType",dbType);  
                          
                        map.put("valueType", changeDbType(dbType));  
                        result.add(map);  
                        
                        System.out.println("colName==="+colName+"   &&remarks=="+remarks);
                    }  
                }  
            }  
            
      
            // table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".  
//            String[] types = { "TABLE" };  
//            ResultSet rs = dbmd.getTables(conn.getCatalog(), "s_user", "%", types);  
//            while (rs.next()) {  
//                String tableName = rs.getString("TABLE_NAME");  //表名  
//                String tableType = rs.getString("TABLE_TYPE");  //表类型  
//                String remarks = rs.getString("REMARKS");       //表备注  
//                System.out.println(tableName + "-" + tableType + "-" + remarks);  
//            }  
         
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        
        
          
        return result;  
    }  */  
    
    /** 
     * 获得该用户下面的所有表 
     */  
    public void getAllTableList(String schemaName) {  
 
    }  
    
    private static String changeDbType(String dbType) {  
        dbType = dbType.toUpperCase();  
        switch(dbType){  
            case "VARCHAR":  
            case "VARCHAR2":  
            case "CHAR":  
                return "1";  
            case "NUMBER":  
            case "DECIMAL":  
                return "4";  
            case "INT":  
            case "SMALLINT":  
            case "INTEGER":  
                return "2";  
            case "BIGINT":  
                return "6";  
            case "DATETIME":  
            case "TIMESTAMP":  
            case "DATE":  
                return "7";  
            default:  
                return "1";  
        }  
    }  
    
  //其他数据库不需要这个方法 oracle和db2需要  
    private static String getSchema(Connection conn) throws Exception {  
        String schema;  
        schema = conn.getMetaData().getUserName();  
        if ((schema == null) || (schema.length() == 0)) {  
            throw new Exception("ORACLE数据库模式不允许为空");  
        }  
        return schema.toUpperCase().toString();  
  
    }  

    
    /** 
     * 获得某表中所有字段的注释 
     * @param tableName 
     * @return 
     * @throws Exception 
     
    public static void getColumnCommentByTableName(String tableName) throws Exception {  
        Map map = new HashMap();  
        Connection conn = getConn();  
        Statement stmt = conn.createStatement();  
      
            String table = (String) tableName;  
            ResultSet rs = stmt.executeQuery("show full columns from " + table);  
            System.out.println("【"+table+"】");  
//          if (rs != null && rs.next()) {  
                //map.put(rs.getString("Field"), rs.getString("Comment"));  
            while (rs.next()) {     
//              System.out.println("字段名称：" + rs.getString("Field") + "\t"+ "字段注释：" + rs.getString("Comment") );
            	String type = rs.getString("Type");
            	int len = type.indexOf("(");
            	if(len > 0) {
            		type = type.substring(0, len);
            	}
            	
                System.out.println(rs.getString("Field") + "		&&Type=="+type+ "		&&Comment=="+  rs.getString("Comment") );  
            }   
//          }  
            rs.close();  
       
        stmt.close();  
        conn.close();  
//      return map;  
    }   */ 

}
