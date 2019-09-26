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
	}

}
