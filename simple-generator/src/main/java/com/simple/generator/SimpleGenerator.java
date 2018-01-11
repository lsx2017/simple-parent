																																																	package com.simple.generator;

import java.io.File;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.simple.generator.config.GeneratorXmlConfig;
import com.simple.generator.entity.Generator;
import com.simple.generator.entity.Module;
import com.simple.generator.entity.TableInfo;
import com.simple.generator.global.ConstVal;
import com.simple.generator.utils.VelocityUtils;

/**
 * Java代码快速生成入口
 * @author liushx
 *
 */
public class SimpleGenerator {

	static Logger logger = LoggerFactory.getLogger(SimpleGenerator.class);
	 
	public void execute() {
		System.out.println("========================== start generating files ==========================");
		//1、 初始化配置
		GeneratorXmlConfig xmlConfig = new GeneratorXmlConfig();
		Generator generator = xmlConfig.initGeneratorXmlConfig();
		//2、开始生成代码
		SimpleGenerator mg = new SimpleGenerator();
		for (Module module : generator.getModules()) {
			List<TableInfo> tableInfos = module.getTableInfos();
			for (TableInfo tableInfo : tableInfos) {
//				tableInfo = mg.buildTableInfo(tableInfo);
				tableInfo = generator.getTableParser().buildTableInfo(generator, tableInfo);
				mg.generating(module, tableInfo);
			}
			
		}

		System.out.println("========================== end generating files ==========================");
	}
	
	
	/**
	 * 生成实体类
	 */
	public void generating(Module module, TableInfo tableInfo){

		VelocityUtils.tableToFile(module, tableInfo, ConstVal.ENTITY_TEMPLATE, 
				getOutputFilePath(module.getEntityPkgPath(), tableInfo.getEntityName(), ConstVal.JAVA_FILE_SUFFIX));
		
		VelocityUtils.tableToFile(module, tableInfo, ConstVal.MAPPING_TEMPLATE, 
				getOutputFilePath(module.getMappingPkgPath(), tableInfo.getMapperName(), ConstVal.XML_FILE_SUFFIX));
		
		VelocityUtils.tableToFile(module, tableInfo, ConstVal.DAO_TEMPLATE, 
				getOutputFilePath(module.getDaoPkgPath(), tableInfo.getMapperName(), ConstVal.JAVA_FILE_SUFFIX));		
		
		VelocityUtils.tableToFile(module, tableInfo, ConstVal.SERVICE_TEMPLATE, 
				getOutputFilePath(module.getServicePkgPath(), tableInfo.getServiceName(), ConstVal.JAVA_FILE_SUFFIX));	
		
		VelocityUtils.tableToFile(module, tableInfo, ConstVal.CONTROLLER_TEMPLATE, 
				getOutputFilePath(module.getControllerPkgPath(), tableInfo.getControllerName(), ConstVal.JAVA_FILE_SUFFIX));
		
		
	}
	
	private String getOutputFilePath(String pkgPath, String fileName, String suffix){
		StringBuffer filePath = new StringBuffer(pkgPath);
		filePath.append(File.separator).append(fileName).append(suffix);
//		String outputFilePath = pkgPath +  + tableInfo.getEntityName()+ConstVal.JAVA_FILE_SUFFIX;
		return filePath.toString();
	}

	/*
    //生成代码
    public void execute() {
        logger.debug("==========================准备生成文件...==========================");
        // 初始化配置
        initConfig();
        // 创建输出文件路径
        mkdirs(config.getPathInfo());
        // 获取上下文
        Map<String, VelocityContext> ctxData = analyzeData(config);
        // 循环生成文件
        for (Map.Entry<String, VelocityContext> ctx : ctxData.entrySet()) {
            batchOutput(ctx.getKey(), ctx.getValue());
        }
        // 打开输出目录
        if (config.getGlobalConfig().isOpen()) {
            try {
                String osName = System.getProperty("os.name");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + config.getGlobalConfig().getOutputDir());
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + config.getGlobalConfig().getOutputDir());
                    } else {
                        logger.debug("文件输出目录:" + config.getGlobalConfig().getOutputDir());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("==========================文件生成完成！！！==========================");
    }
    */
	
	
	

	

}
