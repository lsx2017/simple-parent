package com.simple.common.utils;


/**
 * 系统全局常量
 * @author liushx
 * @date 2016年9月16日
 */
public class Constants {
	
	/** 登录链接 */
	public static final String LOGIN_URL = "system/login";
	/** 文件上传ROOT路径 */
	public static final String UPLOAD_ROOT_PATH = "";
	/** 当前用户 */
	public static final String SESSION_USER = "loginUser";
	
	/** 当前组织 */
	public static final String SESSION_ORG = "loginOrg";
	
	/** 当前用户登录ID */
	public static final String SESSION_USER_LOGINID = "loginId";
	
	/** 当前用户权限资源  */
	public static final String SESSION_RES = "session_res";
	
	/** 当前用户子系统  */
	public static final String SESSION_SUBSYS = "session_subsys";
	/** 当前用户目录菜单 */
	public static final String SESSION_DIR_MENU = "session_dir_menu";
	
	/** 资源为NULL,请重新登录 */
	public static final int AUTH_RESULT_NO_RES = 1;
	/** 无URI访问权限 */
	public static final int AUTH_RESULT_NO_PERMISSION = 2;
	/** 有URI访问权限 */
	public static final int AUTH_RESULT_HAS_PERMISSION = 3;
	/**
	//访问的URI是功能，并且无功能权限
	public static final int AUTH_RESULT_NO_FUNCTION_PERMISSION=3;
	//访问的URI是功能，并且有功能权限
	public static final int AUTH_RESULT_HAS_FUNCTION_PERMISSION=4;
	//访问的URI非菜单或者功能
	public static final int AUTH_RESULT_IS_NOT_FUNC_MENU=5;
	public static final int AUTH_RESULT_MENU_IS_EMPTY=6;
	public static final int AUTH_RESULT_FUNCTION_IS_EMPTY=7;
	*/
	
	/**
	 * 默认组织构顶层,组织机构
	 */
	public static Long ROOT_ORG_ID = 100L;
	
	/***********************************数据字典***********************************/
	/** 资产类型 */
	public static final Long ASSETS_TYPES = 2L;
	/** 新闻公共栏目 */
	public static final Long DICT_NEWS = 100L;
	/** 表单字段类型 */
	public static final Long FIELD_TYPE = 200L;	
	/** 表单控件类型 */
	public static final Long COMP_TYPE = 350L;	
	/** 表单模板 */
	public static final Long FORMS_TEMPLATE = 366L;		
	/** 最高学历  */
	public static final Long HIGHEST_DEGREE = 221L;
	
	/** 任务紧急程度 */
	public static final Long DICT_PLAN_LEVEL = 259L;
	
	/** 人事变动类型 */
	public static final Long USER_CHANGE = 376L;
	/** 同事圈-分析类型 */
	public static final Long KDG_TYPE = 399L;
	
	/** 个人消费类型 */
	public static final Long SPEND_TYPE = 389L;
	/** 重要程度 */
	public static final Long NOTICE_LEVEL = 395L; 
	/** 用户类型 */
	public static final Long USER_TYPE = 334L;
	
	
	
	/** 公司制度类型 */
	public static final String DICT_COMPYRULES = "compyRules";	
	/** 表单模板 */
	public static final String FORMS_TEMPLATE_CODE = "formsTemplate";	
	
	
	/** 状态-是 */
	public static final String YES = "1";
	/** 状态-否 */
	public static final String NO  = "0";
	
	/** 工作台-通知公告 */
	public static final Long NOTICES_TYPE_PORTAL =3L;
	
	/************************附件类型*****************************/
	/** 任务计划附件 */
	public static final String ATTR_TASKPLATS = "taskplats";
	/** 通知公告附件 */
	public static final String ATTR_NOTICES = "notices";
	/** 知识中心-知识分享 */
	public static final String ATTR_KNOWLEDGE = "knowledge";
	/** 知识中心-公司制度 */
	public static final String ATTR_REGULATIONS = "regulations";
	/** 用户档案附件*/
	public static final String ATTR_USERS = "users";	
	
	
	
	
	/***********************工作流*****************************/
	
	/** 字符串分隔符 */
	public static final String SEPARATOR = "`";
	
	/**项目定义用户类型*/
	public static final int  USER_BIZ_TYPE=1; 
	
	/**项目定义部门类型*/
	public static final int  ORG_BIZ_TYPE=2; 

	
	
}
