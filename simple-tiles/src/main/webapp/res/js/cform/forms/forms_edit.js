$(function(){
	//$(".ibox-content").height(500);
	//$('#roleIds').multiselect();
	
	//initDictBox('dataType', 200, '请选择控件类型');
	//initDictBox('dataType', 350, '请选择控件类型');
	//$("")
//	$("input[name='dataType']").dictBox({
//		dictId:200,
//		placeholder:'请选择控件类型'
//	});
	
	jQuery.validator.addMethod("username", function(value, element){

		return this.optional(element) || /^\w+$/.test(value);
	}, "只能输入字母数字下划线");
	
	
	initDictBox('template', 366, '请选择表单模板');
	initCompTr();
	initDict();

	
	$(".form-group").on("click", ".delComp", function(){
		$(this).parents("tr").remove();
		var cid = $(this).parents("tr").find("#cid").val();
		if(cid) {
			$.ajax({
				type: "post",
				url: "cform/comp/delete",
				data: {cid:cid},
				success: function(data){
					layer.msg('删除成功', {icon: 1,time: 1000 }); 
				}
			});
		}
		
	});
	//------------------------------------------------
	$(".validates").hide();
	$(".forms").hide();
	$("#dbp").click(function(){
		$(".database").show();
		$(".forms").hide();
		$(".validates").hide();
	});
	
	$("#pps").click(function(){
		$(this).css("color", "red");
		$(".database").hide();
		$(".forms").show();
		$(".validates").hide();
	});
	
//	$("#vps").click(function(){
//		$(".database").hide();
//		$(".forms").hide();
//		$(".validates").show();
//	});

	$("#closeBtn").click(function(){
		top.closeDialog();
	});
	//^[0-9]*$  ="value=value;"
	
	$("#compTable").on("onkeyup", "#seq", function(){

		$(this).val($(this).val().replace("/[^\d]/g/",''))
	});
		
	$("#editForm").validate({
		//debug:true,
		rules : {
			tableTitle: {
				required: true,
				maxlength: 50
			},
			tableName: {
				required: true,
				username: true
			},
			template: {
				required: true
			}
		},
		messages : {
			tableTitle: {
				required: '此项为必须输入项',
				maxlength: '最多输入50个字'
			},
			tableName: {
				required: '此项为必须输入项'
			},
			template: {
				required: "请选择表单模板"
			}
		},
		errorPlacement: function(error, element) { 
			if(element.attr("type")=='hidden'){
				//$("").appendTo( element.parent()  );
			}
		 	error.appendTo(element.parent());                            //这里的element是录入数据的对象   
		},
		submitHandler : function(form) {
			//每行校验
			var tag = true;

			$("input[name=ctitle]").each(function(i, el){
		
				if($(this).val() == '') {
					tag = false;
					layer.msg('字段标题不能为空', {icon: 1,time: 2000 });
					el.focus();
					return false;
				}
			});
			
			$("input[name=cname]").each(function(i, el){
				if($(this).val() == '') {
					tag = false;
					layer.msg('字段名称不能为空', {icon: 1,time: 2000 });
					el.focus();
					return false;
				}
			});

			$("input[name=clength]").each(function(i, el){
				var dataTypeVal = $(this).parents("tr").find("#dataType").val();
	
				if(dataTypeVal=='varchar' || dataTypeVal=='bigint' || dataTypeVal=='int' || dataTypeVal=='char') {
					if($(this).val() == '') {
						tag = false;
						layer.msg('字段长度不能为空', {icon: 1,time: 1000 });
						el.focus();
						return false;
					} else if(isNaN($(this).val())) {
						tag = false;
						layer.msg('字段长度只能为数字', {icon: 1,time: 2000 });
						el.focus();
						return false;
					}
				}
			});
			
			$("input[name=seq]").each(function(i, el){
				
				if($(this).val() == '') {
					tag = false;
					layer.msg('排序不能为空', {icon: 1,time: 1000 });
					el.focus();
					return false;
				} else if(isNaN($(this).val())) {
					tag = false;
					layer.msg('排序只能为数字', {icon: 1,time: 2000 });
					el.focus();
					return false;
				}
			});
		
			var isExistPk = false;
			$("input[name=isPk]").each(function(i, el){
		
				if($(this).val() == 1) {
					isExistPk = true;
				} 
			});
			if(!isExistPk) {//不存在主键
				tag = false;
				layer.msg('必须设置主键', {icon: 1,time: 2000 });
				return false;
			}
			
			if(tag) {
				var loadIndex = layer.load(2, {shade: [0.5, '#fff']});
				$(form).ajaxSubmit(function(data) {
					layer.close(loadIndex);  
					if(data.success){
						layer.msg('保存成功', {icon: 1,time: 1000 }, function(index){
							top.closeDialog(true);
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

		}
	});
	
	
	$("#addComp").click(function(){

		var compHtml = '<tr>';
		compHtml += '<td>';
		compHtml += '<input type="hidden" id="cid" name="cid" value=""/>';
		compHtml += '<input type="hidden" id="compStatus" name="compStatus" value="1"/>';
		compHtml += '<input id="ctitle" name="ctitle" class="form-control" type="text" value="" maxlength="50">';
		//compHtml += '<div for="ctitle" style="display:none;">亲输入字段标题</div>';
		compHtml += '</td>';
		compHtml += '<td><input id="cname" name="cname" class="form-control" type="text" value="" maxlength="50"></td>';
		//<!-- 数据库属性 -->
		compHtml += '<td class="database">';
		compHtml += '<select id="dataType" name="dataType" dictId="200" class="datadict form-control">';

		//compHtml += $("#dataType").html();
		compHtml += '</select>';
		compHtml += '</td> ';
		compHtml += '<td class="database"><input type="text" id="clength" name="clength" class="form-control" value="" maxlength="4"></td>';
		compHtml += '<td class="database"><input type="text" id="dvalue" name="dvalue" class="form-control" maxlength="10"></td>';
		compHtml += '<td class="database">';
		compHtml += '<input type="hidden" id="isPk" name="isPk" value="0"><input type="radio" name="isPkRadio">';
		compHtml += '</td>';
		compHtml += '<td class="database">';
		compHtml += '<input type="hidden" id="isMust" name="isMust" value="0"><input id="isMustCheck" name="isMustCheck" type="checkbox">';
		compHtml += '</td>';
		//<!-- 表单属性 -->
		compHtml += '<td class="forms">';
		compHtml += '<select id="compType" name="compType" dictId="350" class="datadict form-control">';
		//compHtml += $("#compType").html();
		compHtml += '</select>';
		compHtml += '</td>';
		compHtml += '<td class="forms"><input type="text" id="compTypeValue" name="compTypeValue" class="form-control" maxlength="20"></td>';
		compHtml += '<td class="forms">';
		compHtml += '<input type="hidden" id="listDisplay" name="listDisplay" value="0">';
		compHtml += '<input type="checkbox" id="listDisplayCheck" name="listDisplayCheck">';
		compHtml += '</td>';
		compHtml += '<td class="forms">';
		compHtml += '';
		compHtml += '</td>';
		compHtml += '<td class="forms">';
		compHtml += '<input type="hidden" id="searchDisplay" name="searchDisplay" value="0">';
		compHtml += '<input type="checkbox" id="searchDisplayCheck" name="searchDisplayCheck"></td>';
		compHtml += '<td class="forms">';
		compHtml += '<input type="hidden" id="readOnly" name="readOnly" value="0"><input type="checkbox" id="readOnlyCheck" name="readOnlyCheck">';
		compHtml += '</td>';
		//<!-- 校验属性 -->
		compHtml += '<td class="validates"><input id="searchType1" name="searchType1" class="form-control" type="text"></td>	';
		compHtml += '<td class="validates"></td>';
		compHtml += '<td><input type="text" id="seq" name="seq" class="form-control" maxlength="3"/></td>';
		compHtml += '<td class="tcenter"><a href="javascript:;" class="delComp"><i class="fa fa-trash t-delete"></i></a></td>';
		compHtml += '</tr>';
		$("#compTable").append(compHtml);
		
		$(".validates").hide();
		$(".forms").hide();
		initCompTr();
		initDict();
	});
});


function initDict(){
	$(".datadict").each(function(){
		var dictId = $(this).attr("dictId");

		var _sel = $(this);
		$.ajax({
			  type: "post",
			  url: "system/datadict/dictlist",
			  data: {dictId:dictId},
			  success: function(data){
			
				  _sel.empty();
				  $.each(data, function(){

					  if(this.dictCode == _sel.attr("dataCode")) {
						  _sel.append('<option value="'+this.dictCode+'" selected="selected">'+this.dictName+'</option>'); 
					  } else {
						  _sel.append("<option value='"+this.dictCode+"'>"+this.dictName+"</option>"); 
					  }
					  
				  });

			  }
		});

	});
}

// 保存
function initCompTr() {
	
	$("[name='isPkRadio']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="isPkRadio"]').on('switchChange.bootstrapSwitch', function(event, state) {

		if(state) {
			$("input[name=isPk]").val(0)
			$(this).parents("td").find("#isPk").val(1);
		} else {
			$(this).parents("td").find("#isPk").val(0);
		}

	});
	
	//---------------------------------------
	$("[name='isMustCheck']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="isMustCheck"]').on('switchChange.bootstrapSwitch', function(event, state) {

		if(state) {
			$(this).parents("td").find("#isMust").val(1);
		} else {
			$(this).parents("td").find("#isMust").val(0);
		}
	});
	//---------------------------------------
	$("[name='listDisplayCheck']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="listDisplayCheck"]').on('switchChange.bootstrapSwitch', function(event, state) {
		if(state) {
			$(this).parents("td").find("#listDisplay").val(1);
		} else {
			$(this).parents("td").find("#listDisplay").val(0);
		}
	});
	//---------------------------------------
	$("[name='formDisplayCheck']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="formDisplayCheck"]').on('switchChange.bootstrapSwitch', function(event, state) {
		if(state) {
			$(this).parents("td").find("#formDisplay").val(1);
		} else {
			$(this).parents("td").find("#formDisplay").val(0);
		}
	});
	//---------------------------------------
	$("[name='searchDisplayCheck']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="searchDisplayCheck"]').on('switchChange.bootstrapSwitch', function(event, state) {
		if(state) {
			$(this).parents("td").find("#searchDisplay").val(1);
		} else {
			$(this).parents("td").find("#searchDisplay").val(0);
		}
	});
	//---------------------------------------
	$("[name='readOnlyCheck']").bootstrapSwitch({
		//size:'mini',
		radioAllOff:true,
		//inverse:true,
		onText:'是',
		offText:'否'
	});
	
	$('input[name="readOnlyCheck"]').on('switchChange.bootstrapSwitch', function(event, state) {
		if(state) {
			$(this).parents("td").find("#readOnly").val(1);
		} else {
			$(this).parents("td").find("#readOnly").val(0);
		}
	});
}