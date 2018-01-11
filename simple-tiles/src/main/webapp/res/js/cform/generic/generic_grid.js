$(function(){
	
	//添加自表单
	$("#addSubForm").click(function(){
		var compHtml = '<tr>';
		$("#subField").find("th").each(function(){
			var cname = $(this).attr("cname");
			var clength = $(this).attr("clength");
			var comptype =  $(this).attr("comptype");
			if(cname) {
				compHtml += '<td';
				if(comptype == 'hidden') {
					compHtml +=  ' class="hiddens" '
				}
				compHtml += ' >';
				compHtml += '<input id="'+cname+'" name="'+cname+'" class="form-control" type="text" value="" maxlength="'+clength+'">';
				compHtml += '</td>';
			} else {
				compHtml += '<td class="tcenter"><a href="javascript:;" class="delComp"><i class="fa fa-trash t-delete"></i></a></td>';
			}
		});

		compHtml += '</tr>';
		//alert(compHtml);
		$("#formList").append(compHtml);

		//initCompTr();
		//initDict();
		
		var isflow = $("#isflow").val();
		if(!(isflow == undefined || isflow=="" || isflow == null)) {
			parent.initIframeHeight();
		}
	});
	
	$("#formList").on("click", ".delComp", function(){
		$(this).parents("tr").remove();
	});
});