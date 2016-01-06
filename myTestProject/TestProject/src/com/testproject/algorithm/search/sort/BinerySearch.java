package com.testproject.algorithm.search.sort;
/*Given a sorted array arr[] of n elements, write a function to search a given element x in arr[]
 * TimeComplexity o(logn).
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
}
