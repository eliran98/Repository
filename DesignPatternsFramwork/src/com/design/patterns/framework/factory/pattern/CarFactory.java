package com.design.patterns.framework.factory.pattern;

public class CarFactory {
   
	public static Car buildCar(CarType carType){
		
		Car car = null;
		
		if(carType == null){
			return car;
		}
		
		switch (carType) {
		case LUXURY:
			car = new LuxuryCar(carType);
			break;
		case SEDAN:
			car = new SedanCar(carType);
			break;
		case SMALL:
			car = new SmallCar(carType);
			break;
		default:
			break;
		}
		
		return car;
	}
	
}
