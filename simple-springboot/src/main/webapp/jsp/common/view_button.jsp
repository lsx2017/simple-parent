<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:forEach var="oper" items="${operList}">
	<button type="button" id="${oper.URI}" class="btn btn-success slideInRight mar_T2 fright">
		<i class="fa ${oper.IMAGE_NAME} mar_R10"></i>${oper.RES_NAME}
	</button>&nbsp;&nbsp;
</c:forEach>