<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="taskcontainer">
	<c:if test="${not empty tasklist }">
		<c:forEach var="task" items="${tasklist }">
			<div class="task">
				<c:choose>
					<c:when test="${fn:length(task.taskName) > 50 }">
						<h4>${fn:substring(task.taskName,0,49) } ...</h4>
					</c:when>
					<c:otherwise>
						<h4>${task.taskName }</h4>
					</c:otherwise>
				</c:choose>
				<c:url value="/deletetask/todo/${todo.todoId }/task/${task.taskId }" var="deletetaskUrl" />
				<a class="delete-task" href="${deletetaskUrl }"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
			</div>
		</c:forEach>
	</c:if>
	<form:form modelAttribute="createtask" id="create-task-form" name="create-task-form">
		<div class="task">
			<form:input path="taskName" placeholder="enter task"/>
			<c:url value="/createtask/${todo.todoId }" var="createtaskUrl" />
			<a id="create-task" href="${createtaskUrl }"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a>
		</div>
	</form:form>
</div>
