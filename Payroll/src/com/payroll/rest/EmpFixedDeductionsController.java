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

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.deductions.business.EmpFixedDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpFixedDeductions;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpFixedDeductionsController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listFixedEmpDeductions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpFixedDeductions> getEmpFixedDeductions(){
		System.out.println("listFixedEmpDeductions-- getEmpDeductDtlsList");
		//new PayrollEngine().processPayroll(199508018);
	   List<EmpFixedDeductions> empdeductionsList = new EmpFixedDeductionsService().getEmpFixedDeductions();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpFixedDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeFixedDeductions";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("viewEmpFixedDeductions-- viewEmpDeductions");
			return "listFixedEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee's fixed deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	
	@RequestMapping(value = "/inputEmpFixedDeductions", method = RequestMethod.GET)
	public void getEmpFixedDeduction() {
		System.out.println("@RequestMapping(value = /inputEmpFixedDeductions, method = RequestMethod.GET)");
	
	}
//	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
//	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance) {
	
	@RequestMapping(value = "/inputEmpFixedDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpFixedDeductions(EmpFixedDeductions empFixedDeductions, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeFixedDeductions";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmpDeductions -- empDeductions:"+empFixedDeductions.getEmployeeId());
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(empFixedDeductions.getEmployeeId() != "0")
				empFixedDeductions = new EmpFixedDeductionsService().getEmpDeductionsById(empFixedDeductions.getEmployeeId());
			System.out.println("empFixedDeductions Employee id : "+ empFixedDeductions.getEmployeeId() + "empFixedDeductions Department : " + empFixedDeductions.getDepartmentId());
			model = new ModelAndView("empFixedDeductions", "command", empFixedDeductions);
			model.addObject("empDeductions", empFixedDeductions);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee's fixed deductions. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
	}
	   
	@RequestMapping(value="/addEmpFixedDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductDtls(@RequestBody EmpFixedDeductions empFixedDeductions, HttpServletRequest request){
		
		permissionForThis = "addEmployeeFixedDeductions";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addEmpDeductDtls -- empDeductions:"+empFixedDeductions);
			   String result = new EmpFixedDeductionsService().addUpdateEmpDeductions(empFixedDeductions);
			   System.out.println("result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee's fixed deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/deleteEmpFixedDeductions",method=RequestMethod.POST)
	public String deleteEmpDeductDtls(EmpFixedDeductions empDeductions, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeFixedDeductions";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteEmpDeductDtls -- empDeductions:"+empDeductions.getEmployeeId());
			   String result = new EmpFixedDeductionsService().deleteEmpDeductions(empDeductions.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listFixedEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee's fixed deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}
}
