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
<div data-options="region:'west',split:true," style="width:260px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:false,border:false" style="height:38px">
			<div style="text-align: center; height: 26px; line-height: 26px; border-bottom:1px solid #CCCCCC; padding: 4px;">
				<span class="ex-btn">
					<img id="btn_newchild" alt="" title="新增子项" src="res/images/explorer/btn_newchild.png">
				</span>&nbsp;
				<span class="ex-btn">
					<img id="btn_newafter" alt="" title="添加项，前面" src="res/images/explorer/btn_newafter.png">
				</span>	&nbsp;
				<span class="ex-btn">
					<img id="btn_newbefore" alt="" title="添加项，后面" src="res/images/explorer/btn_newbefore.png">
				</span>&nbsp;
				<span class="ex-btn">
					<img id="btn_moveup" alt="" title="下移" src="res/images/explorer/btn_moveup.bmp">
				</span>&nbsp;					
				<span class="ex-btn">
					<img id="btn_movedown" alt="" title="上移" src="res/images/explorer/btn_movedown.bmp">
				</span>&nbsp;

			</div>
		</div>
		<div data-options="region:'center',border:false">
			<div id="docsTree" class="ztree"></div>
		</div>
	</div>
 </div>
<div data-options="region:'center'" data-options="border:false">
	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
        <div title="Index" style="padding:20px;overflow:hidden;"> 
            <div style="margin-top:20px;"></div>
        </div>
    </div>
</div>
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
