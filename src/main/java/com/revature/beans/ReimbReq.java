package com.revature.beans;

import java.util.ArrayList;
import java.util.Random;

public class ReimbReq {

	private String id;
	private String status;
	// private double balance;
	private String origIssuer;
	private String resolver;
	private ArrayList<String> filePaths;
	// private double amount; // TODO: figure out where this should go

	// Constructor Chaining

	public ReimbReq() {
		this("", "", "");
	}

	public ReimbReq(String status, String origIssuer, String resolver) {
		this(generateID(), status, origIssuer, resolver);
	}

	public ReimbReq(String id, String status, String origIssuer,
			String resolver) {

		this.status = status;
		// this.balance = balance;
		this.origIssuer = origIssuer;
		this.resolver = resolver;
		this.filePaths = new ArrayList<>();
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if (status.equalsIgnoreCase("pending")
				|| status.equalsIgnoreCase("denied")
				|| status.equalsIgnoreCase("accepted"))
			this.status = status;
		else
			this.status = "#UNKNOWN";
	}

//	public double getBalance() {
//		return this.balance;
//	}

//	public void setBalance(double balance) {
//		this.balance = balance;
//	}

	public String getOrigIssuer() {
		return this.origIssuer;
	}

	public void setOrigIssuer(String origIssuer) {
		this.origIssuer = origIssuer;
	}

	public String getResolver() {
		return this.resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getFilePath(int position) {
		return this.filePaths.get(position);
	}

	public void setFilePath(String filePath) {
		this.filePaths.add(filePath);
	}

	private static String generateID() {
		String idBasis = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder idTok = new StringBuilder();
		Random obj = new Random();

		for (int i = 0; i < 8; ++i)
			idTok.append(idBasis.charAt(obj.nextInt(idBasis.length())));

		return idTok.toString();
	}

	public String getID() {
		return this.id;
	}

	@Override
	public String toString() {
		return "ReimbReq [id=" + this.id + ", status=" + this.status
				+ ", origIssuer=" + this.origIssuer + ", resolver="
				+ this.resolver + ", filePaths=" + this.filePaths + "]";
	}

//	@Override
//	public String toString() {
//		return "ReimbReq [id=" + this.id + ", status=" + this.status + ", balance=" + this.balance + ", origIssuer="
//				+ this.origIssuer + ", resolver=" + this.resolver + ", filePaths=" + this.filePaths + "]\n";
//	}

}
