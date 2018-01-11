<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title></title>
	<link rel="stylesheet" href="res/js/layui/css/layui.css"/>
	<link rel="stylesheet" href="res/css/font-awesome/css/font-awesome.css" />

  </head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search search-form">
		<div class="layui-inline">
			用户名：
			<div class="layui-input-inline">
				<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
			</div>
			密码：
			<div class="layui-input-inline">
				<input type="text" value="" placeholder="请输入关键字" class="layui-input search_input">
			</div>
			<a class="layui-btn search_btn">查询</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-normal newsAdd_btn">添加文章</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn recommend" style="background-color:#5FB878">推荐文章</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn audit_btn">审核文章</a>
		</div>
		<div class="layui-inline">
			<a class="layui-btn layui-btn-danger batchDel">批量删除</a>
		</div>
	</blockquote>
	<div class="layui-form news_list">
		<table class="layui-table">
			<colgroup>
				<col width="50">
				<col>
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="9%">
				<col width="15%">
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
				<th style="text-align:left;">文章标题</th>
				<th>发布人</th>
				<th>审核状态</th>
				<th>浏览权限</th>
				<th>是否展示</th>
				<th>发布时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="gridBody" class="news_content"></tbody>
		</table>
		<div id="page"></div>
	</div>

</body>
<script type="text/javascript" src="res/js/layui/layui.js" ></script>
<script type="text/javascript" src="res/js/jquery/jquery.min.js" ></script>
<script type="text/javascript" src="newsList.js" ></script>
</html>
