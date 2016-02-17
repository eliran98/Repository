package com.algorithms.framework.questions;

import com.algorithms.framework.logic.IStack;
import com.algorithms.framework.questions.Stack.StackImpl;

public class Stack {
   
	//Sort a stack using recursion pop(),push(),isEmpty(),top().
	public static void sortStack(StackImpl stack) throws Exception{
		
		if(!stack.isEmpty()){
			int temp = stack.pop();
			sortStack(stack);
			insertSortedOrder(stack, temp);
		}
		
	}
	
	private static void insertSortedOrder(StackImpl stack,int value) throws Exception{
		 
		 if(stack.isEmpty() || stack.top() < value){
			 stack.push(value);
			 return;
		 }
		 
		 int temp = stack.pop();
		 insertSortedOrder(stack, value);
		 
		 stack.push(temp);
	}
	
	//Design a stack with o(1) Time-Complexity - push,pop,findMiddle,deleteMiddle
	public static class StackImpl implements IStack{
		
		private int count;
		private NodeDoubleLinkedList stackMiddle,stackTail;
		
		public StackImpl(){
			stackMiddle = null;
			stackTail = null;
			count = 0;
		}
		
		/**
		 * Time-Complexity O(1).
		 */
		@Override
		public void push(int data) throws Exception {
			
			if(stackTail == null){
				stackTail = new NodeDoubleLinkedList(data);
				stackMiddle = stackTail;
				count++;
				return;
			}
			
			stackTail.setNext(new NodeDoubleLinkedList(data));
			stackTail.getNext().setPrev(stackTail);
			stackTail = stackTail.getNext();
			count++;
			
			updateMiddleElement(true);
			
		}
		
		/**
		 * Time-Complexity O(1).
		 */
		@Override
		public int pop() throws Exception {
			if(stackTail == null){
				return -1;
			}
			
			NodeDoubleLinkedList prevTail = stackTail.getPrev();
			int data = stackTail.getData();
			stackTail.deleteElement();
			stackTail = prevTail;
			count--;
			
			updateMiddleElement(false);
			
			return data;
		}

		/**
		 * Time-Complexity O(1).
		 */
		@Override
		public int findMiddle() throws Exception {
			if(stackMiddle == null){
				return -1;
			}
			return stackMiddle.getData();
		}

		/**
		 * Time-Complexity O(1).
		 */
		@Override
		public void deleteMiddle() throws Exception {
			NodeDoubleLinkedList prevStackMiddle = stackMiddle.getPrev();
			NodeDoubleLinkedList nextStackMiddle = stackMiddle.getNext();
			
			if(stackMiddle == stackTail){
				stackMiddle = null;
				stackTail = null;
				count--;
				return;
			}
			
			stackMiddle.deleteElement();
			count--;
			
			if(count == 1){
				stackMiddle = nextStackMiddle;
				return;
			}
			
			if(count%2 != 0){
				//odd elements
				stackMiddle = prevStackMiddle.getNext();
			}else{
				stackMiddle = prevStackMiddle;
			}
		}
		
		/**
		 * Time-Complexity O(1).
		 */
		private void updateMiddleElement(boolean isPushOrPop){
			
			if(isEmpty()){
				stackMiddle = null;
				return;
			}
			
			boolean isCountOdd = (count%2 != 0) ? true : false;
			
			if(isPushOrPop && isCountOdd){
				//push and odd elements
				stackMiddle = stackMiddle.getNext();
			}else if(!isPushOrPop && !isCountOdd){
			   //pop and even elements
				stackMiddle = stackMiddle.getPrev();
			}
		}
		
		public boolean isEmpty(){
			return count == 0;
		}
		
		public int getCount(){
			return count;
		}
		
		public void printStack(){
			if(stackTail == null){
				System.out.println("Stack is Empty!");
				return;
			}
			
			NodeDoubleLinkedList printTracker = stackTail;
			
			while(printTracker!=null){
				System.out.print(printTracker.getData()+" ");
				printTracker = printTracker.getPrev();
			}
			System.out.println("");
		}

		@Override
		public int top() throws Exception {
			if(stackTail!=null){
				return stackTail.getData();
			}
			return -1;
		}
		
		
	}

	public static void reverseStackRec(StackImpl stack) throws Exception {
		reverseStack(stack);
	}
		
	//Reverse a stack using recursion
	private static void reverseStack(StackImpl stack) throws Exception{
		
		 if(!stack.isEmpty()){
			 int value = stack.pop();
			 reverseStack(stack);
			
			 reverse(stack, value);
		 }
		 //1,2,3 - 1\2,3  3,2,1
		 //2,3 -   2\3
		 //3  -    3\ --
	}
	
	private static void reverse(StackImpl stack ,int value) throws Exception{
		
		if(stack.isEmpty()){
			stack.push(value);
			return;
		}
		
		int val = stack.pop();
		reverse(stack, value);
		stack.push(val);
		
	}
	
	//Next Greater Element int[] arr = {13, 7, 6, 12}
	/*Element         NGE
	   13      -->    -1
	   7       -->     12
	   6       -->     12
	   12      -->     -1
	 */
	public static void getNGE(int[] arr){
		
		java.util.Stack<Integer> stack = new  java.util.Stack<Integer>();
		stack.push(arr[0]);
		
		
		for (int i = 1; i < arr.length; i++) {
			
			if(arr[i] < stack.peek()){
				stack.push(arr[i]);
			}else{
				
			}
			
		}
		
	}
	
	private static void findInStack(java.util.Stack<Integer> stack,Integer val){
		
		if(stack.isEmpty()){
			return;
		}
		
		int valInStack = stack.pop();
		findInStack(stack, val);
		if(valInStack < val){
			
		}
		stack.push(val);
		
	}
	
	//Check for balanced parentheses in an expression
	//Time Complexity: O(n)
	//Auxiliary Space: O(n) for stack.
	public static boolean isExBalancedParentheses(String exp){
		
		java.util.Stack<Character> stack = null;
		char[] arrChars = null;
		
		if(exp == null){
			return false;
		}
		
		stack = new  java.util.Stack<Character>();
		arrChars = exp.toCharArray();
		
		for (char c : arrChars) {
		
			if(c == '{' || c == '[' || c == '('){
			  stack.push(c);	
			}else if(c == '}' || c == ']' || c == ')'){
			  char openParentheses = stack.pop();
			  if(stack.isEmpty() || !isMatchingPair(openParentheses, c)){
				  return false;
			  }
			}
		}
		return true;
	}
	
	
	private static boolean isMatchingPair(char character1, char character2)
	{
	   if (character1 == '(' && character2 == ')')
	     return true;
	   else if (character1 == '{' && character2 == '}')
	     return true;
	   else if (character1 == '[' && character2 == ']')
	     return true;
	   else
	     return false;
	}
}
