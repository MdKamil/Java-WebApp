<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Log in to ToDo</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/bootstrap.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/login_signup.css"/>">
		<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<div id="container">
			<div id="title">
				<h4>Start Tracking</h4>
			</div>
			<div id="login">
				<c:url var="loginUrl" value="/j_spring_security_check"></c:url>
				<form method="post" action="${loginUrl}">
					<table class="mainTable">
						<tr>
							<td><input type="text" name="username" placeholder="Email"/></td>
						</tr>
						<tr>
							<td><input type="password" name="password" placeholder="Password"/></td>
						</tr>
						<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
							<tr>
								<td>
									<span class="login-error"> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></span>
								</td>
							</tr>
						</c:if>
					</table>
					<table class="mainTable btnTable">
						<tr>
							<td><input type="submit" value="Log In" placeholder="Username"/></td>
							<c:url var="signup" value="/signup"/>
							<td><a href="signup" style="text-decoration: none;">or Join</a></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>