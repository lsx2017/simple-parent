<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<base href="<%=basePath%>">
    <title>simplejson</title>
    <link rel="stylesheet" type="text/css" href="res/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/res/js/ztree/css/zTreeStyle/zTreeStyle.css">
 
  </head>
  
<body class="easyui-layout">
<div data-options="region:'west',split:true," style="width:250px;">
<textarea id="jsonText" rows="10" cols="80"></textarea>
</div>
<div data-options="region:'center',split:true">
	<div data-options="region:'center',border:false">
			<div id="docsTree" class="ztree"></div>
	</div>
</div>

<div data-options="region:'east',split:false" style="width:250px;">
east
</div>
<script type="text/javascript" src="<%=basePath%>/res/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="res/js/easyui/jquery.easyui.min.js"></script>	
<script type="text/javascript" src="res/js/ztree/js/jquery.ztree.all-3.5.js"></script>	
<script type="text/javascript" src="<%=basePath%>/res/js/layer/layer.js"></script>
<script type="text/javascript">


$(function() {
	var swidth = $(window).width();
	var westWidth = (swidth -280)/2;
	$("body").layout("panel", "west").panel("resize", {width:(westWidth+'px')});

	
	$("#jsonText").width(westWidth-2);
	$("#jsonText").height($("#jsonText").parent().height());
	
	var jsonContent = $("#jsonText").val();
});
</script>


</body>
</html>
