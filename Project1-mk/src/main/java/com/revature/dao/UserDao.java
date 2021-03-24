package com.revature.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.revature.models.User;
import com.revature.models.User.userRole;
import com.revature.utils.ConnectionUtil;

public class UserDao {
	
	public User getUser(String username, String pass) {
		User u1 = new User();
		String str = "";
		Statement s = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT * FROM ers_users inner join ers_user_roles eur on ers_username = '"+username+"' "
					+ "and ers_password = '"+pass+"'"
							+ "and eur.ers_user_role_id = ers_users.user_role_id;";
			s = ConnectionUtil.getConnectionUtil().getConnection().createStatement();
			rs = s.executeQuery(sql);
			while(rs.next()) {
				u1.setId(rs.getInt(1));
				u1.setUsername(rs.getString(2));
				u1.setPassword(rs.getString(3));
				u1.setFirstName(rs.getString(4));
				u1.setLastName(rs.getString(5));
				u1.setEmail(rs.getString(6));
				str = rs.getString(9);
				if(str == null) {}
				else if(str.equals("MANAGER")) {
					u1.setUserRole(userRole.MANAGER);
				}
				else {
					u1.setUserRole(userRole.EMPLOYEE);
				}
			}
		}catch(SQLException e) {
			//e.printStackTrace();
		}finally {
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
		//System.out.println(u1);
		return u1;
	}
}
