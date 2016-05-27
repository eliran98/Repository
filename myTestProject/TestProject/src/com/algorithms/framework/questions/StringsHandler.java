package com.algorithms.framework.questions;

import java.util.HashSet;
import java.util.Set;

public class StringsHandler {
	
	public static void main(String[] args) {
		//Input  : a -> bc -> d -> dcb -> a -> NULL abcdcba
		int arr1[] = {1, 2, 3};
		int arr2[] = {1, 2, 3};
		if (arr1.equals(arr2))
			System.out.println("Same");
		else
			System.out.println("Not same");
	}
    
	//1.4
	
    /**
     * question: return the first char in the string that appear once in the string.
     * Time complexity: O(n+n)=O(n).
     * @param the string.
     * @return the first char that appear once in the string.
     */
    static public Character findFirstCharThatAppearOnce(String str){
		
    	int[]  results = null;
    	char[] arrChars = null;
    	
    	if(str == null || str.length() == 0){
    		return null;
    	}
    	
		arrChars= str.toCharArray();
		results = new int[arrChars.length];
		
		for (int i = 0; i < arrChars.length; i++) {
			results[i] = results[i] + 1; 
		}
		
		for (int j = 0; j < results.length; j++) {
			if(results[j] == 1){
				return arrChars[j];
			}
		}
		
		return null;
	}
    
    /**
     * question: return the first char in the string that appear once in the string.
     * Time complexity: O(nxn)=O(n^2).
     * @param the string.
     * @return the first char that appear once in the string.
     */
    static public Character findFirstCharThatAppearOnceNaiveSol(String str){
    	int counter = 0;
    	char[]  arrChars= str.toCharArray();
    	
    	for (int i = 0; i < arrChars.length; i++) {
			
    		char c = arrChars[i];
    		
    		for (int j = 0; j < arrChars.length;) {  
				if(c == arrChars[j]){
					counter++;
				}
			}
    		
    		if(counter == 1){
    			return c;
    		}
    		
    		counter = 0;
		}
    	
    	return null;
    }
    
    /**
     * question: return the size of network of words that consider friends words.
     * word consider friend to another if the distance between them not big than one
     */
    private static int counter = 0;
    public static int getNetWorkSize(String word,Set<String> bank){
    	
    	for (String string : bank) {
			if(isFreindStrings(word,string)){
				counter++;
				Set<String> bank_other = new HashSet<String>();
				bank_other.remove(string);
				getNetWorkSize(string,bank_other);
			}
		}
    	return counter;
    }
    
    
    private static boolean isFreindStrings(String s,String t){
    	
    	int def = 0;
    	char[] s_chars = s.toCharArray();
    	char[] t_chars = t.toCharArray();
    	
    	if(Math.abs(s_chars.length - t_chars.length)>1){
    	  	return false;
    	}else if(Math.abs(s_chars.length - t_chars.length) == 1){
    		def++;
    	}
    	
    	for (int i = 0; i < t_chars.length; i++) {
			if(i<= s_chars.length - 1 && t_chars[i] != s_chars[i]){
				def++;
			}
		}
    	
    	return def == 1 || def == 0;
    }
    
    //1.4 - Write a method to decide if two strings are anagrams or not.
    //Time Complexity o(n).
    public static boolean isAnagrams(String strA,String strB){
    	
    	if(strA == null || strB == null || strA.length() != strB.length()){
    		return false;
    	}
    	
    	
    	int[] letters = new int[256];
    	int numUniqueCharsA = 0;
    	int numUniqueCharsB = 0;
    	char[] charsA = strA.toCharArray();
    	char[] charsB = strB.toCharArray();
    	
    	for (char c : charsA) {
    		
			if(letters[c] == 0){
				numUniqueCharsA++;
			}
			
			++letters[c];
		}
    	
    	
    	for (int i=0 ; i<charsB.length ; i++) {
    		
    		char c = charsB[i];
    		
    		if(letters[c] == 0){
				return false;
			}
    		
    		--letters[c];
    		
    		if(letters[c] == 0){
    			numUniqueCharsB++;
    			
    			if(numUniqueCharsB == numUniqueCharsA){
    				return i == charsB.length - 1;
    			}
    		}
    	}	
    	return false;
    }
    
