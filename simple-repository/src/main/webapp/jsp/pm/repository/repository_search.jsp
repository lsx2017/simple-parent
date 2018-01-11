<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<base href="<%=basePath%>">
    <title>editor.md</title>
    <link rel="stylesheet" type="text/css" href="res/js/editormd/css/editormd.css" />
    <link rel="stylesheet" type="text/css" href="res/js/easyui/themes/default/easyui.css">
	<script type="text/javascript" src="res/js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="res/js/easyui/jquery.easyui.min.js"></script>	
</head>
<body class="easyui-layout">

<script src="res/js/editormd/editormd.js"></script>
<script type="text/javascript" src="res/js/explorer/repository_list.js"></script>
<script type="text/javascript">
    var testEditor;
    /**
    $("#fpTabs").tabs({
		onSelect:function(title){
			$.ajax({
		    	url:"repository/openFile",
		     	type:"post",
		     	dataType:'json',
		     	data:{'fileName':title},
		      	success:function(result){

		      	}
			});
		}
		
	});
		*/
$(function() {

});
</script>
</body>
</html>
