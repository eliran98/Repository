package com.algorithms.framework.beans;

import com.algorithms.framework.data.structures.BineryHeap;

public class CustomMinHeapNode extends BineryHeap.BineryHeapNode{
	
	private int arrIndex;
	private int nextIndex;

	public CustomMinHeapNode(int element) {
		super(element);
	}

	public int getArrIndex() {
		return arrIndex;
	}

	public void setArrIndex(int arrIndex) {
		this.arrIndex = arrIndex;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	@Override
	public String toString() {
		return "CustomMinHeapNode [arrIndex=" + arrIndex + ", nextIndex=" + nextIndex + "]";
	}
	
}
