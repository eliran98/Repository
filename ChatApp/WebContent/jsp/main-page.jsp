<%@page import="com.prectise.ICommons.ICommons"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome</title>
		<link rel="stylesheet" href="/ChatApp/css/main-page-style.css"></link>
	</head>
	<%
		String contextPath = request.getContextPath();
		String chatUrl = contextPath + "/controller?action=CheckIsUserAlreadyRegistaredAction";
		String registrationUrl = contextPath + "/jsp/" + ICommons.PAGE_REGISTRATION ;
		String signInUrl = contextPath + "/jsp/" + ICommons.PAGE_SIGN_IN;
	 %>
	<body>
	
		<div id="main-page-container" class="main-page-container" data-context="<%=contextPath%>">
			<div class="horizontal-tool-bar-container">
				<ul class="horizontal-tool-bar">
				  <li><a href="#home">Home</a></li>
				  <li><a href="<%=signInUrl%>">Sign-In</a></li>
				  <li><a href="<%=registrationUrl%>">Registration</a></li>
				  <li><a href="<%=chatUrl%>">Chat</a></li>
				  <!--li style="float:right"><a class="active" href="#about">About</a></li-->
				</ul>
			</div>
		</div>
		
		<script type="text/javascript" src="/ChatApp/js/jquery-2.2.3.min.js"></script>	
		<script type="text/javascript" src="/ChatApp/js/main-page-script.js"></script>	
	</body>
</html>