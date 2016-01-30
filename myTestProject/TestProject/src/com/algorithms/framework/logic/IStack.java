package com.algorithms.framework.logic;

public interface IStack {
	public void push(int data) throws Exception;

	public int pop() throws Exception;
	
	public int top() throws Exception;

	public int findMiddle() throws Exception;

	public void deleteMiddle() throws Exception;
}
