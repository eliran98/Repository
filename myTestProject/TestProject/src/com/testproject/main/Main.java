package com.testproject.main;
import java.util.HashSet;
import java.util.Set;

import com.testproject.algo.PatternSearching;
import com.testproject.algorithm.search.sort.SortAlgorithm;
import com.testproject.algorithm.search.sort.SortAlgorithm.eType;
import com.testproject.questions.ArraysHandler;
import com.testproject.questions.NodeLinkedList;
import com.testproject.questions.NodeTree;
import com.testproject.questions.Queue;
import com.testproject.questions.Stack;
import com.testproject.questions.Stack.StackImpl;
import com.testproject.questions.StringsHandler;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main.main()");
		try {
			testQueue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testQueue() throws Exception{
		
		int k = 10;
		int n = 100;
		
		Queue.KQueues queue = new Queue.KQueues(k,n);
		
		
		for (int i = 0; i < n; i++) {
			queue.enqueue(i+1, i/k); 
		}
		
		for (int i = 0; i < k; i++) {
			queue.printQueue(i);
		}
		
		queue.enqueue(995, 3);
		queue.enqueue(995, 4);
		queue.enqueue(995, 5);
		
		queue.dequeue(4);
		queue.printQueue(4);
		queue.dequeue(4);
		queue.printQueue(4);
		queue.dequeue(4);
		queue.printQueue(4);
		
		queue.enqueue(33, 4);
		queue.printQueue(4);
		queue.enqueue(33, 4);
		queue.printQueue(4);
		queue.enqueue(33, 4);
		queue.printQueue(4);
		queue.enqueue(33, 4);
		
		queue.printQueue(4);
		
		queue.dequeue(4);
		queue.dequeue(4);
		
		queue.printQueue(4);
		
	}
	
	public static void testPatternSearching(){
		char[] txt = {'A','A','A','B','A','B','A','A'};
		char[] pattern = {'A','A','B','A'}; 	
		PatternSearching.searchPattern(txt, pattern);
	}
	
	public static void testStack() throws Exception{
		//Stack stackUtils = new Stack();
		Stack.StackImpl stack = new Stack.StackImpl();
		
        stack.push(30);
		stack.push(-5);
		stack.push(18);
		stack.push(14);
		stack.push(-3);
		
		stack.printStack();
		
		System.out.println("");
		
		Stack.sortStack(stack);
		
		stack.printStack();
	}
	
	public static void testArrayHandler(){
		//int[] arr={50, 40, 70, 60, 90};
		//int[] indexes={3,  0,  4,  1,  2};
		//arr = ArraysHandler.reorderArr(arr, indexes);
		//ArraysHandler.printArr(arr);
//		Object[][] matrix = ArraysHandler.buildCharsMatrix(5, 7);
//		ArraysHandler.printMatrix(matrix);
//		
//		System.out.println("");
//		System.out.println("");
//		System.out.println("Matrix after rotation:");
//		matrix = ArraysHandler.rotateMatrix(matrix);
//		ArraysHandler.printMatrix(matrix);
//		
//		Integer[][] arr = ArraysHandler.buildBineryMatrix(3, 4);
//		ArraysHandler.printMatrix(arr);
//		
//		System.out.println("");
//		System.out.println("");
//		System.out.println("");
//		
//		arr = ArraysHandler.setAllCells(arr);
		//ArraysHandler.printMatrix(arr);
		
		int[][]  matrix = {{1  ,  2  ,  3  },
						   {4  ,  5  ,  6  },
						   {7  ,  8 ,  9}};
		   
		
		char[][] matrixB = {{'b', 'e', 'f'},
                  			{'h', 'd', 'a'},
                  			{'i', 'c', 'a'}};
		//int ans = ArraysHandler.findLongestPath_GFG(matrix, 'b');
		ArraysHandler.rotateMatrixClockwise(matrix);
		ArraysHandler.printMatrix(matrix);
		//System.out.println(ans);
		
	}
	
	
	public static void testNodeTree(){
		//NodeTree rootTree = .buildBineryTree();
		//System.out.println(NodeTree.TreeHigh(rootTree));
		
		//int sum = NodeTree.findSumOfLeftLeaves_GeeksForGeeks(rootTree);
		//System.out.println("Sum LeftLeaves=" + sum);
		
		int[] parent = {1, 5, 5, 2, 2, -1, 3};
		NodeTree root = NodeTree.createTree(parent);
		NodeTree.printTreeInlevelOrderFromLeftToRight(root);
	}
	
	
  public static void testGetNetWorkSize(){
		Set<String> list=new HashSet<String>();
		list.add("abc");
		list.add("abd");
		list.add("abz");
		list.add("abcd");
		list.add("abm");
		list.add("abcr");
		list.add("abcq");
		list.add("abs");
		list.add("abe");
		list.add("abeabeabe");
		list.add("absabsabs");
		list.add("abcrabcrabcr");
		list.add("abzabzabzabz");
		list.add("abdabdabdabd");
		list.add("abmabmabmabm");
		list.add("rrrrrrrrrrr");
		list.add("ssssssssss");
		list.add("ggggggggg");
		list.add("fff");
		list.add("ddsdsdssdds");
		list.add("eeeeeeee");
		int result =StringsHandler.getNetWorkSize("abc",list);
		System.out.print(result);
   }
  
   public static void testNodeLinkedList(){
	   
	   // First List: 5->6->3  // represents number 563
	   //Second List: 8->4->2 //  represents number 842
//	   NodeLinkedList linkedListA = new NodeLinkedList(9);
//	   linkedListA.appendToTail(9);
//	   linkedListA.appendToTail(9);
//	   NodeLinkedList linkedListB = new NodeLinkedList(1);
//	   linkedListB.appendToTail(8);
//	   
//	   NodeLinkedList.printLinkedList(linkedListA);
//	   System.out.println("");
//	   System.out.println("");
//	   NodeLinkedList.printLinkedList(linkedListB);
//	   System.out.println("");
//	   System.out.println("");
//	   NodeLinkedList result = NodeLinkedList.addSumLinkedList(linkedListA, linkedListB);
//	   NodeLinkedList.printLinkedList(result);
	   
	   // 1->2->3->4->5->6->7->8->9->10 | M = 3, N = 2
	   NodeLinkedList linkedList = new NodeLinkedList(1);
	   linkedList.appendToTailWithResult(2).appendToTailWithResult(3).appendToTailWithResult(4).appendToTailWithResult(5).appendToTailWithResult(6).appendToTailWithResult(7).appendToTailWithResult(8);//.appendToTailWithResult(9).appendToTailWithResult(10);
	   linkedList = NodeLinkedList.func(linkedList, 2, 2);
	   NodeLinkedList.printLinkedList(linkedList);
   }
   
   public static void testStringsHandler(){
	   
	  
	  String[][] listsOfWords =  {{"you", "we"},
						          {"have", "are"},
						          {"sleep", "eat", "drink"}
						         };
	  StringsHandler.printAllSentences(listsOfWords);
   }
   
   public static void temp(){
	   int data = 1;
	   NodeLinkedList head = new NodeLinkedList(data);
	   
	   for (int i = 0 ; i < 8 ; i++) {
		   head.appendToTail(++data);
	   }
	   
	   NodeLinkedList.printLinkedList(head);
	   System.out.println();
	  // head = NodeLinkedList.reverseLinkedList(head);
	  // NodeLinkedList.printLinkedList(head);
	   //System.out.println();
	   head = NodeLinkedList.reverseEveryKNodes(head,3);
	   NodeLinkedList.printLinkedList(head);
   }
   
   private static void sort(){
	   int arr[] = {64, 34, 25, 12, 22, 11, 90,7,4,44,33,21,6,78,99,100,2,1};
	   
	   NodeLinkedList list = null,start = null , end = null;
	   
	   for (int i = 0; i < arr.length; i++) {
		 if(list == null){
			 list = new NodeLinkedList(arr[i]);
			 start = list;
		 }else{
			 end = list.appendToTailWithResult(arr[i]);
		 }
	   }
	   
	   System.out.println("print linkedList");
	   NodeLinkedList.printLinkedList(list);
	   System.out.println("");
	   System.out.println("");
	   SortAlgorithm.quickSortRec(list,start,end);
	   //SortAlgorithm.quickSort(arr, eType.RECURSIVE);
	   System.out.println("print sorted linkedList");
	   NodeLinkedList.printLinkedList(list);
	   
	   //SortAlgorithm.bubbleSort(arr);
	   //SortAlgorithm.selectionSort(arr);
	   //int[] arr_={1,2,3,4,5,6,7,8,9,10};
	   //arr=SortAlgorithm.bucketSort(arr);
	   //int res=SortAlgorithm.min(arr_, 0);
	   //System.out.println(res);
	  // for (int i = 0; i < arr.length; i++) {
			//System.out.print(arr[i] + " ");
	    //}
   }
}	
