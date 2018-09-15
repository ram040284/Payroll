package com.payroll.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.employee.servicebill.business.EmpServiceBillService;
import com.payroll.employee.servicebill.dataobject.EmpServiceBill;
import com.payroll.employee.servicebill.dataobject.EmpServiceBillDao;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmployeeServiceBookController {

	String permissionForThis = null;

	@RequestMapping(value = "/generateEmpBook", method = RequestMethod.POST)
	public ModelAndView generateEmpBook(EmpServiceBill empServiceBill, HttpServletRequest request) {

		permissionForThis = "generateEmpBook";
		ModelAndView model = null;
		System.out.println("employee id is :"+empServiceBill.getEmployeeId());
		User loggedInUser = (User) request.getSession().getAttribute("user");

		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId())
				.contains(permissionForThis)) {
			
			if(!empServiceBill.getEmployeeId().equalsIgnoreCase("0")){
				empServiceBill = new EmpServiceBillDao().getEmployeeServiceBill(empServiceBill.getEmployeeId());
			}
			
			model = new ModelAndView("empServiceBill", "command", empServiceBill);
			model.addObject(empServiceBill);
			return model;

		} else {
			model = new ModelAndView("unauthorized", "message",
					"You do not have access to add employee. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
	}
}
