require(['jquery','easyui','ztree'], function($, zhCN, easyui) {
	(function(){
		var _8611 = setInterval(parse,10);
		function parse(){
			if($.parser && $.fn.slider && !window.renderedFlag){
				clearInterval(_8611);
				$.parser.parse();
				window.renderedFlag = true;
			}
		}
	})();

	function addTab(subtitle,url,icon){

		if(!$("#tabs").tabs('exists',subtitle)){
			$('#tabs').tabs('add',{
				title:subtitle,
				fit:true,
				border:false,
				content:'<iframe scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:99%;overflow: hidden;"></iframe>',
				closable:true//,
				//icon:icon
			});
		}else{
			$('#tabs').tabs('select',subtitle);
			$('#mm-tabupdate').click();
		}
		//tabClose();
	}
	$(function(){
		var treeObj = null;
		var leftSetting = {
			data: {
				key: {
					name: "docs_name"
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
					addTab(treeNode.docs_name,"explorer/openDocs?docsId="+treeNode.docs_id);
					/**$("#userTable").egrid('reload', {
						'type' : treeNode.type,
						id : treeNode.id
					});
					*/
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
				      		console.log('------------result--------------');
				      		//console.log('重命名成功');
				      		//layer.msg('重命名成功',{time: 1000});
				    		
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
				      		console.log('------------result--------------');
				      		//alert('shanchu 成功');
				      		//layer.msg('删除节点成功',{time: 1000});
				    		
				      	}
					});
						
				}
			}
	
		};
		
		$.ajax({
	    	url:"explorer/exDocsTree",
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
		      		console.log('------------result-----44---------');
		      		console.log(typeof result);
		  
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
			console.log('----------------btn_movedown--------------------------');
			console.log(treeObj);
			var nodes = treeObj.getSelectedNodes();
			var preNode = nodes[0].getPreNode();
			console.log(nodes[0]);
			console.log('PreNode', nodes[0].getPreNode());
			//console.log('tId==='+nodes[0].tId);
			treeObj.moveNode(preNode, nodes[0],  "prev");
		
			$.ajax({
		    	url:"explorer/updateExDocsSort",
		     	type:"post",
		     	dataType:'json',
		     	data:{'srcDocsId':nodes[0].docs_id, "targetDocsId":preNode.docs_id, "moveType":"prev"},
		      	success:function(result){
		      		console.log('------------move success--------------');
		      		//console.log('重命名成功');
		      		//layer.msg('move success',{time: 1000});
		    		
		      	}
			});
			
		});
		
		//下移
		$("#btn_movedown").click(function(){
			//var treeObj = $.fn.zTree.getZTreeObj("docsTree");
			console.log('----------------btn_movedown--------------------------');
			console.log(treeObj);
			var nodes = treeObj.getSelectedNodes();
			var nextNode = nodes[0].getNextNode();
			console.log(nodes[0]);

			//console.log('tId==='+nodes[0].tId);
			treeObj.moveNode(nextNode, nodes[0],  "next");
			$.ajax({
		    	url:"explorer/updateExDocsSort",
		     	type:"post",
		     	dataType:'json',
		     	data:{'srcDocsId':nodes[0].docs_id, "targetDocsId":nextNode.docs_id, "moveType":"next"},
		      	success:function(result){
		      		console.log('------------move success-----1---------');
		      		//console.log('重命名成功');
		      		//layer.msg('move success',{time: 1000});
		    		
		      	}
			});
		});
	});
});