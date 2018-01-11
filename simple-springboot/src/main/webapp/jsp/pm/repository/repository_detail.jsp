<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>Explorer Edit</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/res/js/easyui/themes/default/easyui.css">
	  <link rel="stylesheet" type="text/css" href="<%=basePath%>/res/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/res/js/editormd/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
<style type="text/css">
img {display:block; margin:0 auto;}
*{
	/*font-size: 14px;*/
	line-height: 28px;
}
strong{
	color:red;
}

</style> 
  </head>
  
<body class="easyui-layout">
<div data-options="region:'north',split:false," style="height:45px;">
	<div class="easyui-panel" data-options="border:false," style="padding:5px;">

		<a href="javascript: location.reload();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
		<a href="<%=basePath%>/repository/edit?docsId=${exDocs.docsId}" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" target="_blank">编辑</a>
	</div>
</div>
<div data-options="region:'east',split:false," style="width:250px;">
	<div id="custom-toc-container"></div>
</div>
<div data-options="region:'center'" style="overflow-x: hidden;">
	<input type="hidden" id="docsId" name="docsId" value="${exDocs.docsId}">
	<input type="hidden" id="parentId" name="parentId" value="${exDocs.parentId}">
	<input type="hidden" id="docsName" name="docsName" value="${exDocs.docsName}">
	<div style="text-align: center; margin:0 auto; ">
		<div id="test-editormd" style="width:100%; margin:0 auto;text-align: left;">
			<textarea id="content"  name="content" style="display:none;">${exDocs.content}</textarea>
		</div>
	</div>
</div>

<script type="text/javascript" src="<%=basePath%>/res/js/easyui/jquery.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/res/js/editormd/marked.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/editormd/editormd.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
$(function() {

	var testEditormdView;
	testEditormdView = editormd.markdownToHTML("test-editormd", {
		width   : "100%",
		markdown        : $("#content").text(),
		//htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
		htmlDecode      : "style,script,iframe",  // you can filter tags decode
		//toc             : false,
		tocm            : true,    // Using [TOCM]
		tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
		//gfm             : false,
		//tocDropdown     : true,
		// markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
		emoji           : true,
		taskList        : true,
		tex             : true,  // 默认不解析
		flowChart       : true,  // 默认不解析
		sequenceDiagram : true,  // 默认不解析
	});

});
</script>

</body>
</html>
