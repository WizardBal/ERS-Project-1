package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;

import com.controller.ManagerController;
import com.controller.EmployeeController;
import com.controller.LoginController;

public class RequestServlet {
	
	 public static String process(HttpServletRequest req) {
		 System.out.println(req.getRequestURI());
		 switch(req.getRequestURI()) {
		 case "/Project1-mk/login.change":
			 System.out.println("In login.change rhelper");
			 return LoginController.login(req);
		 case "/Project1-mk/logout.change":
			 System.out.println("In logout.change rhelper");
			 return LoginController.logout(req);
		 case "/Project1-mk/manager.change":
			 System.out.println("In manager.change rhelper");
			 return ManagerController.home(req);
		 case "/Project1-mk/employee.change":
			 System.out.println("In employee.change rhelper");
			 return EmployeeController.home(req);
		 case "/Project1-mk/approve.change":
			 System.out.println("In approve.change rhelper");
			 return ManagerController.approve(req);
		 case "/Project1-mk/deny.change":
			 System.out.println("In deny.change rhelper");
			 return ManagerController.deny(req);
		 case "/Project1-mk/createReim.change":
			 System.out.println("In createReim.change rhelper");
			 return EmployeeController.createReim(req);
		default:
			System.out.println("In default");
			return "resources/html/unsuccessfullogin.html";
		 }
	 }
}