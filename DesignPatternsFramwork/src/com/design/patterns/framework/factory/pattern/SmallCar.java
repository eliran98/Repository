package com.design.patterns.framework.factory.pattern;

public class SmallCar extends Car{

	SmallCar(CarType model) {
		super(model);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building small car");
	}
}
