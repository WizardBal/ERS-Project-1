package com.controller;

import javax.servlet.http.HttpServletRequest;
import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.models.User.userRole;

public class LoginController {
	
	public static String login(HttpServletRequest req) {
		if(!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int userId = 0;
		User u = new User();
		UserDao udao = new UserDao();
		u = udao.getUser(username, password);
		if(u.getId() == null) {
			return "wrongcreds.change";
		}
		userId = u.getId();
		String uRole = "";
		if(u.getUserRole() == userRole.EMPLOYEE) {
			uRole = "EMPLOYEE";
		}else if(u.getUserRole() == userRole.MANAGER) {
			uRole = "MANAGER";
		}else {}
		
		if((u.getPassword() == null && u.getUsername() == null)) {
			return "wrongcreds.change";
		}else if(uRole == "MANAGER"){
			req.getSession().setAttribute("loggedUsername", username);
			req.getSession().setAttribute("loggedpassword", password);
			req.getSession().setAttribute("loggedUserId", userId);
			return "manager.change";
		}else if(uRole == "EMPLOYEE") {
			req.getSession().setAttribute("loggedUsername", username);
			req.getSession().setAttribute("loggedpassword", password);
			req.getSession().setAttribute("loggedUserId", userId);
			return "employee.change";
		}else {
			return "nocreds.change";
		}
	}
	
	public static String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		return "resources/html/index.html";
	}	
}