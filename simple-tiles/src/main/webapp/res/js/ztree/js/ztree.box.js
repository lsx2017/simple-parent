/**
 * zTree 树形下拉框
 * 
 * @author: liushx
 * @date: 2016-09-20
 */
function ztreeBox(opts){
	
	var _width = $("#"+opts.inputName).width()+24;
	
	var ztreeHtml = '<div id="'+opts.ztreeId+'Div" style="display:none; position: absolute;z-index:99999; border:1px solid #cccccc;border-radius:3px; background:#FFFFFF; width:'+_width+'px;">';
	ztreeHtml += '<div id="'+opts.ztreeId+'" class="ztree"></div></div>';
	$("body").append(ztreeHtml);

	var idBox = $("#"+opts.inputId); 
	var nameBox = $("#"+opts.inputName);
	var idSelect = $("#"+opts.ztreeId+"Div"); 
	var ztreeSetting = {
		data: {
			simpleData: {
				enable: false
			}
		},/**
		view: {
			dblClickExpand: false,
			showLine: false,
			selectedMulti: false,
			nameIsHTML: true, //为了可以使用fontawesome
			showIcon: false
		},*/
		callback: {
			//onCheck: onCheck3,
			onClick: function(event, treeId, treeNode, clickFlag){
				//console.log("[ id=="+treeNode.id+" ]-----[ name=="+treeNode.name+" ]"+treeNode.level );
				//$("#"+opts.hiddenId).val(treeNode.id);
				//$("#"+textId).val(treeNode.name);
				idBox.val(treeNode.id);
				nameBox.val(treeNode.orgName);
				hideSelect();
				if(opts.clickCallback) {
					opts.clickCallback(treeId, treeNode);
				}
				

			}
		}
	};
	
	var treeObj;
	$.ajax({
		url:opts.dataUrl,
		type:"post",
		dataType:'json',
		data:{},
		success:function(result){
			console.log('-----------result-----------------------');
			//console.log(result);
			treeObj = $.fn.zTree.init($("#"+opts.ztreeId), ztreeSetting, result);
			//var hiddenVal = $("#"+opts.hiddenId).val();
			//数据回填
			//var treeNode = treeObj.getNodeByParam('id', hiddenVal);
			//if(treeNode) {
			//	$('#'+textId).val(treeNode.name);
			//}
		}
	});

	nameBox.click(function(){
		console.log('--------------idBox.click-----------------');
		//idBox.find("i").removeClass("fa-caret-down").addClass("fa-caret-up");
		var _obj = nameBox;
		var _offset = nameBox.offset();
		console.log(_offset);
		idSelect.css({left:_offset.left + "px", top:_offset.top + (_obj.outerHeight()+2) + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	});
	

	function hideSelect() {
		idSelect.fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == opts.inputName || event.target.id == (opts.ztreeId+"Div") || $(event.target).parents("#"+opts.ztreeId+"Div").length>0)) {
			hideSelect();
		}
	}
	
	return treeObj;
}