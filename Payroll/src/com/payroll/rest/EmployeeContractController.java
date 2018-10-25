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

import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.contract.EmployeeContract;
import com.payroll.employee.contract.EmployeeContractService;
import com.payroll.employee.contract.EmployeeContractVO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmployeeContractController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/employeeContractList", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<EmployeeContractVO> getEmployeeContractList(){
		List<EmployeeContractVO> contractVOs = new EmployeeContractService().getEmployeeContract();
		return contractVOs;
	}
	
	@RequestMapping(value="/viewContractEmp", method = RequestMethod.GET)
	public String viewContractualEmp(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewContractEmp";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmployeeContract";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view contractual employee details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/viewEmpContract", method = RequestMethod.POST)
	public ModelAndView addContractualEmp(EmployeeContractVO employeeContract, HttpServletRequest request) {
		
		permissionForThis = "addContractualEmp";
		ModelAndView model = null;
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			
			if(!employeeContract.getEmployeeId().equalsIgnoreCase("0")){
				employeeContract = new EmployeeContractService().getContractualEmpById(employeeContract.getEmployeeId());
			}
			model = new ModelAndView("employeeContract", "command", employeeContract);
			model.addObject(employeeContract);
			
			return model;
		}else {
			   model = new ModelAndView("unauthorized", "message", "You do not have access to add employee bank. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
	}
	
	@RequestMapping(value="/addUpdateContractualEmp",method=RequestMethod.POST)
	public @ResponseBody String addUpdateContractualEmp(@RequestBody EmployeeContractVO employeeContract, HttpServletRequest request){
		permissionForThis = "addUpdateContractualEmp";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			   String result = new EmployeeContractService().addUpdateContractEmp(employeeContract);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add contractual employee details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value="/deleteContractEmp",method=RequestMethod.POST)
	public String deleteContractEmp(EmployeeContractVO	employeeContractVO, HttpServletRequest request) {
		
		permissionForThis = "deleteContractEmp";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			   new EmployeeContractService().deleteContractEmp(employeeContractVO.getEmployeeId());
			   return "listEmployeeContract";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete contractual employee. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value="/loadContractualEmp", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeVO> loadContractualEmp(){
		List<EmployeeVO> employees = new EmployeeService().getAllContactEmployees();
		return employees;
    }
}
