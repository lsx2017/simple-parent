$(document).ready(function(){
	
	
	initEditPanel("编辑表单","cform/comp/edit");

	$("#addBtn").click(function(){
		//showEditPanel("cform/forms/edit");
		var formsId = $("#formsId").val();
		showEditPanel("cform/comp/edit?formsId="+formsId);
		//top.openDialog("编辑表单", "cform/forms/edit?formsId="+formsId, 4);

	});
	
	//列表查询
	$("#queryBtn").click(function(){
		searchList(0);
	});
	
	$("#close-btn").click(function(){
		hideEditPanel();
	});
	
	
	treeViewPanel("formsTree", "cform/forms/tree", function(node){
		$("#formsId").val(node.id);
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
	// 查看是否选中

	$("#contentFrame", parent.document).attr("src", "cform/generic/view?formsId="+formsId);
}



// 修改
function edit(cid) {

	showEditPanel("cform/comp/edit?cid="+cid);
	//top.openDialog("编辑表单", "cform/forms/edit?cid="+cid, 4);
	
}

//修改
function designer(formsId) {

	//showEditPanel("system/user/edit?userId="+userId);
	top.openDialog("编辑设计器", "cform/forms/designer?formsId="+formsId, 4);
	
}

//发布表单生成数据库表
function deploy(formsId) {
	// 查看是否选中
	//top.openDialog("新增", "/system/user/add?orgId=" + $("#filter_EQ_orgId").val(), 600, 600);	
	$.ajax({
		url:"cform/forms/deploy",
		type:"post",
		data:{"formsId":formsId},
		datatype:"json",
		success:function(data,textStatus){
			layer.msg('发布成功', {icon: 1,time: 1000 }, function(index){
				//parent.closeEditPanel();
			}); 
		},
		complete:function(XMLHttpRequest,textStatus){
		},
		error:function(XMLHttpRequest,textStatus,errorThrown){
			alert("删除失败");
		}
	});
	
}

//删除
function del(cid) {
	layer.confirm('确定要删除该条记录？', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
  			url:"cform/comp/delete",
			type:"post",
			data:{"cid":cid},
			datatype:"json",
			success:function(data,textStatus){
				layer.msg('删除成功', {icon: 1,time: 1000 }, function(index){
					searchList(0);
				}); 
			},
			complete:function(XMLHttpRequest,textStatus){
			},
			error:function(XMLHttpRequest,textStatus,errorThrown){
				layer.msg('删除失败', {icon: 1,time: 1000 });
			}
		});
	});	
}
