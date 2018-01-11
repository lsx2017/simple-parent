<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>${exDocs.docsName}</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/res/js/editormd/css/editormd.css" />
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon" />
<style type="text/css">
img {display:block; margin:0 auto;}
*{
	/*font-size: 14px;*/
	line-height: 28px;
}
strong{
	color:red;
}

</style> 
  </head>
  
<body>

<form id="editFrom" action="<%=basePath%>/explorer/save" method="post">
<input type="hidden" id="docsId" name="docsId" value="${exDocs.docsId}">
<input type="hidden" id="parentId" name="parentId" value="${exDocs.parentId}">
<input type="hidden" id="docsName" name="docsName" value="${exDocs.docsName}">
<div style="text-align: center; margin:0 auto; ">
	<div id="test-editormd" style="width:100%; margin:0 auto;text-align: left;">
		<textarea id="content"  name="content" style="display:none;">${exDocs.content}</textarea>
	 </div>
</div>

</form>

<script type="text/javascript" src="<%=basePath%>/res/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/editormd/marked.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/editormd/editormd.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/editormd/plugins/image-dialog/image-dialog.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=basePath%>/res/js/layer/layer.js"></script>
<script type="text/javascript">
function saveRep(){
	$("#editFrom").ajaxSubmit({  
 		type:"post",  //提交方式  
       	dataType:"json", //数据类型  
          //url:"${basePath}/login.action", //请求url  
       	success:function(data){ //提交成功的回调函数  
       		if((typeof data) == 'string') {
       		   data = $.parseJSON(data);
     	   }
       	   if (data.success) {
  					//alert('保存成功');
       		   layer.msg('保存成功',{icon: 1, time: 2000});
       		   //return true; 
       	   }
      	}  
	});
}
document.onkeydown = function(e){
	if (e.keyCode == 83 && (navigator.platform.match("Mac") ? e.metaKey : e.ctrlKey))      {
        e.preventDefault();
        saveRep();
	}
}
var testEditor;
$(function() {
	testEditor = editormd("test-editormd", {
    	width   : "100%",
       	height  : 640,
        //syncScrolling : "single",
        path    : "../res/js/editormd/lib/",
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : "/simple-repository/attachment/upload",
//        onload : function() {
//        	testEditor.fullscreen();
//        	testEditor.previewing();
//        },
        toolbarIcons : function() {
            // Or return editormd.toolbarModes[name]; // full, simple, mini
            // Using "||" set icons align right.
            return [
            	"undo", "redo", "|", 
            	"bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|", 
            	"h1", "h2", "h3", "h4", "h5", "h6", "|", 
            	"list-ul", "list-ol", "hr", "|",
            	"link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "html-entities", "pagebreak", "|",
           		"goto-line", "watch", "preview", "fullscreen", "clear", "search","markdownSave"
         	]
        },
        toolbarIconsClass : {
      		markdownSave : "fa-save"  // 指定一个FontAawsome的图标类
        },
        // 自定义工具栏按钮的事件处理
        toolbarHandlers : {
            
             // @param {Object}      cm         CodeMirror对象
             // @param {Object}      icon       图标按钮jQuery元素对象
             // @param {Object}      cursor     CodeMirror的光标对象，可获取光标所在行和位置
             // @param {String}      selection  编辑器选中的文本
             
             markdownSave : function(cm, icon, cursor, selection) {
                
                //var cursor    = cm.getCursor();     //获取当前光标对象，同cursor参数
                //var selection = cm.getSelection();  //获取当前选中的文本，同selection参数
                // 替换选中文本，如果没有选中文本，则直接插入
               	// cm.replaceSelection("###" + selection);
                
                // 如果当前没有选中的文本，将光标移到要输入的位置
               	// if(selection === "") {
                //    cm.setCursor(cursor.line, cursor.ch + 1);
                //}
                // this == 当前editormd实例
                saveRep();

            }
        },
        lang : {
            toolbar : {
               
          	  markdownSave : "保存"
            }
        }
	});
	//testEditor.getValue();
});
</script>


</body>
</html>
