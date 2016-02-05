package com.algorithms.framework.data.structures;

import java.util.LinkedList;
import java.util.Queue;

import com.algorithms.framework.beans.CustomMinHeapNode;
import com.algorithms.framework.logic.IMinHeap;

public class BineryHeap implements IMinHeap{
	
	private final int HEAP_SIZE;
	private final int HEAD_HEAP = 0;
	private final Object[] heap;
	private final eTypeHeap type;
	
	public enum eTypeHeap{MIN_HEAP,MAX_HEAP};
	
	public BineryHeap(int[] values,eTypeHeap type){
		this.type = type;
		HEAP_SIZE = values.length;
		heap = new BineryHeapNode[HEAP_SIZE];
		
		for (int i = 0; i < values.length; i++) {
			BineryHeapNode minHeapNode = new BineryHeapNode(values[i]);
			heap[i] = minHeapNode;
		}
		
		int k = (HEAP_SIZE - 1)/2;
		while(k>=0){
			heapify(k);
			k--;
		}
	}
	
	public BineryHeap(CustomMinHeapNode[] values,eTypeHeap type){
		this.type = type;
		HEAP_SIZE = values.length;
		heap = new CustomMinHeapNode[HEAP_SIZE];
		
		for (int i = 0; i < values.length; i++) {
			heap[i] = values[i];
		}
		
		int k = (HEAP_SIZE - 1)/2;
		while(k>=0){
			heapify(k);
			k--;
		}
	}
	
	@Override
	public int left(int i) {
		return 2*i + 1;
	}

	@Override
	public int right(int i) {
		return 2*i + 2;
	}

	@Override
	public void heapify(int i) {
		
		switch (type) {
		case MIN_HEAP:
			minHeapify(i);
			break;
		case MAX_HEAP:
			maxHeapify(i);
			break;

		default:
			break;
		}
		
	}
	
	private void minHeapify(int i){
		int smallest = i;
		int l = left(i);
		int r = right(i);
		
		if(l<HEAP_SIZE && ((BineryHeapNode)heap[l]).getElement()<((BineryHeapNode)heap[smallest]).getElement()){
			smallest = l;
		}
		if(r<HEAP_SIZE && ((BineryHeapNode)heap[r]).getElement()<((BineryHeapNode)heap[smallest]).getElement()){
			smallest = r;
		}
		
		if(smallest!=i){
			swap(heap[i], heap[smallest]);
			minHeapify(smallest);
		}
	}
	
	private void maxHeapify(int i){
		int smallest = i;
		int l = left(i);
		int r = right(i);
		
		if(l<HEAP_SIZE && ((BineryHeapNode)heap[l]).getElement()>((BineryHeapNode)heap[smallest]).getElement()){
			smallest = l;
		}
		if(r<HEAP_SIZE && ((BineryHeapNode)heap[r]).getElement()>((BineryHeapNode)heap[smallest]).getElement()){
			smallest = r;
		}
		
		if(smallest!=i){
			swap(heap[i], heap[smallest]);
			maxHeapify(smallest);
		}
	}


	@Override
	public Object getMin() {
		if(heap == null){
			return null;
		}
		return heap[HEAD_HEAP];
	}

	@Override
	public void replaceMin(Object val) {
		if(heap == null){
			return;
		}
		heap[HEAD_HEAP] = val;
		heapify(HEAD_HEAP);
	}
	
	private void swap(Object src,Object des){
		int temp = ((BineryHeapNode)src).element;
		((BineryHeapNode)src).element = ((BineryHeapNode)des).element;
		((BineryHeapNode)des).element = temp;
	}
	
	public void printHeapLevels(){
		if(heap == null){
			return;
		}
		print(HEAD_HEAP);
	}
	
	private void print(int i){
		
		int count = 0;	
		Queue<BineryHeapNode> queue = new LinkedList<BineryHeapNode>();
		queue.add(((BineryHeapNode)heap[count]));
		
		while(!queue.isEmpty()){
			BineryHeapNode bineryHeapNode = queue.poll();
			int R = right(count);
			int L = left(count);
			if(L < HEAP_SIZE){
				queue.add(((BineryHeapNode)heap[L]));
			}
			if(R < HEAP_SIZE){
				queue.add(((BineryHeapNode)heap[R]));
			}
			System.out.print(bineryHeapNode.element + " ");
			count++;
		}
		
	}
	
	//TimeComplexity O(n).
	public static boolean isBinerHeap(int[] arr , eTypeHeap type){
		boolean result = false;
		switch (type) {
			case MAX_HEAP:
				result = isBineryHeapMax(arr);
				break;
			case MIN_HEAP:
				result = isBineryHeapMin(arr);
				break;
	
			default:
				break;
		}
		return result;
	}
	
	private static boolean isBineryHeapMax(int[] arr){
		
		int arrLen = arr.length;
		int parent = 0;
		int left_child = 0;
		int right_child = 0;
		
		int lastInternalNode = ((arrLen-1)-1/2); 
		
		for (int i = 0 ; i < lastInternalNode ; i++) {
			parent = arr[i];
			left_child = 2*i +1;
			right_child = 2*i + 2;
			if((left_child < arrLen && parent < arr[left_child]) || (right_child < arrLen && parent < arr[right_child])){
				return false;
			}
		}
		return true;
	}
	
	private static boolean isBineryHeapMin(int[] arr){
		
		int arrLen = arr.length;
		int parent = 0;
		int left_child = 0;
		int right_child = 0;
		
		for (int i = 0 ; i < arrLen ; i++) {
			parent = arr[i];
			left_child = 2*i +1;
			right_child = 2*i + 2;
			if((left_child < arrLen && parent > arr[left_child]) || (right_child < arrLen && parent > arr[right_child])){
				return false;
			}
		}
		return true;
		
	}
	
	public static class BineryHeapNode{
		private int element;
		
		public BineryHeapNode(int element){
			this.element = element;
		}

		public int getElement() {
			return element;
		}

		public void setElement(int element) {
			this.element = element;
		}
	}
}
