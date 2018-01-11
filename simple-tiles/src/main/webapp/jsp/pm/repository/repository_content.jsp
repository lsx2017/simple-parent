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
<div data-options="region:'center'" data-options="border:false">
	<div id="tt" class="easyui-tabs" data-options="tabPosition:'right', fit:true, border:false">
		<c:forEach var="fp" items="${fileList}">
			<div title="${fp.fileName}">
				<iframe id="fileContent" name="fileContent" scrolling="auto" frameborder="0" src="" style="width:100%; height:100%;overflow: hidden;"></iframe>
			</div>
		</c:forEach>
	</div>
</div>
<script src="res/js/editormd/editormd.js"></script>
<script type="text/javascript" src="res/js/explorer/repository_view.js"></script>
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
