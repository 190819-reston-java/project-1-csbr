package com.revature.dao;

import com.revature.beans.OrgMember;
import com.revature.beans.ReimbReq;

public class ReimbTableDAOIntegrationTest {

	public static void main(String[] args) {
		
		System.out.println("Hello");
		
		ReimbTableDAO test = new ReimbTableDAO();
		OrgMember ell = test.
				getOrgMember("jkrow8", true);
		
		System.out.println(ell);
		
		ReimbReq all = test.
				getReimbRequest("jkrow8");
		test.getRecieptFilePaths("jkrow8", all);
		
		System.out.println(all);
		
		System.out.println(test.getOrgMembers(true));
		
		OrgMember aell = test.
				getOrgMember("asdf134", true);
		OrgMember bell = test.
				getOrgMember("whois", true);
		
		
		ReimbReq ael = //test.getReimbRequest("asdf134");
				// above is the retrieval of the record for "asdf134"
				// below is the new ReimbReq object to pass to database.
		new ReimbReq("PENDING",40.00,aell.getUsername(),bell.getUsername());
		//ael.setFilePath("c:/blah/eeejroe.png");
		//test.addNewReimbRequest(ael);
		//test.addNewReimbReciept(ael);
		
		
		test.getRecieptFilePaths("asdf134", ael);
		System.out.println(ael);
	}

}
