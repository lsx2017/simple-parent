
// 修改
function edit(pkVal, pkName) {
	var formsId = $("#formsId").val();

	var _url = "cform/generic/edit?formsId="+formsId+"&pkVal="+pkVal+"&pkName="+pkName;
	showEditPanel(_url);
	//top.openDialog("修改", "/system/user/edit?id=" + selectId, 600, 600);
	
}

$(document).ready(function(){
	
	//$(".gridview").width($(window).width());
	//$("#orgview").height($(window).height());
	
	initEditPanel("编辑表单","system/user/edit");
	
	var _ztree = ztreePanel({
		ztreeId:'formsTreeDiv',
		dataUrl:'cform/forms/tree?formsStatus=2',
		clickCallback:function(treeId, treeNode){
			//点击Tree控制事件
			//$("#resId").val(treeNode.id);
			//$("#resType").val(treeNode.resType);
			$("#formsId").val(treeNode.id);
			searchList(0);
		}
	});

	$("#addBtn").click(function(){
		showEditPanel("cform/generic/edit?formsId="+$("#formsId").val());

	});
	
	//列表查询
	$("#queryBtn").click(function(){
		searchList(0);
	});
	
});

/**
 * 列表查询
 * @param page 当前页
 */
function searchList(page){
	$('#page').val(page);
	$('#searchForm').ajaxSubmit(function(data) {
		//1、清空表格
		$("#lists").empty();
		
		var thead = '<thead><tr><th width="5%" class="tcenter">序号</th>';
		$.each(data.columns, function(i, column){
			thead += '<th>'+column.title+'</th>';
		});
		
		thead +='<th width="12%" class="tcenter">操作</th>';
		thead += '</tr></thead>';
		$("#lists").append(thead);
		var tbody = '';
		$.each(data.page.dataList, function(ind, obj){
			
			tbody += '<tr><td class="tcenter">'+(ind+1)+'</td>';
			
			$.each(data.columns, function(index, col){
				tbody += '<td>'+(obj[col.field]||" ")+'</td>';
			});
			
			tbody += '<td class="tcenter">';
			tbody += '<a href="javascript:edit('+obj[data.pkName]+', \''+data.pkName+'\');"><i class="fa fa-pencil t-eidt"></i></a>&nbsp;&nbsp;';
			tbody += '<a href="javascript:del('+obj[data.pkName]+', \''+data.pkName+'\');"><i class="fa fa-trash t-delete"></i></a>';
			
			tbody += '</td></tr>';
		});
		$("#lists").append(tbody);

		//初始化分页工具条
		$.jqPaginator('#pagination1', {
			totalPages: data.page.totalPages,
	        //totalCounts：${page.total},
	        visiblePages: 10,
	        currentPage: (parseInt(data.page.page)+1),
	        onPageChange: function (num, type) {
	        	//alert(typeof type);
	        	if(type != 'init') {
	        		//alert('num=='+num);
	        		searchList(num-1);
	        	}
	            
	        }
	    });

		var _th = $("#lists").find("thead th");
		var len = _th.length;
		var wd = parseInt(83/(len-2)) +'%';
		_th.each(function(i, n){
			if (i === len - 1 || i ==0) {
				
			} else {
				$(this).attr("width", wd);
			}
			
		});
	});
}


// 删除
function del(pkVal, pkName) {

	layer.confirm('确定要删除该条记录？', {
		btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
  			url:"cform/generic/delete",
			type:"post",
			data:{"formsId":$('#formsId').val(), 'pkVal':pkVal, 'pkName':pkName},
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


