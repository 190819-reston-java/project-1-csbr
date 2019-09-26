package com.revature.dao;

import com.revature.beans.OrgMember;
import com.revature.beans.ReimbReq;

public class ReimbTableDAOIntegrationTest {

	public static void main(String[] args) {
		
		System.out.println("Hello");
		
		OrgMember ell = ReimbTableDAO.
				getOrgMember("jkrow8", true);
		
		System.out.println(ell);
		
		ReimbReq all = ReimbTableDAO.
				getReimbRequest("jkrow8");
		ReimbTableDAO.getRecieptFilePaths("jkrow8", all);
		
		System.out.println(all);
		
		System.out.println(ReimbTableDAO.getOrgMembers(true));
		
		OrgMember aell = ReimbTableDAO.
				getOrgMember("asdf134", true);
		OrgMember bell = ReimbTableDAO.
				getOrgMember("whois", true);
		
		
		ReimbReq ael = //ReimbTableDAO.getReimbRequest("asdf134");
				// above is the retrieval of the record for "asdf134"
				// below is the new ReimbReq object to pass to database.
		new ReimbReq("PENDING",40.00,"asdf134","whois");
		ael.setFilePath("c:/blah/eefjroe.png");
		boolean[] tes = {bell.isDetermine(),aell.isDetermine()};
		ReimbTableDAO.addNewReimbRequest(ael, tes);
		ReimbTableDAO.addNewReimbReciept(ael);
		
		
		ReimbTableDAO.getRecieptFilePaths("asdf134", ael);
		System.out.println(ael);
	}

}
