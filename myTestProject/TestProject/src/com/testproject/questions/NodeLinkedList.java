package com.testproject.questions;

public class NodeLinkedList {

	private NodeLinkedList next;
	private int data;
	private char charData;

	public NodeLinkedList(int data) {
		this.data = data;
		this.next = null;
	}
	
	
	
	public void setNext(NodeLinkedList next) {
		this.next = next;
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
	public NodeLinkedList appendToTailWithResult(int data) {
		NodeLinkedList nodeLinkedList = new NodeLinkedList(data);
		
		NodeLinkedList end = this;
		
		while (end.next != null) {
			end = end.next;
		}
		
		end.next = nodeLinkedList;
		return end.next;
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
	
	//Add two numbers represented by linked 
	public static NodeLinkedList addSumLinkedList(NodeLinkedList linkedListA,NodeLinkedList linkedListB){
		
		//linkedListA = reverseLinkedList(linkedListA);
		//linkedListB = reverseLinkedList(linkedListB);
		
		///return reverseLinkedList(calSumLinkedList(linkedListA,linkedListB,null,0));
		
		return addList(linkedListA,linkedListB);
	}
	
	/*assumption linkedListA and linkedListB in the same length*/
	private static NodeLinkedList calSumLinkedList(NodeLinkedList linkedListA,NodeLinkedList linkedListB,NodeLinkedList headSumLinkedList,int resude){
		
		if(linkedListA == null || linkedListB == null){
			return headSumLinkedList;
		}
		
		int sum = linkedListA.data + linkedListB.data;
		boolean isResude = sum >= 10  ? true : false;
		boolean isLastNode = linkedListA.next == null ? true : false;
		
		if(headSumLinkedList == null){
			if(isResude){
				if(isLastNode){
					headSumLinkedList = new NodeLinkedList(sum - 10 + resude);
					headSumLinkedList.appendToTail(1);
					resude = 0;
				}else{
					headSumLinkedList = new NodeLinkedList(sum - 10 + resude);
					resude = 1;
				}
			}else{
				headSumLinkedList = new NodeLinkedList(linkedListA.data + linkedListB.data + resude);
				resude = 0;
			}
			
		}else{
			if(isResude){
				if(isLastNode){
					headSumLinkedList.appendToTail(sum - 10 + resude);
					headSumLinkedList.appendToTail(1);
					resude = 0;
				}else{
					headSumLinkedList.appendToTail(sum - 10 + resude);
					resude = 1;
				}
				
			}else{
			   headSumLinkedList.appendToTail(linkedListA.data + linkedListB.data + resude);
			   resude = 0;
			}
		}
		
		calSumLinkedList(linkedListA.next, linkedListB.next, headSumLinkedList,resude);
		
		return headSumLinkedList;
	}
	
	public static NodeLinkedList temp(NodeLinkedList linkedListA,NodeLinkedList linkedListB,int len){
		
		if(linkedListA == null || linkedListB == null){
			return null;
		}
		
		NodeLinkedList headSumLinkedList = temp(linkedListA.next,linkedListB.next,len++);
		
		if(linkedListA.next == null || linkedListB.next == null){
			headSumLinkedList = new NodeLinkedList(linkedListA.data + linkedListB.data);
		}else{
			if(linkedListA.next.data + linkedListB.next.data >= 10){
				/*update prev Node*/
				if(len == 1){
					NodeLinkedList prev = null;
					if(linkedListA.data + linkedListB.data + 1 > 10){
						headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1 - 10);
						headSumLinkedList.appendToTail(1);
					}else{
						 headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1);
					}
				}else{
					 headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1);
				}
			}else{
				headSumLinkedList.appendToTail(linkedListA.data + linkedListB.data);
			}
		}
		return headSumLinkedList;
	}
	
	private void appendToTailWithReturnPrev(int data) {
		NodeLinkedList nodeLinkedList = new NodeLinkedList(data);

		NodeLinkedList end = this;

		while (end.next != null) {
			end = end.next;
		}
        
		if(end.data >= 10)
				end.data-=10;
		end.next = nodeLinkedList;
	}
	
	
	public static NodeLinkedList addList(NodeLinkedList linkedListA,NodeLinkedList linkedListB){
		
		NodeLinkedList result = null;
		
		int sizeA = getLinkedListSize(linkedListA);
		int sizeB = getLinkedListSize(linkedListB);
		Carry carry = new Carry(0);
		
		if(sizeA == sizeB){
			result = addSameSize(linkedListA,linkedListB,carry);
			if(carry.value>0){
				result = addToBeginning(result,carry.value);
			}
		}else{
			
			int diff = Math.abs(sizeB - sizeA);
			if(sizeB>sizeA){
				NodeLinkedList temp = linkedListA;
				linkedListA = linkedListB;
				linkedListB = temp;
			}
			NodeLinkedList cur = linkedListA;
			while(diff>0){
				cur = cur.next;
				diff--;
			}
			result = addSameSize(cur,linkedListB,carry);
			// get addition of remaining first list and carry
			result = addCarryToRemaining(linkedListA, cur, carry, result);
	        if(carry.value>0){
				result = addToBeginning(result,carry.value);
			}
		}
		
		return result;
		
	}
	
	private static NodeLinkedList addSameSize(NodeLinkedList linkedListA,NodeLinkedList linkedListB,Carry carry){
		NodeLinkedList result = null;
		if(linkedListA == null){
			return result;
		}
		
	    result = new NodeLinkedList(0);
		result.next = addSameSize(linkedListA.next,linkedListB.next,carry);
		
        int sum = linkedListA.data + linkedListB.data + carry.value;	
        carry.value = sum/10;
        sum%=10;
        
        result.data=sum;
		
		return result;
	}
	
	
	private static class Carry{
		
		public int value;
		
		public Carry(int val){
			value = val;
		}
	}
	

	/* A utility function to insert a node at the beginning of linked list */
	private static NodeLinkedList addToBeginning(NodeLinkedList head,int data){
		NodeLinkedList node = new NodeLinkedList(data);
		node.next = head;
		return node;
	}
	
	private static NodeLinkedList addCarryToRemaining(NodeLinkedList linkedListA,NodeLinkedList cur,Carry carry,NodeLinkedList result){
		
		int sum = 0;
		if(linkedListA!=cur){
			addCarryToRemaining(linkedListA.next,cur,carry,result);
			
			sum = linkedListA.data + carry.value;
			carry.value = sum/10;
			sum%=10;
			result = addToBeginning(result, sum);
		}
		
		return result;
	}

	public int getData() {
		return data;
	}
	
	public NodeLinkedList getNext() {
		return next;
	}
	
	public static void swap(NodeLinkedList src , NodeLinkedList dest){
		int temp = src.data;
		src.data = dest.data;
		dest.data = temp;
	}
	
	public static NodeLinkedList getPrev(NodeLinkedList pivot,NodeLinkedList start){
		while(start!=null){
			
			if(start.getNext() == pivot){
				return start;
			}
			
			start = start.getNext();
		}
		
		return null;
	}
	
	//Compare two strings represented as linked lists
	public static int compareLinkedList(NodeLinkedList headA, NodeLinkedList headB){
		
		while(headA!=null && headB!=null && headA.charData == headB.charData){
			headB = headB.next;
			headA = headA.next;
		}
		
		if(headA!=null && headB!=null){
			return headA.charData > headB.charData ? 1 : -1;
		}
		
		if(headA!=null && headB == null){
			return 1;
		}else if(headB!=null && headA == null){
			return -1;
		}
		
		return 0;
	}
	//Delete N nodes after M nodes of a linked list
	public static NodeLinkedList deletePartOfList(NodeLinkedList head,int n,int m){
		
		NodeLinkedList mTail = head,nTail = head;
		
		while(mTail!=null){
			int skip = 0;
			while(skip!=m && mTail!=null){
				mTail = mTail.next;
				skip++;
			}
			
			if(mTail == null){
				return head;
			}
			
			nTail = mTail.next;
			mTail.next = null;
			skip = 0;
			
			while(skip!=n && nTail!=null){
				nTail = nTail.next;
				skip++;
			}
			
			if(nTail == null || nTail.next == null){
				return head;
			}
			
			mTail.next = nTail.next;
			nTail.next = null;
			
			mTail = mTail.next;
			nTail = mTail;
		}
		
		
		return head;
	}
	
	public static NodeLinkedList func(NodeLinkedList head,int n,int m){
		
		NodeLinkedList mhead = head , nhead = null,nTail = null;
		
		while(mhead!=null){
			int countM = 1;
			while(countM!=m && mhead!=null){
				mhead = mhead.next;
				countM++;
			}
			
			if(mhead == null || mhead.next == null){
				return head;
			}
			
			nhead = mhead.next;
			nTail = nhead;
			mhead.next = null;
			
			if(nhead.next == null){
				return head;
			}
			
			int countN = 1;
			while(countN!=n && nhead!=null){
				nTail = nTail.next;
				nhead.next = null;
				nhead = nTail;
				countN++;
			}
			
			mhead.next = nTail.next;
			mhead = mhead.next;
		}
		
		return head;
	}
	
}
