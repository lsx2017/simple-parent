package com.simple.generator.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.simple.generator.contverts.MySQLTableParser;
import com.simple.generator.entity.Generator;
import com.simple.generator.entity.Module;
import com.simple.generator.entity.TableInfo;
import com.simple.generator.global.ConstVal;
import com.simple.generator.utils.DomUtils;

public class GeneratorXmlConfig {
	
	/** JDBC连接配置 */
	private static final String JDBC_CONNECTION = "jdbcConnection";
	/** 按模块生成代码 */
	private static final String MODULE ="module";
	private static final String ENTITY = "entity";
	private static final String MAPPING = "mapping";
	private static final String DAO = "dao";
	private static final String SERVICE = "service";
	private static final String CONTROLLER = "controller";
	private static final String TARGET_PACKAGE = "targetPackage";
	

    public Generator initGeneratorXmlConfig(){
    	Generator generator = new Generator();
    	String path = ConstVal.RES_PATH + File.separator + ConstVal.GENERATOR_NAME;
    	Document doc = DomUtils.getDocByPath(path);
    	Element rootNode = doc.getRootElement();
    	//数据库连接配置
    	Element jdbcConnNode = DomUtils.element(rootNode, JDBC_CONNECTION);
    	String driverName = DomUtils.getAttrVal(jdbcConnNode, "driverName");
    	generator.setDriverName(driverName);
    	generator.setUrl(DomUtils.getAttrVal(jdbcConnNode, "url"));
    	generator.setUsername(DomUtils.getAttrVal(jdbcConnNode, "username"));
    	generator.setPassword(DomUtils.getAttrVal(jdbcConnNode, "password"));
    	String daoSuffix = null;
    	if(driverName.contains("mysql")) {
    		generator.setDbType("mysql");
    		daoSuffix = "Mapper";
    		generator.setTableParser(new MySQLTableParser());
    	} else if(driverName.contains("oracle")) {
    		generator.setDbType("oracle");
    		daoSuffix = "Dao";
    	} 
    	//先读取模块 -> 再读取模块下相关配置
    	List<Module> modules = new ArrayList<>();
    	Module module = null;
    	List<Element> moduleNodes = DomUtils.elements(rootNode, MODULE);
    	for (Element moduleNode : moduleNodes) {
    		module = new Module();
    		module.setDaoSuffix(daoSuffix);
    		module.setModuleName(DomUtils.getAttrVal(moduleNode, "name"));
        	//entity 配置
        	Element entityNode = DomUtils.element(moduleNode, ENTITY);
        	String entityPkgName = DomUtils.getAttrVal(entityNode, TARGET_PACKAGE);
        	module.setEntityPkgName(entityPkgName);
        	module.setEntityPkgPath(initPkgDir(entityPkgName));
        	//mapping配置
        	Element mappingNode = DomUtils.element(moduleNode, MAPPING);
        	String mappingPkgName = DomUtils.getAttrVal(mappingNode, TARGET_PACKAGE);
        	module.setMappingPkgName(mappingPkgName);
        	module.setMappingPkgPath(initPkgDir(mappingPkgName));
        	//dao配置
        	Element daoNode = DomUtils.element(moduleNode, DAO);
        	String daoPkgName = DomUtils.getAttrVal(daoNode, TARGET_PACKAGE);
        	module.setDaoPkgName(daoPkgName);
        	module.setDaoPkgPath(initPkgDir(daoPkgName));
        	//service配置
        	Element serviceNode = DomUtils.element(moduleNode, SERVICE);
        	String servicePkgName = DomUtils.getAttrVal(serviceNode, TARGET_PACKAGE);
        	module.setServicePkgName(servicePkgName);
        	module.setServicePkgPath(initPkgDir(servicePkgName));
        	//controller配置
        	Element controllerNode = DomUtils.element(moduleNode, CONTROLLER);
        	String controllerPkgName = DomUtils.getAttrVal(controllerNode, TARGET_PACKAGE);
        	module.setControllerPkgName(controllerPkgName);
        	module.setControllerPkgPath(initPkgDir(controllerPkgName));
        	//表配置相关信息
        	List<Element> tables = DomUtils.elements(moduleNode, "table");
        	List<TableInfo> tableInfos = new ArrayList<>();
        	TableInfo tableInfo = null;
        	for (Element tableNode : tables) {
        		tableInfo = new TableInfo();
        		tableInfo.setTableName(DomUtils.getAttrVal(tableNode, "tableName"));
        		tableInfo.setEntityName(DomUtils.getAttrVal(tableNode, "entityName"));
        		tableInfo.setAuthor(DomUtils.getAttrVal(tableNode, "author"));
        		tableInfos.add(tableInfo);
			}
        	module.setTableInfos(tableInfos);
        	modules.add(module);
		}
    	generator.setModules(modules);
    	return generator;
    	
    }
    
    /**
     * 初始化
     * @param pkgName
     */
	private static String initPkgDir(String pkgName) {
		String[] pkgNames = pkgName.split("\\.");
		StringBuffer pkgPath = new StringBuffer(ConstVal.JAVA_PATH);
		for (String temPkgName : pkgNames) {
			pkgPath.append(File.separator).append(temPkgName);
		}
		File file = new File(pkgPath.toString());
		if(!file.isDirectory()) {
			file.mkdirs();
		}
		return pkgPath.toString();
	}
}
