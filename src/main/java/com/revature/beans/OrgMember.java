package com.revature.beans;

import java.util.Arrays;

public class OrgMember {
	
	private String username;
	private String name;
	private String[] TYPE = {"Employee","Manager"};
	private String typeOfMem;
	private boolean determine;
	private String password;
	private String email;
	private String address;
	private String city;
	private String country;
	
	public OrgMember() {
		username = "";
		name = "";
		typeOfMem = "";
		determine = true;
		password = "";
		email = "";
		address = "";
		city = "";
		country = "";
	}
	
	
	public OrgMember(String username, String name, 
			boolean determine, String password, String email) {

		this.username = username;
		this.name = name;
		this.determine = determine;
		this.password = password;
		this.email = email;
		
		this.address = "";
		this.city = "";
		this.country = "";
		
		setTypeOfMem(determine);
	}
	
	public OrgMember(String username, String name, 
			boolean determine, String password, 
			String email, String address, String city,
			String country) {

		this.username = username;
		this.name = name;
		this.determine = determine;
		this.password = password;
		this.email = email;
		this.address = address;
		this.city = city;
		this.country = country;
		
		setTypeOfMem(determine);
	}
	
	public String getTypeOfMem(boolean determine) {
		return determine ? TYPE[0] : TYPE[1];
	}
	
	public void setTypeOfMem(boolean determine) {
		if (determine)
			typeOfMem = TYPE[0];
		else
			typeOfMem = TYPE[1];
	}


	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isDetermine() {
		return determine;
	}


	public void setDetermine(boolean determine) {
		this.determine = determine;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "OrgMember [name=" + name + ", TYPE=" + Arrays.toString(TYPE) + ", typeOfMem=" + typeOfMem
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	

}
