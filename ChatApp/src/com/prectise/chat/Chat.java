package com.prectise.chat;

import java.util.ArrayList;
import java.util.List;

public class Chat {
	
	//private String[] bla;
	private List<String> blas = new ArrayList<String>();
	//private int version;
	
	public void join(String userName){
		blas.add(userName + " is join");
	}
	
	public void add(String text,String userName){
		blas.add(userName + ": " + text);
	}
	
	public List<String> getBlas(){
		return blas;
	}
}
