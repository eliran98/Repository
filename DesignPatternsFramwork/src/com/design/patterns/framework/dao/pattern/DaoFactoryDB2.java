package com.design.patterns.framework.dao.pattern;

public class DaoFactoryDB2 extends DaoFactory{
	
	private static final class SingletonHolder {
		private static final DaoFactory INSTANCE = new DaoFactoryOracle();
	}
	
	public static DaoFactory getInstance() {
		return SingletonHolder.INSTANCE;
	}

	@Override
	public FirstCustomDAO getFirstCustomDAO() {
		return FirstCustomDAO.getInstance();
	}

	@Override
	public SecondCustomDAO getSecondCustomDAO() {
		return SecondCustomDAO.getInstance();
	}
	
}
