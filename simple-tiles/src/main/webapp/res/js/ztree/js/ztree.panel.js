
/**
 * zTree 树形面板插件
 * 
 * @author: liushx
 * @date: 2016-09-20
 */
function ztreePanel(opts){
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
				opts.clickCallback(treeId, treeNode);
				//$("#"+opts.hiddenId).val(treeNode.id);
				//$("#"+textId).val(treeNode.name);
				//hidePanel();

			}
		}
	};
	var treeObj;
	$.ajax({
		url:opts.dataUrl,
		type:"post",
		dataType:'json',
		data:opts.dataJson,
		success:function(result){
			console.log('-----------result-----------------------');
			treeObj = $.fn.zTree.init($("#"+opts.ztreeId), ztreeSetting, result);
			var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);  
			treeObj.selectNode(rootNode);//选择点
			treeObj.setting.callback.onClick(null, treeObj.setting.treeId, rootNode);//调用事件  

		}
	});

	return treeObj;
}

/**
 * 获取当前默认的id
 * @param orgTreeDiv
 * @returns
 */
function getSelectedId(orgTreeDiv){
	var treeObj = $.fn.zTree.getZTreeObj(orgTreeDiv);
	var nodes = treeObj.getSelectedNodes();
	var id = nodes[0].id;
	return id;
}
/**
 * 
 * 只选中id结点并展开子节点；没点击效果
 * @param orgTreeDiv
 * @returns
 */
function selectZtreeNode(orgTreeDiv ,id){
	var treeObj = $.fn.zTree.getZTreeObj(orgTreeDiv);
	var node = treeObj.getNodeByParam("id", id);
	treeObj.selectNode(node);
	treeObj.expandNode(node, true, false, false);
}

/**
 * 
 * 获取顶点第一个结点的ID
 * @param orgTreeDiv
 * @returns
 */
function getTopFirstId(orgTreeDiv){
	var treeObj = $.fn.zTree.getZTreeObj(orgTreeDiv);
	return treeObj.getNodeByTId(orgTreeDiv+"_1").id;
}
