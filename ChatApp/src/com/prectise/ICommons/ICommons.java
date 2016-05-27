package com.prectise.ICommons;

public interface ICommons {
	
	public final String ACTION = "action";
	public final String RESOURCE_BUNDLE = "com.prectise.resources.mysql";
	public final String JDBC_DRIVER = "jdbc.driver";
	public final String DB_URL = "db.url";
	public final String DB_USER = "db.user";
	public final String DB_PASSWORD = "db.password";
	
	//pages
	public final String PAGE_CHAT 			= "chat.jsp";
	public final String PAGE_REGISTRATION   = "registration.jsp";
	public final String PAGE_SIGN_IN   		= "signIn.jsp";
	public final String PAGE_HOME_PAGE   	= "main-page.jsp";
	
	//general
	public final String DATE_PATTERN 		    = "yyyy-MM-dd";
	public final String DATE_AND_TIME_PATTERN 	= "yyyy-MM-dd : hh-mm";
	public final String MY_SQL_DATE_FORMAT      = "%Y-%m-%d";
	public final String COOKIE_KEY_TOKEN        = "TOKEN";
	public final String CHECK_BOX_ON    	    = "on";
	public final int BLOCK_SIZE    	    		= 10;
	
	//params
	public final String PARAM_FIRST_NAME    = "firstName";
	public final String PARAM_LAST_NAME     = "lastName";
	public final String PARAM_GENDER        = "gender";
	public final String PARAM_EMAIL         = "mail";
	public final String PARAM_USER_NAME     = "userName";
	public final String PARAM_PASSWORD      = "newPassword";
	public final String PARAM_IS_REMEMBER   = "isRemember";
	public final String PARAM_SIGN_IN_PASSWORD   = "password";
	public final String PARAM_TOKEN			     = "TOKEN";
	public final String PARAM_BLOCK_NUMBER		 = "blockNumber";
	
	//attributes
	public final String ATTR_USER_NAME = "userName";
	
	//DB consts
	//tbl_users cols
	public final String TBL_USERS_COL_ID     		 = "id";
	public final String TBL_USERS_COL_FIRST_NAME     = "First_Name";
	public final String TBL_USERS_COL_LAST_NAME      = "Last_Name";
	public final String TBL_USERS_COL_EMAIL     	 = "Email";
	public final String TBL_USERS_COL_GENDER    	 = "Gender";
	public final String TBL_USERS_COL_JOIN_DATE 	 = "Join_Date";
	public final String TBL_USERS_COL_USER_NAME	 	 = "UserName";
	public final String TBL_USERS_COL_PASSWORD	 	 = "Password";
	public final String TBL_USERS_COL_TOKEN	 		 = "Token";
	
	//tbl_messages cols
	public final String TBL_MESSAGES_COL_ID     	 = "id";
	public final String TBL_MESSAGES_COL_TEXT     	 = "Text";
	public final String TBL_MESSAGES_COL_DATE_MSG    = "DateMsg";
	public final String TBL_MESSAGES_COL_USER_ID     = "User_ID";
	
	//tbl Custom cols
	public final String TBL_USERS_CUSTOM_COL_TOKEN   = "COUNT_TOKEN";
	
	//queries
	public final String QUERY_INSERT_NEW_USER 			 		= "insert.new.user";
	public final String QUERY_SELECT_IS_TOKEN_USER_EXIST 	    = "select.is.token.user.exist";
	public final String QUERY_SELECT_IS_USER_NAME_ALREADY_EXIST = "select.is.user.name.already.exist";
	public final String QUERY_SELECT_IS_EMAIL_USER_EXIST 		= "select.is.email.user.exist";
	public final String QUERY_SELECT_SIGN_IN 					= "select.sign.in";
	public final String QUERY_UPDATE_SIGN_IN 					= "update.sign.in";
	public final String QUERY_UPDATE_SIGN_OUT 					= "update.sign.out";
	public final String QUERY_INSERT_MESSAGE 					= "insert.message";
	public final String QUERY_SELECT_ALL_MESSAGES 				= "select.all.messages";
	public final String QUERY_SELECT_BLOCK_MESSAGES 			= "select.block.messages";
}
