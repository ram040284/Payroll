package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.Utils;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.Employee;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.deductions.business.EmpDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.employee.vo.EmployeeVO;

@Controller
public class EmpDeductionsController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listEmpDeductions", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<EmpDeductionsVO> getEmpDeductionsList(){
		System.out.println("listEmpDeductions-- getEmpDeductionsList");
	   List<EmpDeductionsVO> empdeductionsList = new EmpDeductionsService().getEmpDeductionsList();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeDeduction";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee's deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/inputEmpDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpDeductions(EmpDeductions empDeductions, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeDeduction";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmpDeductions -- empDeductions:"+empDeductions.getEmployeeId());
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(!empDeductions.getEmployeeId().equalsIgnoreCase("0")) {
			System.out.println("employee is " + empDeductions.getEmployeeId());
				empDeductions = new EmpDeductionsService().getEmpDeductionsById(empDeductions.getEmployeeId());
		}
			
			model = new ModelAndView("empDeductions", "command", empDeductions);
			model.addObject("empDeductions", empDeductions);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee's deductions. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		
	}
	   
	@RequestMapping(value="/addEmpDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductions(@RequestBody EmpDeductions empDeductions, HttpServletRequest request){
		
		permissionForThis = "addEmployeeDeduction";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addEmpDeductions -- empDeductions:"+empDeductions);
			   String result = new EmpDeductionsService().addUpdateEmpDeductions(empDeductions);
			   System.out.println("result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee's deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/deleteEmpDeductions",method=RequestMethod.POST)
	public String deleteEmpDeductions(EmpDeductions empDeductions, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeDeduction";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteEmpDeductions -- empDeductions:"+empDeductions.getEmployeeId());
			   String result = new EmpDeductionsService().deleteEmpDeductions(empDeductions.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee's deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}

}
