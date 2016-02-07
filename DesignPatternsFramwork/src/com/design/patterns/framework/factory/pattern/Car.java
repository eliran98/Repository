package com.design.patterns.framework.factory.pattern;

public abstract class Car {
   
	private CarType model;
	
	public Car(CarType model){
		this.model = model;
	}

	public CarType getModel() {
		return model;
	}

	public void setModel(CarType model) {
		this.model = model;
	}
	
	// Do subclass level processing in this method
    protected abstract void construct();
	
}
