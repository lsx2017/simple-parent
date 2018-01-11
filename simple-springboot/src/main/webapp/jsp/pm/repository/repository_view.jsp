<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
	<base href="<%=basePath%>">
    <title>知识库</title>
    <link rel="stylesheet" type="text/css" href="res/js/editormd/css/editormd.css" />
    <link rel="stylesheet" type="text/css" href="res/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="res/js/ztree/css/zTreeStyle/zTreeStyle.css">
<style type="text/css">
.ex-tools {
	text-align: center; height: 26px; line-height: 26px; border-bottom:1px solid #CCCCCC; margin-top: 10px !important;
}
.ex-btn img{
	cursor: pointer;
}
</style>
</head>
<body class="easyui-layout">
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
    background: url(res/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
    line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <!-- 
    <span style="float:right; padding-right:20px;" class="head">欢迎 疯狂秀才 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
     -->
    <span style="padding-left:10px; font-size: 16px; ">
    	<img src="res/images/blocks.gif" width="20" height="20" align="absmiddle" /> Markdown在线笔记
    </span>
</div>
<div data-options="region:'west',split:true," style="width:250px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',split:false,border:false" style="height:38px">
			<div class="ex-tools">
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
<div data-options="region:'center'">
	<div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    	<div title="Index" style="padding:20px;overflow:hidden;"> 
         	<iframe scrolling="auto" frameborder="0" src="" style="width:100%; height:100%;overflow: hidden;"></iframe>
    	</div>
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
<script type="text/javascript" src="res/js/ztree/js/jquery.ztree.all-3.5.js"></script>	

<script type="text/javascript" src="<%=basePath%>/res/js/layer/layer.js"></script>
<script type="text/javascript" src="res/js/explorer/repository_view.js"></script>
<script type="text/javascript" src="res/js/hello.js"></script>
</body>
</html>
