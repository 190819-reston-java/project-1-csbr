package com.revature.beans;

public class ReimbReq {

	private boolean status;
	private boolean pending;
	private double balance;
	private String origIssuer;
	private String resolver;
	
	public ReimbReq() {
		status = false;
		pending = false;
		balance = 0.0;
		origIssuer = "";
		resolver = "";
	}
	
	public ReimbReq(boolean status, boolean pending, double balance, String origIssuer, String resolver) {

		this.status = status;
		this.pending = pending;
		this.balance = balance;
		this.origIssuer = origIssuer;
		this.resolver = resolver;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isPending() {
		return pending;
	}
	public void setPending(boolean pending) {
		this.pending = pending;
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
	
	
	
}
