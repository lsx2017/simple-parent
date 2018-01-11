
$(function() {
	$('#win').window('close');
	$('#list_data').treegrid({    
	    url:'project.shtml?method=projectItemList',    
	    idField:'id',    
	    treeField:'step_',
	    toolbar:'#tb',
	    border:0,
		nowrap:true,
		striped:true,
		fit: true,
	    columns:[[
	              {field:'step_',title:'阶段[事项名称]',align:'left', width:100,
	      			formatter: function(value,row,index){
	    				//return value;
	    				if (row.item_id != '0'){
	    					return row.name;
	    				} else {
	    					return row.step_;
	    				}
	      				//return row.itemId + row.name;
	    			}
	              },    
	              /*{field:'name',title:'事项名称'},*/    	       
	              {field:'seq',title:'排序'},    
	              {field:'day',title:'天数'},
	              {field:'hourt',title:'工时'},
	              {field:'role',title:'角色',width:120},
	              /*{field:'descript',title:'描述',width:100},*/
	              {field:'note',title:'说明',width:100}
	    ]],
	    fitColumns:true,
	    singleSelect:true,
		pagination:false,
		rownumbers:true
	}); 
	
});

/**
 * 查询
 */
function searchList(){
	$("#list_data").treegrid('reload',{
		projectId:$('input[name=projectId]').val()
	});
}



function del(){

	var row = $('#list_data').datagrid('getSelected');
	if(row){
		var itemId = row.item_id;
		var projectId = $("#projectId").val();
		var projectId_ = row.projectId;
		if(itemId == null || itemId == "0" || projectId_ == null || projectId_ == "0"){
			$.messager.alert('提示','请选择一条具体事项!');
		}else{
			$.messager.confirm("提示", "确认是否要删除选中的事项？", function(r) {  
	            if (r){
	                $.ajax({  
	                   url:"item.shtml?method=del&itemId="+itemId + "&projectId=" + projectId,  
	                   type:'post',  
	                   success:function(data){   
	                        if(data){ 
	                        	$('#list_data').treegrid('clearSelections');
	                        	$("#list_data").treegrid('reload',{
	                        		projectId:$('input[name=projectId]').val()
	                        	});
	                            $.messager.alert('提示', '删除成功!'); 
	                        }else{  
	                            $.messager.alert('提示', '删除失败!','warning');
	                        }     
	                   }  
	                });  
	           }  
	        });
		}
	}else{
		$.messager.alert('提示','请选择一条记录!');
	}
	
}

function add(){
	var row = $('#list_data').datagrid('getSelected');
	if (row) {
		var step = row.step;
		var projectId = row.projectId;
		var itemId = row.item_id;
		var projectId_ = $("#projectId").val();
		//top.openDialog('新增事项', 1100, 500, '/item.shtml?method=edit&step='+step + '&projectId=' + projectId_);
		openDialog('新增事项', 'item.shtml?method=edit&step='+step + '&projectId=' + projectId_, 900, 320);
	} else {
		var projectId_ = $("#projectId").val();
		//top.openDialog('新增事项', 1100, 500, '/item.shtml?method=edit&step&projectId=' + projectId_);
		openDialog('新增事项', 'item.shtml?method=edit&step&projectId=' + projectId_, 900, 320);
	}
	//var projectId = $("#projectId").val();
	//top.openDialog('新增需求单', 1100, 500, '/demand.shtml?method=edit&projectId='+projectId);
}


function edit(){
	var row = $('#list_data').datagrid('getSelected');
	if (row) {
		var itemId = row.item_id;
		var projectId = $("#projectId").val();
		var projectId_ = row.projectId;
		var step = row.step;
		if(itemId == null || itemId == "0" || projectId_ == null || projectId_ == "0"){
			$.messager.alert('提示','请选择一条具体事项!');
		} else {
			//top.openDialog('编辑事项', 1100, 500, '/item.shtml?method=edit&step='+step + '&projectId=' + projectId + '&itemId=' + itemId);
			openDialog('编辑事项', 'project.shtml?method=editItem&step='+step + '&projectId=' + projectId + '&itemId=' + itemId, 900, 320);
		}
	} else {
		$.messager.alert('提示','请选择一条记录!');
	}
}


$(function(){
	//项目树
	var method = $('#method').val();
	
	var leftSetting = {
		async: {
			enable: true,
			autoParam:['id','type'],
			otherParam:{'data':(new Date())},
			type:"post",
			url:"project.shtml?method=projectTree"
		},
		callback: {
			onClick: function(event, treeId, treeNode, clickFlag){
				if(treeNode.type=='3'){
					$('#list_data').datagrid('clearSelections');
					$("#projectId").val(treeNode.id);
					$("#cityId").val('');
					$("#list_data").treegrid('reload',{
		    		   projectId:$('input[name=projectId]').val()
					});
				}else if(treeNode.type=='2'){
					$('#list_data').datagrid('clearSelections');
					$("#cityId").val(treeNode.id);
					$("#projectId").val('');
					$("#list_data").treegrid('reload',{
						cityId:$('input[name=cityId]').val()
					});
				}
			}
		}
	};
	$.fn.zTree.init($("#ui_projectTree"), leftSetting);
});


/**
 * 关联事项
 */
function selectItem(){

	var itemIds = $('#itemIds').val();
	openDialog('关联事项', "project.shtml?method=selectItem&itemIds="+itemIds, 800, 400);
}

function setItem(itemIds,itemNames){
	
	$('#itemIds').val(itemIds);
	$('#itemNames').val(itemNames);
	$("#list_data").treegrid('reload',{
	   projectId:$('input[name=projectId]').val()
	});
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
function closeDialg(){
	searchList();
	$('#win').window('close'); 
	
}
