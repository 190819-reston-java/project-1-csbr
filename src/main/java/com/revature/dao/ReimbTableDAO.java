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
	

	public  void addNewReimbRequest(ReimbReq req) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String sql = "INSERT INTO reimb_table" 
				+ "(reimb_id,reimb_status,reimb_balance,"
				+ "mgr_user_id_fk,emp_user_id_fk) " 
				+ "VALUES(?,?,?,?,?);";
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

	public  void addNewReimbReciept(ReimbReq req) {
		Connection conn = null;
		PreparedStatement stmt = null;
		final String sql = "INSERT INTO reimb_reciepts_table" 
				+ "(reimb_id_fk,reciept_img_path) " + "VALUES(?,?);";
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

	public  void updateReimbRequest(ReimbReq req, OrgMember mgr) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String sql = "UPDATE reimb_table SET reimb_status = ?, " 
				+ "WHERE reimb_id = ? AND mgr_user_id_fk = ?;";

		try {

			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, req.getStatus());
			stmt.setString(2, req.getID());
			stmt.setString(3, mgr.getUsername());
			stmt.executeQuery();

		} catch (SQLException f) {
			f.printStackTrace();
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}
	
	public boolean updateEmpContactInfo(OrgMember emp) {
		Connection conn = null;
		PreparedStatement stmt = null;

		final String sql = "UPDATE employees_table SET first_name = ?, " 
				+ "last_name = ?, email = ?, street_address = ?, "
				+ "city = ?, country = ?"
				+ "WHERE user_id = ? AND manager = false;";

		String[] name = emp.getName().split(" ");
		try {

			conn = ConnectorUtil.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name[0]);
			stmt.setString(2, name[1]);
			stmt.setString(3, emp.getEmail());
			stmt.setString(4, emp.getAddress());
			stmt.setString(5, emp.getCity());
			stmt.setString(6, emp.getCountry());
			stmt.setString(7, emp.getUsername());
			stmt.executeQuery();
			return true;

		} catch (SQLException f) {
			f.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}
	}

	public  ReimbReq getReimbRequest(String username, int type) {
		
		final String sql = "SELECT * FROM reimb_table " 
				+ "WHERE emp_user_id_fk = ? AND reimb_status = ?";

		final String[] setType = {"PENDING","APPROVED","DENIED"};
		
		ReimbReq reimb = null;

		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				
				if (type == 2)
					stmt.setString(2, setType[2]);
				else if (type == 1)
					stmt.setString(2, setType[1]);
				else
					stmt.setString(2, setType[0]);
				
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
	
	public  ArrayList<ReimbReq> 
		getReimbRequests(String username, int type, boolean status) {
		
		String sql = "SELECT * FROM reimb_table " 
				+ "WHERE emp_user_id_fk = ?";
		
		if (status == true)
			sql += " AND reimb_status = ?;";
		else
			sql += ";";

		final String[] setType = {"PENDING","APPROVED","DENIED"};

		ArrayList<ReimbReq> reimbs = new ArrayList<>();

		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				
				stmt.setString(1, username);
				
				if (status == true) {
					if (type == 2)
						stmt.setString(2, setType[2]);
					else if (type == 1)
						stmt.setString(2, setType[1]);
					else
						stmt.setString(2, setType[0]);
				}
				
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							reimbs.add(ReimbReqInstance(rs));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbs;
	}

	public  void getRecieptFilePaths(String username, ReimbReq request) {
		final String sql = "SELECT reimb_reciepts_table.reciept_img_path "
				+ "FROM reimb_reciepts_table INNER JOIN reimb_table "
				+ "ON reimb_table.reimb_id = reimb_reciepts_table.reimb_id_fk "
				+ "WHERE reimb_table.emp_user_id_fk = ?;";

		try (Connection conn = ConnectorUtil.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							request.setFilePath(rs.getString("reciept_img_path"));
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public  ArrayList<OrgMember> getOrgMembers(boolean type) {
		ArrayList<OrgMember> list = new ArrayList<>();
		String sql = "";
		try (Connection conn = ConnectorUtil.getConnection()) {
			if (type) {
				sql = "SELECT user_id, first_name, " + 
						"last_name, manager, passwd, " + 
						"email FROM employees_table;";
			} else {
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

	public  OrgMember getOrgMember(String username, boolean type) {
		String sql = "";
		OrgMember orgm = null;
		try (Connection conn = ConnectorUtil.getConnection()) {
			if (type) {
				sql = "SELECT user_id, first_name, " + 
						"last_name, manager, passwd, "
						+ "email FROM employees_table WHERE user_id = ?;";
			} else {
				sql = "SELECT * FROM employees_table;";
			}
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, username);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.executeQuery()) {
						while (rs.next()) {
							if (type)
								orgm = OrgMemInstance(rs);
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

	private  ReimbReq ReimbReqInstance(ResultSet rs) throws SQLException {
		return new ReimbReq(rs.getString("reimb_id"), rs.getString("reimb_status"), rs.getDouble("reimb_balance"),
				rs.getString("emp_user_id_fk"), rs.getString("mgr_user_id_fk"));
	}

	private  OrgMember OrgMemInstance(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("user_id"), (rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("manager"), rs.getString("passwd"), rs.getString("email"));
	}

	private  OrgMember OrgMemCompleteInst(ResultSet rs) throws SQLException {
		return new OrgMember(rs.getString("user_id"), (rs.getString("first_name") + " " + rs.getString("last_name")),
				rs.getBoolean("manager"), rs.getString("passwd"), rs.getString("email"), rs.getString("address"),
				rs.getString("city"), rs.getString("country"));
	}

}
