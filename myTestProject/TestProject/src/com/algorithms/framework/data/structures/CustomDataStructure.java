package com.algorithms.framework.data.structures;

import com.algorithms.framework.exception.AlgorithmsFrameworkException;
import com.algorithms.framework.logic.ICustomDataStructure;
import com.algorithms.framework.questions.NodeDoubleLinkedList;

//http://www.geeksforgeeks.org/a-data-structure-question/
//Design an efficient data structure for given operations
public class CustomDataStructure implements ICustomDataStructure{
	
	private BineryHeap minHeap , maxHeap;
	private NodeDoubleLinkedList doubleLinkedList;
	
	//Time-complexity O(1)
	@Override
	public Object findMin() throws AlgorithmsFrameworkException {
		return ((NodeDoubleLinkedList)minHeap.getMin()).getExtraData();
	}

	//Time-complexity O(1)
	@Override
	public Object findMax() throws AlgorithmsFrameworkException {
		return ((NodeDoubleLinkedList)maxHeap.getMax()).getExtraData();
	}
	
	//Time-complexity O(logn)
	@Override
	public void deleteMin() throws AlgorithmsFrameworkException {
		NodeDoubleLinkedList node = (NodeDoubleLinkedList) minHeap.getMin();
		minHeap.deleteRoot();
  	    node.deleteElement();
	}
	
	//Time-complexity O(logn)
	@Override
	public void deleteMax() throws AlgorithmsFrameworkException {
		NodeDoubleLinkedList node = (NodeDoubleLinkedList) maxHeap.getMin();
		maxHeap.deleteRoot();
  	    node.deleteElement();
	}

	//Time-complexity O(logn)
	@Override
	public void insert(Object obj) throws AlgorithmsFrameworkException {
		
		if(doubleLinkedList == null){
			doubleLinkedList = new NodeDoubleLinkedList(0);
		}else{
			doubleLinkedList = doubleLinkedList.addToHead(0);
		}
		
		doubleLinkedList.setExtraData(obj);
		
		minHeap.insert(doubleLinkedList);
		maxHeap.insert(doubleLinkedList);
		
	}

	//Time-complexity O(n)
	@Override
	public void delete(Object obj) throws AlgorithmsFrameworkException {
		
		NodeDoubleLinkedList node = doubleLinkedList.findNode(obj);
		
		minHeap.delete(node);
		maxHeap.delete(node);
		
		node.deleteElement();
	}

}
