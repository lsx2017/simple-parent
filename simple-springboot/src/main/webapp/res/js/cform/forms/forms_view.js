$(document).ready(function(){
	
	initEditPanel("编辑表单","cform/forms/edit");

	$("#addBtn").click(function(){
		var formTypeId = $("#formTypeId").val();
		if(formTypeId == '') {
			layer.msg('请选择表单类型', {icon: 1,time: 2000 });
			return;
		}
		top.openDialog("编辑表单", "cform/forms/edit?formTypeId="+formTypeId, 4);

	});
	
	//列表查询
	$("#queryBtn").click(function(){
		searchList(0);
	});
	
	
	treeViewPanel("formtypeTree", "cform/formtype/tree", function(node){
		$("#formTypeId").val(node.id);
		searchList(0);
	});
	
	searchList(0);
});

/**
 * 列表查询
 * @param page 当前页
 */
function searchList(page){
	$('#page').val(page);
	$('#searchForm').ajaxSubmit(function(data) {
		$('#content').html(data);
	});
}


// 查看
function test(formsId) {

	$("#contentFrame", parent.document).attr("src", "cform/generic/view?formsId="+formsId);
}



// 修改
function edit(formsId) {

	top.openDialog("编辑表单", "cform/forms/edit?formsId="+formsId, 4);
	
}

//修改
function designer(formsId) {

	top.openDialog("编辑设计器", "cform/forms/designer?formsId="+formsId, 4);
	
}

//发布表单生成数据库表
function deploy(formsId) {
	// 查看是否选中
	var loadIndex = layer.load(2, {shade: [0.5, '#fff']});
	$.ajax({
		url:"cform/forms/deploy",
		type:"post",
		data:{"formsId":formsId},
		datatype:"json",
		success:function(data, textStatus){
			layer.close(loadIndex);  
			if(data.success){
				layer.msg('同步成功', {icon: 1,time: 2000 }, function(index){
					searchList(0);
				}); 
			} else {
				layer.msg('同步失败', {icon: 1,time: 2000 });
			}
		}
	});
	
}

// 删除
function del() {
	// 查看是否选中
	if (!selectId) {
		alert("请选择一条记录");
		return;
	}

	if(confirm("确定要删除该条记录？")){
		$.ajax({
  			url:"/system/user/delete",
			type:"post",
			data:{"id":selectId},
			datatype:"json",
			success:function(data,textStatus){
				alert("删除成功");
				init();
			},
			complete:function(XMLHttpRequest,textStatus){
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				alert("删除失败");
			}
		});
	}
}
