package com.algorithms.framework.questions;

public class NodeLinkedList {

	private NodeLinkedList next;
	private int data;
	private Object objData;
	private char charData;

	public static void main(String[] args) {

		// 10->40->53->30->67->12->89->NULL
		NodeLinkedList listA = new NodeLinkedList(10);
		listA.appendToTailWithResult(40).appendToTailWithResult(53).appendToTailWithResult(30)
				.appendToTailWithResult(67).appendToTailWithResult(12).appendToTailWithResult(89);
		printLinkedList(listA);
		System.out.println();
		printLinkedList(sort(listA));
		System.out.println();
		// NodeLinkedList listB = new NodeLinkedList(2);
		// listB.appendToTailWithResult(4).appendToTailWithResult(6).appendToTailWithResult(8).appendToTailWithResult(10);
		// printLinkedList(listA);
		// System.out.println();
		// printLinkedList(listB);
		// System.out.println();
		// System.out.println("NodeLinkedList.main()");
		// printLinkedList(mergeLinkedLists(listA,listB));
	}

	public NodeLinkedList(int data) {
		this.data = data;
		this.next = null;
	}

	public NodeLinkedList(Object objData) {
		this.objData = objData;
	}

	public void setNext(NodeLinkedList next) {
		this.next = next;
	}

	public Object getObjData() {
		return objData;
	}

