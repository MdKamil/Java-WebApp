$(document).ready(function() {
	
	//Home - JS
	// Modal dialog properties...
	var dialog, form,
		title = $("#todoTitle"),
		allFields = $([]).add(title), 
		tips = $(".validateTips");
	
	function updateTips( t ) {
	    tips
	        .text( t )
	        .addClass( "ui-state-highlight" );
	      	setTimeout(function() {
	        tips.removeClass( "ui-state-highlight", 1500 );
	      }, 500 );
	}
	
	
	function checkLength( o, n, min) {
	    if (o.val().length < min ) {
	        o.addClass( "ui-state-error" );
	        updateTips( "Length of " + n + " must be between " +
	          	min + "." );
	        	return false;
	    }else {
	        return true;
	    }
	}
	
	function addTodo() {
     	var valid = true;
			allFields.removeClass( "ui-state-error" );
			valid = valid && checkLength( title, "todo title", 10);
			if ( valid ) {
				var createTodoForm = $("#createtodo-form").serialize();
				//Create Todo Ajax call
				$.ajax({
					type		:	"POST",
					data		:	createTodoForm,
					dataType	: 	"text",
					url			:	"createTodo",
					success		: 	function(data){
						console.log("Successfully created todo");
						$("div#todo-holder").html(data);
					}
				});
				dialog.dialog( "close" );
			}
        return valid;
    }
	
	
	dialog = $( "#dialog-form" ).dialog({
	    autoOpen: false,
	    height: 260,
	    width: 500,
	    modal: true,
	    buttons: {
	         "Create ToDo": addTodo,
	         Cancel: function() {
	          	dialog.dialog( "close" );
	        }
	    },
	    close: function() {
	        form[ 0 ].reset();
	        allFields.removeClass( "ui-state-error" );
	    }
    });
	
	form = dialog.find( "form" ).on( "submit", function( event ) {
	    event.preventDefault();
	    addTodo();
	});
	
	
	$( "#create-todo" ).on( "click", function() {
  		dialog.dialog( "open" );
	});
	
	
	//Delete Todo Ajax Call
	$(document).on("click","a.delete-todo",function(event){
		event.preventDefault();
		var todoid = this.href.substring(this.href.lastIndexOf("/")+1,this.href.length);
		$.ajax({
			type		:	"DELETE",
			url			:	"deletetodo/"+todoid,
			success		:	function(){
				console.log("Deleted todo Successfully");
				//Retrieve all todo Ajax call 
				$.ajax({
					type	:	"GET",
					url		:	"getAllTodo",
					success	:	function(data){
						console.log("Retrieved all todo's successfully");
						$("div#todo-holder").html(data);
					}
				});
			}
		});
	});
	
	
	//Pagination JS
	
	$(document).on("click","button#pagination-next",function(){
		$.ajax({
			type	:	"GET",
			url		:	"gettodo?page=next",
			success	:	function(data){
				console.log("Retrieved todo's by clicking NEXT pagination button");
				$("div#todo-holder").html(data);
			}
		});
	});
	
	$(document).on("click","button#pagination-previous",function(){
		$.ajax({
			type	:	"GET",
			url		:	"gettodo?page=previous",
			success	:	function(data){
				console.log("Retrieved todo's by clicking PREVIOUS pagination button");
				$("div#todo-holder").html(data);
			}
		});
	});
	
	
	//ViewToDo JS
	
	$(document).find("#create-task-form").on("submit",function(event){
		 event.preventDefault();
	});
	
	//Create task Ajax call
	$(document).on("click","a#create-task",function(event){
		event.preventDefault();
		var createTaskForm = $("#create-task-form").serialize();
		var fullpath = this.pathname;
		$.ajax({
			type		:	"POST",
			url			:	fullpath,
			data		:	createTaskForm,
			dataType	: 	"text",
			success		: 	function(data){
				console.log("Created Task successfully..");
				$("section#taskviewer").html(data);
			}
		});
	});
	
	//Delete Task Ajax call...
	$(document).on("click","a.delete-task",function(event){
		event.preventDefault();
		var fullpath = this.pathname;
		$.ajax({
			type		:	"DELETE",
			url			:	fullpath,
			dataType	: 	"text",
			success		: 	function(data){
				console.log("Deleted Task successfully..");
				var pathparam = window.location.pathname;
				var todoid = pathparam.substring(pathparam.lastIndexOf('/')+1,pathparam.length);
				console.log(contextPath+"/gellAllTasks/todo/"+todoid);
				//Get all Task Ajax call
				$.ajax({
					type	:	"GET",
					url		:	contextPath+"/gellAllTasks/todo/"+todoid,
					success	:	function(data){
						console.log("Retrieved all tasks successfully..");
						$("section#taskviewer").html(data);
					}
				});
			}
		});
	});
	
	
	//Settings - JS
	//Change Password slide
	
	$("div#password-message").css("display","none");
	
	$("li#slidedown").click(function(){
		$("div#change-password-heading").hide();
		$("div#change-password-holder").css("display","inline-block");
	});

	$("button#change-password-cancel-button").click(function(){
		 event.stopPropagation();
		$("div#change-password-holder").hide();
		$("div#change-password-heading").css("display","inline-block");
		$("div#change-password-holder input:password").val("");
		$("button#change-password-save-button").prop('disabled',true);
		$("button#change-password-save-button").css('background-color','#D6E0F5');
		$("div#password-message").css("display","none");
	});
	
	$("button#change-password-save-button").prop('disabled',true);
	$("button#change-password-save-button").css('background-color','#D6E0F5');
	
	$("input[type=password]#old-password").on('input',function(){
		var oldPass = $("input[type=password]#old-password").val();
		if(oldPass.length >= 1){
			$("button#change-password-save-button").prop('disabled',false);
			$("button#change-password-save-button").css('background-color','#3079ed');
			$(document).off().on("click","button#change-password-save-button",function(event){
				if($("input[type=password]#new-password-first").val() === $("input[type=password]#new-password-re").val()){
					$("div#password-message").css("display","none");
					var input = $("#change-password-form").serialize();
					$.ajax({
						type	:	"POST",
						url		:	"changepassword",
						data	: 	input,
						success	:	function(data){
							console.log(data);
							if(data.result === "FAILURE"){
								$("div#password-message").css({
									"display" : "block",
									"background-color" : "#b83130"
								});
								$("div#password-message h6").text(data.message);
								console.log("ddd")
							}else if (data.result === "SUCCESS") {
								$("div#change-password-holder").hide();
								$("div#change-password-heading").css("display","inline-block");
								$("div#change-password-holder input:password").val("");
								$("button#change-password-save-button").prop('disabled',true);
								$("button#change-password-save-button").css('background-color','#D6E0F5');
								$("div#password-message").css("display","none");
								$( "#dialog-message" ).dialog( "open" );
								$("div#dialog-message p").text(data.message);
							}
						}
					});
				}else {
					$("div#password-message h6").text("Password doesnt match")
					$("div#password-message").css({
						"display" : "block",
						"background-color" : "#b83130"
					});
				}
			});
		}else {
			$("button#change-password-save-button").prop('disabled',true);
			$("button#change-password-save-button").css('background-color','#D6E0F5');
		}
	});
	
	//Chane Password Confirmation
	
	$( "#dialog-message" ).dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
	        }
	    }
	});
	
});