package com.algorithms.framework.algorithm.search.sort;

/*
  Heap Sort Algorithm for sorting in increasing order:
	1. Build a max heap from the input data.
	2. At this point, the largest item is stored at the root of the heap. 
	   Replace it with the last item of the heap followed by reducing the size of heap by 1. Finally, heapify the root of tree.
	3. Repeat above steps until size of heap is greater than 1.
 */
public class HeapSort {
 
	//Time-Complexity = n*T(heapify())=O(nlogn). 
	public static void sort(int[] arr){
		int[] heap = buildMaxHeap(arr);
		int heapLen = heap.length;
		
		while(heapLen>1){
			swap(heap,0,heapLen-1);
			heapLen--;
			heapify(0 , heap, heapLen);
		}
	}
	
	private static int[] buildMaxHeap(int[] arr){
		int k = (arr.length - 1)/2;
		while(k>=0){
			heapify(k,arr,arr.length);
			k--;
		}
		return arr;
	}
	
	//Time-Complexity O(logn).
	private static void heapify(int i,int[] heap,int heapLen){
		int smallest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		if(l<heapLen && heap[l]>heap[smallest]){
			smallest = l;
		}
		if(r<heapLen && heap[r]>heap[smallest]){
			smallest = r;
		}
		
		if(smallest!=i){
			swap(heap,i,smallest);
			heapify(smallest,heap,heapLen);
		}
	}
	
	private static void swap(int[] heap,int i,int j){
		int temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
	}
	
}
