<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  	<head>
		<title>事项</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
    	<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    	<link href="resources/styles/edit.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" href="resources/scripts/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    	<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>	
		
		<script type="text/javascript" src="resources/scripts/jquery/jquery.form.js"></script>	
	    <script type="text/javascript" src="resources/scripts/validate/jquery.validate.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>	

	    <link href="resources/scripts/swfupload/css/swfupload.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="resources/scripts/ztree/js/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="resources/scripts/loadmask/jquery.loadmask.js"></script>
	    <script type="text/javascript" src="jsp/workflow/project/projectItem_edit.js"></script>
<style>
body {
	border: 0;
	margin: 0 auto;
}

</style>
  	</head>
  
  <body>
<div id="contentborder" align="center" style="height:450px; overflow-y:auto;">
  <form action="project.shtml" id="editForm" name="editForm" method="post">
	<input type="hidden" name="method" value="saveItem" /> 
	<input type="hidden" id="id" name="id" value="${projectItem.id}" />
	<input type="hidden" id="projectId" name="projectId" value="${projectId }" /> 
	<input type="hidden" id="role" name="role" value="${projectItem.role }" />
	<table id="tb_form"  border=0 cellspacing=0 cellpadding=0 width=100% class="edit-table">
		<tr> 
		    <th><b style="color: red;">*</b>事项名称：</th>
		    <td colspan="5">
				<input type="text" id="name" name="name" value="${projectItem.name}" 
	    		class="must-input" maxlength="70" data-options="required:true" style="width:98% !important;"/>
		    </td>
    	</tr>
    	<tr>
    		<th><b style="color: red;">*</b>阶段：</th>
		    <td align="left" colspan="2">
		    	<select name="step" id="step" class="easyui-combobox mustInput  easyui-validatebox" editable="false" style="width:98% !important;" data-options="panelHeight:null,width:200">
		    		<option value="">--请选择--</option>
		    		<c:forEach var="s" items="${itemSteps }">
						<option value="${s.id}" <c:if test="${s.id==projectItem.step }">selected="selected"</c:if>>${s.name }</option>
					</c:forEach>
				</select>
				<div style="display: inline;" name="err"></div>
		    </td>
		    <th><b style="color: red;">*</b>角色：</th>
		    <td align="left" colspan="2">
		    	<select name="roleList" id="roleList" class="easyui-combobox mustInput easyui-validatebox" style="width:95% !important;" data-options="panelHeight:160,required:true" multiple="true" editable="false" >
		    		<%-- <option value="">-----请选择-----</option> --%>
		    		<c:forEach var="r" items="${roles }" varStatus="status">
		    			<c:choose>
		    				 <c:when test="${selectRole != null }">
		    				 	<option value="${r.id}" 
		    				 		<c:forEach var="sr" items="${selectRole }">
										<c:if test="${r.id==sr.id}">selected="selected"</c:if>		    					
		    						</c:forEach>
		    					>${r.name}</option>
		    				</c:when>
		    				<c:otherwise>
		    					<option value="${r.id}">${r.name}</option>
		    				</c:otherwise>
		    			</c:choose>
		    			<%-- <option value="${r.id}">${r.name}</option> --%>
					</c:forEach>
				</select>
				<div style="display: inline;" name="err"></div>
		    </td>
    	</tr>
    	<tr>
    		<th><b style="color: red;">*</b>排序：</th>
		    <td>
				<input type="text" class="easyui-numberbox must-input" style="width:65% !important;"
						id="seq" name="seq" required="required" 
						value="${projectItem.seq }" /><div style="display: inline;" name="err"></div>
		    </td>
		    <th><b style="color: red;">*</b>天数：</th>
		    <td>
				<input type="text" class="easyui-numberbox must-input" style="width:65% !important;"
						id="day" name="day" required="required" precision="1"
						value="${projectItem.day }" />&nbsp;天&nbsp;<div style="display: inline;" name="err"></div>
		    </td>
		    <th><b style="color: red;">*</b>工时：</th>
		    <td>
				<input type="text" class="easyui-numberbox must-input" style="width:65% !important;"
						id="hourt" name="hourt" required="required" precision="1"
						value="${projectItem.hourt}" />&nbsp;小时&nbsp;<div style="display: inline;" name="err"></div>
		    </td>
    	</tr>
    	<tr> 
			<th>描述：</th>
			 <td colspan="5">
				<textarea name="descript" id="descript" rows="3" cols="80" style="width:98% !important;" onpropertychange="if(value.length>2000) value=value.substr(0,2000)">${projectItem.descript}</textarea>&nbsp;
			</td>
		</tr>
    	<tr>
	  		<th>说明：</th>
		    <td colspan="5" style="line-height:0; padding: 5px;" >
		    	<textarea name="note" id="note" rows="3" cols="80" style="width:98% !important;" onpropertychange="if(value.length>2000) value=value.substr(0,2000)">${projectItem.note}</textarea>&nbsp;
		    </td>
  		</tr>
	</table>
	<%@include file="/jsp/inst/demand/imageViewer.jsp"%>	
	<div id="toolbar">
		<input type="submit" id="subBtn" name="subBtn" style="display: none;"> 
		<a href="javascript:void(0);" class="easyui-linkbutton" id="btnSave" iconCls="icon-save">保存</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" id="closeBtn" iconCls="icon-cancel">关闭</a>
	</div>
  </form>
</div>
  </body>
</html>
