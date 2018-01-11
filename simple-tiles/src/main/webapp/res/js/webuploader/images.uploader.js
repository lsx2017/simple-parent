
/**
 * webuploader附件上传工具
 * 需要webuploader.css、webuploader.js、Jquery配合使用
 * @author: liushx
 * @date: 2016-10-02
 */
$(function(){  
	
//	var $list=$("#fileList");   //这几个初始化全局的百度文档上没说明，好蛋疼。  
	var selBtn = $("#imgBtn"); //上传附件

    var uploader = WebUploader.create({
        // swf文件路径
        swf: 're/js/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: 'system/userrecord/upload?attaType='+selBtn.attr("attaType"),
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#imgBtn',
        fileNumLimit: 1,
     // 只允许选择文件，可选。
		accept : {
			title : 'Images',
			extensions : 'gif,jpg,jpeg,bmp,png',
			mimeTypes : 'image/*'
		},
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
	
    uploader.on( 'fileQueued', function( file ) {
    	//$("#userPhoto").attr("src", file.name);
    	/**
    	$list.append('<div id="' + file.id + '" class="attachment"><a class="fileName" href="javascript:;">' + file.name + '</a>'
    			+ ' &nbsp;&nbsp;<i class="state">等待上传...</i><i class="fa fa-close mar_L10"></i></div>'   );
    	
    	
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">等待上传...</p>' +
        '</div>' );
    	*/
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
    	console.log(resp);
    	
    	$("#userPhoto").val(resp._raw);
    	$("#userImg").attr("src", resp._raw);
       // $('#fileList').empty().append(' <input type="hidden" id="attId" name="attId" value="'+resp+'">');
       
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('i.state').text('上传出错');
    });

    /**
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });*/
    
	$("#ctlBtn").click(function(){
 	   //	console.log("上传......");  
	   	uploader.upload();  
	   	//console.log("上传成功......");  
    });
	/**
	//删除附件
	$("#fileList").on("click", ".fa-close", function() {
		     //do something here
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
	
*/
});  