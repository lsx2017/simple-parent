(function ($) {
	
	
    $.fn.extend({
        "dictBox": function (config) {
        	//alert(config.dictId);
        	var idName = $(this).attr("name");
        	//2、后台数据字典列表填充内容
        	$.ajax({
         		cache: false,
        		type: "POST",
        		url: "system/datadict/dictlist",
        		data: "dictId="+config.dictId,
        		dataType: "json",
        		error :	function(){
        			console.log('服务器异常，获取数据失败！');
        		},
        		success : function(data){
        			console.log('----------------data-------------');
        			
        			var boxBody = '<ul id="'+idName+'Body" class="layui-form-select" style="display: none;">';
        			$.each(data, function(i, el){
        				/**
        				console.log('el.dictId=='+typeof el.dictCode);
        				console.log('idNameVal=='+typeof idNameVal);
        				if(el.dictCode==idNameVal) {
        					$("#"+idName+"Name").val(el.dictName)
        					opts += '<li lay-value="'+el.dictCode+'" class="drop-on">'+el.dictName+'</li>';
        				} else { 
        					
        				}*/
        				
        				boxBody += '<li lay-value="'+el.dictCode+'">'+el.dictName+'</li>';
        				
        			});
        			boxBody += '</ul>';
        			//alert(boxBody);
        			//$(""+idName+"Body").empty();
        			$("body").append(boxBody);
        			//console.log('width::'+$("#"+idName+"Name").width());
        			//$(""+idName+"Body").width(200+24);//$("#"+idName+"Name").width()
        			
        		}
        	});
        	//var dictBody = $(""+idName+"Body");
        	//alert(dictBody.html());
        	
        	$(this).each(function(){
        		console.log('val=='+$(this).val());
        		var boxHead = '<div class="drop-box" curbox=0 style="position:relative;">'
        			boxHead+= '<input type="text" id="'+idName+'Name" name="'+idName+'Name" placeholder="';
        			boxHead+= config.placeholder+'" readonly class="form-control"';
        			if($(this).val()) {
        				boxHead+=' value="'+$(this).val()+'"';
        			}
        			boxHead+='>';
        			boxHead+= '<div class="layui-edge"><i class=" fa fa-caret-down"></i></div>';
        			boxHead+= '</div>';
        		$(this).parent().append(boxHead);	
        	});
        	var idBox = $(".drop-box");
        	
        	idBox.click(function(){
        		idBox.attr("curbox", "0");
        		$(this).attr("curbox", "1");
        		console.log('--------------idBox.click-----------------');
        		//console.log($(""+idName+"Body").html());
        		$(this).find("i").removeClass("fa-caret-down").addClass("fa-caret-up");
        		var cityObj = $(this);
        		var cityOffset = $(this).position();
        		console.log("val===="+$(this).text());
        		var dictVal = $(this).find("input").val();
        		$("#"+idName+"Body").find("li").removeClass('drop-on');
        		$("#"+idName+"Body").find("li").each(function(i, el){
    				//console.log('el.dictId=='+typeof el.dictCode);
    				//console.log('idNameVal=='+$(this).text());
    				if(dictVal ==$(this).attr("lay-value")) {
    					$(this).addClass('drop-on');
    				}
    				/**
    				if(el.dictCode==idNameVal) {
    					$("#"+idName+"Name").val(el.dictName)
    					opts += '<li lay-value="'+el.dictCode+'" class="drop-on">'+el.dictName+'</li>';
    				} else { 
    					opts += '<li lay-value="'+el.dictCode+'">'+el.dictName+'</li>';
    				}*/
    				
    			});
    			
    			
        		$("#"+idName+"Body").width($("#"+idName+"Name").width()+24);//
        		$("#"+idName+"Body").css({left:cityOffset.left + "px", top:cityOffset.top + (cityObj.outerHeight()+3) + "px"});
        		$("#"+idName+"Body").slideDown("fast");
        		$("body").bind("mousedown", onBodyDown);
        	});
        	
        	$("#"+idName+"Name").css("cursor","pointer");
        	$("#"+idName+"Body").on('click', "li", function(){
        	
        		$("#"+idName + "[curbox=1]").val($(this).attr("lay-value"));
        		$("#"+idName+"Name").val($(this).text());
        		idBox.find("i").removeClass("fa-caret-up").addClass("fa-caret-down");
        		//$(""+idName+"Body").find("li").removeClass("drop-on");
        		//$(this).addClass("drop-on");
        		hideSelectMenu();
        	});

        	function hideSelectMenu() {
        		$(""+idName+"Body").fadeOut("fast");
        		$("body").unbind("mousedown", onBodyDown);
        	}
        	
        	function onBodyDown(event) {
        		if (!(event.target.id == (idName+"Name") || event.target.id == (idName+"Body") || $(event.target).parents(".layui-form-select").length>0)) {
        			hideSelectMenu();
        		}
        	}
        }
    });
})(jQuery);