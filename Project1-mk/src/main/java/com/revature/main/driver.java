package com.revature.main;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class driver {
	
	static Logger logger = Logger.getLogger("log4j.properties");
	
	public static void main(String[] args) {
		ReimbursementDao rdao = new ReimbursementDao();
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		//reimList = rdao.viewAllReim();
		//System.out.println(reimList);
		reimList = rdao.userReimList(1);
		System.out.println(reimList);
		rdao.approve(3, 1);
		rdao.deny(3, 1);
		//rdao.createNewReimRequest(7, 5623.54, 1, 3, 4);
	}
}
