package com.prectise.model.dao;

import com.prectise.model.dao.logic.IMsgDAO;
import com.prectise.model.dao.logic.IUsersDAO;

abstract public class FactoryDAO {

	public enum eDB {
		MySQL, DB2, ORACLE
	};

	public static FactoryDAO getFactory(eDB dbType) {
		switch (dbType) {
		case MySQL:
				return FactoryMySqlDAO.getInstance();
		case DB2:
		case ORACLE:	
		default:
			break;
		}
		return null;
	}
	
	public abstract IMsgDAO getMsgDAO();
	public abstract IUsersDAO getUsersDAO();
}
