package com.simple.common.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.common.utils.ReflectionUtils;



/**
 * 封装了基础增删改查的action
 * @author mengyang
 * @param <T>
 */
public abstract class BaseController<T, PK extends Serializable> {
	
//	protected Logger logger = Logger.getLogger(getClass());
	
	protected Class<T> entityClass;

	protected static final String modelAttributeName = "_model_attribute_";
	
	private String classRequestMapping;
	
	public BaseController() {
		
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
		
		String[] mappings = this.getClass().getAnnotation(RequestMapping.class).value();
		if(mappings != null && mappings.length > 0){
			this.classRequestMapping = mappings[0];
		}
	}
	
	/**
	 * 获取jsp存放位置，默认位置为请求uri
	 * @param method
	 * @return
	 */
	protected String getViewName(String method){
		
		return classRequestMapping + "/" + entityClass.getSimpleName().toLowerCase() + "_" + method;
	}
	
	/**
	 * 进入模块列表页面
	 * 获取才菜单下用户操作权限并跳转到列表页面
	 * @param menuId 菜单ID
	 * @return
	 */
	@RequestMapping("view")
	public String view(@RequestParam Long menuId, ModelMap model, HttpServletRequest request) {
		
		return getViewName("view");
	}
	
//	
//	protected void list(HttpServletRequest request,  ModelMap model) {
//		User user = WebUtil.getCurUser(request);
//		String menuId = request.getParameter("menuId");
//		List<Map<String, Object>> operList = resService.queryListOperListByUserId(user.getUserId(), Long.valueOf(menuId));
//		//model.put("menuId", menuId);
//		model.put("operList", operList);
//		
//	}

}