    /**
     * Remove repeating charaters from a given string in O(1) space and O(n) time , where n is the length
	 * of the given string.
	 * TimeComplexity o(n) , TimeSpace o(1).
     */
    public static String removeRepeatingsChars(String str){
    	
    	StringBuilder sb = null;
    	int checker = 0;
    	
    	if(str == null || str.length() == 1){
    		return str;
    	}
    	 sb = new StringBuilder();
    	
    	for (int i=0 ; i<str.length() ; i++) {
			char c = str.charAt(i);
			int value = c - 'a';
			
			if((checker & (1<<value)) == 0 ){
				checker |= (1<<value);
				sb.append(c);
			}
		}
    	return sb.toString();
    }
    
    /**TimeComplexity O(len*(2^N-1))
     * Count number of binary strings without consecutive 1’s
	   Given a positive integer N, count all possible distinct binary strings of length N such that there are no consecutive 1’s.
     * @param N
     * @return
     */
       public static int getNumStrings(int N){
    	   
    	   int counter = 0;
    	   int maxNum = (int)Math.pow(2d, (double)N) - 1;
    	   
    	   for (int num = 0; num <= maxNum; num++) {
			   String bineryNum = getBineryString(num,N);
    		   if(!bineryNum.contains("11")){
    			   System.out.print(bineryNum + " ");
    			   counter++;
    		   }
		   }
    	    
    	   System.out.println();
    	   
    	   return counter;
       }
       
       /**
        * convert positive number to binery number.
        * @param number
        * @param len
        * @return
        */
       public static String getBineryString(int number,int len){
    	   StringBuilder sb = new StringBuilder();
    	   
    	   while(number!=0){
    		   
    		   int res = number%2;
    		   if(res == 0){
    			   number/=2;
    			   sb.append("0");
    		   }else{
    			  number-=res;
    			  number/=2;
    			  sb.append("1");
    		   }
    		   
    		   len--;
    	   }
    	   
    	   
    	   while(len>0){
    		   sb.append("0");
    		   len--;
    	   }
    	   
    	   return sb.reverse().toString();
       }
       
       //Recursively print all sentences that can be formed from list of word lists
       private static void printSentence(String[][] listsOfWords,int rowIndex,int col,String[] output){
    	   
    	   int rows = listsOfWords.length;
    	   if(col >= listsOfWords[rowIndex].length){
    		   return;
    	   }
    	   output[rowIndex] = listsOfWords[rowIndex][col];
    	   if(rowIndex == rows-1){
    		   for (String  str : output) {
				 System.out.print(str + " ");
			   }
    		   System.out.println("");
    		   return;
    	   }
    	   
    		   for (int i = 0; i < listsOfWords[2].length ; i++) {
    			   printSentence(listsOfWords, rowIndex+1,i,output);
    		   }
       }
       
       public static void printAllSentences(String[][] listsOfWords){
    	   
    	   String[] output = new String[listsOfWords.length]; 
    	   int cols = listsOfWords[0].length;
    	   for (int i = 0; i < cols; i++) {
    		   printSentence(listsOfWords, 0,i,output);	
		   }
       }
       
