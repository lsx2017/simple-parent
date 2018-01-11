<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>功能正在建设中</title>
    <link rel="stylesheet" type="text/css" href="res/js/bootstrap/css/bootstrap.css"><!--bootstrap框架-->
    <link rel="stylesheet" type="text/css" href="res/css/font-awesome.min.css"/><!--字体-->
    <link rel="stylesheet" type="text/css" href="res/css/oa-golbal.css"><!--通用样式-->
    <link rel="stylesheet" type="text/css" href="res/css/bot-modify.css"><!--bootstrap修改样式-->
	<link rel="stylesheet" type="text/css" href="res/css/erroe-page.css"><!--错误页面样式-->
	<script type="text/javascript" src="res/js/jquery/jquery.min.js"></script>
  </head>
  
<body>
	<div class="error-con" style="width:600px;">
        <div class="fleft">
        	<img src="res/images/builder.png" width="120">
        </div>
        <div class="fleft mar_L20">
        	<p  class="err-fon7">建设中</p>
        	<p>很抱歉，该页正在建设中</p>
        </div>
        <div class=" clear"></div>
        <!--end 500-->
    </div>
</body>
</html>
