package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.revature.models.*;
import com.revature.models.Reimbursement.*;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDao {

	// View all reimbursements for all employees -- for manager use
	public List<Reimbursement> viewAllReim() {
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct er.*, ers.reimb_status, ert.reimb_type, eu.user_first_name, eu.user_last_name, eu.user_email from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id inner join ers_users eu on er.reimb_author = eu.ers_users_id order by er.reimb_id ;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement r1 = new Reimbursement();
				r1.setReimId(rs.getInt(1));
				r1.setAmount(rs.getDouble(2));
				r1.setAuthor(rs.getString(12) + rs.getString(13));
				r1.setResolver(rs.getString(7));
				r1.setSubmitted(rs.getTimestamp(3));
				r1.setResolved(rs.getTimestamp(4));
				r1.setDescription(rs.getString(5));
				r1.setUserEmail(rs.getString(14));
				String str = "";
				str = rs.getString(10);
				if (str.equals("APPROVED")) {
					r1.setReimStatus(ReimStatus.APPROVED);
				} else if (str.equals("DENIED")) {
					r1.setReimStatus(ReimStatus.DENIED);
				} else if (str.equals("PENDING")) {
					r1.setReimStatus(ReimStatus.PENDING);
				} else {
				}
				;
				str = rs.getString(11);
				if (str.equals("WORK")) {
					r1.setReimType(ReimType.WORK);
				} else if (str.equals("TRAVEL")) {
					r1.setReimType(ReimType.TRAVEL);
				} else if (str.equals("FOOD")) {
					r1.setReimType(ReimType.FOOD);
				} else if (str.equals("OTHER")) {
					r1.setReimType(ReimType.OTHER);
				} else {
				}
				;
				reimList.add(r1);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return reimList;
	}

	// approve reimbursement -- for manager use
	public void approve(int resolverId, int reimId) {
		PreparedStatement ps = null;
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		try {
			String sql = "update ers_reimbursement set reimb_resolver = ? , " + "reimb_resolved = ? , "
					+ "reimb_status_id = 1 where reimb_id = ?;";
			ps = ConnectionUtil.getConnectionUtil().getConnection().prepareStatement(sql);
			ps.setInt(1, resolverId);
			ps.setTimestamp(2, currentTimestamp);
			ps.setInt(3, reimId);
			ps.execute();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
	}

	// deny reimbursement -- for manager use
	public void deny(int resolverId, int reimId) {
		PreparedStatement ps = null;
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		try {
			String sql = "update ers_reimbursement set reimb_resolver = ? , " + "reimb_resolved = ? , "
					+ "reimb_status_id = 2 where reimb_id = ?;";
			ps = ConnectionUtil.getConnectionUtil().getConnection().prepareStatement(sql);
			ps.setInt(1, resolverId);
			ps.setTimestamp(2, currentTimestamp);
			ps.setInt(3, reimId);
			ps.execute();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
	}

	// filter requests by status -- for manager use --possible front end option

	// View Past tickets -- for employee use
	public List<Reimbursement> userReimList(int userId) {
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct er.*, ers.reimb_status, ert.reimb_type, eu.user_first_name, eu.user_last_name, eu.user_email from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id inner join ers_users eu on er.reimb_author = eu.ers_users_id"
					+ " and er.reimb_author = " + userId + " order by er.reimb_id;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement r1 = new Reimbursement();
				r1.setReimId(rs.getInt(1));
				r1.setAmount(rs.getDouble(2));
				r1.setResolver(String.valueOf(rs.getInt(7)));
				r1.setSubmitted(rs.getTimestamp(3));
				r1.setResolved(rs.getTimestamp(4));
				r1.setDescription(rs.getString(5));
				r1.setUserEmail(rs.getString(14));
				String str = "";
				str = rs.getString(10);
				if (str.equals("APPROVED")) {
					r1.setReimStatus(ReimStatus.APPROVED);
				} else if (str.equals("DENIED")) {
					r1.setReimStatus(ReimStatus.DENIED);
				} else if (str.equals("PENDING")) {
					r1.setReimStatus(ReimStatus.PENDING);
				} else {}
				str = rs.getString(11);
				if (str.equals("WORK")) {
					r1.setReimType(ReimType.WORK);
				} else if (str.equals("TRAVEL")) {
					r1.setReimType(ReimType.TRAVEL);
				} else if (str.equals("FOOD")) {
					r1.setReimType(ReimType.FOOD);
				} else if (str.equals("OTHER")) {
					r1.setReimType(ReimType.OTHER);
				} else {}
				r1.setAuthor(rs.getString(12) + " " + rs.getString(13));
				reimList.add(r1);
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return reimList;
	}

	// Add reimbursement request -- for employee use
	public Reimbursement createNewReimRequest(int id, Double inputAmount, String description, int rAuthor, int rStatus, int rType) {
		PreparedStatement ps = null;
		Reimbursement r = new Reimbursement();
		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		try {
			String sql = "insert into ers_reimbursement(reimb_id, reimb_amount, reimb_submitted,reimb_description, reimb_author, reimb_status_id, reimb_type_id)values(?,?,?,?,?,?,?);";
			ps = ConnectionUtil.getConnectionUtil().getConnection().prepareStatement(sql);
			ps.setInt(1, id); r.setReimId(id);
			ps.setDouble(2, inputAmount); r.setAmount(inputAmount);
			ps.setTimestamp(3, currentTimestamp); r.setSubmitted(currentTimestamp);
			ps.setInt(5, rAuthor); //r.setAuthor(rAuthor);
			ps.setInt(6, rStatus); 
			ps.setString(4, description);
			if (rStatus == 1) {
				r.setReimStatus(ReimStatus.APPROVED);
			} else if (rStatus == 2) {
				r.setReimStatus(ReimStatus.DENIED);
			} else if (rStatus == 3) {
				r.setReimStatus(ReimStatus.PENDING);
			} else {}
			if (rType == 1) {
				r.setReimType(ReimType.WORK);
			} else if (rType == 2) {
				r.setReimType(ReimType.TRAVEL);
			} else if (rType == 3) {
				r.setReimType(ReimType.FOOD);
			} else if (rType == 4) {
				r.setReimType(ReimType.OTHER);
			} else {}
			ps.setInt(7, rType);
			ps.execute();
		} catch (SQLException e) {
			// e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return r;
	}

	public int maxReimbursementId() {
		int maxId = 0;
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select max (er.reimb_id) from ers_reimbursement er;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			rs.next();
			maxId = rs.getInt(1);
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		return maxId;
	}
	
	public List<Reimbursement> viewPendingReim() {
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct er.*, ers.reimb_status, ert.reimb_type, eu.user_first_name, eu.user_last_name, eu.user_email from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id and ers.reimb_status_id = 3 inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id inner join ers_users eu on er.reimb_author = eu.ers_users_id order by er.reimb_id ;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement r1 = new Reimbursement();
				r1.setReimId(rs.getInt(1));
				r1.setAmount(rs.getDouble(2));
				r1.setResolver(rs.getString(7));
				r1.setSubmitted(rs.getTimestamp(3));
				r1.setResolved(rs.getTimestamp(4));
				r1.setDescription(rs.getString(5));
				r1.setAuthor(rs.getString(12) + " " + rs.getString(13));
				r1.setUserEmail(rs.getString(14));
				String str = "";
				str = rs.getString(10);
				if (str.equals("APPROVED")) {
					r1.setReimStatus(ReimStatus.APPROVED);
				} else if (str.equals("DENIED")) {
					r1.setReimStatus(ReimStatus.DENIED);
				} else if (str.equals("PENDING")) {
					r1.setReimStatus(ReimStatus.PENDING);
				} else {
				}
				;
				str = rs.getString(11);
				if (str.equals("WORK")) {
					r1.setReimType(ReimType.WORK);
				} else if (str.equals("TRAVEL")) {
					r1.setReimType(ReimType.TRAVEL);
				} else if (str.equals("FOOD")) {
					r1.setReimType(ReimType.FOOD);
				} else if (str.equals("OTHER")) {
					r1.setReimType(ReimType.OTHER);
				} else {
				}
				;
				reimList.add(r1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reimList;
	}
	
	public List<Reimbursement> viewApprovedReim() {
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct er.*, ers.reimb_status, ert.reimb_type, eu.user_first_name, eu.user_last_name, eu.user_email from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id and ers.reimb_status_id = 1 inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id inner join ers_users eu on er.reimb_author = eu.ers_users_id order by er.reimb_id ;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement r1 = new Reimbursement();
				r1.setReimId(rs.getInt(1));
				r1.setAmount(rs.getDouble(2));
				r1.setResolver(rs.getString(7));
				r1.setSubmitted(rs.getTimestamp(3));
				r1.setResolved(rs.getTimestamp(4));
				r1.setDescription(rs.getString(5));
				r1.setAuthor(rs.getString(12) + " " + rs.getString(13));
				r1.setUserEmail(rs.getString(14));
				String str = "";
				str = rs.getString(10);
				if (str.equals("APPROVED")) {
					r1.setReimStatus(ReimStatus.APPROVED);
				} else if (str.equals("DENIED")) {
					r1.setReimStatus(ReimStatus.DENIED);
				} else if (str.equals("PENDING")) {
					r1.setReimStatus(ReimStatus.PENDING);
				} else {
				}
				;
				str = rs.getString(11);
				if (str.equals("WORK")) {
					r1.setReimType(ReimType.WORK);
				} else if (str.equals("TRAVEL")) {
					r1.setReimType(ReimType.TRAVEL);
				} else if (str.equals("FOOD")) {
					r1.setReimType(ReimType.FOOD);
				} else if (str.equals("OTHER")) {
					r1.setReimType(ReimType.OTHER);
				} else {
				}
				;
				reimList.add(r1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reimList;
	}
	
	public List<Reimbursement> viewDeniedReim() {
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "select distinct er.*, ers.reimb_status, ert.reimb_type, eu.user_first_name, eu.user_last_name, eu.user_email from ers_reimbursement er inner join ers_reimbursement_status ers on er.reimb_status_id = ers.reimb_status_id and ers.reimb_status_id = 2 inner join ers_reimbursement_type ert on er.reimb_type_id = ert.reimb_type_id inner join ers_users eu on er.reimb_author = eu.ers_users_id order by er.reimb_id ;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while (rs.next()) {
				Reimbursement r1 = new Reimbursement();
				r1.setReimId(rs.getInt(1));
				r1.setAmount(rs.getDouble(2));
				r1.setResolver(rs.getString(7));
				r1.setSubmitted(rs.getTimestamp(3));
				r1.setResolved(rs.getTimestamp(4));
				r1.setDescription(rs.getString(5));
				r1.setAuthor(rs.getString(12) + " " + rs.getString(13));
				r1.setUserEmail(rs.getString(14));
				String str = "";
				str = rs.getString(10);
				if (str.equals("APPROVED")) {
					r1.setReimStatus(ReimStatus.APPROVED);
				} else if (str.equals("DENIED")) {
					r1.setReimStatus(ReimStatus.DENIED);
				} else if (str.equals("PENDING")) {
					r1.setReimStatus(ReimStatus.PENDING);
				} else {
				}
				;
				str = rs.getString(11);
				if (str.equals("WORK")) {
					r1.setReimType(ReimType.WORK);
				} else if (str.equals("TRAVEL")) {
					r1.setReimType(ReimType.TRAVEL);
				} else if (str.equals("FOOD")) {
					r1.setReimType(ReimType.FOOD);
				} else if (str.equals("OTHER")) {
					r1.setReimType(ReimType.OTHER);
				} else {
				}
				;
				reimList.add(r1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return reimList;
	}

}
