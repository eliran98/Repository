package com.algorithms.framework.questions;

import java.util.Arrays;

import com.algorithms.framework.logic.IStack;
import com.algorithms.framework.questions.Stack.StackImpl;

public class Stack {
	
	
	public static void main(String[] args) {
		//{{1,3},{2,4},{5,7},{6,8}}
		TimeInterval[] intervals = new TimeInterval[4];
		intervals[0] = new TimeInterval(1,3);
		intervals[1] = new TimeInterval(2,4);
		intervals[2] = new TimeInterval(5,7);
		intervals[3] = new TimeInterval(6,8);
		printIntervals(intervals);
		printIntervals(mergeOverlappingTimeIntervals(intervals));
	}
	
	private static void printIntervals(TimeInterval[] intervals){
		
		if(intervals == null || intervals.length == 0){
			return;
		}
		
		System.out.println();
		for (TimeInterval timeInterval : intervals) {
			System.out.print(timeInterval.startTime + " " + timeInterval.endTime);
			System.out.print(" | ");
		}
		System.out.println();
	}
   
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
	
	//Check if a given array can represent Preorder Traversal of Binary Search Tree
	//Input:  pre[] =,{40, 30, 35, 80, 100},{2, 4, 3},{2, 4, 1}
	//http://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
	public static boolean isRepresentPreOrderTraversalBST(int[] arr,int left,int right){
		
		
		
		return false;
	}
	
	// {40, 30, 35, 80, 100}
    public boolean canRepresentBST(int pre[], int n) {
        // Create an empty stack
    	java.util.Stack<Integer> s = new java.util.Stack<Integer>();
 
        // Initialize current root as minimum possible
        // value
        int root = Integer.MIN_VALUE;
 
        // Traverse given array
        for (int i = 0; i < n; i++) {
            // If we find a node who is on right side
            // and smaller than root, return false
            if (pre[i] < root) {
                return false;
            }
            
            // If pre[i] is in right subtree of stack top,
            // Keep removing items smaller than pre[i]
            // and make the last removed item as new
            // root.
            while (!s.empty() && s.peek() < pre[i]) {
                root = s.peek();
                s.pop();
            }
 
            // At this point either stack is empty or
            // pre[i] is smaller than root, push pre[i]
            s.push(pre[i]);
        }
        return true;
    }
    
    //Next Greater Element
    public static void printNGE(int[] arr){
    	//10,9,8,7,6,5,4,3,2,1
    	//1,2,3,4,5,6,7,8,9,10
    	//5,2,1
    }
    
    //Minimum number of bracket reversals needed to make an expression balanced
    public static int minNumberReversalBracket(String expression){
    	
    	java.util.Stack<Character> stack = null; 
    	char[] bracketSet = null;
    	
    	if(expression == null || expression.isEmpty()){
    		System.out.println("Can't be made balanced using reversals");
    		return -1;
    	}
    	
    	bracketSet = expression.toCharArray();
    	//odd number of brackets
    	if(bracketSet.length%2 != 0){
    		System.out.println("Can't be made balanced using reversals");
    		return -1;
    	}
    	//"{{{{","}{","{{{{}}"
    	stack = new java.util.Stack<Character>();
    	//remove balanced expression , and put in stack unbalanced expression only.
    	for (char oneBracket : bracketSet) {
    		if(!stack.isEmpty() && stack.peek() == '{' && oneBracket == '}'){
    			stack.pop();
    		}else{
    			stack.push(oneBracket);
    		}
		}
    	
    	int reducedExp_len = stack.size();
    	int count_OpenBrackets = 0;
    	while(!stack.isEmpty() && stack.peek() == '{'){
    		stack.pop();
    		count_OpenBrackets++;
    	}
    	
    	return (reducedExp_len/2 + count_OpenBrackets%2);
    }
    
    //Merge Overlapping Intervals
    //{{1,3},{2,4},{5,7},{6,8}}
    public static TimeInterval[] mergeOverlappingTimeIntervals(TimeInterval[] intervalsArr){
    	
    	TimeInterval top = null;
    	TimeInterval[] results = null;
    	
    	if(intervalsArr == null || intervalsArr.length == 1){
    		return intervalsArr;
    	}
    	
    	/*nlogn*/
    	Arrays.sort(intervalsArr);
    	java.util.Stack<TimeInterval> stack = new java.util.Stack<TimeInterval>();
    	
    	int index = 0;
    	stack.add(intervalsArr[index++]);
    	
    	/*o(n)*/
    	while(index < intervalsArr.length){
    		top = stack.peek();
    		if(top.endTime < intervalsArr[index].startTime){
    			stack.add(intervalsArr[index]);
    		}else if(top.endTime < intervalsArr[index].endTime){
    			top.endTime = intervalsArr[index].endTime;
    			stack.pop();	
    			stack.add(top);
    		}
    		
    		index++;
    	}
    	
    	results = new TimeInterval[stack.size()];
    	
    	/*o(n)*/
    	for (int i = 0; i < results.length; i++) {
			results[i] = stack.pop();
		}
    	
    	return results;
    }
    
    
    public static class TimeInterval implements Comparable<TimeInterval>{
    	
    	private Integer startTime;
    	private Integer endTime;
    	
    	public TimeInterval(int startTime,int endTime){
    		this.startTime = startTime;
    		this.endTime = endTime;
    	}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getEndTime() {
			return endTime;
		}

		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}

		@Override
		public int compareTo(TimeInterval o) {
			return this.startTime.compareTo(o.startTime);
		}
    }
    
}
