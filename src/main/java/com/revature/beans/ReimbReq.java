package com.revature.beans;

import java.util.ArrayList;

public class ReimbReq {

	private String id;
	private String status;
	private double balance;
	private String origIssuer;
	private String resolver;
	private ArrayList<String> filePaths;
	
	public ReimbReq() {
		id = "";
		status = "";
		balance = 0.0;
		origIssuer = "";
		resolver = "";
		filePaths = new ArrayList<String>();
	}
	
	public ReimbReq(String status, double balance, 
			String origIssuer, String resolver) {

		this.status = status;
		this.balance = balance;
		this.origIssuer = origIssuer;
		this.resolver = resolver;
		filePaths = new ArrayList<String>();
		this.id = generateID();
	}
	
	public ReimbReq(String id, String status, 
			double balance, String origIssuer, 
			String resolver) {

		this.status = status;
		this.balance = balance;
		this.origIssuer = origIssuer;
		this.resolver = resolver;
		filePaths = new ArrayList<String>();
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		if (status.equalsIgnoreCase("pending") ||
			status.equalsIgnoreCase("denied") ||
			status.equalsIgnoreCase("accepted"))
			this.status = status;
		else
			this.status = "#UNKNOWN";
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getOrigIssuer() {
		return origIssuer;
	}
	public void setOrigIssuer(String origIssuer) {
		this.origIssuer = origIssuer;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	
	public String getFilePath(int position) {
		return filePaths.get(position);
	}
	public void setFilePath(String filePath) {
		filePaths.add(filePath);
	}
	private String generateID() {
		return Double.toString(Math.random() * 5);
	}
	public String getID() {
		return id;
	}
	
	
	
}
