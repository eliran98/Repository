package com.algorithms.framework.logic;

import com.algorithms.framework.exception.AlgorithmsFrameworkException;

public interface ICustomDataStructure {
	
	//Frequency: Most frequent
	public Object findMin() throws AlgorithmsFrameworkException;
	public Object findMax() throws AlgorithmsFrameworkException;
	
	//Frequency: Moderate frequent 
	public void deleteMin() throws AlgorithmsFrameworkException;
	public void deleteMax() throws AlgorithmsFrameworkException;
	
	//Frequency: Least frequent
	public void insert(Object obj) throws AlgorithmsFrameworkException;
	public void delete(Object obj) throws AlgorithmsFrameworkException;
	
}
