
var candidateWin = null;
var tabs = null;
var orgPanel  = null;
var postPanel = null;
var rolePanel = null; 
var selStore = null;
//var memberIds = $("#projectmember").val();//opts.curXmlNode.getAttribute("candidateIds");

Ext.onReady(function(){
	
	
	
	
	orgId = "";
	orgName = "";
	
/**（行业、组织机构）获取用户*/

Ext.define('Tree',{
	extend:'Ext.data.Model',
	fields:[
		{name:'id', type:'int'},
		{name:'text', type:'string'},
		{name:'type', type:'int'}
	]	
});

var orgStore = Ext.create('Ext.data.TreeStore',{
	autoLoad: true,
	model:'Tree',
    root: {
        text: orgName,//document.getElementById('orgName').value,
        id: orgId,//document.getElementById('orgId').value,
        expanded: true
    },
    proxy: {
    	type: 'ajax',
    	url: '/platform.shtml?method=orgTree',
    	extraParams :{
	    	orgId:''
		}
    },
    listeners : {
   		beforeexpand : function(node, eOpts){  
		
   			//点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
            this.proxy.extraParams.orgId = node.raw.id;  
        }
    }
});

var orgTree = Ext.create('Ext.tree.Panel',{
	
	
    store: orgStore,
    rootVisible: true,
    autoScroll : true,
    containerScroll: true,
    height:324,
	listeners:{
		itemclick:function(view, record, item, idx, event, eOpts) {
			userStore.load({
				params:{'orgId':record.get('id')}
			});
			/*if(record.get('type')>0){
				//ajax
				userStore.load({
					params:{'orgId':record.get('id')}
				});
				
			} else {
				Ext.Msg.alert('提示','行业类型不可选');
			}*/
    	}
    	
    }
});

//已经选中组织机构树
var userStore = Ext.create('Ext.data.TreeStore',{
	//autoLoad: true,
    root: {
        text: '用户列表',
        id: '-1',
        expanded: true
    },
    proxy: {
    	type: 'ajax',
    	url: '/platform.shtml?method=userTree'
    }
});

var userTree = Ext.create('Ext.tree.Panel',{
	rootVisible: true,
    store: userStore,
    height:324,
    border:true,
    listeners:{
		itemdblclick:function(view, record, item, idx, event, eOpts) {
			if(!selStore.getNodeById(record.get('id'))){
				
				selTree.getRootNode().appendChild({
			    	id:record.get('id'),
			    	text:record.get('text'),
			    	leaf:true
			    });
			
			}
    	}	
    }
});

//已经选中组织机构树
selStore = Ext.create('Ext.data.TreeStore',{
	autoLoad: true,
    root: {
        text: '用户列表',
        id: '-1',
        expanded: true
    }
});

var projectmember = $("#projectmember").val();

if(projectmember.length > 2){
	selStore.getRootNode().appendChild(Ext.decode(projectmember));
}

var selTree = Ext.create('Ext.tree.Panel',{
	rootVisible: true,
    store: selStore,
    height:324,
    border:true,
    listeners:{
    	itemdblclick:function(view, record, item, idx, event, eOpts) {//tree双击事件
    		record.remove();
    	}
    	
    }
});

var userPanel = Ext.create('Ext.panel.Panel', {
	renderTo:"userInfo",
	layout: 'column',
	width: 880,
    height: 400,
	border:false,
    items: [{
    	border:false,
    	columnWidth:0.4, 
    	layout: 'fit',
        items:orgTree
    },{
    	layout: 'fit',
    	border:false,
    	items:userTree,
    	columnWidth: 0.3
    }, {
    	border:false,
    	xtype: "panel",
    	height: 325,
    	width: 85,
    	bodyStyle:'padding:10 10 10 10',
    	items:[{
    		xtype:'panel',
    		border:false,
    		height:115
    	},{
   
    		xtype:'button',
    		minWidth:70,
    		text:'全删',
    		listeners:{
    			click:function(){
    				selStore.getRootNode().removeAll();
    			}
    		}   		
    	},{
    		xtype:'panel',
    		border:false,
    		height:10
    	},{
    		xtype:'button',
    		minWidth:70,
    		text:'移除',
    		listeners:{
    			click:function(){
    				selStore.getNodeById(_selTree.getSelectionModel().getSelection()[0].raw.id).remove();
    			}
    		}
    	}]
    }, {
    	layout: 'fit',
    	border:false,
        items:selTree,
    	columnWidth: 0.3
    }]
}); 
//-----------------------------------------------------------
/** 人员选择面板 */

function selectPanel(textName, _url){
	//var orgId = document.getElementById('orgId').value;

	var _store = Ext.create('Ext.data.TreeStore',{
		autoLoad: true,
		model:'Tree',
	    root: {
	        text: textName,
	        id:  orgId,
	        expanded: true
	    },
	    proxy: {
	    	type: 'ajax',
	    	url: _url,
	    	extraParams :{
	    		orgId:''
	    	}
	    //,
	    //	root:'list'
	    },
	    listeners : {
	   		beforeexpand : function(node, eOpts){  
				
       			//点击父亲节点的菜单会将节点的id通过ajax请求，将到后台  
                this.proxy.extraParams.orgId = node.raw.id;  
            }
	    }
	});
	
	var _tree = Ext.create('Ext.tree.Panel',{
	    store: _store,
	    rootVisible: true,
	    autoScroll : true,
	    containerScroll: true,
	    height:324,
		listeners:{  
			itemdblclick:function(view, record, item, idx, event, eOpts) {
            
				if(!_selStore.getNodeById(record.get('id'))){
					/*if(record.get('type')>0){
						
					} else {
						Ext.Msg.alert('提示','行业类型不可选');
					}*/
					_selTree.getRootNode().appendChild({
				    	id:record.get('id'),
				    	text:record.get('text'),
				    	leaf:true
				    });
				}
	    	}
	    	
	    }
	});
	
	//已经选中组织机构树
	var _selStore = Ext.create('Ext.data.TreeStore',{
		autoLoad: true,
	    root: {
	        text: textName,
	        id: '1',
	        expanded: true,
	        children: [{
	        	id: '2',
	        	text: 'qqqq'
	        }]
	    }
	});
	
	var _selTree = Ext.create('Ext.tree.Panel',{
		rootVisible: true,
	    store: _selStore,
	    height:324,
	    border:true,
	    listeners:{
	    	itemdblclick:function(view, record, item, idx, event, eOpts) {//tree双击事件
	    		record.remove();
	    	}
	    	
	    }
	});
	
	var _panel = Ext.create('Ext.Panel', {
		layout: 'border',
		width: 690,
	    height: 325,
		border:false,
		defaults: {
	        split: false,                 //是否有分割线
	        collapsible: false           //是否可以折叠
	    
	    },
	    items: [{
	    	border:false,
	    	bodyStyle: 'background:red;',
	    	region: 'west',
	        width: 300,
	        items:_tree
	    }, {
	    	border:false,
	    	bodyStyle: 'background:red;',
	    	region: 'center',
	    	xtype: "panel",
	    	bodyStyle:'padding:10 10 10 10',
	    	items:[{
	    		xtype:'panel',
	    		border:false,
	    		height:115
	    	},{
	   
	    		xtype:'button',
	    		minWidth:70,
	    		text:'全删',
	    		listeners:{
	    			click:function(){
	    				_selStore.getRootNode().removeAll();
	    			}
	    		}   		
	    	},{
	    		xtype:'panel',
	    		border:false,
	    		height:10
	    	},{
	    		xtype:'button',
	    		minWidth:70,
	    		text:'移除',
	    		listeners:{
	    			click:function(){
	    				_selStore.getNodeById(_selTree.getSelectionModel().getSelection()[0].raw.id).remove();
	    			}
	    		}
	    	}]
	    }, {
	    	border:false,
	    	xtype: "panel",
	    	region: 'east',
	        width: 300,
	        items:_selTree
	    }]
	}); 

	return {
		panel:_panel,
		store:_store,
		tree:_tree,
		selStore:_selStore,
		selTree:_selTree
		
	};
}

//任务委派

orgPanel  = selectPanel('组织列表', '/platform.shtml?method=orgTree'); 

//postPanel = selectPanel('职位列表', '/platform.shtml?method=postTree'); 
//var funPanel  = selectPanel('职能列表', '/workflow/platform.shtml?method=funTree'); 
rolePanel = selectPanel('角色列表', '/platform.shtml?method=roleTree'); 
//var userPanel = selectPanel('用户列表', '/workflow/platform.shtml?method=userTree'); 

//----------------------------------------------


function saveCandidate(){
	var curTab = tabs.getActiveTab();
	var catalog = 0;
	var candidateIds = '';
	switch(curTab.id){
		case "orgTab"://组织机构-1
			catalog = 1;
			$("#nodeactorType").val(catalog);
			orgPanel.selStore.getRootNode().eachChild(function(child){
				candidateIds += child.get('id')+ '-'+child.get('text')+',';
			});
			break;
		case "roleTab"://角色-2	
			catalog = 2;
			$("#nodeactorType").val(catalog);
			rolePanel.selStore.getRootNode().eachChild(function(child){
				candidateIds += child.get('id')+ '-'+child.get('text')+',';
			});
			break;
		case "userTab"://用户-3(特殊处理)
			catalog = 3;
			$("#nodeactorType").val(catalog);
			selStore.getRootNode().eachChild(function(child){
				candidateIds += child.get('id')+ '-'+child.get('text')+',';
			});
			break;
		/*case "postTab"://职位-4
			catalog = 4;
			$("#nodeactorType").val(catalog);
			postPanel.selStore.getRootNode().eachChild(function(child){
				candidateIds += child.get('id')+ '-'+child.get('text')+',';
			});
			break;*/
			
	}
	
	if(candidateIds != ''){
		candidateIds = candidateIds.substring(0,candidateIds.length-1);
		$("#nodeactorIds").val(candidateIds);
//		modifyXmlNodeAttribute(opts.curXmlNode, 'catalog', catalog);
//		modifyXmlNodeAttribute(opts.curXmlNode, 'candidateIds', candidateIds);
	}
	
}


});

