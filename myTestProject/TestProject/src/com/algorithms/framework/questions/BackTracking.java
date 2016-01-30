package com.algorithms.framework.questions;

public class BackTracking {
	
	public static void printPermutations(String str){
		if(str == null){
			return;
		}
		permutations(str.toCharArray(),0,str.length()-1);
	}
     
	private static void permutations(char[] arr,int startIndex,int lastIndex){
		
		if(startIndex == lastIndex){
			System.out.println(String.valueOf(arr));
		}else{
			
			for (int i = startIndex; i <= lastIndex; i++) {
				
				swap(arr,startIndex,i);
				permutations(arr,startIndex+1,lastIndex);
				swap(arr,startIndex,i); /*return back - backtrack*/
			}
			
		}
	}
	
	
	private static void swap(char[] arr,int indexA,int indexB){
		char temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}
}
