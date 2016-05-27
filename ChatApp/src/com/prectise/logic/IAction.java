package com.prectise.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {
	
	public void perform(HttpServletRequest request,HttpServletResponse response);
	
	public String getName();
}
