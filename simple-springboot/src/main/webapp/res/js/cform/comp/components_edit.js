$(function(){
	
	$("#closeBtn").click(function(){
		parent.closeEditPanel();
	});
	
	//$("#dataType")
	initDictBox('dataType', 200, '请选择数据类型');
	initDictBox('compType', 350, '请选择控件类型');
	$('#editForm').validate({
		rules : {
			ctitle: {
				required: true,
				maxlength: 50
			},
			cname:{
				required: true,
				maxlength : 100
			},
			clength:{
				maxlength : 3,
				number:true
			},
			dataType:{
				required: true
			},
			remarks : {
				maxlength : 200
			}
		},
		messages : {
			ctitle: {
				required: '请输入字段名称',
				maxlength: '最多输入50个字'
			},
			cname:{
				required: '请输入英文名称',
				maxlength : '最多输入50个字'
			},
			clength:{
				maxlength : '最多输入3个字',
				number: '请输入合法数字'
			},
			dataType:{
				required: '请选择字段类型'
			},
			remarks : {
				maxlength: '最多输入200个字'
			}
		},
		submitHandler : function(form) {
			$(form).ajaxSubmit(function(data) {
				if(data.success){
					//layer.msg('closeDialog', {icon: 1,time: 1000 },); 
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
			});
		}
	});
});