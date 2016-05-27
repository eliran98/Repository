package com.prectise.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.ICommons.ICommons;
import com.prectise.logic.IAction;
import com.prectise.model.db.ConnectionsManager;
import com.prectise.utils.MD5Digest;

public class SignInAction implements IAction,ICommons{

	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		
		ResourceBundle resourceBundle = null;
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try{
			Date date = new Date();
			String token = "";
			boolean isRemember = false;
			String checkBoxState = request.getParameter(PARAM_IS_REMEMBER);
			if(checkBoxState != null){
				isRemember = request.getParameter(PARAM_IS_REMEMBER).equals(CHECK_BOX_ON);
			}
			
			resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			String userName = request.getParameter(PARAM_USER_NAME);
			String password = request.getParameter(PARAM_SIGN_IN_PASSWORD);
			
			if(userName == null || userName.isEmpty() || password == null || password.isEmpty()){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("User Name not exist or wrong password");
				return;
			}
			
			connectionsManager = ConnectionsManager.getInstance();
			connection = connectionsManager.openConnection();
			
			query = resourceBundle.getString(QUERY_SELECT_SIGN_IN);
			statement = connection.prepareStatement(query);
			statement.setString(1,userName);
			statement.setString(2,password);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				System.out.println("resultSet.getLong(COUNT_RESULT)=" + resultSet.getLong("COUNT_RESULT"));
				if(resultSet.getLong("COUNT_RESULT") == 0){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					response.getWriter().write("User Name not exist or wrong password");
					return;
				}
			}else{
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("User Name not exist or wrong password");
				return;
			}
			if(isRemember){
				token = MD5Digest.generateMD5(String.valueOf(date.getTime()));
				query = resourceBundle.getString(QUERY_UPDATE_SIGN_IN);
				statement = connection.prepareStatement(query);
				statement.setString(1,token);
				statement.setString(2,userName);
				statement.setString(3,password);
				int updateRecords = statement.executeUpdate();
				System.out.println("updateRecords=" + updateRecords);
				response.setStatus(HttpServletResponse.SC_OK);
				Cookie cookieToken = new Cookie(COOKIE_KEY_TOKEN,token);
				response.addCookie(cookieToken);
			}
			if(request.getSession() != null){
				request.getSession().setAttribute(ATTR_USER_NAME,userName);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return SignInAction.class.getSimpleName();
	}
	
	private boolean isFeildLegal(String field , ResourceBundle resourceBundle,String queryPattern,Connection connection) throws SQLException{
		
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		boolean isLegal = false;
		
		String query = resourceBundle.getString(queryPattern);
		statement = connection.prepareStatement(query);
		statement.setString(1,field);
		resultSet = statement.executeQuery();
		if(resultSet.next()){
			System.out.println("resultSet.getLong(COUNT_RESULT)=" + resultSet.getLong("COUNT_RESULT"));
			isLegal = resultSet.getLong("COUNT_RESULT") == 0 ? true : false;
		}
		return isLegal;
	}

}
