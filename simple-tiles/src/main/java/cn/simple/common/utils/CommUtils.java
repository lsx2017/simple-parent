package cn.simple.common.utils;

import javax.servlet.http.HttpServletRequest;


public class CommUtils {
	
    /**
     * 取得客户端请求的ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
  
    /**
     * @Description:  传入维度代码取到维度范围
     * @param dimensionCode
     * @return
     * @create: 2010-11-3 下午03:54:53.
     * @author yezhangwei
     * @update logs
     * @throws Exception
   
    @SuppressWarnings("static-access")
	public static String getCurrUserDimensionValue(String dimensionCode)
    {
    	PermissionHelper permissionHelper=new PermissionHelper();
    	List<String> list=permissionHelper.getCurrUserDimensionValue(dimensionCode);
		String dimensionValue="";
		if(list == null){	
			return "NULL";
		}
		if(list.size()==0){
			return "NULL";
		}
		
		dimensionValue=list.toString();
		dimensionValue=dimensionValue.replace("[", "(");
		dimensionValue=dimensionValue.replace("]", ")");
	
		return dimensionValue;
    }  */
    
    /** 读绝对路径下的properties文件
    public static Properties getAbsoluteProperties(String fileName) {
	Properties properties = new Properties();
	try {
	    InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
	    properties.load(reader);
	    if (reader != null) {
		reader.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} 
	return properties;
    }
    
     */
}
