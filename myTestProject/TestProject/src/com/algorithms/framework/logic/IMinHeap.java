package com.algorithms.framework.logic;

public interface IMinHeap {

	public int right(int i);

	public int left(int i);

	public void heapify(int i);

	public Object getMin();

	public void replaceMin(Object val);
}
