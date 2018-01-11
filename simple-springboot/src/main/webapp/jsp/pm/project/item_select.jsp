<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>		
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" href="resources/scripts/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="resources/scripts/ztree/js/jquery.ztree.core-3.5.js"></script>	    
	</head>
<body class="easyui-layout">
<div data-options="region:'west',title:'阶段',split:true" style="width:180px">
	<div id="div_projectTree" style="height:auto; width:180px; overflow:auto;">    
		<ul id="ui_projectTree" class="ztree"></ul>    
	</div> 
</div>
<div data-options="region:'south'" >

	<input type="hidden" id="itemIds" name="itemIds" value="${itemIds}" />
	<input type="hidden" id="step" name="step" value="" />
	<input type="hidden" id="start" name="start" value="0" />
	<a href="javascript:saveItemIds();" class="easyui-linkbutton" iconCls="icon-search" style="float: right;">确定</a>
</div>
<div data-options="region:'center',split:true" style="width: 300px;">
	<table id="list_data"></table>
</div>
<div data-options="region:'east'" style="width: 300px;">
	<table id="list_data1"></table>
</div>

</body>
<script type="text/javascript">

$(function() {
 
	var itemIds = $('#itemIds').val();
	//项目树
	var leftSetting = {
			async: {
				enable: true,
				autoParam:['id','type'],
				otherParam:{'data':(new Date())},
				type:"post",
				url:"project.shtml?method=itemStepTree"
			},
			callback: {
				onClick: function(event, treeId, treeNode, clickFlag){
					//if(treeNode.type=='3'){
						$("#step").val(treeNode.id);
						$("#list_data").datagrid('reload',{
							step:$('input[name=step]').val()
						});
					//}
				}
			}
		};
		$.fn.zTree.init($("#ui_projectTree"), leftSetting);
	$("#list_data").datagrid({ 
		title:"事项列表",
		border:0,
		nowrap:true,
		striped:true,
		fit: true,//自动大小
		url : "project.shtml?method=itemList",
		method:'post',
		idField:'ID',
		columns:[[	
			{field:'ID',hidden:true},
			{title:'事项名称',field:'NAME', align:'left'},
			{title:'天数',field:'DAY', align:'left'},
			{title:'工时',field:'HOURT', align:'left'},
			{title:'角色',field:'ROLE', align:'left'},
			{title:'描述',field:'DESCRIPT', align:'left'},
			{title:'说明',field:'NOTE', align:'left'}
		]],
		//toolbar:'#tb',
		fitColumns:true,
		singleSelect:true,
		//pagination:true,
		rownumbers:true,
		//pageSize:10,
		onDblClickRow:function(rowIndex, rowData){
			var rows = $("#list_data1").datagrid('getRows');
			if(isExistRow(rows, rowData.ID,rowData.NAME)) {
				if(rowData.ID){
					$("#list_data1").datagrid('appendRow',{
						ID:rowData.ID,
						NAME:rowData.NAME
				  	});
				}
			}
		},
		onLoadSuccess:function(data) {
			
			var start = $("#start").val();
			if(start=='0'){
				if(itemIds != "" && itemIds != null) {
					$("#start").val("1")
					var arr_itemIds = itemIds.split(",");
					var rows = $("#list_data").datagrid('getRows');
					$.each(rows, function(i, val){
						for(var i=0; i<arr_itemIds.length; i++) {
							if(arr_itemIds[i]==val.ID){
								$("#list_data1").datagrid('appendRow',{
									ID:val.ID,
									NAME:val.NAME
							  	});
							}
						}
					});
				}
			}
		}
	});
	
	$("#list_data1").datagrid({ 
		title:"[已选]事项列表",
		border:0,
		nowrap:true,
		striped:true,
		fit: true,//自动大小
		url : "",
		method:'post',
		idField:'ID',
		columns:[[	
			{field:'ID',hidden:true},
			{title:'事项名称',field:'NAME',align:'left'}/*,
			{title:'所属项目',field:'PROJECT_NAME',width:100,align:'center',
				formatter:function(value, row, index) {
					return showName(value, 10);
				}	
			}*/
	      ]],
		//toolbar:'#tb',
		//fitColumns:false,
		singleSelect:true,
		//pagination:true,
		rownumbers:true,
		//pageSize:10,
		onDblClickRow:function(rowIndex, rowData){
			$("#list_data1").datagrid('deleteRow',rowIndex);
		}
	});
	
});

function isExistRow(rows, uId,name){
	var tag = true;
	$.each(rows, function(i, val){
		if(val.ID == uId){
			tag = false;
		}
	});
	$.each(rows, function(i, val){
		if(val.NAME == name){
			tag = false;
		}
	});
	return tag;
}



function saveItemIds(){
	var ids = '';
	var names = '';
	var rows = $("#list_data1").datagrid('getRows');
	$.each(rows, function(i, val){
		ids += val.ID+',';
		names += val.NAME+',';
	});
	ids = ids.substring(0, ids.length-1);
	names = names.substring(0, names.length-1);
	
	parent.setItem(ids,names);
	parent.closeDialg();
}


</script>
</html>