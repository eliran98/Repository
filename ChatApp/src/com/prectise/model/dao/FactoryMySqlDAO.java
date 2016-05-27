package com.prectise.model.dao;

import com.prectise.model.dao.impl.MsgDAO;
import com.prectise.model.dao.impl.UsersDAO;
import com.prectise.model.dao.logic.IMsgDAO;
import com.prectise.model.dao.logic.IUsersDAO;

public class FactoryMySqlDAO extends FactoryDAO{
	
	private static FactoryMySqlDAO instance;
	
	private FactoryMySqlDAO(){};
	
	public static FactoryMySqlDAO getInstance(){
		if(instance == null){
			instance = new FactoryMySqlDAO();
		}
		return instance;
	}

	@Override
	public IMsgDAO getMsgDAO() {
		return MsgDAO.getInstance();
	}

	@Override
	public IUsersDAO getUsersDAO() {
		return UsersDAO.getInstance();
	}

}
