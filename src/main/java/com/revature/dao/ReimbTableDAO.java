package com.revature.dao;

import com.revature.services.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.OrgMember;
import com.revature.beans.ReimbReq;
import com.revature.services.ConnectorUtil;

public class ReimbTableDAO {
	
	public static void addNewReimbRequest(ReimbReq req, 
			OrgMember emp, int position) {
		Connection conn = null;
		PreparedStatement[] stmt = {null,null};
		
		final String[] sql = {"INSERT INTO reimb_employee" + 
				"(reimb_id,work_emp_id_fk,emp_fk,reciept_img_path) " + 
				"VALUES(?,?,?,?);",
				"INSERT INTO reimb_manager" + 
				"(reimb_id_fk,reimb_status,reimb_balance,work_mgr_id_fk,mgr_fk) " +
				"VALUES(?,?,?,?,?)"};
		
		try {
			
			conn = ConnectorUtil.getConnection();
			stmt[0] = conn.prepareStatement(sql[0]);
			
			
			stmt[0].setString(1, req.getID());
			stmt[0].setString(2, emp.getUsername());
			stmt[0].setBoolean(3, emp.isDetermine());
			// links to ReimbReq's arrayList, hence the position variable.
			stmt[0].setString(4, req.getFilePath(position));
			
			stmt[1] = conn.prepareStatement(sql[1]);
			
			stmt[1].setString(1, req.getID());
			stmt[1].setString(2, "PENDING");
			stmt[1].setDouble(3, req.getBalance());
			stmt[1].setString(4, "Unknown");
			stmt[1].setBoolean(5, false);
			
		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt[0]);
			StreamCloser.close(stmt[1]);
		}
	}
	
	public static void updateReimbRequest(ReimbReq req, OrgMember mgr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String sql = "UPDATE reimb_manager SET reimb_status = ?, " +
				"work_mgr_id_fk = ? WHERE reimb_id = ?";
		
		try {
			
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, req.getStatus());
			stmt.setString(2, mgr.getUsername());
			stmt.setString(3, req.getID());
			
		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static ArrayList<OrgMember> getOrgMembers(boolean type) {
		ArrayList<OrgMember> list = new ArrayList<OrgMember>();
		String sql = "";
		try (Connection conn = ConnectorUtil.getConnection()) {
			if (type) {
				sql = "SELECT work_id, first_name, " + 
						"last_name, employee_or_manager, acc_password, " + 
						"acc_email FROM work_table;";
				}
			else {
				sql = "SELECT * FROM work_table;";
			}
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							if (type)
								list.add(OrgMemInstance(rs));
							else
								list.add(OrgMemCompleteInst(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static OrgMember getOrgMember(String username, boolean type) {
		String sql = "";
		OrgMember orgm = null;
		try (Connection conn = ConnectorUtil.getConnection()) {
			if (type) {
				sql = "SELECT work_id, first_name, " + 
						"last_name, employee_or_manager, acc_password, " + 
						"acc_email FROM work_table;";
				}
			else {
				sql = "SELECT * FROM work_table;";
			}
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							if (type)
								orgm  = OrgMemInstance(rs);
							else
								orgm = OrgMemCompleteInst(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orgm;
	}
	
	
	
	private static OrgMember OrgMemInstance(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("username"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("employee_or_manager"),
				rs.getString("acc_password"),
				rs.getString("acc_email"));
	}
	
	private static OrgMember OrgMemCompleteInst(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("username"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("employee_or_manager"),
				rs.getString("acc_password"),
				rs.getString("acc_email"), 
				rs.getString("address"),
				rs.getString("city"), 
				rs.getString("country"));
	}
	
}
