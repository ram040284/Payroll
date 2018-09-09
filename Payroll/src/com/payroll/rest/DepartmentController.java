package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class DepartmentController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listDept", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Department> getDepartments(){
	   List<Department> departments = new DepartmentService().getDepartments();
	   return departments;
    }
	
	@RequestMapping(value = "/viewDept", method = RequestMethod.GET)
	public String viewDept(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewDepartment";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listDept";
		} else {
			request.getSession().setAttribute("message", "You do not have access to add department. Please click home button to go back.");
			 request.getSession().setAttribute("unauthorizedMessage", true);
			 return "unauthorized";
		}
		
	}
	
	@RequestMapping(value = "/inputDept", method = RequestMethod.POST)
	public  ModelAndView inputDept(Department department, HttpServletRequest request) {
		
	    permissionForThis = "updateDepartment";
	    ModelAndView model = null;
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("department:"+department);
			if(department.getDepartmentId() != 0)
				department = new DepartmentService().getDeptById(department.getDepartmentId());
			model = new ModelAndView("department", "command", department);
			model.addObject("department", department);
			//RedirectView
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add/update department. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
		}
		
		return model;
	}
	   
	@RequestMapping(value="/addDept",method=RequestMethod.POST)
	public @ResponseBody String addDept(@RequestBody Department department, HttpServletRequest request){
		
		permissionForThis = "addDepartment";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("department:"+department);
			String result = new DepartmentService().addUpdateDepartment(department);
			return result;
		} else {
			 request.getSession().setAttribute("message", "You do not have access to add department. Please click home button to go back.");
			 request.getSession().setAttribute("unauthorizedMessage", true);
			 return "unauthorized";
		}
		
	}
	
	@RequestMapping(value="/deleteDept",method=RequestMethod.POST)
	public String deleteDept(Department department, HttpServletRequest request){
		
		 permissionForThis = "deleteDepartment";
		 User loggedInUser = (User) request.getSession().getAttribute("user");
			
		 if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			 System.out.println("department:"+department);
			 if(new DepartmentService().deleteDepartment(department.getDepartmentId())) {
				 System.out.println("Successfully deleted Department!!");
			 } else {
				 System.out.println("Failed to deleted Department!!");
			 }
			 return "listDept";
			 
		 } else {
			 request.getSession().setAttribute("message", "You do not have access to delete department. Please click home button to go back.");
			 request.getSession().setAttribute("unauthorizedMessage", true);
			 return "unauthorized";
		 }
	}
	
}
