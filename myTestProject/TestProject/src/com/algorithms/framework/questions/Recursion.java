package com.algorithms.framework.questions;

import java.util.ArrayList;

public class Recursion {

	/**
	 * Write a method to generate the nth Fibonacci number.
	 */
	public static int generateNthFibonacciNumber(int nth) {
		if(nth <= 0){
		   return 0;  // f(0) = 0	
		 }else if(nth == 1){
			return 1;  // f(1) = 1
		}else if(nth>1){
			return generateNthFibonacciNumber(nth-1) + generateNthFibonacciNumber(nth-2);  //f(n) = f(n—1) + f(n-2)
		}else{
			return -1; // Error condition
		}
		
	}

	private static int calNthFibonacciNumber(int f0, int f1) {
		/* 0,1,0+1=1,1+1=2,1+2=3,2+3=5,3+5=8,5+8=13... */

		return 0;
	}

	// ******************************************

	// Write a method to compute all permutations of a string.
	public static void printPermutations(String s) {
		// 123 - 123,231,321,

	}

	public static ArrayList<String> getPerms(String s) {
		 ArrayList<String> permutations = new ArrayList<String>();
		 if (s == null) { // error case
			 return null;
		 } else if (s.length() == 0) { // base case
			 permutations.add("");
		 	 return permutations;
		 }
		 
		 char first = s.charAt(0); // get the first character
		 String remainder = s.substring(1); // remove the first character
		 ArrayList<String> words = getPerms(remainder);	
		  
		 for (String word : words) {
			for (int j = 0; j <= word.length(); j++) {
				permutations.add(insertCharAt(word, first, j));
			  }
		  }
		 	return permutations;
	  }
	
	public static int findMin(int[] elements, int index) {

		  if (index == elements.length - 1) {
		    return elements[index];
		  }

		  int val = findMin(elements, index + 1);

		  if (elements[index] < val)
		    return elements[index];
		  else
		    return val;
	}

	public static String insertCharAt(String word, char c, int i) {
		String start = word.substring(0, i);
		String end = word.substring(i);
		return start + c + end;
	}
	// This solution takes O(n!) time, since there are n! permutations.

}
