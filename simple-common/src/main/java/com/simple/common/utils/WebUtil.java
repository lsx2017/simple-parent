package com.simple.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 处理 J2EE WEB的工具类,如取得cookie等,操作session等
 * @author liushx
 * @date 2016年11月1日
 */
public class WebUtil {
	
	
	/**
     * 
     * @Description:  获取当前请求的URL
     * @param request
     * @return
     * @create: 2010-10-17 上午10:51:49.
     * @author Hejiyuan
     * @update logs
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public static String getUrl(HttpServletRequest request){
		String strUrl = "";
	    Enumeration e = request.getParameterNames();
	    while (e.hasMoreElements()) {
	    	String valueName = (String)e.nextElement();
	    	if (strUrl.equals("")){
	    		strUrl = valueName + "=" + request.getParameter(valueName);
	    	}else{
	    		strUrl = strUrl + "&" + valueName + "=" + request.getParameter(valueName);
	    	}
	    }
	    return strUrl;
	}
	
	/**
	 * 获取访问链接
	 * @param request
	 * @return
	 */
	public static String getAccessUri(HttpServletRequest request) {
		//String httpUri = ;
//		String methodName = request.getParameter("method");
		//if (methodName != null) {
		//	httpUri = httpUri.substring(httpUri.lastIndexOf("/") + 1);
		//	httpUri = httpUri + "?method=" + methodName;
		//}
		String cpath = request.getContextPath();
		String url = request.getRequestURI();
		
		return url.replaceAll(cpath, "");
	}
	

	
	
    /**
     * 判断请求是否是ajax请求
     * 
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return (request.getHeader("x-requested-with") != null && request
                .getHeader("x-requested-with").equalsIgnoreCase(
                        "XMLHttpRequest"));
    }

    /**
     * 根据传入的cookie name取得客户端请求的cookie
     * @param request http的请求
     * @param name cookie名称
     * @return 如果对应名称的cookie不存在,返回null
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }

    /**
     * 根据cookie名称返回cookie的值
     * @param request  http请求
     * @param name cookie名称
     * @return 返回cookie值,值不存在则返回null
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie == null)
            return null;
        return cookie.getValue();
    }

    /**
     * 在http响应中增加cookie返回给客户端
     * 
     * @param response
     *            http响应
     * @param secure
     *            是否安全cookie,安全cookie需要https访问
     * @param domain
     *            cookie的域名
     * 
     * @param path
     *            cookie的路径
     * 
     * @param expiry
     *            cookie的有效期,-1为会话cookie,表示关闭浏览器的时候关闭cookie
     * @param key
     *            cookie的名称
     * 
     * @param value
     *            cookie的值
     * 
     * @return 增加的cookie对象
     */
    public static Cookie addCookie(HttpServletResponse response,
            boolean secure, String domain, String path, int expiry, String key,
            String value) {
        Cookie cook = new Cookie(key, value);
        cook.setSecure(secure);
        cook.setMaxAge(expiry);
        if (domain != null)
            cook.setDomain(domain);
        if (path != null)
            cook.setPath(path);
        response.addCookie(cook);
        return cook;
    }

    /**
     * 删除某个cookie
     * 
     * @param response
     *            http响应
     * @param key
     *            要删除的cookie的名称
     * 
     * @param request
     *            http请求
     */
    public static void delCookie(HttpServletResponse response, String key,
            String domain, String path, HttpServletRequest request) {
        Cookie cooks[] = request.getCookies();
        if (cooks == null)
            return;
        for (int i = 0; i < cooks.length; i++) {
            Cookie cook = cooks[i];
            String name = cook.getName();
            if (name.equals(key)) {
                cook.setMaxAge(0);
                if (domain != null)
                    cook.setDomain(domain);
                if (path != null)
                    cook.setPath(path);
                response.addCookie(cook);
            }
        }

    }
    /**
     * 销毁session
     * @param request
     */
    public static void destroySession(HttpServletRequest request) {
        Enumeration enu = request.getSession().getAttributeNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            request.getSession().removeAttribute(name);
        }
        request.getSession().invalidate();
    }

    /**
     * 根据属性名称取得session的属性,如果session不存在或者属性不存在,则返回null
     * 
     * @param req
     *            http的请求
     * 
     * @param attrName
     *            session的属性名称
     * 
     * @return 返回属性值,找不到则返回null
     */
    public static Object getSessionAttr(HttpServletRequest req, String attrName) {
        HttpSession session = req.getSession(false);
        return (session != null ? session.getAttribute(attrName) : null);
    }

    /**
     * 给session添加属性值
     * @param req  http请求
     * @param attrName  属性名称
     * @param value 属性值
     */
    public static void setSessionAttr(HttpServletRequest req, String attrName, Object value) {
        HttpSession session = req.getSession(false);
        if (session == null)
            return;
        session.setAttribute(attrName, value);
    }
	
	/**
	 * 封装查询参数
	 * @param request
	 * @author liusx
	 * @return 参数Map
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, Object> buildBean(HttpServletRequest request) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		//得到所有参数名
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String paramName = (String)params.nextElement();
			
			//2011-3-16 longweier修改，防止SQL注入
			Object paramValue=null;
            try {
            	//request.getParameter(paramName).replaceAll(".*([';]+|(--)+).*", " ")
                paramValue = java.net.URLDecoder.decode( request.getParameter(paramName),"UTF-8");
                if (paramValue != null && !"pkName".equals(paramName)) {
                    paramsMap.put(paramName, paramValue);
                }
            } catch (UnsupportedEncodingException e) {
            
                e.printStackTrace();
            }
			
		}
		return paramsMap;
	}
	

}
