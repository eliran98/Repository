package com.testproject.questions;

import com.testproject.logic.IStack;

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
}
