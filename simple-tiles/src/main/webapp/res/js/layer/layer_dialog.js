/**
 * jquery easy ui对话窗口层的封装，可以让子框架页面弹出的对话框突破frame的限制，在最顶层页面打开。
 * 用法：
 *     1. 把这个js放在框架的最顶层页面
 *     2. 子框架页中要弹出或者关闭窗口，需要通过top对象来调用，例如：top.openDialog('视频回放',310,400,'${rc.getContextPath()}/admin/uam/systemInterface/systemInterfaceWindows.jhtml?interfaceId='+interfaceId);
 */
(function(){
	/**
	 * 删除数组元素
	 * @param {Object} index 数组下标从0开始
	 * @memberOf {TypeName} 
	 * @return {TypeName} 
	 */
	Array.prototype.remove=function(dx) {
	　　if(isNaN(dx)||dx>this.length){return false;}
	　　for(var i=0,n=0;i<this.length;i++)
	　　{
	　　　　if(this[i]!=this[dx])
	　　　　{
	　　　　　　this[n++]=this[i]
	　　　　}
	　　}
	　　this.length-=1
	}
	
	var dialogs = [];
	
	//获取当前菜单对应的frame
	//可以通过返回的iframe对象调用其中的方法或查找dom节点
	//调用frame中的方法：iframe.contentWindow.methodName();
	//查找frame中的dom：$('#objID', iframe);
	window.getCurrentIndex = function(){
		var curIndex = dialogs[dialogs.length-1];
		 
		dialogs.remove(dialogs.length-1);
		return curIndex;
		/**
		var nav = $('.nav-tab-now');
		if(nav.size() != 1){
			return null;
		}else{
			var menuid = nav.attr('id').substring('nav-tab'.length);
			return $('#iframe-box' + menuid).get(0);
		}
		*/
	};
	
	//获取弹出层里面的iframe
	//index：代表的是要获取哪一个弹出层，从0开始，表示获取最后一个弹出层，1代表倒数第二个，默认为0
	window.getDialogIFrame = function(index){
		if(arguments.length == 0){
			index = 0;
		}
		
		index  = dialogs.length - index - 1;
		
		return $('iframe', dialogs[index]).get(0);
	};
	
	//弹出一个对话窗口层
	//title: 标题
	//height: 窗口高度
	//width: 窗口宽度
	//url: 页面的url路径
	//config: 其他的配置，任何符合easyui dialog组件的配置项都可以传进来，这个参数可为空。
	//dsize  1:小框440*250, 2:中框620*400 3:大框980*560, 4:最大化  
	//{area：【】}
	window.openDialog = function(title, url, dsize, config){

		var _area = ['620px', '400px'];
		switch(dsize){
			case 1:
				_area = ['440px', '250px'];
				break;
			case 2:
				_area = ['620px', '400px'];
				break;
			case 3:
				_area = ['980px', '560px'];
				break;  
			case 4:
				_area = ['100%', '100%'];
				break; 				
			default:
			  
		}
	
		if (title == null || title == '') {
			title = false;
		};
		
		var opt = {
			type: 2,
			fix: false, //不固定
			shade:0.4,
			area: _area,
			//full:true,
			title: title,
			content: url
		};
		
		if(config){
			for(i in config){
				opt[i] = config[i];
			}
		}
			
		var index = layer.open(opt);
		/**
		if(dsize == 4) {

			layer.full(index);
		}*/
		
		dialogs.push(index);

	};
	
	//关闭最后一个打开的对话窗口层
	//fn: 关闭时候的回调方法，可为空
	window.closeDialog = function(refreshFrame){
		
		if(refreshFrame){
//			getCurrentTabIFrame().contentWindow.location.reload();
			var centerFrame = document.getElementById('centerFrame');
			console.log('----------closeDialog----------------');
			console.log(centerFrame);
			var contentFrame = centerFrame.contentWindow.document.getElementById('contentFrame');
			$("#queryBtn", contentFrame.contentWindow.document.body).click();
			/** 调用Iframe的searchList刷新方法，异步刷新datagrid */
			//getCurrentTabIFrame().contentWindow.searchList();
			/*
			var name = $("#searchBtn", getCurrentTabIFrame().contentWindow.document.body).attr('href');
			$("#searchBtn", getCurrentTabIFrame().contentWindow.document.body).click();
			*/
			contentFrame
		}
		//var index = top.layer.getFrameIndex(window.name);
		//top.close(index);
		layer.close(getCurrentIndex());
		//layer.closeAll();
	}
	
})();