	public void setObjData(Object objData) {
		this.objData = objData;
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
	
	public NodeLinkedList appendToTailWithResult(Object data) {
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
		// 1,2,3,4,5,6,7,8,9
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
		// 3->2->1 prev 3 , head 1 , next 4
		// 6->5->4 prev 6 , head 4 , next 7
		// 9->8->7 prev 9 , head 7 , next null.
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
	
	public static void printLinkedListObject(NodeLinkedList head) {

		while (head != null) {
			System.out.print(head.objData + " ");
			head = head.next;
		}
	}
	
	/**
	 * Write a function to get the intersection point of two Linked Lists. 1)
	 * Get count of the nodes in first list, let count be c1. 2) Get count of
	 * the nodes in second list, let count be c2. 3) Get the difference of
	 * counts d = abs(c1 – c2) 4) Now traverse the bigger list from the first
	 * node till d nodes so that from here onwards both the lists have equal no
	 * of nodes. 5) Then we can traverse both the lists in parallel till we come
	 * across a common node. (Note that getting a common node is done by
	 * comparing the address of the nodes) TimeComplexity O(n*m).
	 */
	public NodeLinkedList getIntersectionPoint(NodeLinkedList headOne, NodeLinkedList headTwo) {

		NodeLinkedList intersectionPoint = null;
		int c1 = getLinkedListSize(headOne);
		int c2 = getLinkedListSize(headTwo);

		if (c1 > c2) {
			intersectionPoint = findIntersectionPoint(headOne, headTwo, c1 - c2);
		} else {
			intersectionPoint = findIntersectionPoint(headTwo, headOne, c2 - c1);
		}

		return intersectionPoint;
	}

	private static NodeLinkedList findIntersectionPoint(NodeLinkedList headOne, NodeLinkedList headTwo, int d) {

		while (d > 0) {
			headOne = headOne.next;
			d--;
		}

		while (headOne != null && headTwo != null) {

			if (headOne == headTwo) {
				return headOne;
			}

			headOne = headOne.next;
			headTwo = headTwo.next;
		}

		return null;
	}

	private static int getLinkedListSize(NodeLinkedList head) {
		int counter = 0;

		if (head == null) {
			return 0;
		}

		while (head != null) {
			counter++;
			head = head.next;
		}
		return counter;
	}

	// Add two numbers represented by linked
	public static NodeLinkedList addSumLinkedList(NodeLinkedList linkedListA, NodeLinkedList linkedListB) {

		// linkedListA = reverseLinkedList(linkedListA);
		// linkedListB = reverseLinkedList(linkedListB);

		/// return
		/// reverseLinkedList(calSumLinkedList(linkedListA,linkedListB,null,0));

		return addList(linkedListA, linkedListB);
	}

	/* assumption linkedListA and linkedListB in the same length */
	private static NodeLinkedList calSumLinkedList(NodeLinkedList linkedListA, NodeLinkedList linkedListB,
			NodeLinkedList headSumLinkedList, int resude) {

		if (linkedListA == null || linkedListB == null) {
			return headSumLinkedList;
		}

		int sum = linkedListA.data + linkedListB.data;
		boolean isResude = sum >= 10 ? true : false;
		boolean isLastNode = linkedListA.next == null ? true : false;

		if (headSumLinkedList == null) {
			if (isResude) {
				if (isLastNode) {
					headSumLinkedList = new NodeLinkedList(sum - 10 + resude);
					headSumLinkedList.appendToTail(1);
					resude = 0;
				} else {
					headSumLinkedList = new NodeLinkedList(sum - 10 + resude);
					resude = 1;
				}
			} else {
				headSumLinkedList = new NodeLinkedList(linkedListA.data + linkedListB.data + resude);
				resude = 0;
			}

		} else {
			if (isResude) {
				if (isLastNode) {
					headSumLinkedList.appendToTail(sum - 10 + resude);
					headSumLinkedList.appendToTail(1);
					resude = 0;
				} else {
					headSumLinkedList.appendToTail(sum - 10 + resude);
					resude = 1;
				}

			} else {
				headSumLinkedList.appendToTail(linkedListA.data + linkedListB.data + resude);
				resude = 0;
			}
		}

		calSumLinkedList(linkedListA.next, linkedListB.next, headSumLinkedList, resude);

		return headSumLinkedList;
	}

	public static NodeLinkedList temp(NodeLinkedList linkedListA, NodeLinkedList linkedListB, int len) {

		if (linkedListA == null || linkedListB == null) {
			return null;
		}

		NodeLinkedList headSumLinkedList = temp(linkedListA.next, linkedListB.next, len++);

		if (linkedListA.next == null || linkedListB.next == null) {
			headSumLinkedList = new NodeLinkedList(linkedListA.data + linkedListB.data);
		} else {
			if (linkedListA.next.data + linkedListB.next.data >= 10) {
				/* update prev Node */
				if (len == 1) {
					NodeLinkedList prev = null;
					if (linkedListA.data + linkedListB.data + 1 > 10) {
						headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1 - 10);
						headSumLinkedList.appendToTail(1);
					} else {
						headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1);
					}
				} else {
					headSumLinkedList.appendToTailWithReturnPrev(linkedListA.data + linkedListB.data + 1);
				}
			} else {
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

		if (end.data >= 10)
			end.data -= 10;
		end.next = nodeLinkedList;
	}

	public static NodeLinkedList addList(NodeLinkedList linkedListA, NodeLinkedList linkedListB) {

		NodeLinkedList result = null;

		int sizeA = getLinkedListSize(linkedListA);
		int sizeB = getLinkedListSize(linkedListB);
		Carry carry = new Carry(0);

		if (sizeA == sizeB) {
			result = addSameSize(linkedListA, linkedListB, carry);
			if (carry.value > 0) {
				result = addToBeginning(result, carry.value);
			}
		} else {

			int diff = Math.abs(sizeB - sizeA);
			if (sizeB > sizeA) {
				NodeLinkedList temp = linkedListA;
				linkedListA = linkedListB;
				linkedListB = temp;
			}
			NodeLinkedList cur = linkedListA;
			while (diff > 0) {
				cur = cur.next;
				diff--;
			}
			result = addSameSize(cur, linkedListB, carry);
			// get addition of remaining first list and carry
			result = addCarryToRemaining(linkedListA, cur, carry, result);
			if (carry.value > 0) {
				result = addToBeginning(result, carry.value);
			}
		}

		return result;

	}

	private static NodeLinkedList addSameSize(NodeLinkedList linkedListA, NodeLinkedList linkedListB, Carry carry) {
		NodeLinkedList result = null;
		if (linkedListA == null) {
			return result;
		}

		result = new NodeLinkedList(0);
		result.next = addSameSize(linkedListA.next, linkedListB.next, carry);

		int sum = linkedListA.data + linkedListB.data + carry.value;
		carry.value = sum / 10;
		sum %= 10;

		result.data = sum;

		return result;
	}

	private static class Carry {

		public int value;

		public Carry(int val) {
			value = val;
		}
	}

	/* A utility function to insert a node at the beginning of linked list */
	private static NodeLinkedList addToBeginning(NodeLinkedList head, int data) {
		NodeLinkedList node = new NodeLinkedList(data);
		node.next = head;
		return node;
	}

	private static NodeLinkedList addCarryToRemaining(NodeLinkedList linkedListA, NodeLinkedList cur, Carry carry,
			NodeLinkedList result) {

		int sum = 0;
		if (linkedListA != cur) {
			addCarryToRemaining(linkedListA.next, cur, carry, result);

			sum = linkedListA.data + carry.value;
			carry.value = sum / 10;
			sum %= 10;
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

	public static void swap(NodeLinkedList src, NodeLinkedList dest) {
		int temp = src.data;
		src.data = dest.data;
		dest.data = temp;
	}

	public static NodeLinkedList getPrev(NodeLinkedList pivot, NodeLinkedList start) {
		while (start != null) {

			if (start.getNext() == pivot) {
				return start;
			}

			start = start.getNext();
		}

		return null;
	}

	// Compare two strings represented as linked lists
	public static int compareLinkedList(NodeLinkedList headA, NodeLinkedList headB) {

		while (headA != null && headB != null && headA.charData == headB.charData) {
			headB = headB.next;
			headA = headA.next;
		}

		if (headA != null && headB != null) {
			return headA.charData > headB.charData ? 1 : -1;
		}

		if (headA != null && headB == null) {
			return 1;
		} else if (headB != null && headA == null) {
			return -1;
		}

		return 0;
	}

	// Delete N nodes after M nodes of a linked list
	public static NodeLinkedList deletePartOfList(NodeLinkedList head, int n, int m) {

		NodeLinkedList mTail = head, nTail = head;

		while (mTail != null) {
			int skip = 0;
			while (skip != m && mTail != null) {
				mTail = mTail.next;
				skip++;
			}

			if (mTail == null) {
				return head;
			}

			nTail = mTail.next;
			mTail.next = null;
			skip = 0;

			while (skip != n && nTail != null) {
				nTail = nTail.next;
				skip++;
			}

			if (nTail == null || nTail.next == null) {
				return head;
			}

			mTail.next = nTail.next;
			nTail.next = null;

			mTail = mTail.next;
			nTail = mTail;
		}

		return head;
	}

	public static NodeLinkedList func(NodeLinkedList head, int n, int m) {

		NodeLinkedList mhead = head, nhead = null, nTail = null;

		while (mhead != null) {
			int countM = 1;
			while (countM != m && mhead != null) {
				mhead = mhead.next;
				countM++;
			}

			if (mhead == null || mhead.next == null) {
				return head;
			}

			nhead = mhead.next;
			nTail = nhead;
			mhead.next = null;

			if (nhead.next == null) {
				return head;
			}

			int countN = 1;
			while (countN != n && nhead != null) {
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

	// Write a program function to detect loop in a linked list
	// Time-Complexity O(n).
	// Floyd’s Cycle-Finding Algorithm:
	// This is the fastest method. Traverse linked list using two pointers. Move
	// one pointer by one and other pointer by two.
	// If these pointers meet at some node then there is a loop. If pointers do
	// not meet then linked list doesn’t have loop.
	public static boolean isLinkedListWithLoop(NodeLinkedList head) {

		NodeLinkedList oneStepRunner = head;
		NodeLinkedList twoStepRunner = head;

		while (twoStepRunner != null && oneStepRunner != null && twoStepRunner.next != null) {

			oneStepRunner = oneStepRunner.next;
			twoStepRunner = twoStepRunner.next.next;

			if (twoStepRunner == oneStepRunner) {
				return true;
			}

		}

		return false;
	}

	// Merge two sorted linked lists such that merged list is in reverse order
	public static NodeLinkedList mergeSoretedListsToReverseList(NodeLinkedList firstLinkedList,
			NodeLinkedList secondLinkedList) {

		NodeLinkedList linkedList = null, head = null;
		java.util.Stack<NodeLinkedList> stack = new java.util.Stack<NodeLinkedList>();

		while (firstLinkedList != null || secondLinkedList != null) {

			if (firstLinkedList == null) {
				stack.add(secondLinkedList);
				secondLinkedList = secondLinkedList.next;
			}
			if (secondLinkedList == null) {
				stack.add(firstLinkedList);
				firstLinkedList = firstLinkedList.next;
			}
			if (firstLinkedList != null && secondLinkedList != null) {
				if (firstLinkedList.data > secondLinkedList.data) {
					stack.add(secondLinkedList);
					secondLinkedList = secondLinkedList.next;
				} else {
					stack.add(firstLinkedList);
					firstLinkedList = firstLinkedList.next;
				}
			}
		}

		if (stack.isEmpty()) {
			return linkedList;
		}

		linkedList = stack.pop();
		head = linkedList;

		while (!stack.isEmpty()) {
			linkedList.next = stack.pop();
			linkedList = linkedList.next;
		}
		linkedList.next = null;
		return head;
	}

	// TimeComplexity O(n). AuxillarySpace O(1).
	public static NodeLinkedList mergeSoretedListsToReverseList_B(NodeLinkedList firstLinkedList,
			NodeLinkedList secondLinkedList) {
		System.out.println("NodeLinkedList.mergeSoretedListsToReverseList_B()");
		NodeLinkedList linkedList = null;

		while (firstLinkedList != null || secondLinkedList != null) {
			if (firstLinkedList == null) {
				NodeLinkedList newNode = new NodeLinkedList(secondLinkedList.data);
				newNode.next = linkedList;
				linkedList = newNode;
				secondLinkedList = secondLinkedList.next;
			}
			if (secondLinkedList == null) {
				NodeLinkedList newNode = new NodeLinkedList(firstLinkedList.data);
				newNode.next = linkedList;
				linkedList = newNode;
				firstLinkedList = firstLinkedList.next;
			}
			if (firstLinkedList != null && secondLinkedList != null) {
				if (firstLinkedList.data > secondLinkedList.data) {
					NodeLinkedList newNode = new NodeLinkedList(secondLinkedList.data);
					newNode.next = linkedList;
					linkedList = newNode;
					secondLinkedList = secondLinkedList.next;
				} else {
					NodeLinkedList newNode = new NodeLinkedList(firstLinkedList.data);
					newNode.next = linkedList;
					linkedList = newNode;
					firstLinkedList = firstLinkedList.next;
				}
			}
		}
		return linkedList;
	}

	// Sort a linked list that is sorted alternating ascending and descending
	// orders?
	// Input List: 10->40->53->30->67->12->89->NULL
	public static NodeLinkedList sortLinkedList(NodeLinkedList nodeLinkedList) {

		NodeLinkedList headA = nodeLinkedList, tailA = nodeLinkedList;
		NodeLinkedList headB = nodeLinkedList, tailB = nodeLinkedList;
		NodeLinkedList result = null;

		while (nodeLinkedList != null) {

			headA = nodeLinkedList;
			tailA = headA;
			while (nodeLinkedList != null && nodeLinkedList.next != null
					&& nodeLinkedList.data < nodeLinkedList.next.data) {
				// ascending
				nodeLinkedList = nodeLinkedList.next;
				tailA = tailA.next;
			}

			if (headA != tailA) {
				nodeLinkedList = nodeLinkedList.next;
				tailA.next = null;

				if (result == null) {
					result = headA;
				} else {
					result = mergeLinkedLists(result, headA);
				}
			}

			headB = nodeLinkedList;
			tailB = headB;
			while (nodeLinkedList != null && nodeLinkedList.next != null
					&& nodeLinkedList.data > nodeLinkedList.next.data) {
				// descending
				nodeLinkedList = nodeLinkedList.next;
				tailB = tailB.next;
			}

			if (headB != tailB) {
				nodeLinkedList = nodeLinkedList.next;
				tailB.next = null;

				if (result == null) {
					result = reverseLinkedList(headB);
				} else {
					result = mergeLinkedLists(result, reverseLinkedList(headB));
				}
			}

			if (headA == tailA && headB == tailB && nodeLinkedList != null && nodeLinkedList.next == null) {
				mergeLinkedLists(result, nodeLinkedList);
			}
		}

		return result;
	}

	private static NodeLinkedList mergeLinkedLists(NodeLinkedList nodeLinkedListA, NodeLinkedList nodeLinkedListB) {
		NodeLinkedList mergeList = null, headMergeList = null;

		while (nodeLinkedListA != null && nodeLinkedListB != null) {

			if (nodeLinkedListA.data > nodeLinkedListB.data) {
				if (mergeList == null) {
					mergeList = nodeLinkedListB;
					headMergeList = mergeList;
					nodeLinkedListB = nodeLinkedListB.next;
				} else {
					mergeList.next = nodeLinkedListB;
					nodeLinkedListB = nodeLinkedListB.next;
					mergeList = mergeList.next;
				}
			} else {
				if (mergeList == null) {
					mergeList = nodeLinkedListA;
					headMergeList = mergeList;
					nodeLinkedListA = nodeLinkedListA.next;
				} else {
					mergeList.next = nodeLinkedListA;
					nodeLinkedListA = nodeLinkedListA.next;
					mergeList = mergeList.next;
				}
			}
		}

		if (mergeList != null) {
			mergeList.next = nodeLinkedListA == null ? nodeLinkedListB : nodeLinkedListA;
		}

		return headMergeList;
	}

	/* Function to reverse the linked list */
	private static NodeLinkedList reverseList(NodeLinkedList Dhead) {
		NodeLinkedList current = Dhead;
		NodeLinkedList prev = null;
		NodeLinkedList next;

		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		Dhead = prev;
		return Dhead;
	}

	// A utility function to merge two sorted linked lists
	// 1->3->5-> 7->9->null head1
	// 2->4->6-> 8->10->11 head2
	private static NodeLinkedList mergeList(NodeLinkedList head1, NodeLinkedList head2) {
		// Base cases
		if (head1 == null)
			return head2;
		if (head2 == null)
			return head1;

		NodeLinkedList temp = null;
		if (head1.data < head2.data) {
			temp = head1;
			head1.next = mergeList(head1.next, head2);
		} else {
			temp = head2;
			head2.next = mergeList(head1, head2.next);
		}
		return temp;
	}

	private static void splitList(NodeLinkedList Ahead, NodeLinkedList Dhead, NodeLinkedList head) {

		NodeLinkedList ascn = Ahead;
		NodeLinkedList dscn = Dhead;
		NodeLinkedList curr = head;

		// Link alternate nodes

		while (curr != null) {
			// Link alternate nodes in ascending order
			ascn.next = curr;
			ascn = ascn.next;
			curr = curr.next;

			if (curr != null) {
				dscn.next = curr;
				dscn = dscn.next;
				curr = curr.next;
			}
		}

		ascn.next = null;
		dscn.next = null;
	}

	// Sort a linked list that is sorted alternating ascending and descending
	// orders?
	public static NodeLinkedList sort(NodeLinkedList head) {

		NodeLinkedList Ahead = new NodeLinkedList(0);
		NodeLinkedList Dhead = new NodeLinkedList(0);

		splitList(Ahead, Dhead, head);

		Ahead = Ahead.next;
		Dhead = Dhead.next;

		Dhead = reverseList(Dhead);
		
		return mergeList(Ahead, Dhead);
	}
	
	public static NodeLinkedList linkedListFromArray(Object[] arrStr){
		
		NodeLinkedList result = null;
		
		if(arrStr == null){
			return null;
		}
		
		for (Object obj : arrStr) {
			if(result == null){
				result = new NodeLinkedList(obj);
			}else{
				result.appendToTailWithResult(obj);
			}
		}
		
		return result;
	}
	
    //Check if a linked list of strings forms a palindrome
    public static boolean isLinkedListPalindrome(NodeLinkedList linkedList){
 	   
 	   StringBuilder stringBuilder = null;
 	   char[] charsArr = null;
 	   
 	   if(linkedList == null){
 		   return false;
 	   }
 	   
 	   stringBuilder = new StringBuilder();
 	   
 	  while(linkedList!=null){
 		  stringBuilder.append(linkedList.getObjData());	
 		  linkedList = linkedList.getNext();
 	  }
 	  System.out.println(stringBuilder.toString());
 	  charsArr = stringBuilder.toString().toCharArray();
 	  
 	  return isPalindrom(charsArr,0,charsArr.length-1);
    }
    
    private static boolean isPalindrom(char[] charsArr,int start,int end){
 	   
 	   if(start>=end){
 		   return true;
 	   }
 	   
 	   return charsArr[start] == charsArr[end] && isPalindrom(charsArr, start+1, end-1); 
    }
    
    private static boolean isPalindromItarative(char[] charsArr,int start,int end){
 	   
 	  int len = charsArr.length;
 	   
 	  for (int i = 0; i < charsArr.length; i++) {
				if(charsArr[i] != charsArr[len - i - 1]){
					return false;
				}
		  }
 	  
 	  return true;
    }
}
