package com.revature.beans;

import java.util.Arrays;

public class OrgMember {
	
	private String name;
	private String[] TYPE = {"Employee","Manager"};
	private String typeOfMem;
	private boolean determine;
	private String password;
	private String email;
	
	public OrgMember() {
		name = "";
		typeOfMem = "";
		determine = true;
		password = "";
		email = "";
	}
	
	
	public OrgMember(String name, boolean determine, String password, String email) {

		this.name = name;
		this.determine = determine;
		this.password = password;
		this.email = email;
		
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


	@Override
	public String toString() {
		return "OrgMember [name=" + name + ", TYPE=" + Arrays.toString(TYPE) + ", typeOfMem=" + typeOfMem
				+ ", password=" + password + ", email=" + email + "]";
	}
	
	

}
