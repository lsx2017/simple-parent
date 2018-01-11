<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>开发平台</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="js/layui/css/layui.css" />
	<link rel="stylesheet" href="css/font-awesome/css/font-awesome.css" />
	<link rel="stylesheet" href="css/index.css" />
  </head>
  
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <a href="#" class="logo">layui后台管理</a>
        <!-- 显示/隐藏菜单 -->
		<a href="javascript:;" class="hideMenu"><i class="fa fa-bars" aria-hidden="true"></i></a>
		<!-- 搜索 -->
		<div class="layui-form component">
	        <select name="modules" lay-verify="required" lay-search="">
				<option value="">直接选择或搜索选择</option>
				<option value="1">layer</option>
				<option value="2">form</option>
				<option value="3">layim</option>
				<option value="4">element</option>
				<option value="5">laytpl</option>
				<option value="6">upload</option>
				<option value="7">laydate</option>
				<option value="8">laypage</option>
				<option value="9">flow</option>
				<option value="10">util</option>
	        </select>
	        <i class="layui-icon">&#xe615;</i>
	    </div>
		<!-- 顶部右侧菜单 -->
		<ul class="layui-nav top_menu">	 
			<li class="layui-nav-item showNotice" id="showNotice" pc>
				<a href="javascript:;"><i class="fa fa-volume-up"></i>&nbsp;<cite>系统公告</cite></a>
			</li>
			<li class="layui-nav-item lockcms" pc>
				<a href="javascript:;"><i class="fa fa-lock">&nbsp;</i><cite>锁屏</cite></a>
			</li>
			<li class="layui-nav-item" pc>
				<a href="javascript:;">
					<img src="images/face.jpg" class="layui-circle" width="35" height="35">
					<cite>请叫我马哥</cite>
				</a>
				<dl class="layui-nav-child">
					<dd><a href="javascript:;" data-url="page/user/userInfo.html"><i class="iconfont icon-zhanghu" data-icon="icon-zhanghu"></i><cite>个人资料</cite></a></dd>
					<dd><a href="javascript:;" data-url="page/user/changePwd.html"><i class="iconfont icon-shezhi1" data-icon="icon-shezhi1"></i><cite>修改密码</cite></a></dd>
					<dd><a href="javascript:;" class="changeSkin"><i class="iconfont icon-huanfu"></i><cite>更换皮肤</cite></a></dd>
					<dd><a href="page/login/login.html" class="signOut"><i class="iconfont icon-loginout"></i><cite>退出</cite></a></dd>
				</dl>
			</li>
		</ul>
    </div>
    <div class="layui-side layui-bg-black">
		<div class="user-photo">
			<a class="img" title="我的头像" ><img src="images/face.jpg"></a>
			<p>你好！<span class="userName">liushx</span>, 欢迎登录</p>
		</div>
		<div class="navBar layui-side-scroll"></div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
		<div class="layui-tab marg0" lay-filter="mainTab" id="top_tabs_box" lay-allowClose="true">
			<ul class="layui-tab-title top_tab" id="top_tabs">
				<li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
			</ul>
			<ul class="layui-nav closeBox">
			  <li class="layui-nav-item">
			    <a href="javascript:;"><i class="fa fa-hand-pointer-o"></i> 页面操作</a>
			    <dl class="layui-nav-child">
				  <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
			      <dd><a href="javascript:;" class="closePageOther"><i class="iconfont icon-prohibit"></i> 关闭其他</a></dd>
			      <dd><a href="javascript:;" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a></dd>
			    </dl>
			  </li>
			</ul>
			<div class="layui-tab-content clildFrame">
				<div class="layui-tab-item layui-show">
					<iframe src="page/main.html"></iframe>
				</div>
			</div>
		</div>        
    </div>
    <div class="layui-footer footer">
		<p>copyright @2017 </p>
    </div>
</div>	
<script type="text/javascript" src="res/js/layui/layui.js" ></script>
<script type="text/javascript" src="res/js/jquery/jquery.min.js" ></script>
<script type="text/javascript" src="res/js/index/leftNav.js" ></script>
<script type="text/javascript" src="res/js/index/mainTab.js" ></script>
<script type="text/javascript" src="res/js/index/index.js" ></script>
</body>
</html>
