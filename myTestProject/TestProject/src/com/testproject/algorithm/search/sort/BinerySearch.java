package com.testproject.algorithm.search.sort;
/*Given a sorted array arr[] of n elements, write a function to search a given element x in arr[]
 * TimeComplexity o(logn).
 * 
 * n/2^x=1 -> n-array len , x - num of times divide array , 1 - is the worst case that divide until stay with one element.
 * => n = 2^x -> log(n)=x.
 */
public class BinerySearch {
   
	public static int searchRecur(int[] arr , int start,int end,int searchElement){
		
		if(end>=start){
			int mid = (end - start)/2;
			if(arr[mid] == searchElement){
				return mid;
			}
			
			if(arr[mid]<searchElement){
				return searchRecur(arr,mid+1,end,searchElement);
			}else{
				return searchRecur(arr,start,mid-1,searchElement);
			}
		}
		
		return -1;
	}
	
	public static int searchIteration(int[] arr , int start,int end,int searchElement){
		
		while(end>=start){
			int mid = (end - start)/2;
			if(arr[mid] == searchElement){
				return mid;
			}
			if(arr[mid]<searchElement){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		
		return -1;
	}
	
	//Search an element in a sorted and rotated array
	//Devise a way to find an element in the rotated array in O(log n) time.
	public static int pivotedBinarySearch(int[] arr,int searchElement){
		
		int pivot = findPivot(arr,0,arr.length-1);
		
		/*the array not rotated*/
		if(pivot == -1){
			return searchRecur(arr, 0, arr.length-1, searchElement);
		}
		
		if(arr[pivot] == searchElement){
			return pivot;
		}
		
		if(arr[0]<=searchElement){
			return searchRecur(arr, 0, pivot-1, searchElement);
		}
		
		return searchRecur(arr, pivot+1, arr.length-1, searchElement);
	}
	
	private static int findPivot(int[] arr, int start,int end){
	
		if(end>=start){
			int mid = (end - start)/2;
			
			if(mid < end && arr[mid]>arr[mid+1]){
				return mid;
			}
			if(mid > start && arr[mid]<arr[mid-1]){
				return mid-1;
			}
			if(arr[start]>=arr[mid]){
				return findPivot(arr,start,mid-1);
			}
			return findPivot(arr,mid+1,end);
		}
		return -1;
	}
}
