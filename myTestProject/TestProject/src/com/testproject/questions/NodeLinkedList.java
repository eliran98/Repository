package com.testproject.questions;

public class NodeLinkedList {

	private NodeLinkedList next;
	private int data;

	public NodeLinkedList(int data) {
		this.data = data;
		this.next = null;
	}

	/**
	 * call this function from certain node in linkedList this method insert the
	 * new node to the tail of the list without matter of position calling node.
	 * 
	 * @param data
	 */
	public void appendToTail(int data) {
		NodeLinkedList nodeLinkedList = new NodeLinkedList(data);

		NodeLinkedList end = this;

		while (end.next != null) {
			end = end.next;
		}

		end.next = nodeLinkedList;
	}

	/**
	 * delete node with the value data , return the head after the change.
	 * 
	 * @param head.
	 * @param data.
	 * @return the head after the delete.
	 */
	public static NodeLinkedList deleteNodeLinkedList(NodeLinkedList head, int data) {
		NodeLinkedList currentNode = null;

		if (head == null) {
			return null;
		}

		currentNode = head;

		if (head.data == data) {
			return head.next;
		}

		while (currentNode.next != null) {

			if (currentNode.next.data == data) {
				currentNode.next = currentNode.next.next;
				return head;
			}

			currentNode = currentNode.next;
		}

		return head;
	}

	/**
	 * 2.3 delete middle Node.
	 * 
	 * @param middleNode
	 * @return return true-success,false-failure.
	 */
	public static boolean removeMiddleNodeLinkedList(NodeLinkedList middleNode) {
		/*
		 * The solution to this is to simply copy the data from the next node
		 * into this node and then delete the next node.
		 */
		NodeLinkedList nextNode = null;

		if (middleNode == null || middleNode.next == null) {
			return false;
		}

		nextNode = middleNode.next;
		// copy nextNode data to middleNode
		middleNode.data = nextNode.data;
		// remove nextNode
		middleNode.next = middleNode.next.next;
		return true;
	}

	/**
	 * Given a linked list, write a function to reverse every k nodes (where k
	 * is an input to the function).
	 */
	public static NodeLinkedList reverseEveryKNodes(NodeLinkedList head, int k) {
		//1,2,3,4,5,6,7,8,9
		NodeLinkedList current = head;
		NodeLinkedList next = null;
		NodeLinkedList prev = null;
		int count = 0;

		/* reverse first k nodes of the linked list */
		while (current != null && count < k) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}
           //3->2->1 prev 3 , head 1 , next 4
		   //6->5->4 prev 6 , head 4 , next 7
		   //9->8->7 prev 9 , head 7 , next null.
		/*
		 * next is now a pointer to (k+1)th node Recursively call for the list
		 * starting from current. And make rest of the list as next of first
		 * node
		 */
		if (next != null)
			head.next = reverseEveryKNodes(next, k);

		/* prev is new head of the input list */
		return prev;
	}

	/**
	 * write algorithm that reverse linkedList
	 * 
	 * @param head
	 */
	public static NodeLinkedList reverseLinkedList(NodeLinkedList head) {

		if (head == null || head.next == null) {
			return head;
		}
		NodeLinkedList three = null;
		NodeLinkedList first = head;
		NodeLinkedList second = head.next;
		if (head.next.next != null) {
			three = head.next.next;
		}

		first.next = null;

		while (second != null) {

			second.next = first;
			first = second;
			second = three;
			if (three != null) {
				three = three.next;
			}

		}
		return first;
	}

	/**
	 * printLinkedList
	 * 
	 * @param head
	 */
	public static void printLinkedList(NodeLinkedList head) {

		while (head != null) {
			System.out.print(head.data + " ");
			head = head.next;
		}
	}
	
	/**
	 * Write a function to get the intersection point of two Linked Lists.
	 *  1) Get count of the nodes in first list, let count be c1.
	 *	2) Get count of the nodes in second list, let count be c2.
	 *	3) Get the difference of counts d = abs(c1 – c2)
	 *	4) Now traverse the bigger list from the first node till d nodes so that from here onwards both the lists have equal no of nodes.
	 *	5) Then we can traverse both the lists in parallel till we come across a common node. (Note that getting a common node is done by comparing the address of the nodes)
	 * TimeComplexity O(n*m).
	 */
	public NodeLinkedList getIntersectionPoint(NodeLinkedList headOne,NodeLinkedList headTwo){
		
		NodeLinkedList intersectionPoint = null;
		int c1 = getLinkedListSize(headOne);
		int c2 = getLinkedListSize(headTwo);
		
		if(c1>c2){
			intersectionPoint = findIntersectionPoint(headOne, headTwo, c1 - c2);
		}else{
			intersectionPoint = findIntersectionPoint(headTwo,headOne, c2 - c1);
		}
		
		return intersectionPoint;
	}
	
	private static NodeLinkedList findIntersectionPoint(NodeLinkedList headOne,NodeLinkedList headTwo,int d){
		
		while(d>0){
			headOne = headOne.next;
			d--;
		}
		
		while(headOne!=null && headTwo !=null){
			
			if(headOne == headTwo){
				return headOne;
			}
			
			headOne = headOne.next;
			headTwo = headTwo.next;
		}
		
		return null;
	}
	
	
	private static int getLinkedListSize(NodeLinkedList head){
		int counter = 0;
		
		if(head == null){
			return 0;
		}
		
		while(head!=null){
			counter++;
			head = head.next;
		}
		return counter;
	}
}
