
function initUploader(opts){
	var $list=$("#fileList");   //这几个初始化全局的百度文档上没说明，好蛋疼。  
	var selBtn = $("#selBtn"); //上传附件
    var $btn =$("#ctlBtn");   //开始上传  

    var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档  
    var thumbnailHeight = 100;  
  //  alert('attaType==='+selBtn.attr("attaType"));
    var uploader = WebUploader.create({
        // swf文件路径
        swf: 're/js/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: 'attachment/upload?attaType='+selBtn.attr("attaType"),
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#selBtn',
        fileNumLimit: opts.fileNumLimit||10,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
	
    uploader.on( 'fileQueued', function( file ) {
    	
    	$list.append('<div id="' + file.id + '" class="attachment"><a class="fileName" href="javascript:;">' + file.name + '</a>'
    			+ ' &nbsp;&nbsp;<i class="state">等待上传...</i><i class="fa fa-close mar_L10"></i></div>'   );
    	uploader.upload();  
        
    });
 	// 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id );
        
      	$li.find('.state').text((percentage * 100).toFixed(2) + '%');
      	
		/**
        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
        */
        
    });
    uploader.on('uploadSuccess', function(file, resp) {
    	console.log('-------------uploadSuccess----------------');
    	console.log(file);
    	var title = $("#title").val();
    	if(title == '') {
    		$("#title").val(resp.fileName);
    	}
    	
    	//attId
        $('#'+file.id).find('i.state').text('已上传');
        $('#'+file.id).append(' <input type="hidden" id="attId" name="attId" value="'+resp.attId+'">');
       
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('i.state').text('上传出错');
    });

    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });
    
	$("#ctlBtn").click(function(){
 	   	console.log("上传......");  
	   	uploader.upload();  
	   	console.log("上传成功......");  
    });
	
	//删除附件
	$("#fileList").on("click", ".fa-close", function() {
		     //do something here
		uploader.reset();
		var attId = $(this).parent('div').attr('attId');
		$(this).parent('div').remove();
		if(attId) {
			$.ajax({
				  type: "post",
				  url: "attachment/delete",
				  data: {'attId':attId},
				  success: function(data){
					  if(data.success){
						  layer.msg('删除成功', {icon: 1,time: 2000 });
					  }
				  }
			});
		}
		
	});
	
	$("#fileList").on("click", ".fileName", function() {
		
		var attId = $(this).parent().find("#attId").val();
		if(attId) {
			document.location.href="attachment/down?attId="+attId;
		}
	});
}