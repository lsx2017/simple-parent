//package com.simple.generator.utils;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.StringWriter;
//import java.io.Writer;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import com.simple.generator.entity.TableInfo;
//
//import freemarker.template.Configuration;
//import freemarker.template.DefaultObjectWrapperBuilder;
//import freemarker.template.Template;
//import freemarker.template.Version;
//
///**
// * freemarker工具类
// * @author liushx
// * @date 2017年12月16日
// */
//public class FreemarkerUtils {
//	 //模板所在路径  
//    public static final String PATH = "template";  
//	public static Version version = new Version("2.3.21"); // FreeMarker版本号
//	public static Configuration config;
//	// 模板对象
//	public static Template template;
////	public static DefaultObjectWrapperBuilder defaultObjectWrapperBuilder;  
//	  
//	// 创建 Configuration 对象  
//    static {  
//        final File file = new File(FreemarkerUtils.class.getResource("/"+PATH).getPath());  
//        System.out.println("filePath=="+file.getPath());
//        config = new Configuration(version);  
//        // 设置默认编码 (至关重要 - 解决中文乱码问题)  
//        config.setDefaultEncoding("UTF-8");  
//          
////        defaultObjectWrapperBuilder = new DefaultObjectWrapperBuilder(version); //用于创建defaultObjectWrapper  
//          
//        try {  
//            // 设置模板的目录  
//            config.setDirectoryForTemplateLoading(file);  
//            // 设置对象包装器  
////            config.setObjectWrapper(defaultObjectWrapperBuilder.build());  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
//   
//    /** 
//     * 根据template和dataMap生成文档内容 
//     * @param templateName 模板文件名 
//     * @param dataMap 需要填充的数据 
//     * @return 生成的文档内容 
//     */  
//    public static <T> String buildContent(final String templateName, final TableInfo tableInfo) {  
//        // 返回结果  
//        String result = null;  
//        try {  
//            // 从模板目录下获取指定 ftl 模板文件  
//            template = config.getTemplate(templateName);  
//            // 设置文件编码  
//            template.setEncoding("UTF-8");  
//            // 字符串 字符输出流  
////            final StringWriter stringWriter = new StringWriter();  
//            
////            final BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);  
//            String  targetFile = "D:\\Workspaces\\base\\simple-parent\\simple-generator\\src\\main\\java\\User.java";
//            File file = new File(targetFile);
//            if(file.isFile()) {
//            	file.delete();
//            }
////          // 生成静态页面  
//         	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8")); 
//         	Map<String, Object> map = new HashMap<>();
//         	map.put("tableInfo", tableInfo);
//         	map.put("fieldInfos", tableInfo.getFieldInfos());
//         	
//            // 把 数据源写到模板中  
//            template.process(map, out);  
//              
//            // 获取输出的文本 (调用其 toString方法获得String)  
////            result = stringWriter.toString();  
//            // 刷新  
//            out.flush();  
//            // 关闭输出流  
//            out.close();  
//              
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//          
//        return result;  
//    }  
//      
//    //测试  
//    public static void main(String[] args) {  
//        Map<String, Object> notice = new HashMap<String, Object>();  
//        notice.put("recipient", "张三");  
//        notice.put("publisher", "系统管理员");  
//        notice.put("content", "这是通知内容，请记住。\n不要问我是谁！");  
//        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");  
//        notice.put("dateTime", df.format(new Date()));  
//          
//        Map<String, Map<String, Object>> dataMap = new HashMap<String, Map<String, Object>>();  
//        dataMap.put("data", notice);  
//          
//        String templateName = "Notice.ftl";  
////        String content = FreemarkerUtils.buildContent(templateName, dataMap);  
////        System.out.print(content);  
//    }  
//    
////	private static Configuration cfg = null;  
////	  
////    /** 
////     * 获取freemarker的配置 freemarker本身支持classpath,目录和从ServletContext获取. 
////     * @return 返回Configuration对象 
////     */  
////    private static Configuration getConfiguration() {  
////        if (cfg == null) {  
////            cfg = new Configuration();  
////            // 这里有三种方式读取  
////            // （一个文件目录）  
////            // cfg.setDirectoryForTemplateLoading(new File("templates"));  
////             //classpath下的一个目录（读取jar文件）  
////             cfg.setClassForTemplateLoading(this.getClass(),"/templates");  
////            // 相对web的根路径来说 根目录  
////            cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "templates");  
////            // setEncoding这个方法一定要设置国家及其编码，不然在flt中的中文在生成html后会变成乱码  
////            cfg.setEncoding(Locale.getDefault(), "UTF-8");  
////              
////            // 设置对象的包装器  
////            cfg.setObjectWrapper(new DefaultObjectWrapper());  
////            // 设置异常处理器//这样的话就可以${a.b.c.d}即使没有属性也不会出错  
////            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);  
////  
////        }  
////  
////        return cfg;  
////    }  
////    /** 
////     *  
////     * @param ftl 模板文件名,相对上面的模版根目录templates路径,例如/module/view.ftl templates/module/view.ftl 
////     * @param data 填充数据 
////     * @param targetFile 要生成的静态文件的路径,相对设置中的根路径,例如 "jsp/user/1.html" 
////     * @return 
////     */  
////    public  static boolean createFile(String ftl, Map<String,Object> data, String targetFile) {  
////  
////        try {  
////            // 创建Template对象  
////            Configuration cfg = FreemarkerUtils.getConfiguration();  
////            Template template = cfg.getTemplate(ftl);  
////            template.setEncoding("UTF-8");  
////  
////            // 生成静态页面  
////            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));  
////            template.process(data, out);  
////            out.flush();  
////            out.close();  
////        } catch (IOException e) {  
////            e.printStackTrace();  
////            return false;  
////        } catch (TemplateException e) {  
////            e.printStackTrace();  
////            return false;  
////        }  
////        return true;  
////    }  
//}
