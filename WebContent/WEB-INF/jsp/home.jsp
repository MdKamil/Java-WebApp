<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>ToDo Home</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/bootstrap.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/jquery-ui.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/main.css"/>">
		<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/jquery-ui.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/views/js/main.js"/>"></script>
	</head>
	<body>
		<%@ include file="subViews/navigationmenu.jsp" %>
		<div class="content">
			<header>
				<div class="container">
					<div id="logocontainer">
						<a href="#">ToDo</a>
					</div>
					<form id="searchtodo">
						<input type="search" placeholder="search todo's"/>
						<button class="header-button">Browse</button>
					</form>
					<div id="createtodocontainer">
						 <button class="header-button" id="create-todo">Create</button>
					</div>
				</div>
			</header>
			<div id="todoviewer">
				<div id="todo-holder">
					<jsp:include page="subViews/alltodo.jsp"/>
				</div>
				<div id="todo-pagination">
					<div>
						<button id="pagination-previous">Previous</button>
						<button id="pagination-next">Next</button>
					</div>
				</div>
			</div>
		</div>
		<div id="dialog-form" title="Create new Todo">
  			<p class="validateTips">Give a title for todo</p>
 			<form:form id="createtodo-form">
				<fieldset>
					<form:input type="text" path="todoTitle" name="todoTitle" placeholder="todo title" class="text ui-widget-content ui-corner-all"/>
				</fieldset>
			</form:form>
		</div>
	</body>
</html>