<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Settings-User</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/main.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/jquery-ui.css"/>">
		<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/jquery-ui.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/main.js"/>"></script>
	</head>
	<body>
		<%@ include file="subViews/navigationmenu.jsp" %>
		<div id="settings-content">
			<jsp:include page="subViews/userdetails.jsp"/>
		</div>
		<div id="dialog-message" title="Change Password Success">
			<p></p>
		</div>
	</body>
</html>