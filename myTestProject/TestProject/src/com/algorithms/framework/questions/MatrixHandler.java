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
	
}
