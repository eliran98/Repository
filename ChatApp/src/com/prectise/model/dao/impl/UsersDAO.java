package com.prectise.model.dao.impl;

import com.prectise.model.dao.logic.IUsersDAO;

public class UsersDAO implements IUsersDAO{
	
	private static UsersDAO instance;
	
	private UsersDAO(){};
	
	public static UsersDAO getInstance(){
		if(instance == null){
			instance = new UsersDAO();
		}
		return instance;
	}
}
