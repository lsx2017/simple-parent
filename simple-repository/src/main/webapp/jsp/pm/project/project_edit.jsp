<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>项目编辑</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
    	<link href="resources/styles/edit.css" rel="stylesheet" type="text/css" />
    	<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
    	<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
    	<link rel="stylesheet" type="text/css" href="designer/js/extjs4.2/resources/css/ext-all.css"/>
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>	
		<script type="text/javascript" src="resources/scripts/jquery/jquery.form.js"></script>	
	    <script type="text/javascript" src="resources/scripts/validate/jquery.validate.js"></script>	
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>	
	    <script type="text/javascript" src="designer/js/extjs4.2/ext-all-debug.js"></script> 
   		<script type="text/javascript" src="designer/js/extjs4.2/locale/ext-lang-zh_CN.js"></script> 
	    <script type="text/javascript" src="jsp/workflow/project/project_edit.js"></script>
	    <script type="text/javascript" src="jsp/workflow/project/userselect.js"></script>	
	</head>
<style type="text/css">
.toolbar-custom{background-image: url("/styles/default/images/title_bg.png"); border-style: none;}

.toolbar-check{
	border: 0; 
	background-color:#F2F7FB;
	text-align:left !important;
}

.input-radio{
	padding:0 !important;	
	border:0 !important;
	width:auto !important;
}

</style>


<body  class="easyui-layout" style="width:100%;height:100%; overflow-y: scroll !important;">
<form action="project.shtml" id="projecteditFrom">

<div data-options="region:'center',split:false,border:0" >

