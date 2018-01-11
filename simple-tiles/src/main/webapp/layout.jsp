<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta http-equiv="Content-Style-Type" content="text/css">  
<meta http-equiv="Content-Script-Type" content="text/javascript">  
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
<meta name="robots" content="noindex,nofollow">  
<meta name="author" content="">  
<meta name="description" content="">  
<meta name="keywords" content="">  
<meta name="format-detection" content="telephone=no, email=no, address=no">  
<meta name="viewport" content="width=device-width, initial-scale=1">  
<title>minyan web</title>  
<link href="<%=basePath%>/css/bootstrap.min.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/css/font-awesome.min.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/lib/iCheck/skins/flat/_all.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css"/>  
<link href="<%=basePath%>/css/bootstrap-datetimepicker.min.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/lib/select2/css/select2.min.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/css/iconfont/iconfont.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/css/style.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<link href="<%=basePath%>/css/theme.css?<%=staticTimestamp%>" rel="stylesheet" type="text/css">  
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js?<%=staticTimestamp%>"></script>  
<script type="text/javascript" src="<%=basePath%>/lib/select2/js/select2.min.js?<%=staticTimestamp%>"></script>  
<script type="text/javascript" src="<%=basePath%>/js/bootstrap.min.js?<%=staticTimestamp%>"></script>  
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.js?<%=staticTimestamp%>"></script>  
<script type="text/javascript" src="<%=basePath%>/js/bootstrap-datetimepicker.ja.js?<%=staticTimestamp%>"></script>  
<script type="text/javascript" src="<%=basePath%>/lib/iCheck/icheck.min.js?<%=staticTimestamp%>" ></script>  
<script type="text/javascript" src="<%=basePath%>/js/common.js?<%=staticTimestamp%>"></script>  
</head>  
<body class="theme-1">  
<tiles:insertAttribute name="header" />  
<div class="row-offcanvas row-offcanvas-left">  
<tiles:insertAttribute name="sidemenu" />  
<div class="content">  
<tiles:insertAttribute name="body" />  
<tiles:insertAttribute name="footer" />  
</div>  
</div>  
</body>  
</html> 