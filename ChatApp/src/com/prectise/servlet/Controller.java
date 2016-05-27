package com.prectise.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.ICommons.ICommons;
import com.prectise.actions.CheckIsUserAlreadyRegistaredAction;
import com.prectise.actions.LoadMsgsAction;
import com.prectise.actions.RegistrationAction;
import com.prectise.actions.SignInAction;
import com.prectise.actions.SignOutAction;
import com.prectise.chat.Chat;
import com.prectise.logic.IAction;

public class Controller extends HttpServlet implements ICommons{
	
	private Map<String,IAction> actions;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		actions = new HashMap<String,IAction>(1);
		
		RegistrationAction registrationAction = new RegistrationAction();
		actions.put(registrationAction.getName(),registrationAction);
		
		CheckIsUserAlreadyRegistaredAction checkIsUserAlreadyRegistaredAction = new CheckIsUserAlreadyRegistaredAction();
		actions.put(checkIsUserAlreadyRegistaredAction.getName(),checkIsUserAlreadyRegistaredAction);
		
		SignInAction signInAction = new SignInAction();
		actions.put(signInAction.getName(),signInAction);
		
		SignOutAction signOutAction = new SignOutAction();
		actions.put(signOutAction.getName(),signOutAction);
		
		LoadMsgsAction loadMsgsAction = new LoadMsgsAction();
		actions.put(loadMsgsAction.getName(),loadMsgsAction);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}
	
	private void processAction(HttpServletRequest request,HttpServletResponse response){
		
		try{
			String actionName = request.getParameter(ACTION);
			if(actionName != null){
				IAction action = actions.get(actionName);
				action.perform(request, response);
				if(actionName.equals(SignInAction.class.getSimpleName()) || actionName.equals(RegistrationAction.class.getSimpleName())){
					if(request.getSession() != null && request.getSession().getAttribute("") == null){
						String userName = request.getParameter(PARAM_USER_NAME);
						request.getSession().setAttribute("key.userName",userName);
						Chat chat = (Chat) getServletContext().getAttribute("key.chat");
						chat.join(userName);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
