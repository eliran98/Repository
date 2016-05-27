package com.prectise.model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.prectise.ICommons.ICommons;
import com.prectise.model.bean.Message;
import com.prectise.model.dao.logic.IMsgDAO;
import com.prectise.model.db.ConnectionsManager;

public class MsgDAO implements IMsgDAO,ICommons{
	
	private static MsgDAO instance;
	
	private MsgDAO(){};
	
	public static MsgDAO getInstance(){
		if(instance == null){
			instance = new MsgDAO();
		}
		return instance;
	}

	@Override
	public void insertMessage(Message message) {

		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResourceBundle resourceBundle = null;
		
		try{
			resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			connectionsManager = ConnectionsManager.getInstance();
			connection = connectionsManager.openConnection();
			query = resourceBundle.getString(QUERY_INSERT_MESSAGE);
			statement = connection.prepareStatement(query);
			statement.setString(1,message.getText());
			statement.setTimestamp(2,new Timestamp(message.getMsgDateAndTime().getTime()));
			statement.setString(3,message.getUserName());
			int numInsertRecord = statement.executeUpdate();
			System.out.println("MsgDAO.insertMessage().numInsertRecord=" + numInsertRecord);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connectionsManager.closeConnection(connection);
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Message> getAllMessages() {
		
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResourceBundle resourceBundle = null;
		ResultSet resultSet = null;
		List<Message> messages = new ArrayList<Message>();
		
		try{
			resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			connectionsManager = ConnectionsManager.getInstance();
			connection = connectionsManager.openConnection();
			query = resourceBundle.getString(QUERY_SELECT_ALL_MESSAGES);
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Message message = new Message();
				
				message.setText(resultSet.getString(TBL_MESSAGES_COL_TEXT));
				message.setMsgDateAndTime(resultSet.getTimestamp(TBL_MESSAGES_COL_DATE_MSG));
				
				messages.add(message);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connectionsManager.closeConnection(connection);
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return messages;
	}

	@Override
	public List<Message> getBlockMessages(int blockNumber) {
		
		String query = null;
		ConnectionsManager connectionsManager = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResourceBundle resourceBundle = null;
		ResultSet resultSet = null;
		List<Message> messages = new ArrayList<Message>();
		int offset = (blockNumber - 1) * BLOCK_SIZE;
		
		try{
			resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE);
			connectionsManager = ConnectionsManager.getInstance();
			connection = connectionsManager.openConnection();
			query = resourceBundle.getString(QUERY_SELECT_BLOCK_MESSAGES);
			statement = connection.prepareStatement(query);
			statement.setInt(1,offset);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				Message message = new Message();
				
				message.setText(resultSet.getString(TBL_MESSAGES_COL_TEXT));
				message.setMsgDateAndTime(resultSet.getTimestamp(TBL_MESSAGES_COL_DATE_MSG));
				
				messages.add(message);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			connectionsManager.closeConnection(connection);
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return messages;
	}
}
	