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
	
	public static void addNewReimbRequest(ReimbReq req) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String sql = 
				"INSERT INTO reimb_table" + 
				"(reimb_id,reimb_status,reimb_balance," + 
				"mgr_usr_id_fk,emp_usr_id_fk) " +
				"VALUES(?,?,?,?,?);";
		try {
			
			conn = ConnectorUtil.getConnection();
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, req.getID());
			stmt.setString(2, "PENDING");
			stmt.setDouble(3, req.getBalance());
			stmt.setString(4, req.getResolver());
			stmt.setString(5, req.getOrigIssuer());
			stmt.execute();
			
			
			
		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void addNewReimbReciept(ReimbReq req) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String sql = "INSERT INTO reimb_reciepts" + 
				"(reimb_id_fk,reciept_img_path) " + 
				"VALUES(?,?);";
		try {
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			
			stmt.setString(1, req.getID());
			// links to ReimbReq's arrayList, hence the position variable.
			stmt.setString(2, req.getFilePath(0));
			stmt.execute();
		} catch (SQLException w) {
			w.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static void updateReimbRequest(ReimbReq req, OrgMember mgr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		final String sql = "UPDATE reimb_table SET reimb_status = ?, " +
				"mgr_usr_id_fk = ? WHERE reimb_id = ?";
		
		try {
			
			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, req.getStatus());
			stmt.setString(2, mgr.getUsername());
			stmt.setString(3, req.getID());
			stmt.executeQuery();
			
		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public static ReimbReq getReimbRequest(String username){
		final String sql = "SELECT * FROM reimb_table " +
				"WHERE emp_usr_id_fk = ?";
		/*
		 * final String sql = "SELECT reimb_table.reimb_id, reimb_table.reimb_status, " + 
				"reimb_table.reimb_balance, reimb_table.mgr_usr_id_fk, " +
				"reimb_table.emp_usr_id_fk, reimb_reciepts.reciept_img_path" +
				"FROM reimb_table INNER JOIN reimb_reciepts " +
				"ON reimb_reciepts.reimb_id_fk = reimb_table.reimb_id " +
				"WHERE reimb_table.emp_usr_id_fk = ?";
		*/
		
		ReimbReq reimb = null;
		
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							reimb = ReimbReqInstance(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}
	
	public static void getRecieptFilePaths(String username, 
			ReimbReq request){
		final String sql = "SELECT reimb_reciepts.reciept_img_path " +
				"FROM reimb_reciepts INNER JOIN reimb_table " +
				"ON reimb_table.reimb_id = reimb_reciepts.reimb_id_fk " +
				"WHERE reimb_table.emp_usr_id_fk = ?;";
		
		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							request.setFilePath(
									rs.getString("reciept_img_path"));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<OrgMember> getOrgMembers(boolean type) {
		ArrayList<OrgMember> list = new ArrayList<OrgMember>();
		String sql = "";
		try (Connection conn = ConnectorUtil.getConnection()) {
			if (type) {
				sql = "SELECT usr_id, first_name, " + 
						"last_name, emp_or_mgr, password, " + 
						"email FROM employees_table;";
				}
			else {
				sql = "SELECT * FROM employees_table;";
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
				sql = "SELECT usr_id, first_name, " + 
						"last_name, emp_or_mgr, password, " + 
						"email FROM employees_table WHERE usr_id = ?;";
				}
			else {
				sql = "SELECT * FROM employees_table;";
			}
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
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
	
	private static ReimbReq ReimbReqInstance(ResultSet rs) throws SQLException {
		return new ReimbReq(rs.getString("reimb_id"),
							rs.getString("reimb_status"),
							rs.getDouble("reimb_balance"),
							rs.getString("emp_usr_id_fk"),
							rs.getString("mgr_usr_id_fk"));
	}
	
	private static OrgMember OrgMemInstance(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("usr_id"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("emp_or_mgr"),
				rs.getString("password"),
				rs.getString("email"));
	}
	
	private static OrgMember OrgMemCompleteInst(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("usr_id"), 
				(rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("emp_or_mgr"),
				rs.getString("password"),
				rs.getString("email"), 
				rs.getString("address"),
				rs.getString("city"), 
				rs.getString("country"));
	}
	
}
