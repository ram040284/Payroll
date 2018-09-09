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

import com.payroll.bank.business.BankDetailsService;
import com.payroll.bank.dataobjects.BankDetails;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.designation.business.DesignationService;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.bank.business.BankService;
import com.payroll.employee.bank.dataobjects.EmpBank;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.leave.business.LeaveService;
import com.payroll.employee.leave.vo.LeaveVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpBankController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listBank", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<BankVO> getEmpBanks(){
		System.out.println("listBank-- getEmpBanks");
	   List<BankVO> empBanks = new BankService().getBankList();
	   return empBanks;
    }
	
	@RequestMapping(value = "/viewBank", method = RequestMethod.GET)
	public String viewBank(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeBank";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listBank";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee bank details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	@RequestMapping(value = "/inputBank", method = RequestMethod.POST)
	public ModelAndView inputBank(EmpBank bank, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeBank";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputBank -- bank:"+bank);
			//List<Designation> desigList = new DesignationService().getDesignationList();
			List<Department> deptList = new DepartmentService().getDepartments();
			List<BankDetails> banksList = new BankDetailsService().getBankDetails(); 
			String desigJSON = "";
			String depJSON = "";
			String banksJSON= "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				//desigJSON = mapper.writeValueAsString(desigList);
				banksJSON = mapper.writeValueAsString(banksList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			BankVO bankVO = null; 
			bankVO = (bank.getEmployeeId() !=0) ? new BankService().getBankByEmpId(bank.getEmployeeId()): new BankVO();
			model = new ModelAndView("bank", "command", bankVO);
			model.addObject("bank", bankVO);
			model.addObject("departments", depJSON);
			//model.addObject("designations", desigJSON);
			model.addObject("banks", banksJSON);
			return model;
		} else {
		   model = new ModelAndView("unauthorized", "message", "You do not have access to add employee bank. Please click home button to go back.");
		   model.addObject("unauthorizedMessage", true);
		   return model;
		}
		
	}
	   
	@RequestMapping(value="/addBank",method=RequestMethod.POST)
	public @ResponseBody
	String addBank(@RequestBody EmpBank bank, HttpServletRequest request){
		
		permissionForThis = "addEmployeeBank";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("addBank -- Bank:"+bank);
			String result = new BankService().addUpdateBank(bank);
			System.out.println("Result:"+result);
			return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee bank. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/deleteBank",method=RequestMethod.POST)
	public String deleteBank(EmpBank bank, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeBank";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deleteBank -- Bank:"+bank.getEmployeeId());
		    String result = new BankService().deleteEmpBank(bank.getEmployeeId());
		    System.out.println("result:"+result);
		    return "listBank";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee bank. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
}
