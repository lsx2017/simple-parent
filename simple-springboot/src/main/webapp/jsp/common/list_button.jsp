<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:forEach var="oper" items="${operList}">
	<a class="${oper.URI}" href="javascript:;" title="${oper.RES_NAME}"><i class="fa ${oper.IMAGE_NAME} t-eidt"></i></a>&nbsp;&nbsp;
</c:forEach>