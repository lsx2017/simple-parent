$(function (){
	$('#flowDetailTab').tabs({ 
		fit:true,
		border:false
	});
	
	$('input[type=radio]').click(function(){
		if(this.name.indexOf("importance")==0){
			$("#tempImportance").val("1");
		}
	});
	
	$("#list_data").datagrid({
		iconCls:'icon-edit',//图标
		title:"客户信息管理",
		border:0,
		nowrap:true,
		striped:true,
		fitColumns:true,
		fit: false,//自动大小
		url : "constomer.shtml?method=list",
		method:'post',
		idField:'ID',
		columns:[[
		          {field:'ID',hidden:true},
		          {field:'CK',checkbox:true },
		          {title:'客户姓名',field:'NAME',align:'left', width:100},
		          {title:'客户所属单位',field:'UNIT_NAME',align:'left', width:100},
		          {title:'客户所属部门',field:'DEPT_NAME',align:'left', width:100},
		          {title:'职务',field:'POST_NAME',align:'left', width:100},
		          {title:'联系电话',field:'PHONE',align:'left', width:100},
		          {title:'电子邮箱',field:'EMAIL',align:'left', width:100}
	          ]],
		toolbar:'#tb',
		singleSelect:false,
		pagination:false,
		rownumbers:true,
		checkOnSelect : true,
		selectOnCheck : false,
		onCheck:function(rowIndex,rowData) {
			addConstomer(rowData);
		},
		onUncheck:function(rowIndex,rowData) {
			delConstomer(rowData);
		},
		onCheckAll:function(rows) {
			addAllConstomer(rows);
		},
		onUncheckAll:function(rows) {
			unCheckAllConstomer(rows);
		},
		onLoadSuccess:function(data) {
			var customers = $("#customers").val();
			var selectContomers = "";
			if(customers!=null&&customers!=""){
				var ids = customers.split(",");
				var rows = $("#list_data").datagrid('getRows');
				$.each(rows, function(i, val){
					for (var j=0; j<ids.length; j++) {
						if(ids[j]==val.ID){
							selectContomers = selectContomers + "," + val.NAME;
							$("#list_data").datagrid('checkRow',i);
						}
					}
				});
				selectContomers = selectContomers.substring(1, selectContomers.length);
				$("#selectContomers").html(selectContomers);
			}
		}
	});
	
	$("#projecteditFrom").validate({
		errorPlacement: function(error, element) {
			error.appendTo( element.parent().parent().find('[name=err]'));
		},
		rules:{
			name:{
				required:true
			},

	        productId:{ 
		        required:true
	        },
	        regionId:{ 
		        required:true
	        },
	        phase:{ 
		        required:true
	        }
		},
		messages:{
			name:{
				required:"项目名称不能为空"
			},
		    productId:{
				required:"请选择产品"
		    },
		    regionId:{
				required:"请选择区域"
		    },
		    phase:{
				required:"请选择阶段"
		    }
		},
		submitHandler : function(form) {
		
			var projectMoney=$("#projectMoney").val();
			var flag = true;
			if(projectMoney!=null&&projectMoney!=''&&(!/^[0-9]\d{0,9}(\.\d{1,2})?$/.test(projectMoney))){
				$.messager.alert('提示', "项目回款范围为10位正数，可以包含两位小数");
				flag = false;
			}
			
			var profit=$("#profit").val();
			if(profit!=null&&profit!=''&&(!/^[0-9]\d{0,9}(\.\d{1,2})?$/.test(profit))&&flag){
				$.messager.alert('提示', "预估毛利范围为10位正数，可以包含两位小数");
				flag = false;
			}
			
			var tempImportance = $("#tempImportance").val();
			if(tempImportance=='0'){
				$.messager.alert('提示', "请选择项目重要性");
				flag = false;
			}
			/*var importance =  $('input[name="importance"][checked]');
			if (importance.length == 0) {
				$.messager.alert('提示', "请选择项目重要性");
				flag = false;
			}*/
		
			if(flag){
				$(form).ajaxSubmit(function(data) {
					if (data.success) {
						$.messager.alert('提示',"保存成功",'info', function(){
							parent.closeDialog();
						});
					} else {
						$.messager.alert('提示',"保存失败");
					}
				});
			}
		}
	});
	
	$("#closeBtn").click(function(){
		parent.closeDialog();
	});
	

	$("#productId").combobox({
		onSelect: function (rec) {	

			references(rec.value);
		}
	});
	
	var defaultproductid=$("#defaultproductid").val();
	
	//$("#productId").val(${product.productId});
	$("#productId").combobox('setValue', defaultproductid);

	function references(productId){
		if(productId != "-1"){
			$.ajax({
				  type: "post",
				  url: "project.shtml?method=getNeedDictInfo",
				  data: {productId:productId},
				  success: function(data){
					  //1.cleanr
					  //2.append
					//   $("#regionId").combobox('clear');
					  var d = [];
					  var defaultid;
					  for(var i=0; i<data.length; i++) {
						  if(data[i].id==$("defaultregionid").val()){
							  defaultid=data[i].id
						  }
					 		 d[i] = {id: data[i].id,
								text:data[i].name	
							}
					  }
					  $("#regionId").combobox('loadData', d);
					  $("#regionId").combobox('select', defaultid);
				  }
			});
		} else {
			
		}
	}
	
});

