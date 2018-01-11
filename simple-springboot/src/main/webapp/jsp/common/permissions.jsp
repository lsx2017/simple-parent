<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>无权限</title>
    <link rel="stylesheet" type="text/css" href="res/js/bootstrap/css/bootstrap.css"><!--bootstrap框架-->
    <link rel="stylesheet" type="text/css" href="res/css/font-awesome.min.css"/><!--字体-->
    <link rel="stylesheet" type="text/css" href="res/css/oa-golbal.css"><!--通用样式-->
    <link rel="stylesheet" type="text/css" href="res/css/bot-modify.css"><!--bootstrap修改样式-->
	<link rel="stylesheet" type="text/css" href="res/css/erroe-page.css"><!--错误页面样式-->

  </head>
  
  <body>
	<div class="error-con" style="width:500px;">
    	<div class="fleft">
        	<img src="images/wqx-pic.jpg">
        </div>
        <div class="fleft mar_L20 mar_T35">
        	<p class="err-fon1">对不起，当前反问无效</p>
            <span class="err-fon2"><a href="#">请尝试重新登录</a></span>
        </div>
        <div class=" clear"></div>
    	<!--end 无效登录-->
        
    </div>
  </body>
</html>
