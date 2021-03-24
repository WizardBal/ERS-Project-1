package com.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;

public class ManagerController {
	static Logger logger = Logger.getLogger("log4j.properties");
	// static FileAppender fileAppender = new FileAppender();

	public static String home(HttpServletRequest req) {
		int userId = (int) req.getSession().getAttribute("loggedUserId");
		logger.info("manageId=" + userId + " has logged in");
		return "resources/html/manager.html";
	}

	public static String approve(HttpServletRequest req) {
		if (req.getParameter("approveId").isEmpty()) {
			return "resources/html/manager.html";
		} else if (req.getSession(false) != null) {
			ReimbursementDao rdao = new ReimbursementDao();
			int userId = (int) req.getSession().getAttribute("loggedUserId");
			int reimbursementId = Integer.parseInt(req.getParameter("approveId"));
			rdao.approve(userId, reimbursementId);
			logger.info("manageId=" + userId + " approved ticketId=" + reimbursementId);
			return "resources/html/manager.html";
		} else {
			return "resources/html/index.html";
		}
	}

	public static String deny(HttpServletRequest req) {
		if (req.getParameter("denyId").isEmpty()) {
			return "resources/html/manager.html";
		}
		else if (req.getSession(false) != null) {
			ReimbursementDao rdao = new ReimbursementDao();
			int userId = (int) req.getSession().getAttribute("loggedUserId");
			int reimbursementId = Integer.parseInt(req.getParameter("denyId"));
			rdao.deny(userId, reimbursementId);
			logger.info("manageId=" + userId + " denied ticketId=" + reimbursementId);
			return "resources/html/manager.html";
		} else {
			return "resources/html/index.html";
		}
	}
}