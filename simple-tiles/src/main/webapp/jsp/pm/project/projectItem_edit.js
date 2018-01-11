
$(function() {
	$('#win').window('close');
	var id = $("#id").val();
	
	//提交表单
	$("#btnSave").click(function() {
		var roles = $("#roleList").combobox('getValues');
		$("#role").val(roles);
		$("#subBtn").submit();
	});
	
	jQuery.validator.addMethod("numLimit", function(value, element) {   
	    return /^-?\d+(\.\d{1,1})?$/.test(value) && value.length<5;   
	    
	 }, "工时范围为4位非零的正数");
	 
	jQuery.validator.addMethod("numRule", function(value, element) {   
	    return /^(0|[1-9][0-9]*)$/.test(value) && value.length<5;   
	    
	 }, "工时范围为4位非零的正数");
	
	$("#editForm").validate({
		errorPlacement: function(error, element) { //指定错误信息位置
			if(element[0].name=='step' || element[0].name=='seq' || element[0].name=='day' || element[0].name=='hourt'){
        		error.appendTo( element.parent().parent().find('[name=err]'));
        	}else {
        		error.insertAfter(element); 
        	}
 		},
		rules : {
			name : {
				required : true,
				maxlength:25,
				remote:{
	                type:"POST",
	                url:"project.shtml?method=checkName",
	                data:{
	                  name:function(){return $("#name").val();},
	                  id:function(){return $("#id").val();}
	                } 
	            } 
			},
			step: {
				required : true
			},
			descript:{
				maxlength:2000
			},			
			note:{
				maxlength:2000
			},
			day : {
				required : true,
				numLimit : true
			},
			hourt : {
				required : true,
				numLimit : true
			},
			seq : {
				required : true,
				numRule : true
			}
		},
		messages : {
			name : {
				required : "不能为空请录入",
				maxlength: "最多可输入25个字符",
				remote:"事项名称已存在"
			},
			step: {
				required : "不能为空请录入"
			},
			descript:{
				maxlength: "最多可输入2000个字符"
			},
			note:{
				maxlength: "最多可输入2000个字符"
			},
			day : {
				required : "不能为空请录入",
				numLimit : "请输入正数"
			},
			hourt : {
				required : "不能为空请录入",
				numLimit : "请输入正数"
			},
			seq : {
				required : "不能为空请录入",
				numRule : "请输入正数"
			}
		},
		submitHandler : function(form) {
			
			$("body").mask();
			var role = $('#role').val();
			if(role == "") {
				$("body").unmask();
				$.messager.alert('提示',"角色不能为空！");
			} else {
				$(form).ajaxSubmit(function(data) {
					if (data.success) {
						$("body").unmask();
						$.messager.alert('提示',"保存成功",'info', function(){
							//top.closeDialg(true);
							parent.closeDialg();
						});
					} else {
						$.messager.alert('提示',"保存失败");
					}
				});
			}
		}
	});

	$("#closeBtn").click(function() {
		//top.closeDialg(true);
		parent.closeDialg();
	});
	
	
});

