<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav>
	<div class="container">
		<div class="leftNavigation">
			<a href="#">Track Your Activities</a>
		</div>
		<div class="rightNavigation">
			<c:url value="/home" var="homeUrl" />
			<a href="${homeUrl }">Home</a>
			<c:url value="/logout" var="logoutUrl" />
			<a href="${logoutUrl}">LogOut</a>
			<c:url value="/settings" var="settingsUrl" />
			<a href="${settingsUrl }">${username }</a>
		</div>
	</div>
</nav>