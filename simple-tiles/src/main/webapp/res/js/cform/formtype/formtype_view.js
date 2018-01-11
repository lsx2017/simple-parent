
$(document).ready(function(){
	
	//$(".gridview").width($(window).width());
	//$("#orgview").height($(window).height());
	
	initEditPanel("编辑表单类型","cform/formtype/edit");

	$("#addBtn").click(function(){
		//showEditPanel("cform/formtype/edit");
		top.openDialog("编辑表单类型", "cform/formtype/edit", 2);
	});
	
	//列表查询
	$("#queryBtn").click(function(){
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

// 修改
function edit(formTypeId) {

	//showEditPanel("cform/formtype/edit?formTypeId="+formTypeId);
	top.openDialog("编辑表单类型", "cform/formtype/edit?formTypeId="+formTypeId, 2);
	//top.openDialog("修改", "/system/user/edit?id=" + selectId, 600, 600);
	
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


