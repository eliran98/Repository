package com.design.patterns.framework.factory.pattern;

public class SedanCar extends Car{

    SedanCar(CarType model) {
		super(model);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building sedan car");
	}

}
