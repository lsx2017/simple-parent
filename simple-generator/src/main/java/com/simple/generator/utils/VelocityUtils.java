package com.simple.generator.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import javax.persistence.Table;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;


import org.springframework.util.StringUtils;

import com.simple.generator.entity.Module;
import com.simple.generator.entity.TableInfo;
import com.simple.generator.global.ConstVal;


public class VelocityUtils {

    /**
     * velocity引擎
     */
	private VelocityEngine engine;
	
//	public static <T> String buildContent(final String templateName, final TableInfo tableInfo) { 
//	    // 初始化模板引擎
//	    VelocityEngine ve = new VelocityEngine();
//	    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//	    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//	    ve.init();
//	    // 获取模板文件
//	    Template t = ve.getTemplate("entity.vm");
//	    // 设置变量
//	    VelocityContext ctx = new VelocityContext();
//	    ctx.put("tableInfo", tableInfo);
////	    List list = new ArrayList();
////	    list.add("1");
////	    list.add("2");
////	    ctx.put("list", list);
//	    // 输出
////	    StringWriter sw = new StringWriter();
////	    t.merge(ctx,sw);
////	    System.out.println(sw.toString());
//	 }
	
	public static void tableToFile(Module module, TableInfo tableInfo, String templateName, String outputFilePath) {
		VelocityUtils vu = new VelocityUtils();
	    VelocityContext ctx = new VelocityContext();
	    ctx.put("module", module);
	    ctx.put("tableInfo", tableInfo);
//	    ctx.put("daoSuffix", ConstVal.DAO_SUFFIX);
	    
		try {
			vu.vmToFile(ctx, templateName, outputFilePath);
		} catch (Exception e) {
			System.out.println("generating mapping file failure ！！！");
			e.printStackTrace();
		}
		
	}
	
//	public static void tableToFile(String pkgName, TableInfo tableInfo, String templateName, String outputFilePath){
//		VelocityUtils vu = new VelocityUtils();
//	    VelocityContext ctx = new VelocityContext();
//	    ctx.put("tableInfo", tableInfo);
//	    ctx.put("pkgName", pkgName);
//		try {
//			vu.vmToFile(ctx, templateName, outputFilePath);
//		} catch (Exception e) {
//			System.out.println("generating file failure ！！！");
//			e.printStackTrace();
//		}
//	}
	
	public static void tableToMappingFile(Module module, TableInfo tableInfo, String templateName, String outputFilePath){
		VelocityUtils vu = new VelocityUtils();
	    VelocityContext ctx = new VelocityContext();
	    ctx.put("module", module);
	    ctx.put("tableInfo", tableInfo);
		try {
			vu.vmToFile(ctx, templateName, outputFilePath);
		} catch (Exception e) {
			System.out.println("generating mapping file failure ！！！");
			e.printStackTrace();
		}
	}
	 
	/**
	 * <p>
	 * 将模板转化成为文件
	 * </p>
	 * 
	 * @param context
	 *            内容对象
	 * @param templatePath
	 *            模板文件
	 * @param outputFile
	 *            文件生成的目录
	 */
	public void vmToFile(VelocityContext context, String templateName, String outputFile) throws Exception {
		if (StringUtils.isEmpty(templateName)) {
			return;
		}
		System.out.println(outputFile);
	//	String dirPath = System.getProperty("user.dir");
//		templatePath ="template\\entity.vm";
		
		VelocityEngine velocity = getVelocityEngine();
		Template template = velocity.getTemplate(templateName, ConstVal.UTF8);
		//outputFile = "D:\\Workspaces\\base\\simple-parent\\simple-generator\\src\\main\\java\\User.java";
		File file = new File(outputFile);
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				System.out.println("创建文件所在的目录失败!");
				return;
			}
		}
		FileOutputStream fos = new FileOutputStream(outputFile);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
		template.merge(context, writer);
		writer.close();
		//.logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
	}
	
	/**
     * 设置模版引擎，主要指向获取模版路径
	 * @throws Exception 
     */
    private VelocityEngine getVelocityEngine() throws Exception {
        if (engine == null) {
            Properties p = new Properties();
//            p.setProperty(ConstVal.VM_LOADPATH_KEY, ConstVal.VM_LOADPATH_VALUE);
//            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
//            p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
//            p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
            p.setProperty("file.resource.loader.unicode", "true");
//    	    p.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//    	    p.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    	    p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, ConstVal.TEMPLATE_PATH);  
       
            engine = new VelocityEngine(p);
        }
        return engine;
    }


}
