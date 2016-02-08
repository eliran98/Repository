package com.algorithms.framework.data.structures;

import java.util.Stack;

import com.algorithms.framework.logic.IQueue;

/*  
    Implement a MyQueue class which implements a queue using two stacks.
	Since the major difference between a queue and a stack is the order (first-in-first-out vs. last-in-first-out), we know that we need to modify peek() and pop() to go in reverse order. We can use our second stack to reverse the order of the elements (by popping s1 and pushing the elements on to s2). In such an implementation, on each peek() and pop() operation, we would pop everything from s1 onto s2, perform the peek / pop operation, and then push everything back.
	This will work, but if two pop / peeks are performed back-to-back, we’re needlessly moving elements. We can implement a “lazy” approach where we let the elements sit in s2.
	s1 will thus be ordered with the newest elements on the top, while s2 will have the oldest elements on the top. We push the new elements onto s1, and peek and pop from s2. When s2 is empty, we’ll transfer all the elements from s1 onto s2, in reverse order.
 */
public class QueueBaseTwoStacks implements IQueue{
	
	private Stack<Object> stackA,stackB;
	
	public QueueBaseTwoStacks(){
		stackA = new Stack<Object>();
		stackB = new Stack<Object>();
	}

	/**
	 * Time Complexity O(1). 
	 */
	@Override
	public void enqueue(Object object) throws Exception {
		stackA.push(object);
	}
	
	/**
	 * Time Complexity O(n). 
	 */
	@Override
	public Object dequeue() throws Exception {
		
		if(!stackB.isEmpty()){
			return stackB.pop();
		}
		
		while(!stackA.isEmpty()){
			stackB.add(stackA.pop());
		}
		
		return stackB.pop();
	}

	@Override
	public boolean isEmpty() throws Exception {
		return stackA.isEmpty() && stackB.isEmpty();
	}

	@Override
	public Object peek() throws Exception {
		if(!stackB.isEmpty()){
			return stackB.peek();
		}
		
		while(!stackA.isEmpty()){
			stackB.add(stackA.pop());
		}
		
		return stackB.peek();
	}

	@Override
	public int size() throws Exception {
		return stackA.size() + stackB.size();
	}
}
