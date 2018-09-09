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

import com.payroll.advance.business.EmployeeAdvanceService;
import com.payroll.advance.dataobjects.EmployeeAdvance;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.overtime.vo.OvertimeVO;

@Controller
public class EmployeeAdvanceController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listAdvances", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeAdvance> getEmployeeAdvances(){
		System.out.println("listEmployeeAdvances-- getEmployeeAdvances");
	   List<EmployeeAdvance> listEmployeeAdvances = new EmployeeAdvanceService().getEmployeeAdvanceList();
	   return listEmployeeAdvances;
    }
	
	@RequestMapping(value = "/viewAdvance", method = RequestMethod.GET)
	public String viewOvertime(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeAdvance";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listAdvance";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee advance. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/inputAdvance", method = RequestMethod.POST)
	public ModelAndView inputEmployeeAdvance(EmployeeAdvance employeeAdvance, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeAdvance";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmployeeAdvance -- employeeAdvance:"+employeeAdvance);
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*if(overtime.getOvertimeDate() !=null)
				overtime.setOvertimeDate(null);*/
			if(employeeAdvance.getAdvanceId()!=0)
				employeeAdvance = new EmployeeAdvanceService().getEmployeeAdvanceById(employeeAdvance.getAdvanceId());
			model = new ModelAndView("advance", "command", employeeAdvance);
			model.addObject("advance", employeeAdvance);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee advance. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
	}
	   
	@RequestMapping(value="/addEmployeeAdvance",method=RequestMethod.POST)
	public @ResponseBody
	String addEmployeeAdvance(@RequestBody EmployeeAdvance employeeAdvance, HttpServletRequest request){
		
		permissionForThis = "addEmployeeAdvance";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addEmployeeAdvance -- advance:"+employeeAdvance);
			   String result = new EmployeeAdvanceService().addUpdateAdvance(employeeAdvance);
			   System.out.println("Add addEmployeeAdvance - Result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee advance. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}

	}
	
	@RequestMapping(value="/loadAdvEmployees",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<EmployeeVO> loadEmployees(@RequestBody OvertimeVO overtime){
	   System.out.println("overtime:"+overtime);
	   List<EmployeeVO> employees = new EmployeeService().getEmployees(overtime.getDesignationId());
	   return employees;
	}
	
	@RequestMapping(value="/deleteAdvance",method=RequestMethod.POST)
	public String deleteEmployeeAdvance(EmployeeAdvance employeeAdvance, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeAdvance";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("employeeAdvance -- advance:"+employeeAdvance);
			   if(new EmployeeAdvanceService().deleteEmployeeAdvance(employeeAdvance.getAdvanceId()))
				   System.out.println("Successfully deleted Advance!!");
			   else
				   System.out.println("Failed to deleted Advance!!");
			   return "listAdvance";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee advance. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
}
