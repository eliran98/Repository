package com.testproject.questions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ArraysHandler {
   
	/**
	 * Majority Element: A majority element in an array A[] of size n is an element that 
	 * appears more than n/2 times (and hence there is at most one such element).
	 * @param arr
	 */
	public static void printMajorityElement(int[] arr){
		if(arr == null){
			return;
		}
		
		int majorityElementNum = arr.length / 2;
		int[] trackStore = new int[256];
		
		for (int element : arr) {
			trackStore[element]+=1;
			if(trackStore[element]>=majorityElementNum){
				System.out.println("the Majority element is="+ trackStore[element]);
				return;
			}
		}
		
		System.out.println("NONE");
	}
	
	
	//Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
	//Time-Complexity o(n). ??
	public static int maxOnesIndex(int arr[], int n)
    {
		//111101111011101110
		//1110000111001110001111
		//0010101010101110111011
        int max_count = 0;  // for maximum number of 1 around a zero
        int max_index=0;  // for storing result
        int prev_zero = -1;  // index of previous zero
        int prev_prev_zero = -1; // index of previous to previous zero
  
        // Traverse the input array
        for (int curr=0; curr<n; ++curr)
        {
            // If current element is 0, then calculate the difference
            // between curr and prev_prev_zero
            if (arr[curr] == 0)
            {
                // Update result if count of 1s around prev_zero is more
                if (curr - prev_prev_zero > max_count)
                {
                    max_count = curr - prev_prev_zero;
                    max_index = prev_zero;
                }
  
                // Update for next iteration
                prev_prev_zero = prev_zero;
                prev_zero = curr;
            }
        }
  
        // Check for the last encountered zero
        if (n-prev_prev_zero > max_count)
            max_index = prev_zero;
  
        return max_index;
    }
	
	//Reorder an array according to given indexes
	//Time-Complexity o(n) , Space - o(1).
	public static int[] reorderArr(int[] arr,int[] index){
		
		 // Fix all elements one by one
	    for (int i=0; i<arr.length; i++)
	    {
	        // While index[i] and arr[i] are not fixed
	        while (index[i] != i)
	        {
	            // Store values of the target (or correct) 
	            // position before placing arr[i] there
	            int  oldTargetI  = index[index[i]];
	            int oldTargetE  = arr[index[i]];
	 
	            // Place arr[i] at its target (or correct)
	            // position. Also copy corrected index for
	            // new position
	            arr[index[i]] = arr[i];
	            index[index[i]] = index[i];
	 
	            // Copy old target values to arr[i] and
	            // index[i]
	            index[i] = oldTargetI;
	            arr[i]   = oldTargetE;
	        }
	    }
		
		return arr;
	}
	
	public static void printArr(int[] arr){
		if(arr == null || arr.length == 0){
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
	//* * * ^ * * *
	//* * * | * * *
	//* * * | * * *
	//* * * | * * *
	public static Character[][] buildCharsMatrix(int dn , int dm){
		Character[][] matrix = new Character[dn][];
		
		for (int i = 0; i < dn; i++) {
			matrix[i] = new Character[dm];
			for(int j=0 ; j < dm ; j++){
				if(j == (dm/2)){
					if(i == 0){
						matrix[i][j] = '^';
					}else{
						matrix[i][j] = '|';
					}
				}else{
					matrix[i][j] = '*';
				}
			}
		}
		return matrix;
	}
	
	//1 0 0 1
	//0 0 1 0
	//0 0 0 0
	public static Integer[][] buildBineryMatrix(int dn , int dm){
		Integer[][] matrix = new Integer[dn][];
		
		for (int i = 0; i < dn; i++) {
			matrix[i] = new Integer[dm];
			for(int j=0 ; j < dm ; j++){
			   if((i == 0 && j == 0) || (i == 0 && j == 3) || (i == 1 && j == 2)){
				   matrix[i][j] = 1;
			   }else{
				   matrix[i][j] = 0;  
			   }
			}
		}
		return matrix;
	}
	
	public static void printMatrix(Object[][] matrix){
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public static void printMatrix(int[][] matrix){
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	//Turn an image by 90 degree
	//TC-O(MXN) , Space-O(MXN).
	public static Object[][] rotateMatrix(Object[][] matrix){
		
		Object[][] matrixB = null;
		int M = matrix.length;
		int N = matrix[0].length;
		
		matrixB = new Object[N][M];
		
		for (int row = 0; row < M ; row++) {
			for (int col = 0; col < N; col++) {
				matrixB[col][M - row - 1] = matrix[row][col];
			}
		}
		
		return matrixB;
	}
	
	//A Boolean Matrix Question
	//Time Complexity: O(M*N)
	//Auxiliary Space: O(M + N)
	public static Integer[][] setAllCells(Integer[][] arr){
		
		int row = 0;
		int col = 0;
		int dn = arr.length;
		int dm = arr[0].length;
		
		int[] rows_track_Index = new int[dn];
		int[] cols_track_Index = new int[dm];
		
		int last_row = dn - 1;
		int last_col = dm - 1;
		
		while(row<=last_row){
			while(col<=last_col){
				if(arr[row][col] == 1){
					rows_track_Index[row] = 1;
					cols_track_Index[col] = 1;
				}
				col++;
			}
			row++;
			col = 0;
		}
		
		col = 0;
		row = 0;
		
		while(row<=last_row){
			while(col<=last_col){
				if(rows_track_Index[row] == 1 || cols_track_Index[col] == 1){
					arr[row][col] = 1;
				}
				col++;
			}
			row++;
			col = 0;
		}
		
		return arr;
	}
//	Input: bool mat[R][C] = {   {0, 1, 0, 1, 0},
	//            				{0, 1, 1, 1, 1},
	//            				{1, 1, 1, 0, 1},
	//            				{1, 1, 1, 1, 1}
//         					};
	//Find the largest rectangle of 1’s with swapping of columns allowed
	public static void findLargestRecArea(int[][] matrix){
		// i-1,j: i+1,j : i,j-1 : i,j+1 : i-1,j-1 : i+1,j+1 : i-1,j+1 : i+1,j-1
		
		
	}
	
	//Find length of the longest consecutive path from a given starting character
	public static void findLongestPath(char[][] matrix,char startPoint){
		LinkedList<Cell> tempCells = null;
		Queue<Cell> queueCells = new LinkedList<Cell>();
		Cell cell = findStartPointPosition(matrix, startPoint);
		queueCells.add(cell);
		int counterPathLength = 0;
		
		do{
			cell = queueCells.poll();
			System.out.println(cell.value);
			tempCells = find(matrix, cell.i,cell.j,cell.value);
			if(tempCells != null){
				queueCells.addAll(tempCells);
			}
			counterPathLength++;
		}while(!queueCells.isEmpty());
		
		System.out.println("path length="+counterPathLength);
	}
	
	private static Cell findStartPointPosition(char[][] matrix,char startPoint){
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(matrix[i][j] == startPoint){
					return new Cell(i, j,startPoint);
				}
			}
		}
		return null;
	}
	
	private static LinkedList<Cell> find(char[][] matrix,int i,int j,char c){
		LinkedList<Cell> adjacent = new LinkedList<Cell>();
		int indexI = i - 1;
		int indexJ = j - 1;
		int max_i = i + 1 > (matrix.length-1) ? matrix.length-1 : i + 1;
		int max_j = j + 1 > (matrix[0].length-1) ? matrix[0].length-1 : j + 1;
		
		while(indexI<=max_i){
			if(0<=indexI && indexI<=max_i){
				while(indexJ<=max_j){
					if(0<=indexJ && indexJ<=max_j){
						if(matrix[indexI][indexJ] - c == 1){
							adjacent.add(new Cell(indexI, indexJ,matrix[indexI][indexJ]));
						}
					}
					indexJ++;
				}
			}
			indexI++;
			indexJ = j - 1;
		}
		
		return adjacent;
	}
	
	private static class Cell{
		int i;
		int j;
		char value;
		
		public Cell(int i,int j,char value){
			this.i = i;
			this.j = j;
			this.value = value;
		}
	}
	
	private static int[][] dp;
	private static int[] x = {0, 1, 1, -1, 1, 0, -1, -1};
	private static int[] y = {1, 0, 1, 1, -1, -1, 0, -1};
	public static int findLongestPath_GFG(char[][] matrix,char startPoint){
		
		int answer = 0;
		dp = new int[matrix.length][matrix[0].length];
		initDpToDefault(matrix);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				
				if(matrix[i][j] == startPoint){
					
					for (int n = 0; n < 8; n++) {
						answer = max(answer,1+findLongestPathDFS(matrix, i + x[n], j + y[n], startPoint));
					}
				}
			}
		}
		return answer;
	}
	
	private static void initDpToDefault(char[][] matrix){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				dp[i][j] = -1;
			}
		}
	}
	
	private static int findLongestPathDFS(char[][] matrix, int i, int j, char prevChar){
		
		int answer = 0;
		
		if(!isvalid(matrix, i, j) || !isadjacent(prevChar, matrix[i][j])){
			return 0;
		}
		
		if(dp[i][j] != -1){
			return dp[i][j];
		}
		
		for (int n = 0; n < 8; n++) {
			answer = max(answer,1+findLongestPathDFS(matrix,  i + x[n], j + y[n], prevChar));
		}
		
		dp[i][j] = answer;
			
		return answer;
	}
	
	private static int max(int i,int j){
		return i>j ? i : j;
	}
	
	// Check whether current character is adjacent to previous
	// character (character processed in parent call) or not.
	private static boolean isadjacent(char prev, char curr)
	{
	    return ((curr - prev) == 1);
	}
	
	// check whether mat[i][j] is a valid cell or not.
	private static boolean isvalid(char[][] matrix,int i, int j)
	{	
		int R = matrix.length;
		int C = matrix[0].length;
		
	    if (i < 0 || j < 0 || i >= R || j >= C)
	      return false;
	    return true;
	}
	//Rotate Matrix Elements
	//Given a matrix, clockwise rotate elements in it.
	public static void rotateMatrixClockwise(int[][] matrix){
		rotateClickwise(matrix, 0, 0,matrix[0].length,matrix.length);
	}
	
	private static void rotateClickwise(int[][] matrix,int i,int j,int dm,int dn){
		
		if(dm <=1 && dn <=1){
			return;
		}
		
		int nextI = i , nextJ = j+1;
		int whichLine = 1;
		
		while(!(nextI == i && nextJ == j)){
			
			switch (whichLine) {
				case 1:
					
					if(nextJ == (dm+j)){
						whichLine++;
						nextJ--;
						nextI++;
					}else{
						swapMatrix(matrix, i, j, nextI, nextJ);
						nextJ++;
					}
					
					break;
				case 2:
					
					if(nextI == (dn+i)){
						whichLine++;
						nextI--;
						nextJ--;
					}else{
						swapMatrix(matrix, i, j, nextI, nextJ);
						nextI++;
					}
					
					break;
				case 3:
					
					if((nextJ-j) < 0){
						whichLine++;
						nextJ++;
						nextI--;
					}else{
						swapMatrix(matrix, i, j, nextI, nextJ);
						nextJ--;
					}
					
					break;
				case 4:
					
						swapMatrix(matrix, i, j, nextI, nextJ);
						nextI--;
					
					break;
			}
		}
		
		//algorithm
		// i=const,j++ j<dm.
		// i=const,j-- j>=0.
		// j=const , i++ i<dn
		// j=const , i-- i>=0
		//last swap with ib,jb == i,j.
		
		rotateClickwise(matrix, i+1, j+1,dm-2,dn-2);
		
	}
	
	private static void swapMatrix(int[][] matrix,int ia,int ja,int ib,int jb){
		System.out.println("ia="+ia+" ja="+ja+"::"+"ib="+ib+" jb="+jb);
		int temp = matrix[ia][ja];
		matrix[ia][ja] = matrix[ib][jb];
		matrix[ib][jb] = temp;
	}
	
	//Merge two sorted arrays with O(1) extra space
	public static void mergeSortedArrs(int[] arrA,int[] arrB){
		
		int maxIndexA = arrA.length - 1; 
		int maxIndexB = arrB.length - 1; 
		boolean isarrA_Bigger = false;
		
		if((arrA[maxIndexA] <= arrB[0]) || (arrA[0] >= arrB[maxIndexB])){
			//arrays are not cutting each other
			return;
		}
		
		if(arrA[0] >= arrB[0]){
			isarrA_Bigger = true;
		 }else{
			isarrA_Bigger = false; 
		 }
		
		for (int indexA = 0; indexA < arrA.length; indexA++) {
			for (int indexB = 0; indexB < arrB.length; indexB++) {
					if(isarrA_Bigger){
						if(arrB[indexB] > arrA[indexA]){
							
						}
					}else{
						
					}
			}
		}
		
	}
	
	private static void swap(int[] a , int[] b ,int ia, int ib){
		
	}
	
	
}
