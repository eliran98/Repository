package com.prectise.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prectise.ICommons.ICommons;
import com.prectise.logic.IAction;
import com.prectise.model.db.ConnectionsManager;
import com.prectise.utils.MD5Digest;
import com.prectise.utils.Utils;

public class RegistrationAction implements IAction,ICommons{
	
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) {
		
		ResourceBundle resourceBundle = null;
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		
		try{
			Date date = new Date();
			boolean isRemember = false;
			String checkBoxState = request.getParameter(PARAM_IS_REMEMBER);
			if(checkBoxState != null){
				isRemember = request.getParameter(PARAM_IS_REMEMBER).equals(CHECK_BOX_ON);
			}
			resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE); 
			String firstName = request.getParameter(PARAM_FIRST_NAME);
			String lastName = request.getParameter(PARAM_LAST_NAME);
			String email = request.getParameter(PARAM_EMAIL);
			String gender = request.getParameter(PARAM_GENDER);
			String userName = request.getParameter(PARAM_USER_NAME);
			String password = request.getParameter(PARAM_PASSWORD);
			String joinDate = Utils.getDateFormat(DATE_PATTERN,date);
			String token = "";

			connectionsManager = ConnectionsManager.getInstance();
			connection = connectionsManager.openConnection();
			
			if(!isFeildLegal(email,resourceBundle, QUERY_SELECT_IS_EMAIL_USER_EXIST,connection)){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Email address already exist");
				return;
			}
			
			if(!isFeildLegal(userName,resourceBundle, QUERY_SELECT_IS_USER_NAME_ALREADY_EXIST,connection)){
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("User name: " + userName + " already exist");
				return;
			}
			
			if(isRemember){
				token = MD5Digest.generateMD5(String.valueOf(date.getTime()));
			}
			
			StringBuilder cols = new StringBuilder();
			StringBuilder values = new StringBuilder();
			
			cols.append(TBL_USERS_COL_FIRST_NAME).append(",");
			cols.append(TBL_USERS_COL_LAST_NAME).append(",");
			cols.append(TBL_USERS_COL_EMAIL).append(",");
			cols.append(TBL_USERS_COL_GENDER).append(",");
			cols.append(TBL_USERS_COL_JOIN_DATE).append(",");
			cols.append(TBL_USERS_COL_USER_NAME).append(",");
			cols.append(TBL_USERS_COL_PASSWORD).append(",");
			cols.append(TBL_USERS_COL_TOKEN);
			
			values.append("'" + firstName + "'").append(",");
			values.append("'" + lastName + "'").append(",");
			values.append("'" + email + "'").append(",");
			values.append("'" + gender + "'").append(",");
			values.append("str_to_date('" + joinDate + "','" + MY_SQL_DATE_FORMAT + "')").append(",");
			values.append("'" + userName + "'").append(",");
			values.append("'" + password + "'").append(",");
			values.append("'" + token + "'");
			
			query = MessageFormat.format(resourceBundle.getString(QUERY_INSERT_NEW_USER),cols.toString(),values.toString());
			System.out.println("query=" + query);
			statement = connection.prepareStatement(query);
			int recordNum = statement.executeUpdate();
			
			response.setStatus(HttpServletResponse.SC_OK);
			if(isRemember){
				Cookie cookieToken = new Cookie(COOKIE_KEY_TOKEN,token);
				response.addCookie(cookieToken);
			}
			
			if(request.getSession() != null){
				request.getSession().setAttribute(ATTR_USER_NAME,userName);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			connectionsManager.closeConnection(connection);
			if(statement != null){
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String getName() {
		return RegistrationAction.class.getSimpleName();
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
