package com.multithreading.framework.src;

public class ConcurrentSingleton {
	
	private static volatile ConcurrentSingleton singleton;
	
	private ConcurrentSingleton(){};
	
	public static ConcurrentSingleton getInstance(){
		
		if(singleton == null){
			synchronized (ConcurrentSingleton.class) {
				if(singleton == null){
					singleton = new ConcurrentSingleton();
				}
			}
		}
		
		return singleton;
	}
}
