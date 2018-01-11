
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

function addTab(subtitle, url, icon){

	if(!$("#tabs").tabs('exists',subtitle)){
		$('#tabs').tabs('add',{
			title:subtitle,
			fit:true,
			border:false,
			content:'<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:100%;overflow: hidden;"></iframe>',
			closable:true//,
			//icon:icon
		});
	}else{
		$('#tabs').tabs('select',subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

$(function(){

	tabClose();
	tabCloseEven();
	

		
	
	var treeObj = null;
	var leftSetting = {
		data: {
			key: {
				name: "docs_name"//
			},
			simpleData: {
				enable: true,
				idKey: "docs_id",
				//name:"docsName",
				pIdKey: "parent_id",
				rootPId: 0
			}
		},
		edit: {
			enable: true
		},
		callback : {
			onClick : function(event, treeId, treeNode, clickFlag) {
				
				//console.log(treeNode);
				//$("#expFrame").attr("src", "explorer/openDocs?docsId="+treeNode.docs_id);
				//console.log
				addTab(treeNode.docs_name,"repository/openDocs?docsId="+treeNode.docs_id);

			},
			onRename :function(event, treeId, treeNode, clickFlag) {
				//alert(treeNode.docs_name);
				//新增比较
				//var treeObj = $.fn.zTree.getZTreeObj("docsTree");
				//var nodes = treeObj.getSelectedNodes();
				$.ajax({
			    	url:"explorer/addExDocs",
			     	type:"post",
			     	dataType:'json',
			     	data:{'docsId':treeNode.docs_id, "docsName":treeNode.docs_name},
			      	success:function(result){
			      		//console.log('------------result--------------');
			      		//console.log('重命名成功');
			      		layer.msg('重命名成功',{icon:1, time: 2000});
			    		
			      	}
				});
				
			},
			onRemove :function(event, treeId, treeNode, clickFlag) {
				$.ajax({
			    	url:"explorer/delete",
			     	type:"post",
			     	dataType:'json',
			     	data:{'docsId':treeNode.docs_id},
			      	success:function(result){
			      		//console.log('------------result--------------');
			      		//alert('shanchu 成功');
			      		layer.msg('删除节点成功',{icon:1, time: 2000});
			    		
			      	}
				});
					
			}
		}

	};
	
	$.ajax({
    	url: "explorer/exDocsTree",//"repository/repTree",//
     	type:"post",
     	dataType:'json',
      	success:function(result){
      		if((typeof result) == 'string') {
      			result = $.parseJSON(result);
      		}
      		treeObj = $.fn.zTree.init($("#docsTree"), leftSetting, result);
      	
      		treeObj.expandAll(false);
      		var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
      		treeObj.expandNode(rootNode, true, false, true);
      	}
	});
	
	
	//新增子项
	$("#btn_newchild").click(function(){
		treeObj = $.fn.zTree.getZTreeObj("docsTree");
		var nodes = treeObj.getSelectedNodes();
		$.ajax({
	    	url:"explorer/addExDocs",
	     	type:"post",
	     	dataType:'json',
	     	data:{'parentId':nodes[0].docs_id},
	      	success:function(result){
	      		//console.log('------------result-----44---------');
	      		//console.log(typeof result);
	  
	      		if((typeof result) == 'string') {
	      			result = $.parseJSON(result);
	      		}
	      		
	    		treeObj.addNodes(nodes[0], {docs_id:result.data.docsId, docs_name:result.data.docsName,parent_id:result.data.parentId}, true);
	    		//treeObj.expandAll(true);
	    		treeObj.expandNode(nodes[0], true, false, true);
	      	}
		});
	});

	//添加项，前面
	$("#btn_newafter").click(function(){
	
	});
	
	//添加项，后面
	$("#btn_newbefore").click(function(){
	
	});
	

	
	//上移
	$("#btn_moveup").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("docsTree");
		//console.log('----------------btn_movedown--------------------------');
		//console.log(treeObj);
		var nodes = treeObj.getSelectedNodes();
		var preNode = nodes[0].getPreNode();
		//console.log(nodes[0]);
		//console.log('PreNode', nodes[0].getPreNode());
		//console.log('tId==='+nodes[0].tId);
		treeObj.moveNode(preNode, nodes[0],  "prev");
	
		$.ajax({
	    	url:"explorer/updateExDocsSort",
	     	type:"post",
	     	dataType:'json',
	     	data:{'srcDocsId':nodes[0].docs_id, "targetDocsId":preNode.docs_id, "moveType":"prev"},
	      	success:function(result){
	      		//console.log('------------move success--------------');
	      		//console.log('重命名成功');
	      		//layer.msg('move success',{time: 1000});
	    		
	      	}
		});
		
	});
	
	//下移
	$("#btn_movedown").click(function(){
		//var treeObj = $.fn.zTree.getZTreeObj("docsTree");
		//console.log('----------------btn_movedown--------------------------');
		//console.log(treeObj);
		var nodes = treeObj.getSelectedNodes();
		var nextNode = nodes[0].getNextNode();
		//console.log(nodes[0]);

		//console.log('tId==='+nodes[0].tId);
		treeObj.moveNode(nextNode, nodes[0],  "next");
		$.ajax({
	    	url:"explorer/updateExDocsSort",
	     	type:"post",
	     	dataType:'json',
	     	data:{'srcDocsId':nodes[0].docs_id, "targetDocsId":nextNode.docs_id, "moveType":"next"},
	      	success:function(result){
	      		//console.log('------------move success-----1---------');
	      		//console.log('重命名成功');
	      		//layer.msg('move success',{time: 1000});
	    		
	      	}
		});
	});
});


function tabClose(){
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update',{
			tab:currTab,
			options:{
				content:createFrame(url)
			}
		})
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		console.log('currtab_title==='+currtab_title);
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tabs').tabs('close',t);
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			//msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}


