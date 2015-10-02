<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="settings-container">
	<ul>
		<li>
			<h3>Manage Settings</h3>
		</li>
		<li>
			<div class="property-group">
				<div class="user-properties">
					<h5>Username</h5>
				</div>
				<div class="user-properties">
					<h5>${user.username }</h5>
				</div>
			</div>
		</li>
		<li>
			<div class="property-group">
				<div class="user-properties">
					<h5>Email</h5>
				</div>
				<div class="user-properties">
					<h5>${user.email }</h5>
				</div>
			</div>
		</li>
		<li id="slidedown">
			<div class="property-group">
				<div class="user-properties">
					<h5 id="password-heading">Password</h5>
				</div>
				<div class="user-properties" id="change-password-heading">
					<h5>change password</h5>
				</div>
				<div id="change-password-holder" class="user-properties">
					<form:form modelAttribute="changePassword" id="change-password-form">
						<div>
							<div class="password-holder-label">
								<h5>Current</h5>
							</div>
							<div class="password-holder-text">
								<form:password path="oldPassword" class="user-property-input" id="old-password"/>
							</div>
						</div>
						<div>
							<div class="password-holder-label">
								<h5>New</h5>
							</div>
							<div class="password-holder-text">
								<input type="password" class="user-property-input" id="new-password-first" />
							</div>
						</div>
						<div>
							<div class="password-holder-label">
								<h5>Re-enter new</h5>
							</div>
							<div class="password-holder-text">
								<form:password path="newPassword" class="user-property-input" id="new-password-re"/>
							</div>
						</div>
					</form:form>					
					<div>
						<div id="password-message">
							<h6></h6>	
						</div>
					</div>
					<div>
						<button class="change-password-button"
							id="change-password-save-button">Save</button>
						<button class="change-password-button"
							id="change-password-cancel-button">Cancel</button>
					</div>
				</div>
			</div>
		</li>
	</ul>
</div>