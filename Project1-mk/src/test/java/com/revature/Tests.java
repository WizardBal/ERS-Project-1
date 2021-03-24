package com.revature;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement.ReimStatus;
import com.revature.models.Reimbursement.ReimType;
import com.revature.models.User;

public class Tests {
	
	ReimbursementDao rDao = new ReimbursementDao();
	UserDao uDao = new UserDao();
	Reimbursement r = new Reimbursement();
	User u = new User();
	Calendar calendar = Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
	List<Reimbursement> reimList = new ArrayList<Reimbursement>();
	
	@Before
	public void setupNewReimbursement() {
		Reimbursement r = new Reimbursement();
		r.setAmount(41.51);
		r.setAuthor("Testuser");
		r.setDescription(null);
		r.setReimId(500);
		r.setReimStatus(ReimStatus.APPROVED);
		r.setReimType(ReimType.TRAVEL);
		r.setResolver("Testmanager");
		r.setResolved(currentTimestamp);
		r.setSubmitted(currentTimestamp);
		rDao.createNewReimRequest(r.getReimId(), r.getAmount(), "test description", 1, 1, 1);
	}

	@Test
	public void testMaxReimbursementId() {
		int max = rDao.maxReimbursementId();
		assertEquals(max, 500);
	}
	
	@Test
	public void testApprove() {
		rDao.approve(3, 1);
		reimList = rDao.userReimList(1);
		r = reimList.get(0);
		assertEquals(ReimStatus.APPROVED, r.getReimStatus());
	}
	
	@Test
	public void testDeny() {
		rDao.deny(3, 1);
		reimList = rDao.userReimList(1);
		r = reimList.get(0);
		assertEquals(ReimStatus.DENIED, r.getReimStatus());
	}
	
	@Test
	public void testGetReimType() {
		reimList = rDao.userReimList(1);
		r = reimList.get(1);
		assertEquals(ReimType.TRAVEL, r.getReimType());
	}
	
	@Test
	public void testviewAllReim() {//This size will change, but this works
		rDao.deny(3, 1);
		reimList = rDao.viewAllReim();
		int size = reimList.size();
		assertEquals(30, size);
	}
	
	@Test
	public void testGetUser() {//This size will change, but this works
		u = uDao.getUser("tad","pass");
		assertEquals("taddy", u.getFirstName());
	}

}
