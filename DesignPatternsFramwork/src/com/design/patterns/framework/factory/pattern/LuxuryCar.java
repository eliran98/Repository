package com.design.patterns.framework.factory.pattern;

public class LuxuryCar extends Car{

	LuxuryCar(CarType model) {
		super(model);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building luxury car");
	}
}
