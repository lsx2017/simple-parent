function initFormHTML(){
	$.ajax({
		url:"",
		type:"post",
		dataType:'json',
		data:{},
		success:function(result){

			if(result) {
				$.each(result, function(i, el){
					
				});
			}
			/**
				treeObj = $.fn.zTree.init($("#"+opts.ztreeId), ztreeSetting, result);
				var rootNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);  
				treeObj.selectNode(rootNode);//选择点
				treeObj.setting.callback.onClick(null, treeObj.setting.treeId, rootNode);//调用事件  
			 */
		}
	});
};
$(function(){
	
	initFormHTML();
	
	$(".dates").datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
    	format: 'yyyy-mm-dd',
    	autoclose: 1,
    	todayBtn:  1,
		language:  'zh-CN'
    });
	
	$(".datadict").each(function(){
		var dictId = $(this).attr("dictId");
		var fieldId = $(this).attr("id");
		console.log('dictId==='+dictId+'	&&fieldId=='+fieldId);
		initDictBox(fieldId, dictId, '请选择');
	});
	
	$("#editForm").validate({
		//debug:true,
		rules : {
			username: {
				required: true,
				maxlength: 25
			}
		},
		messages : {
			username: {
				required: '此项为必须输入项',
				maxlength: '最多输入25个字'
			}
		},
		errorPlacement: function(error, element) { 
			if(element.attr("type")=='hidden'){
				$("<span style=' margin-right: 20px'>&nbsp;</span>").appendTo( element.parent()  );
			}
		 	error.appendTo(element.parent());                            //这里的element是录入数据的对象   
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit(function(data) {
				//$("#formId").val(data);
				//$("#flowSubBtn").click();
				var isflow = $("#isflow").val();
				if(isflow == 0) {
					if(data){
						layer.msg('保存成功', {icon: 1,time: 1000 }, function(index){
							parent.closeEditPanel();
						}); 
					}else{
						if (data.message != null && data.message != "") {
							alert(data.message);
						} else {
							alert("保存失败");
						}
					}
				} else {
					//回调返回流程申请方法
					parent.formSubCallback(data);
				}

			});
		}
	});

	$("#subBtn").click(function(){
		//1、提交表单
		$("#formSubBtn").click();
	});
	
	$("#closeBtn").click(function(){
		parent.closeEditPanel();
	});
	
	var isflow = $("#isflow").val();
	if(!(isflow == undefined || isflow=="" || isflow == null)) {
		setTimeout("parent.initIframeHeight();",10);//延时3秒 
	}


});

/**
$("#flowForm").validate({
	rules : {
		email: {
			email: true,
			maxlength: 50
		},
		descript : {
			maxlength : 100
		}
	},
	messages : {
		email: {
			email: '请输入正确格式的电子邮件',
			maxlength: '最多输入50个字'
		},
		descript : {
			maxlength: '最多输入100个字'
		}
	},
	submitHandler : function(form) {
		$(form).ajaxSubmit(function(data) {
			layer.msg('提交成功', {icon: 1,time: 1000 }, function(index){

				parent.closeEditPanel();
			}); 
		});
	}
});

*/