       //TimeComplexity O(n).
       public static boolean isWordsdistanceOne(String firstword, String secondword){
    	   
    	   int secondwordLen = secondword.length();
    	   int firstwordLen  = firstword.length();
    	   
    	   if(firstword == null || secondword == null || firstword.equals(secondword) || Math.abs(secondwordLen - firstwordLen)>=2){
    		   return false;
    	   }
    	   
    	   char[] secondwordsChars = secondword.toCharArray();
		   char[] firstwordsChars = firstword.toCharArray();
    	   
    	   if(secondwordLen == firstwordLen){
    		   
    		   int counter = 0;
    				   
    		   for (int i = 0; i < secondwordLen ; i++) {
				  if(secondwordsChars[i]!=firstwordsChars[i]){
					  counter++;
				  }
			   }
    		   
    		   return counter == 1 ? true : false;
    		   
    	   }else{
    		   int length = firstwordLen > secondwordLen ? secondwordLen : firstwordLen;
    		   
    		   for (int i = 0; i < length ; i++) {
				   if(secondwordsChars[i]!=firstwordsChars[i]){
					   return false;
				   }
			   }
    	   }
    	   
    	   //seconword = firstword + endChar , if(lenSecond == lenFirst +1)
    	   //if(lenSecond == lenFirst) and diff in one char.
    	   return true;
       }
       
       //Print all possible strings that can be made by placing spaces
       public static void printAllPossibleStrsWithSpaces(String str){
    	   
    	   if(str == null){
    		   return;
    	   }
    	   
    	   int numSpaces = str.length() - 1;
    	   
    	   
       }
       
       //Print all palindrome permutations of a string
       //Given a string, we need to print all possible palindromes that can be generated using letters of that string.
       public static void printPermutationsOfStrPalindrome(String str){
    	   if(str == null || str.isEmpty()){
    		   return;
    	   }
    	   printPermutPalindrom(str.toCharArray() , 0 , str.length()-1);
       }
       
       private static void printPermutPalindrom(char[] charsArr,int index,int lastIndex){
    	   if(index == lastIndex){
    		   //if(isPalindrome(charsArr, 0 , charsArr.length - 1)){
    		   if(charsArr!=null)
    			   System.out.println(String.valueOf(charsArr));
    		   //}
    	   }else{// ABC
    		   	 // A_BC     
    		   // AB_C AC_B
    		   for (int i = index; i <=lastIndex; i++) {
    			   charsArr = swap(charsArr,i,index);
    			   printPermutPalindrom(charsArr, index+1, lastIndex);
    			   charsArr = swap(charsArr,i,index);
			   }
    	   }
       }
       
       private static char[] swap(char[] charsArr,int i,int j){
    	   
    	   if(charsArr == null || charsArr.length>=i || charsArr.length>=j){
    		   return null;
    	   }
    	   
    	   char temp = charsArr[i];
    	   charsArr[i] = charsArr[j];
    	   charsArr[j] = temp;
    	   
    	   return charsArr;
       }
       
       private static boolean isPalindrome(char[] charsArr,int start,int end){
    	   
    	   if(start>=end){
    		   return true;
    	   }
    	   
    	   return charsArr[start] == charsArr[end] && isPalindrome(charsArr, start+1, end-1);
       }
       
       //Check if two given strings are isomorphic to each other
       //TimeComplexit o(n) , spaceComplexity o(1). 
       public static boolean isIsomorphic(String strA,String strB){
    	   
    	   if(strA == null || strB == null || strA.isEmpty() || strB.isEmpty() || strA.length()!=strB.length()){
    		   return false;
    	   }
    	
    	   boolean[] markVisited = new boolean[256];
    	   int[] mapCharATChar = new int[256];
    	   
    	   for (int i = 0; i < strA.length(); i++) {
			  
    		   if(mapCharATChar[strA.charAt(i)] == 0){
    			   
    			   //can not map one-to-one
    			   if(markVisited[strB.charAt(i)]){
    				   return false;
    			   }
    			   
    			   markVisited[strB.charAt(i)] = true;
    			   mapCharATChar[strA.charAt(i)] = strB.charAt(i);
    			   
    		   }else{
    			   if(mapCharATChar[strA.charAt(i)] != strB.charAt(i)){
    				   return false;
    			   }
    		   }
		   }
    	   
    	   return true;
       }
}
