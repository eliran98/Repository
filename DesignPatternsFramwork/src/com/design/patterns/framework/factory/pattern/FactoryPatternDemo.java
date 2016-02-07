package com.design.patterns.framework.factory.pattern;

/*
   Factory design Pattern:
   The creation of an object requires access to information or resources that should not be contained within the composing class.
   The lifetime management of the generated objects must be centralized to ensure a consistent behavior within the application. 
 */
public class FactoryPatternDemo {
  
	public static void main(String[] args) {
	   System.out.println(CarFactory.buildCar(CarType.SMALL));
       System.out.println(CarFactory.buildCar(CarType.SEDAN));
       System.out.println(CarFactory.buildCar(CarType.LUXURY));
	}
	
}
