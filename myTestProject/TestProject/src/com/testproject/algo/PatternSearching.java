package com.testproject.algo;

public class PatternSearching {
   
	 //Anagram Substring Search (Or Search for all permutations)
	 //Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all occurrences of pat[] and its permutations (or anagrams) in txt[]. You may assume that n > m. 
	 //Expected time complexity is O(n)
	public static void searchPattern(char[] txt,char[] pattern){
		
		int M = pattern.length;
		int N = txt.length;
		int[] countPattern 	 = new int[256];
		int[] countTxtWindow = new int[256];
			
		for (int i = 0; i < M; i++) {
			countPattern[pattern[i]]++;
			countTxtWindow[txt[i]]++;
		}
		
		for (int i = M; i < N ; i++) {
			
			if(compare(countPattern, countTxtWindow)){
				System.out.println("Found at Index " + (i - M));
			}
			
			/*for next iteration*/
			countTxtWindow[txt[i]]++;
			countTxtWindow[txt[i - M]]--;
		}
		
		if(compare(countPattern, countTxtWindow)){
			System.out.println("Found at Index " + (N - M));
		}
	}
	
	private static boolean compare(int[] arrA,int[] arrB){
		
		for (int i = 0; i < arrB.length; i++) {
			if(arrA[i]!=arrB[i]){
				return false;
			}
		}
		return true;
	}
	
	
}
