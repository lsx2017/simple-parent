<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html id="taskfrom_view">
	<head>
		<title>项目管理</title>
			<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
		<link href="resources/scripts/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
		<link href="resources/scripts/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="resources/scripts/jquery/jquery-1.7.2.min.js"></script>		
	    <script type="text/javascript" src="resources/scripts/easyui/jquery.easyui.min.js"></script>
	    <script type="text/javascript" src="resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>	
		
	</head>

<script type="text/javascript">
$(function() {
	//$('#win').window('close'); 
	$("#list_data").datagrid({
		iconCls:'icon-edit',//图标
		title:"项目管理",
		nowrap:false,
		striped:true,
		fit: true,//自动大小
		url : "project.shtml?method=projectList",
		method:'post',
		idField:'ID',
		columns:[[
			{field:'ID',hidden:true},
			{title:'项目编码',field:'CODE',width:'120',align:'center'},
			{title:'项目名称',field:'NAME',width:'230',align:'center'},
			{title:'产品类型',field:'PRODUCT_NAME',width:'110',align:'center'},
			{title:'所属区域',field:'REGION_NAME',width:'110',align:'center'},
			{title:'项目回款',field:'PROJECT_MONEY',width:'110',align:'center'},
			{title:'预估毛利',field:'PROFIT',width:'110',align:'center'},
			{title:'分类',field:'TYPE_NAME',width:'60',align:'center'},
			{title:'开始时间',field:'START_DATE',width:'100',align:'center'},
			{title:'结束时间',field:'END_DATE',width:'100',align:'center'},
			{title:'项目状态',field:'PROJECT_STATUS_',width:'60',align:'center'}
			
		]],
		toolbar:'#tb',
		fitColumns:true,
		singleSelect:true,
		pagination:true,
		rownumbers:true,
		pageSize:10,
		onClickRow:function(rowIndex, rowData){}
	}).datagrid('getPager').pagination({  
        pageSize: 10,//每页显示的记录条数，默认为10  
        pageList: [10,20,50],//可以设置每页记录条数的列表  
        beforePageText: '第',//页数文本框前显示的汉字  
        afterPageText: '页    共 {pages} 页',  
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
	
	//重置
	$("#_reset").click(function(){
		$("input[name=regionId]").val("");
		$("input[name=name]").val("");
		searchList();
	});
	closeDialog();
});

/**
 * 查询
 */
function searchList(){
	$("#list_data").datagrid('reload',{
		regionId:$('input[name=regionId]').val(),
		name:$('input[name=name]').val(),
	});
}



function del(){

	var row = $('#list_data').datagrid('getSelected');
	if(row){
		$.messager.confirm("提示", "是否确认要删除当前所选中的任务？", function(r) {  
            if (r){
                $.ajax({  
                   url:"project.shtml?method=del",
                   data:{id:row.ID},
                   type:'post',  
                   success:function(data){   
                        if(data){ 
                        	$('#list_data').datagrid('clearSelections');
                        	$('#list_data').datagrid('reload');
                            $.messager.alert('提示', '删除成功!'); 
                            
                        }else{  
                            $.messager.alert('提示', '删除失败!','warning');
                        }     
                   }  
                });  
           }  
        });
	}else{
		$.messager.alert('提示','请选择一条记录!');
	}
	
}


function edit(){
	
	var row = $('#list_data').datagrid('getSelected');
	if(row){ 
		openDialog('编辑项目单', "project.shtml?method=edit&id="+row.ID, 900, 450);
	}else{
		$.messager.alert('提示','请选择一条记录!');
	}	
}


function add(){
	openDialog('编辑项目单', 'project.shtml?method=edit&id=', 900, 450);
}

//查询列表
function openDialog(_title, url, _width, _height){
	$('#contentFrame').attr('src', url);
	$('#win').window({
		collapsible:false,
		title:_title,
		top:20,
		left:150,
	    width:_width,    
	    height:_height,
	   // maximized:true,
	    modal:true   
	});  
}
/**关闭弹出层**/
function closeDialog(){
	searchList();
	$('#win').window('close'); 
	
}

function setSrc(url){
	
	$('#contentFrame').attr('src', url);
}

</script>


<body>

<table id="list_data"></table>
<input type="hidden" id="projectId" name="projectId" value="" />
<div id="tb" style="padding:5px;height:auto">
	<div style="margin-bottom:5px; text-align: right;">
		<%@ include file="/jsp/common/button.jsp"%> 
	</div>
	<div style="border-top: 1px solid #99BBE8;">
	<table width="100%" style="font-size: 12px;">
		<tr align="center">
			<td class="th-label">地域：</td>
			<td class="td-input"><input class="easyui-input" style="width:180px" name="regionId"></td>
			<td class="th-label">名称：</td>
			<td class="td-input"><input class="easyui-input" style="width:180px" name="name"></td>
			<td align="right">
				<a href="javascript:searchList();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
				<a href="#" id="_reset" class="easyui-linkbutton" iconCls="icon-reset">重置</a>
			</td>
		</tr>
	</table>
	</div>
</div>

<div id="win" class="easyui-window"  style="width:600px;height:350px;">
	<iframe id="contentFrame" src="" width=100% height=100% marginwidth=0 framespacing=0 marginheight=0 frameborder=no scrolling=no></iframe>
</div> 
	</body>
	
</html>