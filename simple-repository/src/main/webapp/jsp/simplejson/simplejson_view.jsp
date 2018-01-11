<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<base href="<%=basePath%>">
    <title>SimpleJsonTools</title>
    <link rel="stylesheet" type="text/css" href="res/js/easyui/themes/default/easyui.css">

</head>
<body class="easyui-layout">
<div region="north" split="true" border="false" style="overflow: hidden; padding:5px;">
	<a href="javascript:;" class="easyui-linkbutton" id="newLabelBtn" data-options="plain:true">新标签</a>
	<a href="#" class="easyui-menubutton" data-options="iconCls:'icon-edit'">格式化JSON字符串</a>
	<a href="#" class="easyui-menubutton" data-options="iconCls:'icon-help'">清空</a>
	<a href="#" class="easyui-menubutton" data-options="">关于</a>
</div>
<div data-options="region:'center'">
	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    	
	</div>	
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; "></div>
<div id="mm" class="easyui-menu" style="width:150px;">
	<div id="mm-tabupdate">刷新</div>
	<div class="menu-sep"></div>
	<div id="mm-tabclose">关闭</div>
	<div id="mm-tabcloseall">全部关闭</div>
	<div id="mm-tabcloseother">除此之外全部关闭</div>
	<div class="menu-sep"></div>
	<div id="mm-tabcloseright">当前页右侧全部关闭</div>
	<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	<div class="menu-sep"></div>
	<div id="mm-exit">退出</div>
</div>
	
	
<script type="text/javascript" src="res/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="res/js/easyui/jquery.easyui.min.js"></script>	


<script type="text/javascript" src="res/js/simplejson/simplejson_view.js"></script>

</body>
</html>
