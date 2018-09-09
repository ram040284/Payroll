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
import com.payroll.employee.deductions.business.EmpVarDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpVarDeductionsController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listVarEmpDeductions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpVarDeductions> getEmpVarDeductions(){
		System.out.println("listVarEmpDeductions-- getEmpDeductDtlsList");
	   List<EmpVarDeductions> empdeductionsList = new EmpVarDeductionsService().getEmpVarDeductions();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpVarDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeVarDeductions";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("viewEmpVarDeductions-- viewEmpDeductions");
			return "listVarEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee's variable deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	
	@RequestMapping(value = "/inputEmpVarDeductions", method = RequestMethod.GET)
	public void getEmpVarDeduction() {
		System.out.println("@RequestMapping(value = /inputEmpVarDeductions, method = RequestMethod.GET)");
	
	}
//	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
//	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance) {
	
	@RequestMapping(value = "/inputEmpVarDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpVarDeductions(EmpVarDeductions empVarDeductions, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeVarDeductions";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmpDeductions -- empDeductions:"+empVarDeductions.getEmployeeId());
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(empVarDeductions.getEmployeeId() != 0)
				empVarDeductions = new EmpVarDeductionsService().getEmpDeductionsById(empVarDeductions.getEmployeeId());
			System.out.println("empVarDeductions Employee id : "+ empVarDeductions.getEmployeeId() + "empVarDeductions Department : " + empVarDeductions.getDepartmentId());
			model = new ModelAndView("empVarDeductions", "command", empVarDeductions);
			model.addObject("empDeductions", empVarDeductions);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee's variable deductions. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
	}
	   
	@RequestMapping(value="/addEmpVarDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductDtls(@RequestBody EmpVarDeductions empVarDeductions, HttpServletRequest request){
		
		permissionForThis = "addEmployeeVarDeductions";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addEmpDeductDtls -- empDeductions:"+empVarDeductions);
			   String result = new EmpVarDeductionsService().addUpdateEmpDeductions(empVarDeductions);
			   System.out.println("result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee's variable deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value="/deleteEmpVarDeductions",method=RequestMethod.POST)
	public String deleteEmpDeductDtls(EmpVarDeductions empDeductions, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeVarDeductions";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteEmpDeductDtls -- empDeductions:"+empDeductions.getEmployeeId());
			   String result = new EmpVarDeductionsService().deleteEmpDeductions(empDeductions.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listEmpDeductions";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee's variable deductions. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
}
