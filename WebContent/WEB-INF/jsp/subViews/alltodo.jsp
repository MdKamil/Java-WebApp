<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="todo-count">
	<c:choose>
		<c:when test="${todoCount > 0 }">
			<h4>About ${todoCount } results.</h4>
		</c:when>
		<c:otherwise>
			<h4>No ToDo available.</h4>
		</c:otherwise>
	</c:choose>
</div>
<div id="todo-container">
	<c:if test="${not empty todolist }">
		<c:forEach var="todo" items="${todolist }">
			<div class="todo-palette">
				<c:choose>
					<c:when test="${fn:length(todo.todoTitle) > 18 }">
						<c:url value="/viewTodo/${todo.todoId }" var="getTodoUrl" />
						<a class="todo-title" style="text-decoration: none;" href="${getTodoUrl }">${fn:substring(todo.todoTitle,0,15) } ...</a>
					</c:when>
					<c:otherwise>
						<c:url value="/viewTodo/${todo.todoId }" var="getTodoUrl" />
						<a class="todo-title" style="text-decoration: none;" href="${getTodoUrl }">${todo.todoTitle }</a>
					</c:otherwise>
				</c:choose>
				<p>Date Created:${todo.todoCreatedDate }</p>
				<c:url value="deletetodo/${todo.todoId }" var="deletetodoUrl" />
				<a class="delete-todo" href="${deletetodoUrl }"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
			</div>
		</c:forEach>
	</c:if>
</div>