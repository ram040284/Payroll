package com.payroll.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {
	
	 @RequestMapping(value = "/dashboard", method = RequestMethod.GET)public 
	   String printHello(ModelMap model) {
		     return "dashboard";
	   }
	 @RequestMapping(value = "/mastersMenu", method = RequestMethod.GET)
	 public String mastersDashboard(ModelMap model) {
		 return "mastersMenu";
	 }
	 
	 @RequestMapping(value = "/employeeMenu", method = RequestMethod.GET)
	 public String employeeDashboard(ModelMap model) {
		 return "employeeMenu";
	 }
	 
	 @RequestMapping(value = "/leaveMenu", method = RequestMethod.GET)
	 public String leaveDashboard(ModelMap model) {
		 return "leaveMenu";
	 }
	 
	 @RequestMapping(value = "/payrollMenu", method = RequestMethod.GET)
	 public String payrollDashboard(ModelMap model) {
		 return "payrollMenu";
	 }
	 
	 @RequestMapping(value = "/reportsMenu", method = RequestMethod.GET)
	 public String reportsDashboard(ModelMap model) {
		 return "reportsMenu";
	 }

}
