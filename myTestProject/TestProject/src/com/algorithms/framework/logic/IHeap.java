package com.algorithms.framework.logic;

public interface IHeap {

	public int right(int i);

	public int left(int i);

	public void heapify(int i);

	public Object getMin();

	public Object getMax();

	public void replace(Object val);
	
	public int insert(Object val);
	
	public void deleteRoot();
	
	public void delete(Object obj);
}
