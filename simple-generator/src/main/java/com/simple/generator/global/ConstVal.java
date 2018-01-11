package com.simple.generator.global;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import com.simple.generator.contverts.ITableParser;
import com.simple.generator.entity.Module;

/**
 * 全局常量-从global.properties读取
 * @author liushx
 * @date 2017年12月16日
 */
public class ConstVal {
	
	 /** UTF-8编码 */
    public static final String UTF8 = Charset.forName("UTF-8").name();
    /** Java文件后缀 */
    public static final String JAVA_FILE_SUFFIX = ".java";
    /** xml文件后缀 */
    public static final String XML_FILE_SUFFIX = ".xml";
    

    
    public static final String GET_METHOD_PREFIX = "get";
   
    public static final String SET_METHOD_PREFIX = "set";
	
    public static final String GENERATOR_NAME = "generator.xml";
    public static String JAVA_PATH = null;
    public static String RES_PATH = null;
    static {
		String dirPath = System.getProperty("user.dir");
    	RES_PATH = dirPath + File.separator+ "src" + File.separator+ "main" +File.separator+ "resources";
    	JAVA_PATH = dirPath + File.separator+ "src" + File.separator+ "main" +File.separator+ "java";
    }
    
    /** Java实体类模板 */
    public static final String TEMPLATE_PATH = RES_PATH +File.separator+ "template"; 
    public static final String ENTITY_TEMPLATE = "entity.vm";
    public static final String MAPPING_TEMPLATE = "mapper.vm";
    public static final String DAO_TEMPLATE = "dao.vm";
    public static final String SERVICE_TEMPLATE = "service.vm";
    public static final String CONTROLLER_TEMPLATE = "controller.vm";
    
	    
	/** 数据库配置 */

	
//	public static List<Module> MODULES = null;



    
    /**
    private ConstVal(){
    	properties = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("global.properties");
		try {
			properties.load(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }   
     */
	
	public static void main(String[] args) {
//		String[] str = new 
	}
	
}
