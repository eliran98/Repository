package com.algorithms.framework.questions;

public class MatrixHandler {
	
	private final static int DEFUALT_VAL = -1;
	
	//TimeComplexity O(dn*dm^2).
	public static void printMatrixInSortedOrder(int[][] matrix){
		
		int dn = matrix.length;
		int dm = matrix[0].length;
		int minVal = DEFUALT_VAL;
		int minValInd = DEFUALT_VAL;
		int nextMinVal = DEFUALT_VAL;
		
		int[] mapRowToColPosition = new int[dn];
		    
			for (int i = 0; i < dm*dm ; i++) {
				for (int j = 0 ; j < dn ; j++) {
					if(mapRowToColPosition[j] < dm){
						if(minVal > matrix[j][mapRowToColPosition[j]] || minVal == DEFUALT_VAL){
							minVal = matrix[j][mapRowToColPosition[j]];
							minValInd = j;
						}	
					}
				}
				
				System.out.print(minVal + " ");
				mapRowToColPosition[minValInd]++;
				minVal = nextMinVal;
			}
	}
	
	//Submatrix Sum Queries O(n).
	public static int querySumMatrix(int[][] matrix,int tli,int tlj,int rli,int rlj){
		
		/*input validation*/
		if(matrix == null || tli>rli || tlj>rlj  || (matrix.length-1)<rli || (matrix[0].length-1)<rlj){
			return -1;
		}
		
		int sumSubMatrix = 0;
		
		for (int i = tli ; i <=rli ; i++) {
			for (int j = tlj ; j <=rlj ; j++) {
				sumSubMatrix+=matrix[i][j];
			}
		}
		
		return sumSubMatrix;
		
	}
	
	//Maximum size rectangle binary sub-matrix with all 1s
	public void findMaxRecInSubMatrix(int[][] matrix){
	/*	Input :   0 1 1 0
        		  1 1 1 1
        		  1 1 1 1
        		  1 1 0 0

      Output :    1 1 1 1
        		  1 1 1 1*/
		
	}
	
	//Print unique rows in a given boolean matrix
	
}
