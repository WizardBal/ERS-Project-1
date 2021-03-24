package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

@SuppressWarnings("serial")
public class ManagerReimbursementServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		this.doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		ReimbursementDao rdao = new ReimbursementDao();
		List<Reimbursement> rList = new ArrayList<Reimbursement>();
		rList = rdao.viewAllReim();
		res.getWriter().write(new ObjectMapper().writeValueAsString(rList));
	}
}
