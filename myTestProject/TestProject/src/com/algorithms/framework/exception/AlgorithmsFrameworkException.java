package com.algorithms.framework.exception;

public class AlgorithmsFrameworkException extends Exception{
	
	public AlgorithmsFrameworkException(){
		super("AlgorithmsFrameworkException thrown during application runtime");
	}
	
	public AlgorithmsFrameworkException(String errorMsg){
		super(errorMsg);
	}
	
}
