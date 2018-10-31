package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.employee.Employee;
import com.payroll.employee.servicebill.dataobject.EmpServiceBill;
import com.payroll.employee.servicebill.dataobject.EmpServiceBillDao;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmployeeServiceBookController {

	String permissionForThis = null;

	@RequestMapping(value = "/generateEmpBook", method = RequestMethod.GET)
	public ModelAndView generateEmpBook(Employee employee, HttpServletRequest request) {

		EmpServiceBill empServiceBill = null;
		permissionForThis = "generateEmpBook";
		ModelAndView model = null;
		//System.out.println("employee id is :"+empServiceBill.getEmployeeId());
		User loggedInUser = (User) request.getSession().getAttribute("user");

		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis)) {
			
			if(!employee.getEmployeeId().equalsIgnoreCase("0")){
				empServiceBill = new EmpServiceBillDao().getEmployeeServiceBill(employee.getEmployeeId()).get(0);
			} 
			
			return new ModelAndView("pdfView", "empServiceBook", empServiceBill);

		} else {
			return new ModelAndView("noActivity", "", empServiceBill);
		}
	}
}
