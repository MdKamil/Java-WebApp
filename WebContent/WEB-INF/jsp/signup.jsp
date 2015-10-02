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
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/bootstrap.css"/>">
		<link rel="stylesheet" type="text/css" href="<c:url value="/views/css/login_signup.css"/>">
		<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
		<title>Signup for ToDo</title>
	</head>
	<body>
		<div id="container">
			<div id="title">
				<h4>Create your ToDo Account</h4>
			</div>
			<div id="signup">
				<c:url var="signupUrl" value="/createUser"/>
				<form:form modelAttribute="user"  method="post" action="createUser">
					<table class="mainTable">
						<tr>
							<td><form:input path="username" placeholder="Username"/></td>
						</tr>
						<c:set var="usernameErrors">
							<form:errors path="username"/>
						</c:set>
						<c:if test="${not empty usernameErrors}">
							<script>
								$(document).ready(function() {
									$("#username").css("border-color","#dd4b39");
								});
							</script>
							<tr>
								<td><form:errors path="username" class="signup-error"/></td>
							</tr>
						</c:if>
						<tr>
							<td><form:input path="email" placeholder="Email"/></td>
						</tr>
						<c:set var="emailErrors">
							<form:errors path="email"/>
						</c:set>
						<c:if test="${not empty emailErrors}">
							<script>
								$(document).ready(function() {
									$("#email").css("border-color","#dd4b39");
								});
							</script>
							<tr>
								<td><form:errors path="email" class="signup-error"/></td>
							</tr>
						</c:if>
						<c:if test="${not empty emailAlreadyExistsError }">
							<script>
								$(document).ready(function() {
									$("#email").css("border-color","#dd4b39");
								});
							</script>
							<tr>
								<td><span class="emailAlreadyExistsError">${emailAlreadyExistsError}</span></td>
							</tr>
						</c:if>
						<tr>
							<td><form:password path="password" placeholder="Password"/></td>
						</tr>
						<c:set var="passwordErrors">
							<form:errors path="password"/>
						</c:set>
						<c:if test="${not empty passwordErrors}">
							<script>
								$(document).ready(function() {
									$("#password").css("border-color","#dd4b39");
								});
							</script>
							<tr>
								<td><form:errors path="password"  class="signup-error"/></td>
							</tr>
						</c:if>
					</table>
					<table class="mainTable btnTable">	
						<tr>
							<td><input type="submit" value="Sign Up"/></td>
							<c:url var="loginurl" value="/login"/>
							<td><a href="loginurl" style="text-decoration: none;">or Login</a></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</body>
</html>