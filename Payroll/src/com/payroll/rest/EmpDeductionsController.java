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
	
	@RequestMapping(value="/listEmpDeductions", method = RequestMethod.POST)
	public ModelAndView  getEmpDeductionsList(com.payroll.employee.Employee employee, HttpServletRequest request) {
		
		permissionForThis = "listEmpDeductions";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			  try {
				  depJSON = mapper.writeValueAsString(deptList);
			  }catch (Exception e) {
				  e.printStackTrace();
			  }
			  List<EmpDeductionsVO> empDeductions = null;
			  if(employee.getDepartmentId() !=0 || !Utils.isEmpty(employee.getFirstName())){
				  empDeductions = new EmpDeductionsService().getEmpDeductionsList(employee.getDepartmentId(), employee.getHeadId(), employee.getFirstName());
			  }else {
				  empDeductions = new EmpDeductionsService().getAllExemptions();
			  }
			  
			  model = new ModelAndView("listEmpDeductions", "command", empDeductions);
			  model.addObject("empDeductions", empDeductions);
			  model.addObject("departments", depJSON);
			  return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee's deductions. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
    }
	
	private ModelAndView empDeductionList(Employee employeeData) {
		ObjectMapper mapper = new ObjectMapper();
		   List<Department> deptList = new DepartmentService().getDepartments();
		   String depJSON = "";
		   try {
			   depJSON = mapper.writeValueAsString(deptList);
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
		   List<EmpDeductionsVO> empDeductions = null;
		   
		   if(employeeData.getDepartmentId() !=0 || !Utils.isEmpty(employeeData.getFirstName())){
			   System.out.println("employee.getDepartmentId() " + employeeData.getDepartmentId());
			   System.out.println("employee.getFirstName() " + employeeData.getFirstName());
			   empDeductions = new EmpDeductionsService().getEmpDeductionsList(employeeData.getDepartmentId(), employeeData.getHeadId(), employeeData.getFirstName());
		   }
		   
		   ModelAndView model = new ModelAndView("listEmpDeductions", "command", empDeductions);
		   model.addObject("empDeductions", empDeductions);
		   model.addObject("departments", depJSON);
		   return model;
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
