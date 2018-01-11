
function treeViewPanel(treeId, url, clickCallback){
	$.ajax({
 		cache: false,
		type: "POST",
		url: url,
		//data: "dictId="+dictId,
		dataType: "json",
		error :	function(){
			console.log('服务器异常，获取数据失败！');
		},
		success : function(result){
		 	$('#'+treeId).treeview({
		 		data: result,
		 		showBorder: false,
		 		icon: "glyphicon glyphicon-stop",
		 		selectedIcon: "glyphicon glyphicon-stop",
		 		onNodeSelected: function(event, node) {
	
		 			clickCallback(node);
		    	}
		 	});
		}
	});
}
