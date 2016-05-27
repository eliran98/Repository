package com.algorithms.framework.algorithm.search.sort;

import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.algorithms.framework.questions.NodeLinkedList;

public class SortAlgorithm {

	public enum eType {
		RECURSIVE, ITERATIVE
	};

	public static void quickSort(int[] arr, eType type) {

		switch (type) {
		case RECURSIVE:
			quickSortRec(arr, 0, arr.length - 1);
			break;
		case ITERATIVE:
			System.out.println("No ITERATIVE algorithm");
		default:
			break;
		}
	}

	/* TimeComplexity o(nlogn) */
	private static void quickSortRec(int[] arr, int start, int end) {

		if (start < end) {
			int pivot = partition(arr, start, end);

			quickSortRec(arr, start, pivot - 1);
			quickSortRec(arr, pivot + 1, end);
		}

	}

	/* TimeComplexity o(n) */
	private static int partition(int[] arr, int start, int end) {

		int pivotVal = arr[end];
		int low = start - 1;

		for (int j = start; j <= end; j++) {
			if (pivotVal > arr[j]) {
				low++;
				swap(arr, j, low);
			}
		}

		swap(arr, low + 1, end);

		return low + 1;
	}

	private static void swap(int[] arr, int indexA, int indexB) {
		int temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}
	
	private static void swap(Integer[] arr, int indexA, int indexB) {
		int temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}

	/*TimeComplexity o(n^2)*/
	/*
     * In bubble sort, we basically traverse the array from first
     * to array_length - 1 position and compare the element with the next one.
     * Element is swapped with the next element if the next element is greater.
     *
     * Bubble sort steps are as follows.
     *
     * 1. Compare array[0] & array[1]
     * 2. If array[0] > array [1] swap it.
     * 3. Compare array[1] & array[2]
     * 4. If array[1] > array[2] swap it.
     * ...
     * 5. Compare array[n-1] & array[n]
     * 6. if [n-1] > array[n] then swap it.
     *
     * After this step we will have largest element at the last index.
     *
     * Repeat the same steps for array[1] to array[n-1]
     *  
     */
	public static void bubbleSort(int[] arr){
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < (arr.length - i); j++) {
				if(arr[j-1] > arr[j]){
					swap(arr,j,j-1);
				}
			}
		}
	}
	
	/*Time-Complexity o(n^2)*/
	public static void selectionSort(int[] arr){
		
		for (int i = 0; i < arr.length; i++) {
			
			int minIndex = i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[minIndex] > arr[j]){
					minIndex = j;
				}
			}
			swap(arr,minIndex,i);
		}
		
	}
	
	private static int selectionSort(Integer[] arr,int[] src,int startSrcIndex){
		for (int i = 0; i < arr.length; i++) {
			
			int minIndex = i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[minIndex] > arr[j]){
					minIndex = j;
				}
			}
			swap(arr,minIndex,i);
			src[startSrcIndex] = arr[minIndex];
			startSrcIndex++;
		}
		return startSrcIndex;
	}
	
	//TODO not working
	public static int[] bucketSort(int[] arr){
		
		List<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(arr.length);
		
		for (int i = 0; i < buckets.size() ; i++) {
			
			int bucketIndex = arr[i]/buckets.size();
			if(buckets.get(bucketIndex) == null){
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(arr[i]);
				buckets.add(bucketIndex,list);
			}else{
				buckets.get(bucketIndex).add(arr[i]);
			}
			
		}
		
		int index = 0;
		for (int k = 0 ; k < buckets.size() ; k++) {
		    List<Integer> list =  buckets.get(k);
		    Integer[] res = new Integer[list.size()];
		    list.toArray(res);
		    index = selectionSort(res,arr,index);
		}
		
		return arr;
	}
	
	public static void quickSortRec(NodeLinkedList linkedList,NodeLinkedList start,NodeLinkedList end){
		boolean isPivotInFirstPlace = false;
		if(end!=null && isStartBeforeEnd(start,end)){
			
			NodeLinkedList prev_pivot = partition(linkedList,start,end);
			if(prev_pivot == null){
				prev_pivot = linkedList;
				isPivotInFirstPlace = true;
			}
			
			System.out.println("Pivot: "+prev_pivot.getNext().getData());
			System.out.println("linked list after round:");
			NodeLinkedList.printLinkedList(linkedList);
			System.out.println("");
			System.out.println("finish round:");
			System.out.println("");
			System.out.println("");
			
			quickSortRec(linkedList,start,prev_pivot);
			if(isPivotInFirstPlace){
				quickSortRec(linkedList,prev_pivot.getNext(),end);
			}else{
				quickSortRec(linkedList,prev_pivot.getNext().getNext(),end);
			}
		}
	}
	
	private static NodeLinkedList partition(NodeLinkedList linkedList,NodeLinkedList start,NodeLinkedList end){
		
		int pivotVal = end.getData();
		NodeLinkedList low = NodeLinkedList.getPrev(start,linkedList);
		NodeLinkedList prev = null;
		
		if(pivotVal == 64){
			System.out.println("SortAlgorithm.partition()");
		}
		
		while(start!=null){
			if(start.getData() < pivotVal){
				
				if(low == null){
					low = linkedList;
					prev = null;
				}else{
					prev = low;
					low = low.getNext();
				}
				NodeLinkedList.swap(low,start);
			}
			start = start.getNext();
		}
		
		if(low!=null){
			prev = low;
			low = low.getNext();
		}else{
			low = linkedList;
			prev = null;
		}
		
		NodeLinkedList.swap(low,end);
		
		return prev;
	}
	
   private static boolean isStartBeforeEnd(NodeLinkedList start,NodeLinkedList end){
	   if(start == end){
		   return false;
	   }
	     while(start!=null){
	    	 
	    	 if(start == end){
	    		 return true;
	    	 }
	    	 
	    	 start=start.getNext();
	     }
	     return false;
   }
   
   //http://www.java2novice.com/java-sorting-algorithms/merge-sort/
   public static void mergeSort(int[] arr,int start,int end){
		
		if(start > end){
			return;
		}
		
		int mid = start + (end - start)/2;
		
		mergeSort(arr,start,mid);
		mergeSort(arr,mid+1,end);
		
		mergeParts(arr, start, end, mid);
	}
	
	private static void mergeParts(int[] arr,int start,int end,int mid){
		
		int i = start;
		int j = mid+1;
		int k = start;
		
		int[] tempArr = new int[arr.length];
		for (int l = start; l <=end; l++) {
			tempArr[l] = arr[l];
		}
		
		while(i<=mid && j<=end){
			if(tempArr[i]  <= tempArr[j]){
				arr[k] = tempArr[i];
				i++;
			}else{
				arr[k] = tempArr[j];
				j++;
			}
			k++;
		}
		
		while(i<=mid){
			arr[k] = tempArr[i];
			i++;
			k++;
		}
	}
	
	
	//Time Complexity o(logn).
	public static int binerySearch(int[] sortedArr,int start,int end,int find){
		
		if(end < start){
			return 0;
		}
		
		int mid = start + (end - start)/2;
		if(sortedArr[mid] == find){
			return mid;
		}
		
		if(sortedArr[start] <= find && find <= sortedArr[mid]){
			return binerySearch(sortedArr, start, mid, find);
		}else{
			return binerySearch(sortedArr, mid+1, end, find);
		}
	}
}
