<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css" href="res/css/login.css">
    <link href="res/js/supersized/css/supersized.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="res/js/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="res/js/supersized/supersized.3.2.7.min.js"></script>
	<script type="text/javascript" src="res/js/supersized/supersized-init.js"></script>  

  </head>
  
  <body>
    <div class="loginbox">
		<div class="title">
			<div class="logo"><i class="icon-cloud"></i>KodExplorer</div>
			<div class="info">————资源信息管理系统</div>
		</div>
		<div class="loginform">
			<div class="inputs">
				<div><span>用户名：</span><input id="username" name="name" placeholder="用户名" required="" type="text"> </div>
				<div><span>密 &nbsp;&nbsp;码：</span><input id="password" name="password" placeholder="密码" required="" type="password"></div>
        	</div>
			<div class="actions">
				
				<input class="checkbox" name="rember_password" id="rm" checked="checked" type="checkbox">
				<label for="rm">记住密码</label>	
                <input id="submit" value="登陆 " type="submit">			
			</div>
			<div class="guest">&nbsp;</div>
		</div>
	</div>
    
	<div class="common_footer">
    	Powered by KodExplorer v3.12 | Copyright © <a href="http://simple.com/" target="_blank">kalcaddle.com</a> All rights reserved.
    </div>
  </body>
</html>
