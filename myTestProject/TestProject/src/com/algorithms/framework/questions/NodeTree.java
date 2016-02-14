package com.algorithms.framework.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NodeTree {
     
	private int value;
	private NodeTree leftNode;
	private NodeTree rightNode;
	
	public NodeTree(int value){
		this.value = value;
	}
	
	public NodeTree(int value ,NodeTree leftNode , NodeTree rightNode){
		this(value);
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	
	/**
	 * question: write method that print binary Tree in level order from left to right.
	 * Time complexity: O(n) , n - number of nodes in the tree.
	 * @param root of the tree.
	 */
	public static void printTreeInlevelOrderFromLeftToRight(NodeTree root){
		
		Queue<NodeTree> queue = new LinkedList<NodeTree>();
		queue.add(root);
		
		while(!queue.isEmpty()){
			NodeTree currentNode = queue.poll();
			//print node value  
			if(currentNode != null){
				System.out.print(currentNode.getValue() + " ");
				if(root.getLeftNode()!=null)
					queue.add(currentNode.getLeftNode());
				if(root.getRightNode()!=null)
					queue.add(currentNode.getRightNode());
			}
		}
		
	}
	
	/**
	 * iteration sol.
	 * Level order traversal in spiral form
	 * @param root
	 */
	public static void printTreeInSpiral(NodeTree root){
		
		Queue<NodeTree> queueOdd = new LinkedList<NodeTree>();
		Queue<NodeTree> queueEven = new LinkedList<NodeTree>();
		queueOdd.add(root);
		
		while(!queueOdd.isEmpty() || !queueEven.isEmpty()){
			
			while(!queueOdd.isEmpty()){
				
				NodeTree node = queueOdd.poll();
				System.out.print(node.value+" ");
				if(node.rightNode!=null){
					queueEven.add(node.rightNode);
				}
				if(node.leftNode!=null){
					queueEven.add(node.leftNode);
				}
			}
			
			while(!queueEven.isEmpty()){
				NodeTree node = queueEven.poll();
				System.out.print(node.value+" ");
				if(node.leftNode!=null){
					queueOdd.add(node.leftNode);
				}
				if(node.rightNode!=null){
					queueOdd.add(node.rightNode);
				}
			}
			
		}
	}
	
	public static NodeTree buildBineryTree(){
		
		/*Build binary tree from bottom to up*/
		
		//level-4
		NodeTree node_6 = new NodeTree(6,null,null);
		NodeTree node_9 = new NodeTree(9,null,null);
		NodeTree node_10 = new NodeTree(10,null,null);
		NodeTree node_11 = new NodeTree(11,null,null);
		
		//level-3
		NodeTree node_4 = new NodeTree(4,null,null);
		NodeTree node_3 = new NodeTree(3,node_6,null);
		NodeTree node_2 = new NodeTree(2,node_9,node_10);
		NodeTree node_1 = new NodeTree(1,null,node_11);
		
		//level-2
		NodeTree node_5 = new NodeTree(5,node_4,node_3);
		NodeTree node_16 = new NodeTree(16,node_2,node_1);
		
		//level-1
		NodeTree root_node_17 = new NodeTree(17,node_5,node_16);	
		
		return root_node_17;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NodeTree getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(NodeTree leftNode) {
		this.leftNode = leftNode;
	}

	public NodeTree getRightNode() {
		return rightNode;
	}

	public void setRightNode(NodeTree rightNode) {
		this.rightNode = rightNode;
	}
	
	/**
	 * 4.3
	 * Given a sorted (increasing order) array, write an algorithm to create a binary tree with minimal height.
	 * @param arr
	 * @param startIndex
	 * @param endIndex
	 * @return the Tree.
	 */
	private static NodeTree buildTree(int[] arr,int startIndex,int endIndex){
	  /*
	 	1. Insert into the tree the middle element of the array.
		2. Insert (into the left subtree) the left subarray elements
		3. Insert (into the right subtree) the right subarray elements
		4. Recurse 
	  */
		if(startIndex > endIndex){
			return null;
		}
		
		int middleIndex = (endIndex - startIndex)/2;
		
		NodeTree n = new NodeTree(arr[middleIndex]);
		n.leftNode  = buildTree(arr,startIndex,middleIndex-1);
		n.rightNode = buildTree(arr,middleIndex+1,endIndex);
		
		return n;
	}
	
	public static NodeTree createMinimalBST(int[] arr){
		return buildTree(arr,0,arr.length);
	}
	
	/**
	 * 4.4
	 * Given a binary search tree, design an algorithm which creates a linked list of all the nodes at each depth 
	 * (eg, if you have a tree with depth D, you’ll have D linked lists).
	 * @param root
	 * @return ArrayList<LinkedList<NodeTree>>.
	 */
	public ArrayList<LinkedList<NodeTree>> buildListOfLinkedListEachListForLevel(NodeTree root){
		
		int level = 0;
		ArrayList<LinkedList<NodeTree>> result = new ArrayList<LinkedList<NodeTree>>();
		
		LinkedList<NodeTree> list = new LinkedList<NodeTree>();
		list.add(root);
		
		result.add(list);
		
		while(true){
			list = new LinkedList<NodeTree>();
			for (int i = 0 ; i < result.get(level).size() ; i++) {
				NodeTree n = (NodeTree) result.get(level).get(i);
				if(n != null){
					if(n.leftNode != null){
						list.add(n.leftNode);
					}
					if(n.rightNode != null){
						list.add(n.rightNode);
					}
				}
			}
			
			if(list.size() > 0){
				result.add(level+1,list);
			}else{
				break;
			}
			
			level++;
		}
		
		return result;
	}
	
	/**
	 * calculate binaryTree high
	 * @param root
	 * @return
	 */
	public static int TreeHigh(NodeTree root){
		
		if(root == null){
			return 0;
		}
		
		int leftHigh = TreeHigh(root.leftNode);
		int rightHigh = TreeHigh(root.rightNode);
		
		if(leftHigh>rightHigh){
			return leftHigh+1;
		}else{
			return rightHigh+1;
		}
	}
	
	/**
	 * Level order traversal in spiral form
	 * Worst case O(n^2).
	 * @param root
	 */
	public static void printSpiralLevel(NodeTree root){
		int treeHight = TreeHigh(root);
		boolean ltr = false;
		
		for (int level = 1; level <= treeHight; level++) {
			printLevel(root,level,ltr);
			ltr = !ltr;
		}
		
	}
	
	/**
	 * print specific level
	 * TimeComplexity O(n).
	 * @param root
	 * @param level
	 * @param ltr
	 */
	public static void printLevel(NodeTree root,int level,boolean ltr){
		
		if(root == null){
			return;
		}
		
		if(level == 1){
			System.out.print(root.value + " ");
		}else if(level > 1){
			if(ltr){
				printLevel(root.leftNode,level-1,ltr);
				printLevel(root.rightNode,level-1,ltr);
			}else{
				printLevel(root.rightNode,level-1,ltr);
				printLevel(root.leftNode,level-1,ltr);
			}
		}
	}
	
	private static int getSumLevel(NodeTree root,int level,int sum){
		
		if(root == null){
			return sum;
		}
		
		if(level == 1){
			
			/*check if this node is leave*/
			if(isLeaf(root)){
				return sum + root.value;
			}else{
				return sum;
			}
			
		}else if(level > 1){
			sum=getSumLevel(root.leftNode,level-1,sum);
			sum=getSumLevel(root.rightNode,level-1,sum);
		}
		
		return sum;
	}
   
	/**
	 * Find multiplication of sums of data of leaves at same levels
	 * TimeComplexity O(n^2)
	 * @param root
	 * @return
	 */
	public static int findMultiOfSumsLeaves(NodeTree root){
		
		int treeHight = TreeHigh(root);
		int sumLevelMultiplaction = 1;
		
		for (int level = 1; level <= treeHight; level++) {
			int res = getSumLevel(root,level,0);
			sumLevelMultiplaction*= res !=0 ? res : 1;
		}
		
		return sumLevelMultiplaction;
	}
	
	/**
	 * Find multiplication of sums of data of leaves at same levels - iterative sol.
	 * @param root
	 * @return
	 */
	public static int findMultiOfSumsLeavesIterativeSol(NodeTree root){
		
		int sum = 0;
		int multiplication = 1;
		Queue<NodeTree> queueOdd = new LinkedList<NodeTree>();
		Queue<NodeTree> queueEven = new LinkedList<NodeTree>();
		
		queueOdd.add(root);
		
		while(!queueOdd.isEmpty() && !queueEven.isEmpty()){
			
			while(!queueOdd.isEmpty()){
				NodeTree node = queueOdd.poll();
				
				if(isLeaf(node)){
					sum+= node.value;
				}else{
					
					if(node.leftNode != null){
						queueEven.add(node);
					}
					if(node.rightNode != null){
						queueEven.add(node);
					}
					
				}
				
				if(queueOdd.isEmpty()){
					multiplication*= sum!=0 ?  sum : 1;
					sum = 0;
				}
			}
			
			while(!queueEven.isEmpty()){
				NodeTree node = queueEven.poll();
				
				if(isLeaf(node)){
					sum+= node.value;
				}else{
					
					if(node.leftNode != null){
						queueOdd.add(node);
					}
					if(node.rightNode != null){
						queueOdd.add(node);
					}
					
				}
				
				if(queueEven.isEmpty()){
					multiplication*= sum!=0 ?  sum : 1;
					sum = 0;
				}
			}
			
		}
		
		return multiplication;
	}
	
	/**
	 * Find multiplication of sums of data of leaves at same levels - second iterative sol.
	 * TimeComplexity O(n*m)
	 * @param root
	 * @return
	 */
	public static int findMultiOfSumsLeavesIterativeMoreSmartSol(NodeTree root){
		
		int sum = 0;
		int NodeCount = 0;
		int multiplication = 1;
		boolean isLeafFound = false;
		Queue<NodeTree> queue = new LinkedList<NodeTree>();
		
		queue.add(root);
		
		while(true){
			
			NodeCount = queue.size();
			isLeafFound = false;
			sum = 0;
			
			if(NodeCount == 0){
				break;
			}
			
			while(NodeCount > 0){
				
				NodeTree node = queue.poll();
				
				if(isLeaf(node)){
					sum+=node.value;
					isLeafFound = true;
				}else{
					
					/*insert next level*/
					if(node.leftNode != null){
						queue.add(node);
					}
					if(node.rightNode != null){
						queue.add(node);
					}
					
				}
				NodeCount--;
			}
			
			if(isLeafFound){
				multiplication*=sum;
			}
		}
		
		return multiplication;
		
	}
	
	private static boolean isLeaf(NodeTree node){
		
		if(node == null)
		  return false;
		return node.leftNode == null && node.rightNode == null;
	}
	
	/** TimeComplexity O(n).
	 * Boundary Traversal of binary tree
	  Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root
	 */
	public static void printBoundaryAntiClockwise(NodeTree root){
		
		if(root!=null){
			
			//print root
			System.out.print(root.value + " ");
			
			// Print the left boundary in top-down manner.
			printLeftBoundaryTree(root.leftNode);
			
			// Print all leaf nodes
			printLeafsTree(root.leftNode);
			printLeafsTree(root.rightNode);
			
			// Print the right boundary in bottom-up manner
			printrightBoundaryTree(root.rightNode);
			
		}
		
	}
	
	
	private static void printLeftBoundaryTree(NodeTree root){
		
		if(root == null){
			return;
		}
		
		if(root.leftNode!=null){
			
			 System.out.print(root.value + " ");
			 printLeftBoundaryTree(root.leftNode);
			
		}else if(root.rightNode!=null){
			
			System.out.print(root.value + " ");
			printrightBoundaryTree(root.rightNode);
			
		}
		
	}
	
	private static void printLeafsTree(NodeTree root){
		
		if(root == null){
			return;
		}
		
		printLeafsTree(root.leftNode);
		
		if(isLeaf(root)){
			System.out.print(root.value + " ");
		}
		
		printLeafsTree(root.rightNode);
	}
	
	private static void printrightBoundaryTree(NodeTree root){
		if(root == null){
			return;
		}
		
		if(root.rightNode!=null){
			
			 printLeftBoundaryTree(root.rightNode);
			 System.out.print(root.value + " ");
			
		}else if(root.leftNode!=null){
			
			printrightBoundaryTree(root.leftNode);
			System.out.print(root.value + " ");
			
		}
	}
	
	//Print all nodes at distance k from a given node
	public static void printNodesInDistanceK(NodeTree root , NodeTree targetNode , int k){
		
		printkdistanceNodeDown(targetNode ,k);
	}
	
	public static void  printkdistanceNodeDown(NodeTree node , int k){
		
		if(node == null || k<0){
			return;
		}
		
		if(k == 0){
			System.out.println(node.value);
		}else{
			printkdistanceNodeDown(node.leftNode,k-1);
			printkdistanceNodeDown(node.rightNode,k-1);	
		}
		
	}
	
	 // @?@
	//Prints all nodes at distance k from a given target node.
    // The k distant nodes may be upward or downward.  This function
    // Returns distance of root from target node, it returns -1 if target
    // node is not present in tree rooted with root.
	public static int printkdistanceNode(NodeTree node, NodeTree target, int k) {
         
        // Base Case 1: If tree is empty, return -1
        if (node == null) {
            return -1;
        }
 
        // If target is same as root.  Use the downward function
        // to print all nodes at distance k in subtree rooted with
        // target or root
        if (node == target) {
            printkdistanceNodeDown(node, k);
            return 0;
        }
 
        // Recur for left subtree
        int dl = printkdistanceNode(node.leftNode, target, k);
 
        // Check if target node was found in left subtree
        if (dl != -1) {
             
            // If root is at distance k from target, print root
            // Note that dl is Distance of root's left child from target
            if (dl + 1 == k) {
                System.out.print(node.value);
                System.out.println("");
            }
             
            // Else go to right subtree and print all k-dl-2 distant nodes
            // Note that the right child is 2 edges away from left child
            else {
                printkdistanceNodeDown(node.rightNode, k - dl - 2);
            }
 
            // Add 1 to the distance and return value for parent calls
            return 1 + dl;
        }
 
        // MIRROR OF ABOVE CODE FOR RIGHT SUBTREE
        // Note that we reach here only when node was not found in left subtree
        int dr = printkdistanceNode(node.rightNode, target, k);
        if (dr != -1) {
            if (dr + 1 == k) {
                System.out.print(node.value);
                System.out.println("");
            } else {
                printkdistanceNodeDown(node.leftNode, k - dr - 2);
            }
            return 1 + dr;
        }
 
        // If target was neither present in left nor in right subtree
        return -1;
    }
	
	//Find sum of all left leaves in a given Binary Tree
	public static int findSumOfLeftLeaves(NodeTree root,boolean isLeft){
		
		if(root == null){
			return 0;
		}
	     
		if(isLeaf(root) && isLeft){
			System.out.println("left leave=" + root.value);
			return root.value;
		}
		
		int sumA = findSumOfLeftLeaves(root.leftNode,true);
		int sumB = findSumOfLeftLeaves(root.rightNode,false);
     		
		return sumA + sumB;
	}
	
	
	public static int findSumOfLeftLeaves_GeeksForGeeks(NodeTree root){
		
		int sum = 0;
		
		if(root!=null){
			
			if(isLeaf(root.leftNode)){
				sum += root.leftNode.value;
			}else{
				sum += findSumOfLeftLeaves_GeeksForGeeks(root.leftNode);
			}
			sum += findSumOfLeftLeaves_GeeksForGeeks(root.rightNode);
		}
		
		return sum;
	}
	
	//Construct Binary Tree from given Parent Array representation
	/*  Given an array that represents a tree in such a way array indexes
	 *  are values in tree nodes array values give the parent node of that
	 *  particular index (or node). The value of the root node index would
	 *  always be -1 as there is no parent for root. Construct the standard
	 *  linked representation of given Binary Tree from this given 
	 *  representation
	 */
	public static NodeTree createTree(int[] parent){
		
		NodeTree root = null;
		NodeTree[] createdNodeds = new NodeTree[parent.length];
		
		for (int i = 0; i < parent.length; i++) {
			int rootInd = createNode(parent,createdNodeds,i);
			if(rootInd != -1){
				root = createdNodeds[rootInd];
			}
		}
		
		return root;
	}				//   0  1  2  3  4   5  6
	//Input: parent[] = {1, 5, 5, 2, 2, -1, 3}
	private static int createNode(int[] parent,NodeTree[] createdNodeds , int i){
		
		int rootInd = -1;
		/*index in arr is node*/
		if(createdNodeds[i] != null){
			return -1;
		}
		
		createdNodeds[i] = new NodeTree(i);
		
		/*root node*/
		if(parent[i] == -1){
			rootInd = i;
			return rootInd;
		}
		
		/*no parent created to this node*/
		if(createdNodeds[parent[i]] == null){
			/*create parent*/
			rootInd = createNode(parent, createdNodeds, parent[i]);
		}
		
		NodeTree parentNoded = createdNodeds[parent[i]];
		
		if(parentNoded.leftNode == null){
			parentNoded.leftNode = createdNodeds[i];
		}else{
		    parentNoded.rightNode = createdNodeds[i];	
		}
		
		return rootInd;
	}
	
	public static void printInOrder(NodeTree root){
		if(root != null){
			printInOrder(root.leftNode);
			System.out.println(root.value+ " ");
			printInOrder(root.rightNode);
		}
	}
	
	//Check whether a binary tree is a full binary tree or not
	public static boolean isBineryTreeFull(NodeTree root){
		
		if(root == null){
			return true;
		}
		
		if(root.leftNode == null && root.rightNode == null){
			return true;
		}
		
		if(root.leftNode != null && root.rightNode != null){
			return isBineryTreeFull(root.leftNode) && isBineryTreeFull(root.rightNode);
		}
		
		return false;
	}
	
	//Closest leaf to a given node in Binary Tree
	public static int findClosestDistanceFromLeaf(NodeTree root , NodeTree nodeX){
		
		int distOfSubTree = findInSubTreeClosestDistFromLeaf(nodeX,0,Integer.MAX_VALUE);
		int distFromRoot = findFromRootClosestDistFromLeaf(root, nodeX);
		
		return distOfSubTree > distFromRoot ? distFromRoot : distOfSubTree;
	}
	
	private static int findInSubTreeClosestDistFromLeaf(NodeTree node,int level,int minDistance){
		
		if(isLeaf(node)){
			if(level<minDistance){
				minDistance = level;
			}
		}
		
		minDistance = findInSubTreeClosestDistFromLeaf(node.leftNode,level+1,minDistance);
		minDistance = findInSubTreeClosestDistFromLeaf(node.rightNode,level+1,minDistance);
		
		return minDistance;
	}
	
	private static int findFromRootClosestDistFromLeaf(NodeTree root,NodeTree nodeX){
		
		if(root == null)
			return -1;
		if(root == nodeX)
		  return 0;
		
		int l = findFromRootClosestDistFromLeaf(root.leftNode, nodeX);
		
		if(l!=-1){
			
		}
		
		return-1;
	}
	
	public static void removeHalfNodes(NodeTree root,NodeTree parent,boolean isLeft){
		
		if(isHalfNode(root)){
			NodeTree grandChild = null;
			grandChild = root.leftNode == null ? root.rightNode : root.leftNode;
			removeHalfNode(parent, isLeft, grandChild);
		}
		
		removeHalfNodes(root.leftNode,root,true);
		removeHalfNodes(root.rightNode,root,false);
		
	}
	
	private static boolean isHalfNode(NodeTree node){
		return (node.rightNode != null && node.leftNode == null) || (node.rightNode == null && node.leftNode != null);
	}
	
	private static void removeHalfNode(NodeTree parent,boolean isLeft,NodeTree grandChild){
		if(isLeft){
			parent.leftNode = grandChild;
			return;
		}
		
		parent.rightNode = grandChild;
	}
	
	//Given a binary tree, how do you remove all the half nodes?
	public static NodeTree gfg_removeHalfNodes(NodeTree root){
		
		if(root == null){
			return null;
		}
		
		root.leftNode = gfg_removeHalfNodes(root.leftNode);
		root.rightNode = gfg_removeHalfNodes(root.rightNode);
		
		if(root.leftNode == null && root.rightNode == null){
			return root;
		}
		
		
		if(root.leftNode == null){
			return root.rightNode;
		}
		
		if(root.rightNode == null){
			return root.leftNode;
		}
		
		return root;
	}
	
	//Convert a given Binary Tree to Doubly Linked List | Set 1
	public static void convertTree(NodeTree root){
		
		if(root == null){
			return; 
		}
		
		
		convertTree(root.leftNode);
		
		
		convertTree(root.rightNode);
		
	}
	
	//Write Code to Determine if Two Trees are Identical
	public static boolean isTreesIdentical(NodeTree rootOne,NodeTree rootTwo){
		
		return false;
		
	}
}
