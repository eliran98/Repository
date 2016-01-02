package com.testproject.questions;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringsHandler {
    
	//1.4
	
    /**
     * question: return the first char in the string that appear once in the string.
     * Time complexity: O(n+n)=O(n).
     * @param the string.
     * @return the first char that appear once in the string.
     */
    static public Character findFirstCharThatAppearOnce(String str){
		
    	int[] results = null;
    	char[]  arrChars = null;
    	
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
    
}
