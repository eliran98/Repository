package com.algorithms.framework.logic;

/*Queue basic operations*/
public interface IQueue {
	public void enqueue(Object object) throws Exception;

	public Object dequeue() throws Exception;

	public boolean isEmpty() throws Exception;

	public Object peek() throws Exception;
	
	public int size() throws Exception;
}
