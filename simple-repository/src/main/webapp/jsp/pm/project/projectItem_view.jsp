<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html id="notify_view">
	<head>
		<title>项目事项关联</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>		
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" href="resources/scripts/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="resources/scripts/ztree/js/jquery.ztree.core-3.5.js"></script>	    
		<script type="text/javascript" src="jsp/workflow/project/projectItem_view.js"></script>
	</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'项目',split:true" style="width:180px">
	<div id="div_projectTree" style="height:auto; width:250px; overflow:auto;">    
		<ul id="ui_projectTree" class="ztree"></ul>    
	</div> 
</div>
<div data-options="region:'center'">
	<table id="list_data"></table>
	<input type="hidden" id="projectId" name="projectId" value="" />
	<input type="hidden" id="cityId" name="cityId" value="" />
	<input type="hidden" id="method" name="method" value="${method}" />
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px; text-align: right;">
			<%@ include file="/jsp/common/button.jsp"%>
			<%-- <input type="text" name="itemIds" id="itemIds" value=""/>
			<input type="text" name="itemNames" id="itemNames" class="mustinput" value="" readonly="readonly" style="width: 500px !important;"/>
			<a style="float: right;" href="javascript:selectItem();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-detail'">关联事项</a>--%> 
		</div>
	</div>
<div id="win" class="easyui-window"  style="width:500px;height:350px;">
	<iframe id="contentFrame" src="" width=100% height=100% marginwidth=0 framespacing=0 marginheight=0 frameborder=no scrolling=no></iframe>
</div> 
</div>
</body>
</html>