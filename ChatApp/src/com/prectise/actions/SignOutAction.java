package com.prectise.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.ICommons.ICommons;
import com.prectise.logic.IAction;
import com.prectise.model.db.ConnectionsManager;

public class SignOutAction implements IAction,ICommons{

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		
		ResourceBundle resourceBundle = null;
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			String token = request.getParameter("token");
			if(token != null && !token.isEmpty()){
				Cookie cookie = new Cookie(COOKIE_KEY_TOKEN, "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

				resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
				connectionsManager = ConnectionsManager.getInstance();
				connection = connectionsManager.openConnection();
				query = resourceBundle.getString(QUERY_UPDATE_SIGN_OUT);
				statement = connection.prepareStatement(query);
				statement.setString(1,"");
				statement.setString(2,token);
				int updateRecords = statement.executeUpdate();
				System.out.println("updateRecords=" + updateRecords);
			}
			response.sendRedirect(request.getContextPath() + "/jsp/" + PAGE_HOME_PAGE);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public String getName() {
		return SignOutAction.class.getSimpleName();
	}

}
