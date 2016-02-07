package com.design.patterns.framework.dao.pattern;

public class FirstCustomDAO implements IFirstCustomDAO{
	
	private static FirstCustomDAO firstCustomDAO;

	private FirstCustomDAO(){
	}
	
	static FirstCustomDAO getInstance(){
		if(firstCustomDAO == null){
			firstCustomDAO = new FirstCustomDAO();
		}
		return firstCustomDAO;
	}
	
	@Override
	public void save(Object obj) {
		System.out.println("in FirstCustomDAO save operation");
	}

	@Override
	public void update(Object obj) {
		System.out.println("in FirstCustomDAO update operation");
	}

}
