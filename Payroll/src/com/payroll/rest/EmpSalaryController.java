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
import com.payroll.designation.business.DesignationService;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.leave.business.LeaveService;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.vo.LeaveVO;
import com.payroll.employee.salary.business.SalaryService;
import com.payroll.employee.salary.dataobjects.Salary;
import com.payroll.employee.salary.vo.SalaryVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpSalaryController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listSalary", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<SalaryVO> getEmpSalaries(){
		System.out.println("listSalary-- getEmpSalaries");
	   List<SalaryVO> salaries = new SalaryService().getSalaries();
	   return salaries;
    }
	
	@RequestMapping(value = "/viewSalary", method = RequestMethod.GET)
	public String viewSalary(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeSalary";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listSalary";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee salary. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	@RequestMapping(value = "/inputSalary", method = RequestMethod.POST)
	public ModelAndView inputSalary(SalaryVO salary, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeSalary";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputSalary -- salary - empId:"+salary.getEmployeeId());
			//List<Designation> desigList = new DesignationService().getDesignationList();
			List<Department> deptList = new DepartmentService().getDepartments();
			String desigJSON = "";
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!salary.getEmployeeId().equals("0"))
		/*if(salary.getEmployeeId() != "0")*/
				salary = new SalaryService().getEmpSalary(salary.getEmployeeId());
			model = new ModelAndView("salary", "command", salary);
			model.addObject("salary", salary);
			model.addObject("departments", depJSON);
			//model.addObject("designations", desigJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee salary. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
		
	}
	   
	@RequestMapping(value="/addSalary",method=RequestMethod.POST)
	public @ResponseBody
	String addSalary(@RequestBody Salary salary, HttpServletRequest request){
		
		permissionForThis = "addEmployeeSalary";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addSalary -- salary:"+salary);
			   String result = new SalaryService().addUpdateSalary(salary);
			   System.out.println("Add/Update Salary -- result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee salary. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}
	
	@RequestMapping(value="/deleteSalary",method=RequestMethod.POST)
	public String deleteSalary(Salary salary, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeSalary";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteSalary -- salary:"+salary.getEmployeeId());
			   String result = new SalaryService().deleteSalary(salary.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listSalary";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee salary. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}
}
