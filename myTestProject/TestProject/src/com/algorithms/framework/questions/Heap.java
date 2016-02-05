package com.algorithms.framework.questions;

import com.algorithms.framework.beans.CustomMinHeapNode;
import com.algorithms.framework.data.structures.BineryHeap;
import com.algorithms.framework.data.structures.BineryHeap.eTypeHeap;

public class Heap {
	
	private final static int MAX_INT = (int) Math.pow(2,31);
	
	//merge k sorted arrays
	public static int[] mergeSortedArrs(int[][] sortedArrs){
		int k = sortedArrs.length;
		int n = sortedArrs[0].length;
		int[] outputArr = new int[k*n];
		
		CustomMinHeapNode[] arrCustomMinHeapNode = new CustomMinHeapNode[k];
		
		for (int i = 0; i < k ; i++) {
			CustomMinHeapNode customMinHeapNode = new CustomMinHeapNode(sortedArrs[i][0]);
			customMinHeapNode.setArrIndex(i);
			customMinHeapNode.setNextIndex(1);
			arrCustomMinHeapNode[i] = customMinHeapNode;
		}
		
		BineryHeap heap = new BineryHeap(arrCustomMinHeapNode,eTypeHeap.MIN_HEAP);
			for (int i = 0; i < k*n ; i++) {
				CustomMinHeapNode root = (CustomMinHeapNode) heap.getMin();
				outputArr[i] = root.getElement();
				System.out.println("root.getNextIndex()=" + root.getNextIndex());
				if(root.getNextIndex() < n){
					root.setElement(sortedArrs[root.getArrIndex()][root.getNextIndex()]);
					root.setNextIndex(root.getNextIndex()+1);
				}else{
					root.setElement(MAX_INT);
				}
				
				heap.replaceMin(root);
			}
		
		return outputArr;
	}
}
