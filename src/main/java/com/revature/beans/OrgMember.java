package com.revature.beans;

public class OrgMember {

	private String username;
	private String name;
	private final static String[] TYPE = { "Employee", "Manager" };
	private String typeOfMem;
	private boolean determine;
	private String password;
	private String email;
	private String address;
	private String city;
	private String country;

	public OrgMember() {
		this.username = "";
		this.name = "";
		this.typeOfMem = "";
		this.determine = true;
		this.password = "";
		this.email = "";
		this.address = "";
		this.city = "";
		this.country = "";
	}

	public OrgMember(String username, String name, boolean determine, String password, String email) {

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

	public OrgMember(String username, String name, boolean determine, String password, String email, String address,
			String city, String country) {

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

	public String getTypeOfMem() {
		return this.determine ? TYPE[1] : TYPE[0];
	}

	public void setTypeOfMem(boolean determine) {
		if (determine)
			this.typeOfMem = TYPE[1];
		else
			this.typeOfMem = TYPE[0];
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDetermine() {
		return this.determine;
	}

	public void setDetermine(boolean determine) {
		this.determine = determine;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "OrgMember [username=" + this.username + ", name=" + this.name + ", typeOfMem=" + this.typeOfMem
				+ ", password=" + this.password + ", email=" + this.email + "]\n";
	}

}