<input type="hidden"  name="method" value="save"/>
<input type="hidden" name="id" value="${project.id}"/>
<input type="hidden" id="defaultregionid" name="defaultregionid" value="${project.regionId}"/>
<input type="hidden" id="defaultproductid" name="defaultproductid" value="${project.productId}"/>
<input type="hidden" id="userIds" name="userIds" value="${ userIds}"/>
<input type="hidden" id="projectmember" name="projectmember" value='${ projectmember}'/>
<input type="hidden" id="customers" name="customers" value="${project.customers }" />
<input type="hidden" id="tempImportance" name="tempImportance" <c:if test="${project.importance == null}"> value="0" </c:if><c:if test="${project.importance != null}"> value="1" </c:if>  />
<div id="flowDetailTab" style="height: 360px;"> 
	<div title="项目详情">
	  <table border=0 cellspacing=0 cellpadding=0 width=100% class="edit-table" height=300>
		<tr>
			<th><b style="color: red;">*</b>项目名称</th>
			<td><input type="text" name="name" id="name" maxlength="30" value="${project.name}" class="must-input" style="width:250px;"/><div style="display: inline;" name="err" ></div></td>
		</tr>
		<tr>
			<th><b style="color: red;"></b>项目编码</th>
			<td><input type="text" name="code" id="code" maxlength="15" value="${project.code}" class="must-input"/><div style="display: inline;" name="err"></div></td>
		</tr>
		<tr>
			<th><b style="color: red;">*</b>产品</th>
			<td>
			<select name="productId" id="productId" class="easyui-combobox mustInput easyui-validatebox" data-options="panelHeight:null,width:150,required:true"  editable="false" >
		    <option value="">-----请选择-----</option>
		    <c:forEach var="p" items="${productList}">
			<option value="${p.id}" <c:if test="${p.id==project.productId}">selected="selected"</c:if>>${p.name}</option>
			</c:forEach>
				</select>
				<div style="display: inline;" name="err"></div>
			<!--  	<input type="text" name="typeId" id="typeId" maxlength="125" class="must-input input-url" value="${project.typeId}">  -->
			</td>
		</tr>
		<tr>
			<th><b style="color: red;">*</b>所属区域</th>
			<td>
			<select name="regionId"style="width:100px;" id="regionId" class="easyui-combobox mustInput easyui-validatebox" data-options="valueField:'id',textField:'text'"  editable="false" >
		    <option value="">-----请选择-----</option>
		    <c:forEach var="p" items="${regionList}">
			<option value="${p.id}" <c:if test="${p.id==project.regionId}">selected="selected"</c:if>>${p.name}</option>
			</c:forEach>
			</select>
			<div style="display: inline;" name="err"></div>
			</td>
		</tr>
 		<tr>
			<th><b style="color: red;"></b>分类</th>
			<td>
			<select name="typeId" id="typeId" class="easyui-combobox mustInput easyui-validatebox" data-options="panelHeight:null,width:150,required:true"  editable="false" >
		    <option value="">-----请选择-----</option>
		    <c:forEach var="p" items="${projectTypeList}">
			<option value="${p.id}" <c:if test="${p.id==project.typeId}">selected="selected"</c:if>>${p.name}</option>
			</c:forEach>
				</select>
				<div style="display: inline;" name="err"></div>
			</td>
		</tr>
		<tr>
			<th align="right"><b style="color: red;">*</b>重要性：</th>
			<td align="left">
				<c:forEach var="p" items="${importanceList}">
				    <input type='radio' name='importance' value="${p.id}" <c:if test="${p.id==project.importance}">checked="checked"</c:if>/>${p.name}&nbsp;
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th><b style="color: red;"></b>项目状态</th>
			<td>
			<select name="projectStatus" id="projectStatus" class="easyui-combobox mustInput easyui-validatebox" data-options="panelHeight:null,width:150,required:true"  editable="false" >
			    <option value="">-----请选择-----</option>
			    <c:forEach var="ps" items="${projectStatusList}">
			    
					<option value="${ps.id}" <c:if test="${ps.id==project.projectStatus}">selected="selected"</c:if>>${ps.name}</option>
				</c:forEach>
			</select>
				<div style="display: inline;" name="err"></div>
			</td>
		</tr>
		<tr>		
			<th ><b style="color: red;"></b>开始时间：</th>
			<td >
				<input id="startDate" name="startDate" type="text" class="easyui-datebox"  closeText="Close" editable="false" value="${project.startDate}"></input>
			</td>
		</tr> 		
 		<tr>		
			<th ><b style="color: red;"></b>结束时间：</th>
			<td >
				<input id="endDate" name="endDate" type="text" class="easyui-datebox"  closeText="Close" editable="false"value="${project.endDate}"></input>
			</td>
		</tr>
		<tr>		
			<th ><b style="color: red;"></b>合同约定上线时间：</th>
			<td >
				<input id="publishTime" name="publishTime" type="text" class="easyui-datebox"  closeText="Close" editable="false" value="${project.publishTime}"></input>
			</td>
		</tr> 		
 		<tr>		
			<th ><b style="color: red;"></b>合同约定初验时间：</th>
			<td >
				<input id="firstTestTime" name="firstTestTime" type="text" class="easyui-datebox"  closeText="Close" editable="false"value="${project.firstTestTime}"></input>
			</td>
		</tr>
		<tr>		
			<th ><b style="color: red;"></b>合同约定终验时间：</th>
			<td >
				<input id="lastTestTime" name="lastTestTime" type="text" class="easyui-datebox"  closeText="Close" editable="false"value="${project.lastTestTime}"></input>
			</td>
		</tr>
		<tr>		
			<th ><b style="color: red;"></b>实际上线时间：</th>
			<td >
				<input id="actualPublishTime" name="actualPublishTime" type="text" class="easyui-datebox"  closeText="Close" editable="false" value="${project.actualPublishTime}"></input>
			</td>
		</tr> 		
 		<tr>		
			<th ><b style="color: red;"></b>实际初验时间：</th>
			<td >
				<input id="actualFirstTestTime" name="actualFirstTestTime" type="text" class="easyui-datebox"  closeText="Close" editable="false"value="${project.actualFirstTestTime}"></input>
			</td>
		</tr>
		<tr>		
			<th ><b style="color: red;"></b>实际终验时间：</th>
			<td >
				<input id="actualLastTestTime" name="actualLastTestTime" type="text" class="easyui-datebox"  closeText="Close" editable="false"value="${project.actualLastTestTime}"></input>
			</td>
		</tr>
        <tr>
        	<th><b style="color: red;">*</b>阶段</th>
			<td>
			<select name="phase" id="phase" class="easyui-combobox mustInput easyui-validatebox" data-options="valueField:'id',textField:'text'"  editable="false" >
		    <option value="">-----请选择-----</option>
		    <c:forEach var="p" items="${phaseList}">
			<option value="${p.id}" <c:if test="${p.id==project.phase}">selected="selected"</c:if>>${p.name}</option>
			</c:forEach>
				</select>
				<div style="display: inline;" name="err"></div>
			</td>
        </tr> 
        <%-- <tr>
        	<th>客户</th>
			<td align="left" colspan="2">
		    	<select name="customersList" id="customersList" class="easyui-combobox" style="width:95% !important;"  data-options="panelHeight:200" multiple="true" editable="false" >
		    		<c:forEach var="c" items="${customers }">
		    			<c:choose>
		    				<c:when test="${selectCustomers != null }">
		    					<c:forEach var="sc" items="${selectCustomers }">
		    						<option value="${c.id}" <c:if test="${c.id==sc}">selected="selected"</c:if>>${c.name}</option>	
		    					</c:forEach>
		    				</c:when>
		    				<c:otherwise>
		    					<option value="${c.id}">${c.name}</option>
		    				</c:otherwise>
		    			</c:choose>
					</c:forEach>
				</select>
		    </td>
        </tr>  --%>
        <tr>
			<th>项目回款</th>
			<td><input type="text" name="projectMoney" id="projectMoney" style="width:250px;" value="${project.projectMoney}" class="must-input"/><div style="display: inline;" name="err"></div></td>
		</tr>  
		<tr>
			<th>预估毛利</th>
			<td><input type="text" name="profit" id="profit" style="width:250px;" value="${project.profit}" class="must-input"/><div style="display: inline;" name="err"></div></td>
		</tr>  
		       
	</table>

	</div>
	
	<div title="项目成员" id="userInfo" >

	
	</div>
	<div title="项目客户" id="projectConstomers">
		<label for="selectContomers" style="display: none;">已关联客户：</label>
		<label id="selectContomers" style="display: none;"></label>
		<table id="list_data"></table>
	</div>	
</div>


</div>
<div data-options="region:'south',border:false" style="height:50px; line-height:30px; text-align:center;">
		<div id="toolbar">
			<input type="submit" id="subBtn" style="display: none;">
			<a href="javascript:_save();" class="easyui-linkbutton" id="btnSave" iconCls="icon-save">保存</a>
			<a href="#" class="easyui-linkbutton" id="closeBtn" iconCls="icon-cancel">关闭</a>
		</div>
</div>
</form>
	</body>
</html>
