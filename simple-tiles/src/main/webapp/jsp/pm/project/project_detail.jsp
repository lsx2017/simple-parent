<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>项目信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
    	<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    	<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    	<link href="resources/styles/edit.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>	
		<script type="text/javascript" src="resources/scripts/jquery/jquery.form.js"></script>	
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>	
	    <script type="text/javascript" src="jsp/workflow/project/project_edit.js"></script>
	</head>


<body>
<div id="contentborder" align="center" style="height:450px; overflow-y:auto;">
	<table id="tb_form"  border=0 cellspacing=0 cellpadding=0 width=100% class="edit-table">
		<tr>
			<th>项目名称</th>
			<td><label>${project.name }</label></td>
			<th>项目编码</th>
			<td><label>${project.code }</label></td>
		</tr>
		<tr>
			<th>产品</th>
			<td><label>${project.productId }</label></td>
			<th>所属区域</th>
			<td><label>${project.regionId }</label></td>
		</tr>
		<tr>
			<th>分类</th>
			<td><label>${project.typeId }</label></td>
			<th>重要性</th>
			<td><label>${project.importance }</label></td>
		</tr>
		<tr>
			<th>项目状态</th>
			<td><label>${project.projectStatus }</label></td>
			<th>阶段</th>
			<td><label>${project.phase }</label></td>
		</tr>
		<tr>
			<th>开始时间</th>
			<td><label>${project.startDate }</label></td>
			<th>结束时间</th>
			<td><label>${project.endDate }</label></td>
		</tr>
		<tr>
			<th>上线时间</th>
			<td><label>${project.publishTime }</label></td>
			<th>初验时间</th>
			<td><label>${project.firstTestTime }</label></td>
			<%-- <th>终验时间</th>
			<td><label>${project.lastTestTime }</label></td> --%>
		</tr>
		<tr>
			<th>项目回款</th>
			<td><label>${project.projectMoney }</label></td>
			<th>预估毛利</th>
			<td><label>${project.profit }</label></td>
		</tr>
		<tr>
			<th>项目客户</th>
			<td colspan="3"><table border=0 cellspacing=0 cellpadding=0 width=100% class="edit-table">
				<tr>
					<th>序号</th>
					<th>客户姓名</th>
					<th>客户所属单位</th>
					<th>客户所属部门</th>
					<th>职务</th>
					<th>联系电话</th>
					<th>电子邮箱</th>
				</tr>
				<c:if test="${!empty constomers }">
					<c:forEach var="c" items="${constomers }" varStatus="cs">
						<tr>
							<td>${cs.index + 1 }</td>
							<td>${c.name }</td>
							<td>${c.unitName }</td>
							<td>${c.deptName }</td>
							<td>${c.postName }</td>
							<td>${c.phone }</td>
							<td>${c.email }</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty constomers }">
					<tr>
						<td colspan="7">暂未关联客户</td>
					</tr>
				</c:if>
			</table></td>
		</tr>
	</table>
</div>
</body>
</html>
