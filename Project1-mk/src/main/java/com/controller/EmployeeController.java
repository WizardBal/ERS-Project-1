package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;

public class EmployeeController {
	
	static Logger logger = Logger.getLogger("log4j.properties");
	// static FileAppender fileAppender = new FileAppender();
	
	public static String home(HttpServletRequest req) {
		int userId = (int) req.getSession().getAttribute("loggedUserId");
		logger.info("employeeId=" + userId + " has logged in");
		return "resources/html/employee.html";
	}

	public static String createReim(HttpServletRequest req) {
		if(req.getParameter("amount").isEmpty() || req.getParameter("typeId").isEmpty() ) {
			return "resources/html/employee.html";
		}
		else if(req.getSession(false)!=null){
			ReimbursementDao rdao = new ReimbursementDao();
			double reimAmount = Double.parseDouble(req.getParameter("amount"));
			//int reimAuthor = Integer.parseInt(req.getParameter("authorId"));
			//int reimStatus = Integer.parseInt(req.getParameter("statusId"));
			String reimType = req.getParameter("typeId");
			int userId = (int) req.getSession().getAttribute("loggedUserId");
			String reimb_description = req.getParameter("reimb_description");
			int reimTypeId;
			reimType = reimType.toUpperCase();
			if(reimType.equals("WORK")) {
				reimTypeId = 1;
			}
			else if(reimType.equals("TRAVEL")) {
				reimTypeId = 2;
			}
			else if(reimType.equals("FOOD")) {
				reimTypeId = 3;
			}
			else{
				reimTypeId = 4;
			}
			int reimId = rdao.maxReimbursementId() + 1;
			logger.info("employeeId=" + userId + " has created a new request of id=" + reimId);
			rdao.createNewReimRequest(reimId, reimAmount, reimb_description, userId, 3, reimTypeId);
			return "resources/html/employee.html";
		}
		else {
			return "resources/html/index.html";
		}
		
	}
}