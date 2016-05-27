package com.prectise.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.prectise.ICommons.ICommons;

public class ConnectionsManager implements ICommons{
	
	private static ConnectionsManager connectionsManager;
	private String jdbcDriver;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	private ConnectionsManager(){
		ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
		jdbcDriver = resourceBundle.getString(JDBC_DRIVER);
		dbUrl = resourceBundle.getString(DB_URL);
		dbUser = resourceBundle.getString(DB_USER);
		dbPassword = resourceBundle.getString(DB_PASSWORD);
	}
	
	public static ConnectionsManager getInstance(){
		if(connectionsManager == null){
			connectionsManager = new ConnectionsManager();
		}
		return connectionsManager;
	}
	
	public Connection openConnection(){
		
		Connection connection = null;
		
		try {
			
			/*get connection from datasource*/
			//Context context = null;
			//context = new InitialContext();
			//DataSource dc = (DataSource) context.lookup(dataSrcName);
			//dc.getConnection();
			
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public void closeConnection(Connection connection){
		if(connection == null){
			return;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
