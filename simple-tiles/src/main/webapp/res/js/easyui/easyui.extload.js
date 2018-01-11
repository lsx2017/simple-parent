
/**
 * 动态加载JavaScript CSS
 * @param filename
 * @param filetype
 */
function loadJsCssFile(fileName,fileType){

    if(fileType == "js"){
        var fileRef = document.createElement('script');
        fileRef.setAttribute("type","text/javascript");
        fileRef.setAttribute("src",fileName);
    }else if(fileType == "css"){
    
        var fileRef = document.createElement('link');
        fileRef.setAttribute("rel","stylesheet");
        fileRef.setAttribute("type","text/css");
        fileRef.setAttribute("href",fileName);
    }
    if(typeof fileRef != "undefined"){
        document.getElementsByTagName("head")[0].appendChild(fileRef);
    }
    
}

loadJsCssFile("res/js/easyui/themes/gray/easyui.css","css");
loadJsCssFile("res/js/easyui/themes/icon.css","css");
loadJsCssFile("res/js/easyui/locale/easyui-lang-zh_CN.js","js");
loadJsCssFile("res/js/easyui/jquery.easyui.min.js","js");
