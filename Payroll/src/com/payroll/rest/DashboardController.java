package com.payroll.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class DashboardController {
	
	String permissionForThis = null; 
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)public 
	   String printHello(ModelMap model) {
		     return "dashboard";
	   }
	 @RequestMapping(value = "/mastersMenu", method = RequestMethod.GET)
	 public String mastersDashboard(ModelMap model, HttpServletRequest request) {
		 
		String permissionForThis = "viewMasterMenu";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "mastersMenu";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view master details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	 }
	 
	 @RequestMapping(value = "/employeeMenu", method = RequestMethod.GET)
	 public String employeeDashboard(ModelMap model, HttpServletRequest request) {
		
		String permissionForThis = "viewEmployeeMenu";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "employeeMenu";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		 
	 }
	 
	 @RequestMapping(value = "/leaveMenu", method = RequestMethod.GET)
	 public String leaveDashboard(ModelMap model, HttpServletRequest request) {
		 
		String permissionForThis = "viewLeaveMenu";
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "leaveMenu";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view leave details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	 }
	 
	 @RequestMapping(value = "/payrollMenu", method = RequestMethod.GET)
	 public String payrollDashboard(ModelMap model, HttpServletRequest request) {
		 
		 String permissionForThis = "viewPayrollMenu";
		 User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "payrollMenu";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view paybill details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		 
	 }
	 
	 @RequestMapping(value = "/reportsMenu", method = RequestMethod.GET)
	 public String reportsDashboard(ModelMap model, HttpServletRequest request) {
		 
		 String permissionForThis = "viewReportsMenu";
		 User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "reportsMenu";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view report details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	 }

}
