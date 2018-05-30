package com.payroll.rest;

import java.util.List;

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
import com.payroll.hrms.payroll.dataobjects.PayrollEngine;

@Controller
public class EmpFixedDeductionsController {
	
	@RequestMapping(value="/listFixedEmpDeductions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpFixedDeductions> getEmpFixedDeductions(){
		System.out.println("listFixedEmpDeductions-- getEmpDeductDtlsList");
		//new PayrollEngine().processPayroll(199508018);
	   List<EmpFixedDeductions> empdeductionsList = new EmpFixedDeductionsService().getEmpFixedDeductions();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpFixedDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model) {
		System.out.println("viewEmpFixedDeductions-- viewEmpDeductions");
		return "listFixedEmpDeductions";
	}
	
	
	@RequestMapping(value = "/inputEmpFixedDeductions", method = RequestMethod.GET)
	public void getEmpFixedDeduction() {
		System.out.println("@RequestMapping(value = /inputEmpFixedDeductions, method = RequestMethod.GET)");
	
	}
//	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
//	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance) {
	
	@RequestMapping(value = "/inputEmpFixedDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpFixedDeductions(EmpFixedDeductions empFixedDeductions) {
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
		if(empFixedDeductions.getEmployeeId() != 0)
			empFixedDeductions = new EmpFixedDeductionsService().getEmpDeductionsById(empFixedDeductions.getEmployeeId());
		System.out.println("empFixedDeductions Employee id : "+ empFixedDeductions.getEmployeeId() + "empFixedDeductions Department : " + empFixedDeductions.getDepartmentId());
		ModelAndView model = new ModelAndView("empFixedDeductions", "command", empFixedDeductions);
		model.addObject("empDeductions", empFixedDeductions);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmpFixedDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductDtls(@RequestBody EmpFixedDeductions empFixedDeductions){
	   System.out.println("addEmpDeductDtls -- empDeductions:"+empFixedDeductions);
	   String result = new EmpFixedDeductionsService().addUpdateEmpDeductions(empFixedDeductions);
	   System.out.println("result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteEmpFixedDeductions",method=RequestMethod.POST)
	public String deleteEmpDeductDtls(EmpFixedDeductions empDeductions){
	   System.out.println("deleteEmpDeductDtls -- empDeductions:"+empDeductions.getEmployeeId());
	   String result = new EmpFixedDeductionsService().deleteEmpDeductions(empDeductions.getEmployeeId());
	   System.out.println("Result:"+result);
	   return "listEmpDeductions";
	}
}
