package com.design.patterns.framework.dao.pattern;

public abstract class DaoFactory {

	public enum DBType {
		ORACLE, DB2, MYSQL
	};

	public static DaoFactory getFactory(DBType type) {
		
		DaoFactory daoFactory = null;
		if (type == null) {
			return daoFactory;
		}

		switch (type) {
			case DB2:
				daoFactory = DaoFactoryDB2.getInstance();
				break;
			case MYSQL:
				daoFactory = DaoFactoryMySQL.getInstance();
				break;
			case ORACLE:
				daoFactory = DaoFactoryOracle.getInstance();	
				break;
			default:
				break;
		}
		
		return daoFactory;
	}
	
	abstract public IFirstCustomDAO getFirstCustomDAO();
	
	abstract public ISecondCustomDAO getSecondCustomDAO();

}
