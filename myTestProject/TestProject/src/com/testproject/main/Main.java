package com.testproject.main;
import java.util.HashSet;
import java.util.Set;

import com.testproject.questions.ArraysHandler;
import com.testproject.questions.NodeLinkedList;
import com.testproject.questions.NodeTree;
import com.testproject.questions.StringsHandler;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main.main()");
		
		//testPrintTreeInlevelOrderFromLeftToRight();
        //testGetNetWorkSize();
		//StringsHandler.isAnagrams("abcdcba", "");
		
		System.out.println("Main.main() - hello world");
		System.out.println("Main.main() - hello world");
		System.out.println("Main.main() - hello world");
		System.out.println("Main.main() - hello world");  
		
		//testNodeLinkedList();
		testStringsHandler();
		//testNodeTree();
	}
	
	
	public static void testNodeTree(){
		NodeTree rootTree = NodeTree.buildBineryTree();
		System.out.println(NodeTree.TreeHigh(rootTree));
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
   
   public static void testStringsHandler(){
	   
	   int r = StringsHandler.getNumStrings(2);
	  //String res = StringsHandler.removeRepeatingsChars("aaaabbbbccccdfdfdfdfdfdhz");
	  System.out.println("Main.testStringsHandler().res="+r);
   }
	
}	
