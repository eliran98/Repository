package com.design.patterns.framework.dao.pattern;

public class SecondCustomDAO implements ISecondCustomDAO{

	private static SecondCustomDAO secondCustomDAO;

	private SecondCustomDAO(){
	}
	
	static SecondCustomDAO getInstance(){
		if(secondCustomDAO == null){
			secondCustomDAO = new SecondCustomDAO();
		}
		return secondCustomDAO;
	}
	
	@Override
	public void delete() {
		System.out.println("in ISecondCustomDAO delete operation");
	}

	@Override
	public void save(Object obj) {
		System.out.println("in ISecondCustomDAO save operation");
	}

}
