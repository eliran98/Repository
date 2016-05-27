package com.prectise.model.dao.logic;

import java.util.List;

import com.prectise.model.bean.Message;

public interface IMsgDAO {
	
	public void insertMessage(Message message);
	
	public List<Message> getAllMessages();
	
	public List<Message> getBlockMessages(int blockNumber);
}
