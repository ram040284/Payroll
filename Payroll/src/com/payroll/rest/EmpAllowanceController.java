package com.payroll.rest;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.allowance.business.EmpAllowanceService;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
@Controller
public class EmpAllowanceController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listEmpAlwnce", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpAllowanceVO> listEmpAlwnce(){
		System.out.println("listEmpLic-- getEmpLicList");
	   List<EmpAllowanceVO> empAllowances = new EmpAllowanceService().getEmpAllowanceList();
	   System.out.println("Allowanes List: "+ empAllowances.size());
	   return empAllowances;
    }
	
	@RequestMapping(value = "/viewEmpAlwnce", method = RequestMethod.GET)
	public String viewEmpAlwnce(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeFixedAllowance";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmpAlwnce";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee's fixed allowances. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeFixedAllowance";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmpAlwnce -- empAllowance:"+empAllowance);
			List<Department> deptList = new DepartmentService().getDepartments();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		if(!empAllowance.getEmployeeId().equals("0"))
				empAllowance = new EmpAllowanceService().getEmpAllowanceById(empAllowance.getEmployeeId());
			model = new ModelAndView("empAllowance", "command", empAllowance);
			model.addObject("empAllowance", empAllowance);
			model.addObject("departments", depJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee's fixed allowance. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
	}
	   
	@RequestMapping(value="/addEmpAllowance",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpAllowance(@RequestBody EmpAllowance empAllowance, HttpServletRequest request){
		
		permissionForThis = "addEmployeeFixedAllowance";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addEmpAllowance -- EmpAllowance:"+empAllowance);
			   String result = new EmpAllowanceService().addUpdateEmpAllowance(empAllowance);
			   System.out.println("Result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee's fixed allowances. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/deleteEmpAllowance",method=RequestMethod.POST)
	public String deleteEmpAllowance(EmpAllowanceVO empAllowance, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeFixedAllowance";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteEmpAllowance -- EmpAllowanceVO:"+empAllowance.getEmployeeId());
			   String result = new EmpAllowanceService().deleteEmpAllowance(empAllowance.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listEmpAlwnce";
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee's fixed allowances. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/addEmployeeAllowances", method= RequestMethod.POST)
	public String addEmployeeAllowances(@RequestPart(value = "file") MultipartFile multipartFile) throws ParseException {
		System.out.println("Inside addEmployeeAllowances controller ***");
		new EmpAllowanceService().addEmployeeAllowances(multipartFile);
		System.out.println("After addEmployeeAllowances controller ***");
		return "redirect:/viewEmpAlwnce";
		//return "viewEmpAlwnce";
	}
}
