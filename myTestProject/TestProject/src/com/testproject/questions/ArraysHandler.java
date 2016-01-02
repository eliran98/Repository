package com.testproject.questions;

public class ArraysHandler {
   
	/**
	 * Majority Element: A majority element in an array A[] of size n is an element that 
	 * appears more than n/2 times (and hence there is at most one such element).
	 * @param arr
	 */
	public static void printMajorityElement(int[] arr){
		if(arr == null){
			return;
		}
		
		int majorityElementNum = arr.length / 2;
		int[] trackStore = new int[256];
		
		for (int element : arr) {
			trackStore[element]+=1;
			if(trackStore[element]>=majorityElementNum){
				System.out.println("the Majority element is="+ trackStore[element]);
				return;
			}
		}
		
		System.out.println("NONE");
	}
}
