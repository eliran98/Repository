package com.prectise.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.chat.Chat;

public class ChatController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jdt(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		jdt(req, resp);
	}
	
	private void jdt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String text = req.getParameter("textInputField");
		Chat chat = (Chat) getServletContext().getAttribute("key.chat");
		if(text != null){
			String userName = "";
			if(req.getSession() != null){
				userName = (String) req.getSession().getAttribute("key.userName");
			}
			chat.add(text, userName);
			Map<String, String[]> mapParams = req.getParameterMap();
			//mapParams.put("textInputField","");
		}
		//req.getSession().getSessionContext().getSession("").
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/chat-page.jsp");
//		dispatcher.forward(req,resp);
	}
}
