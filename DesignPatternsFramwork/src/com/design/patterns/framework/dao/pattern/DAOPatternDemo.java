package com.design.patterns.framework.dao.pattern;

import com.design.patterns.framework.dao.pattern.DaoFactory.DBType;

public class DAOPatternDemo {

	public static void main(String[] args) {
		
		//generate factory of DB-MySQL.
		DaoFactory factory = DaoFactory.getFactory(DBType.MYSQL);
		
		//get DAO (DB-MySQL) that handle firstCustom business logic.
		IFirstCustomDAO firstCustomDAO = factory.getFirstCustomDAO();
		ISecondCustomDAO secondCustomDAO = factory.getSecondCustomDAO();
		 
		//operation on DB.
		Object obj = new Object();
		firstCustomDAO.update(obj);
		secondCustomDAO.save(obj);
	}

}
