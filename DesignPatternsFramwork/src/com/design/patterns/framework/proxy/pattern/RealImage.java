package com.design.patterns.framework.proxy.pattern;

public class RealImage implements IImage{
	
	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName = fileName;
		loadImageFromDisk(this.fileName);
	}

	@Override
	public void display() throws Exception {
	   System.out.println("Displaying " + fileName);
	}
	
	private void loadImageFromDisk(String fileName){
		System.out.println("Loading " + fileName);
	}

}
