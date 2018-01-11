<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>404</title>
    <link rel="stylesheet" type="text/css" href="res/js/bootstrap/css/bootstrap.css"><!--bootstrap框架-->
    <link rel="stylesheet" type="text/css" href="res/css/font-awesome.min.css"/><!--字体-->
    <link rel="stylesheet" type="text/css" href="res/css/oa-golbal.css"><!--通用样式-->
    <link rel="stylesheet" type="text/css" href="res/css/bot-modify.css"><!--bootstrap修改样式-->
	<link rel="stylesheet" type="text/css" href="res/css/erroe-page.css"><!--错误页面样式-->
	<script type="text/javascript" src="res/js/jquery/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#backIndex").click(function(){
		top.location.href="system/admin/index";
	});
	
});
</script>	
</head>
  
<body>
	<div class="error-con" style="width:600px;">
        <div class="fleft">
        	<img src="res/images/400pic.jpg">
        </div>
        <div class="fleft mar_L20 mar_T35">
        	<p class="err-fon1">很抱歉，您查找的页面不存在</p>
            <p class="err-fon5">您可以：</p>
            <span class="err-fon4">1、检查访问的网址是否正确</span><br/>
            <span class="err-fon4">2、去其他地方逛逛：<a id="backIndex" href="javascript:;" class="err-fon3">返回首页</a></span>
        </div>
        <div class=" clear"></div>
        <!--end 404-->
	</div>
</body>
</html>
