package com.revature.dao;

import com.revature.services.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.OrgMember;
import com.revature.services.ConnectorUtil;

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
	
	public static OrgMember getOrgMembers(String username, boolean type) {
		ArrayList<OrgMember> list = new ArrayList<OrgMember>();
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
	
	public static String ranGen() {
		return Double.toString(Math.random() * 5);
	}
	
}
