<%@page import="com.prectise.chat.Chat"%>
<%@page import="com.prectise.ICommons.ICommons"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<%	
		Chat chat = (Chat)this.getServletContext().getAttribute("key.chat");
		if(chat == null){
			chat = new Chat();
			this.getServletContext().setAttribute("key.chat",chat);
		}
		String contextPath = request.getContextPath();
		Cookie[] cookies = request.getCookies();
		String token = "";
		for(Cookie cookie : cookies){
		   if(cookie.getName().equals(ICommons.COOKIE_KEY_TOKEN)){
		   		token = cookie.getValue();
		   }
		}
		String signOutUrl = contextPath + "/controller?action=SignOutAction&token=" + token;
		String chatUrl = contextPath + "/chat";
		String userName = (String)request.getSession().getAttribute("key.userName");
		String msg = "Welcome " + userName;
	 %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Chat</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"></link>
		<link rel="stylesheet" href="/ChatApp/css/chatAppStyle.css"></link>
	</head>
	<body>
		<div class="chat-container">
			<a class="btn-sign-out-style" href="<%=signOutUrl%>">Sign-Out</a>
			<div class="welcome-msg"><%=msg%></div>
			<div class="chat-converstion-box">
				<div class="conversion">
					<%for(int i=0 ; i<chat.getBlas().size(); i++){%>
						<div>
							<%=chat.getBlas().get(i)%>
						</div>
					<%}%>
				</div>
			</div>
			<form class="bottom-footer" action="<%=chatUrl%>" method="post">
				<input id="text-input-field" class="text-input-field" name="textInputField" type="text" placeholder="please enter your text here.."><input class="btn-send" id="btn-send" type="submit" value="Send">
			</form>
		</div>
		<script type="text/javascript" src="/ChatApp/js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript" src="/ChatApp/js/chat-app-script.js"></script>
	</body>
</html>