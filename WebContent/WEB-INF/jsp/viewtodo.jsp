<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>View ToDo</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/bootstrap.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/main.css"/>">
		<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/jquery-ui.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/main.js"/>"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}";
		</script>
	</head>
	<body>
		<%@ include file="subViews/navigationmenu.jsp" %>
		<div class="viewpage-content">
			<jsp:include page="subViews/todoviewer.jsp"/>
		</div>
	</body>
</html>