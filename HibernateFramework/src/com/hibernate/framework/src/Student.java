package com.hibernate.framework.src;

public class Student {
	
	private int id;
	private int grade;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private String email;
	
	public Student(){};
	
	public Student(int grade,String phoneNumber,String firstName,String lastName,String email){
		this.grade=grade;
		this.phoneNumber=phoneNumber;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
