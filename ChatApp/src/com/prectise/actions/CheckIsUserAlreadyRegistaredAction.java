package com.prectise.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.ICommons.ICommons;
import com.prectise.logic.IAction;
import com.prectise.model.db.ConnectionsManager;
import com.prectise.utils.Utils;

public class CheckIsUserAlreadyRegistaredAction implements IAction,ICommons{

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		
		String tokenCookie = null;
		ResourceBundle resourceBundle = null;
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try{
			tokenCookie = Utils.getCookie(COOKIE_KEY_TOKEN,request);
			if(tokenCookie == null || tokenCookie.isEmpty()){
				response.sendRedirect(request.getContextPath() + "/jsp/" + PAGE_REGISTRATION);
			}else{
				connectionsManager = ConnectionsManager.getInstance();
				connection = connectionsManager.openConnection();
				resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
				query = resourceBundle.getString(QUERY_SELECT_IS_TOKEN_USER_EXIST);
				statement = connection.prepareStatement(query);
				statement.setString(1,tokenCookie);
				resultSet = statement.executeQuery();
				if(resultSet.next()){
					long count = resultSet.getLong(TBL_USERS_CUSTOM_COL_TOKEN);
					if(count == 0){
						response.sendRedirect(request.getContextPath() + "/jsp/" + PAGE_REGISTRATION);
						return;
					}
					if(request.getSession() != null && request.getSession().getAttribute(ATTR_USER_NAME) == null){
						request.getSession().setAttribute(ATTR_USER_NAME,resultSet.getString(TBL_USERS_COL_USER_NAME));
					}
				
					response.setStatus(HttpServletResponse.SC_OK);
					response.sendRedirect(request.getContextPath() + "/jsp/" + PAGE_CHAT);
				}else{
					response.sendRedirect(request.getContextPath() + "/jsp/" + PAGE_REGISTRATION);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public String getName() {
		return CheckIsUserAlreadyRegistaredAction.class.getSimpleName();
	}

}