function _save(){
	var candidateIds = '';
	selStore.getRootNode().eachChild(function(child){
		candidateIds += child.get('id')+',';
		$("#userIds").val(candidateIds);
	}); 
	
//	var customers_ = $("#customersList").combobox('getValues');
//	$("#customers").val(customers_);
	$("#subBtn").click();
}

function addConstomer(rowData) {
	
	var customers = $("#customers").val();
	var selectContomers = $("#selectContomers").html();
	if(customers!=null&&customers!=''){
		var ids = customers.split(",");
		var flag = "1";
		for (var i=0; i<ids.length; i++) {
			if(ids[i]==rowData.ID){
				flag = "0";
				break;
			}
		}
		if(flag == "1"){
			customers = customers + "," + rowData.ID;
			selectContomers = selectContomers + "," + rowData.NAME;
		}
		$("#customers").val(customers);
		$("#selectContomers").html(selectContomers);
	}else{
		$("#customers").val(rowData.ID);
		$("#selectContomers").html(rowData.NAME);
	}
}

function delConstomer(rowData) {
	var customers = $("#customers").val();
	var selectContomers = $("#selectContomers").html();
	
	if(customers!=null&&customers!=''){
		var temp = "";
		var tempName = "";
		var ids = customers.split(",");
		var names = selectContomers.split(",");
		for (var i=0; i<ids.length; i++) {
			if(ids[i]==rowData.ID){
				continue;
			}
			temp += ids[i]+ ",";
			tempName += names[i] + ",";
		}
		if(temp!=""){
			temp = temp.substring(0, temp.length-1);
			tempName = tempName.substring(0, tempName.length - 1);
		}
		$("#customers").val(temp);
		$("#selectContomers").html(tempName);
		
	}else{
		
	}
}

function addAllConstomer(rows) {
	var customers = $("#customers").val();
	var selectContomers = $("#selectContomers").html();
	var num=rows.length;
	if(num>0){
		if(customers!=null&&customers!=""){
			var ids = customers.split(",");
			for (var j=0; j<num; j++) {
				var flag = "1";
				for (var i=0; i<ids.length; i++) {
					if(ids[i]==rows[j].ID){
						flag = "0";
						break;
					}
				}
				if("1"==flag){
					customers = customers+ ","+rows[j].ID;
					selectContomers = selectContomers + "," +rows[j].NAME;
				}
			}
		}else{
			for(var i=0;i<num;i++){
				var id=rows[i].ID; 
				customers += id+",";
				selectContomers += rows[i].NAME;
			}
			if(customers!=null&&customers!=""){
				customers = customers.substring(0, customers.length-1);
				selectContomers = selectContomers.substring(0, selectContomers.length - 1);
			}
		}
		$("#customers").val(customers);
		$("#selectContomers").html(selectContomers);
	}
}

function unCheckAllConstomer(rows) {
	var customers = $("#customers").val();
	var selectContomers = $("#selectContomers").html();
	var num=rows.length;
	if(num>0&&customers!=null&&customers!=""){
		var temp = "";
		var tempName = "";
		var ids = customers.split(",");
		var names = selectContomers.split(",");
		for (var i=0; i<ids.length; i++) {
			var flag = "1";
			for (var j=0; j<num; j++) {
				if(ids[i]==rows[j].ID){
					flag = "0";
					break;
				}
			}
			if("0"==flag){
				continue;
			}else{
				temp += ids[i] + ",";
				tempName += names[i] + ",";
			}
		}
		if(temp!=""){
			temp = temp.substring(0, temp.length-1);
			tempName = tempName.substring(0, tempName.length - 1);
		}
		$("#customers").val(temp);
		$("#selectContomers").html(tempName);
	}
}