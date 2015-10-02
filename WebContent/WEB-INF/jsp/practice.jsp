<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* input[type="text"]{
		border: 1px solid transparent;
	}
	
	input[type="text"]:HOVER {
		border: 1px solid gray;
	} */
}
</style>
<script type="text/javascript" src="<c:url value="/views/js/jquery-1.11.2.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#create").on("click",doAjax);
		
		function doAjax() {
			console.log("Ajax called")
			
			$.ajax({
				method:"GET",
				url	  : "modify",	
				contentType: "text/plain",
				dataType	: "text",
				success : function (data){
					console.log("success");
					console.log(data);
					$("#tasks").html(data);
				}
			});	
		}
		
		
		/* $(":text").on("focusout",show);
		
		function show() {
			var string = $(this).serializeArray();
			var className = $(this).attr("class");
			string[0].entityType = className;
			console.log(string[0]);
			
			$( "#results" ).empty();
		    jQuery.each( string, function( i, field ) {
		      $( "#results" ).append( field.value + " " );
		    });
		    
			 $.ajax({
				method	:"POST",
				url		:"modify",
				data	:JSON.stringify(string[0]),
				
				contentType: "application/json;charset=utf-8",
				dataType	: "json",	
				success		:function (data){
					console.log(data);
				}
			});
		}	 */
		
		
		/* $("#todoFields").focusout(function () {
			$.ajax({
				type:"POST",
				url	  : "modify",	
				contentType: "text/plain",
				success : function (data){
					console.log("success");
					console.log(data);
					$("#div").html(data);
				}
			});
		}) */
	});
</script>
</head>
<body>
	<%-- <div id="div">
		<h2>${name}</h2>
		<input id="text" type="text" value="${name}">
	</div> --%>
	
	<%-- <div>
		<form:form class="todo" modelAttribute="book">
			<form:input class="todo" path="title" value="${book.title }"/>
			<form:input class="todo" path="author" value="${book.author }"/>
		</form:form>
		
		<p id="results"></p>
		
		<form:form class="task" modelAttribute="task">
			<form:input path="taskName" class="task" value="${task.taskName }"/>
		</form:form>
		
	</div> --%>
	
	
	<%-- <div id="tasks">
		<jsp:include page="subViews/taskviewer.jsp"></jsp:include>
	</div> --%>
	
	
	
	<%-- <div id="userdetails">
		<input type="text" id="text" value="${user.username }">
		<input type="text" id="text" value="${user.email }">
	</div> --%>
	
	<form:form modelAttribute="book">
		<c:forEach var="book" items="${booklist }">
			<form:input path="title" value="${book.title}"/>
		</c:forEach>
	</form:form>
	<form:form modelAttribute="createbook">
		<form:input path="title" placeholder="enter book name"/>
	</form:form>
	
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<a href="${logoutUrl}">Log Out</a>
	
	
	
	
</body>
</html>