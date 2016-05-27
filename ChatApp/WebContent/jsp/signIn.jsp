<%@page import="com.prectise.chat.Chat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Sign-In</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"></link>
		<link rel="stylesheet" href="/ChatApp/css/chatAppStyle.css"></link>
	</head>
	<%	
		if(this.getServletContext().getAttribute("key.chat") == null){
			Chat chat = new Chat(); 
			this.getServletContext().setAttribute("key.chat",chat);
		}
		
	 	String contextPath = request.getContextPath();
		String signInUrl = contextPath + "/controller?action=SignInAction";
	 %>
	<body>
			<div class="signIn-container">
				<div class="form-signIn-title-wrapper">
					<h1 class="signIn-title">Sign In</h1>
					<form id="form-signIn" class="form-signIn" data-context="<%=contextPath%>" action="<%=signInUrl%>" method="post">
						<div class="user-name-wrapper">
							<label for="userName">User Name:</label>
							<input id="userName" name="userName" type="text" placeholder="user name"></input>
						</div>
						<div class="password-wrapper">
							<label for="password">Password:</label>
							<input id="password" class="right" name="password" type="password" placeholder="password"></input>
						</div>
						<input id="btn-logIn" type="submit" class="btn-log-in-style" value="LogIn">
						<div class="checkbox-wrapper">
							<input class="checkboxStyle" id="isSignInRemember" name="isRemember" type="checkbox"></input>
							<label for="isSignInRemember">Remember me</label>
						</div>
					</form>
					<div id="errorMsgSignIn" class="error-msg-sign-in">
						<i id="errorMsgSignInCancel" class="fa fa-times-circle"></i>
						<span></span>
					</div>
				</div>
			</div>
			
			<script type="text/javascript" src="/ChatApp/js/jquery-2.2.3.min.js"></script>
			<script type="text/javascript" src="/ChatApp/js/chat-app-script.js"></script>
	</body>
</html>