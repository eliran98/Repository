package com.hibernate.framework.cinema.prectise;

public class CinemaDetials implements  java.io.Serializable{
	
	private Integer id;
	private String city;
	private String address;
	private String phoneNumber;
	
	public CinemaDetials(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
