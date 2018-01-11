

//function maven

function easyuiGrid(options, param){
	if (typeof options == 'string'){
		$(options||"#list_data").datagrid('reload', param);
	} else {
		var config = {
			iconCls:'icon-edit',//图标
			nowrap:false,
			striped:true,
			fit: true,//自动大小
			method:'post',
			toolbar:'#tb',
			//fitColumns:true,
			singleSelect:true,
			pagination:true,
			rownumbers:true
		};

		$(options.tableId||"#list_data").datagrid($.extend(true, {},config,options)).datagrid('getPager').pagination({  
	        pageSize: 10,//每页显示的记录条数，默认为10  
	        pageList: [10,20,50],//可以设置每页记录条数的列表  
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
	    });
	}
}

(function($){
	
	$.fn.egrid = function(options, param){
		if (typeof options == 'string'){
			$(this).datagrid(options, param);
		} else {
			var config = {
				iconCls:'icon-edit',//图标
				nowrap:false,
				striped:true,
				fit: true,//自动大小
				method:'post',
				toolbar:'#tb',
				fitColumns:true,
				singleSelect:true,
				pagination:true,
				rownumbers:true
			};
	
			$(this).datagrid($.extend(true, {},config,options)).datagrid('getPager').pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [10,20,50],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
		    });
		}
	
		
	}
	//打开窗口
	$.fn.openDialog = function(_title, _url, _width, _height, _ismax){

		$('#contFrame').attr('src', _url);
		
		$("#easyuiWin").window({
			title:_title,
			//border:false,
			left:($(window).width()-_width)/2,
			top:($(window).height()-_height)/2+$(window).scrollTop(),
			width:_width||600,
			height:_height||330,
			collapsible:false,
			maximized:_ismax||false,
			modal:true  
		}); 
	}
	//关闭窗口
	$.fn.closeDialog = function(tableId){
		$(tableId||"#list_data").datagrid('reload');
		$("#easyuiWin").window('close');
	}
})(jQuery);




/**关闭弹出层**/
function closeDialog(options){

}