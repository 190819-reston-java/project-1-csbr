package com.revature.dao;

import com.revature.services.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.beans.*;

public class ReimbTableDAO {
	/*public static void addNewReimbRequest(ReimbReq req, 
			OrgMember employee, int position) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String sql = "INSERT INTO reimb_employee" + 
				"(reimb_id,work_emp_id_fk,emp_fk,reciept_img_path) " + 
				"VALUES(?,?,?,?);";
		
		try {
			
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, ranGen());
			stmt.setString(2, employee.getUsername());
			stmt.setBoolean(3, employee.isDetermine());
			stmt.setString(4, req.getFilePath(position));
			
		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}*/
	
	public static String ranGen() {
		return Double.toString(Math.random() * 5);
	}
	
}
