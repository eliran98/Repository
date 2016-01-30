package com.algorithms.framework.logic;

public interface IQueue {
	
	/*adds x to queue number ‘qn’ where qn is from 0 to k-1*/
	public boolean enqueue(int x, int qn) throws Exception;
	/*deletes an element from queue number ‘qn’ where qn is from 0 to k-1*/
	public boolean dequeue(int qn) throws Exception;
	/*print queue data*/
	public void printQueue(int qn) throws Exception;
	
	public boolean isKQueueEmpty(int qn) throws Exception;
	
	public boolean isKQueueFull(int qn) throws Exception;
	
}
