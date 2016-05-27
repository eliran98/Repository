<%@page import="com.prectise.chat.Chat"%>
<%@page import="com.prectise.ICommons.ICommons"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>registration</title>
		
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"></link>
		<link rel="stylesheet" href="/ChatApp/css/chatAppStyle.css"></link>
	</head>
	 <%
	 	if(this.getServletContext().getAttribute("key.chat") == null){
			Chat chat = new Chat(); 
			this.getServletContext().setAttribute("key.chat",chat);
		}
	 	String contextPath = request.getContextPath();
		String registrationUrl = contextPath + "/controller?action=RegistrationAction";
		String signInUrl = contextPath + "/jsp/" + ICommons.PAGE_SIGN_IN;
	 %>
	<body>
		<!-- http://localhost:8080/ChatApp/jsp/registration.jsp -->
		<div class="registration-container">
			<div class="form-title-wrapper">
				<h1 class="registration-title">Registration</h1>
				<form id="form-registration" class="form-registration" data-context="<%=contextPath%>" action="<%=registrationUrl%>" method="post">
					<div class="first-name-wrapper">
						<label for="firstName">First Name:</label>
						<input class="rightStyle" id="firstName" name="firstName" type="text" placeholder="first name"></input>
					</div>
					<div class="last-name-wrapper">
						<label for="lastName">Last Name:</label>
						<input class="rightStyle" id="lastName" name="lastName" type="text" placeholder="last name"></input>
					</div>
					<div class="radio-btns-wrapper">
						<div class="male-wrapper">
							<input id="male" name="gender" type="radio" value="Male">
							<label for="male">Male</label>
						</div>
						<div class="female-wrapper">
							<input id="female" name="gender" type="radio" value="female">
							<label for="female">Female</label>
						</div>
					</div>
					<div class="mail-wrapper">
						<label for="mail">Email:</label>
						<input class="rightStyle" id="mail" name="mail" type="text" placeholder="email"></input>
					</div>
					<div class="user-name-wrapper">
						<label for="userName">User Name:</label>
						<input class="rightStyle" id="userName" name="userName" type="text" placeholder="user name"></input>
					</div>
					<div class="new-password-wrapper">
						<label for="new-password">New Password:</label>
						<input class="rightStyle" id="newPassword" name="newPassword" type="password" placeholder="new password"></input>
					</div>
					<div class="confirm-password-wrapper">
						<label for="confirmPassword">Confirm:</label>
						<input class="rightStyle" id="confirmPassword" name="confirmPassword" type="password" placeholder="confirm password"></input>
					</div>
					<input id="btn-send" type="submit" class="btn-send-style" value="register">
					<div class="form-bottom">
						<div class="checkbox-wrapper">
							<input class="checkboxStyle" id="isRemember" name="isRemember" type="checkbox"></input>
							<label for="isRemember">Remember me</label>
						</div>
						<a class="linkToSignInPage" href="<%=signInUrl%>">Sign-in</a>
					</div>
					
				</form>
				<div id="errorMsg" class="error-msg">
					<i id="errorMsgCancel" class="fa fa-times-circle"></i>
					<span></span>
				</div>
			</div>
		</div>
		
		<script type="text/javascript" src="/ChatApp/js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/ChatApp/js/chat-app-script.js"></script>
	</body>
</html>