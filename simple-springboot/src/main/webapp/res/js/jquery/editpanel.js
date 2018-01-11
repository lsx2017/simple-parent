
/**
 *新增、修改、编辑右边弹出面板
 * 需要animate.css、Jquery配合使用
 * @author: liushx
 * @date: 2016-09-25
 * @param title 
 * @param editUrl
 * @param pw 面板宽度 1 2一半大小
 */
function initEditPanel(title, editUrl, pw) {
	
	var edit_html = '';
	edit_html +='<div id="xbox" class="animated">';
	edit_html +='<div class="ibox float-e-margins">';
	edit_html +='<div class="ibox-title">';
	edit_html +='<h5 id="xbox-title" style="font-size:15px;">'+title+'</h5>';
	edit_html +='<div class="ibox-tools"> ';
	edit_html +='<a id="close-btn" class="close-link"> <i class="fa fa-times"></i> </a> ';
	edit_html +='</div>';
	edit_html +='</div>';
	edit_html +='<iframe id="editFrame" src="" marginwidth=0  width="100%" height="'+($(window).height()-49)+'px" ';
	edit_html +=' framespacing=0 marginheight=0 frameborder=no scrolling=auto><iframe>';
	edit_html +='</iframe></div></div>';
	$('body').append(edit_html);
	$("body").height($(window).height());
	if(pw) {
		$("#xbox").width($(window).width()/pw).height($(window).height());
	} else {
		$("#xbox").width($(window).width()).height($(window).height());
	}
	
	$("#editFrame").height($(window).height()-49);
	
	$("#close-btn").click(function(){
		hideEditPanel();
	});
}

/**
function initEditPanelFull(title, editUrl) {
	
	var edit_html = '';
	edit_html +='<div id="xbox" class="animated">';
	edit_html +='<div class="ibox float-e-margins">';
	edit_html +='<div class="ibox-title">';
	edit_html +='<h5 id="xbox-title" style="font-size:15px;">'+title+'</h5>';
	edit_html +='<div class="ibox-tools"> ';
	edit_html +='<a id="close-btn" class="close-link"> <i class="fa fa-times"></i> </a> ';
	edit_html +='</div>';
	edit_html +='</div>';
	edit_html +='<iframe id="editFrame" src="" marginwidth=0  width="100%" height="'+($(window).height()-48)+'px" ';
	edit_html +=' framespacing=0 marginheight=0 frameborder=no scrolling=auto><iframe>';
	edit_html +='</iframe></div></div>';
	$('body').append(edit_html);
	$("body").height($(window).height());
	$("#xbox").width($(window).width()/1).height($(window).height());
	$("#editFrame").height($(window).height()-49);
}

*/
	
function showEditPanel(_url, _title,pw) {
	//var cityObj = $("#addBtn");
	//var cityOffset = $("#addBtn").offset();
	//$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
	
	if(_title) {
		$("#xbox-title").text(_title);
	}
	$("#editFrame").attr("src", _url);
	if(pw) {
		$("#xbox").width($(window).width()/pw).height($(window).height());
	}
	$("#xbox").removeClass("slideOutRight").addClass("slideInRight").show("slow");
	$("body").bind("mousedown", onBodyDown);
	
}

function hideEditPanel() {
	$("#xbox").addClass("slideOutRight").hide("slow").removeClass("slideInRight");
	$("body").unbind("mousedown", onBodyDown);
}

function closeEditPanel(){
	$("#queryBtn").click();
	hideEditPanel();
}

function onBodyDown(event) {
	if (!(event.target.id == "addBtn" || event.target.id == "xbox" || $(event.target).parents("#xbox").length>0)) {
		hideEditPanel();
	}
}