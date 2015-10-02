<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="todocontainer">
	<section id="todotitleviewer">
		<div id="todo-title-container">
			<h3>${todo.todoTitle }</h3>
		</div>
	</section>
	<article>
		<section id="taskviewer">
			<jsp:include page="taskviewer.jsp" />
		</section>
		<aside id="tododetails">
			<div id="todo-details-container">
				<p>
					<strong>Date created:</strong>${todo.todoCreatedDate }
				</p>
			</div>
			<div>
				<c:url value="/deletetodo/${todo.todoId }" var="deletetodoUrl" />
				<a class="viewpage-delete-todo" href="${deletetodoUrl }"><span
					class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
			</div>
		</aside>
	</article>
</div>