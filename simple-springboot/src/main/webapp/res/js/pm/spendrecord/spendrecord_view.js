
$(document).ready(function(){
	

	initEditPanel("新增收支","system/user/edit", 1.5);

	$("#addBtn").click(function(){
		showEditPanel("pm/spendrecord/edit?userId=1");

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