/**
 * 
 */
function setCandidate(){
//	if(opts.curXmlNode == null) return false;
	
	var catalog = $("#nodeactorType").val();//opts.curXmlNode.getAttribute("catalog");
	var candidateIds = $("#nodeactorIds").val();//opts.curXmlNode.getAttribute("candidateIds");
	orgPanel.selStore.getRootNode().removeAll();
	rolePanel.selStore.getRootNode().removeAll();
	selStore.getRootNode().removeAll();//用户
//	postPanel.selStore.getRootNode().removeAll();
	
//	var cids = 	candidateIds;
	var _obj = null;
	var _store = null;
	if(catalog!="0"){
		switch(catalog){
			case "1"://组织机构-1
				_store = orgPanel.selStore;
				tabs.setActiveTab(0);
				break;
			case "2"://角色-2
				_store = rolePanel.selStore;
				tabs.setActiveTab(1);
				break;
			case "3"://用户-3
				_store = selStore;
				tabs.setActiveTab(2);
				break;	
			/*case "4"://职位-4
				_store = postPanel.selStore;
				tabs.setActiveTab(3);
				break;	*/	
		}
		if(candidateIds != ""){
			candidateIds = candidateIds.substring(0,candidateIds.length-1);
			var cids = 	candidateIds.split(",");
			for(var i = 0;i < cids.length; i++ ){
				if(cids[i]!=""){
					_obj = cids[i].split("-");
					if(!_store.getNodeById(_obj[0])){
						_store.getRootNode().appendChild({
					    	id:_obj[0],
					    	text:_obj[1],
					    	leaf:true
					    });
					}
				}
			}
		}
	}
	candidateWin.show();
	
}
