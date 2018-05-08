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
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.employee.deductions.business.EmpVarDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;

@Controller
public class EmpVarDeductionsController {
	
	@RequestMapping(value="/listVarEmpDeductions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpVarDeductions> getEmpVarDeductions(){
		System.out.println("listVarEmpDeductions-- getEmpDeductDtlsList");
	   List<EmpVarDeductions> empdeductionsList = new EmpVarDeductionsService().getEmpVarDeductions();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpVarDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model) {
		System.out.println("viewEmpVarDeductions-- viewEmpDeductions");
		return "listVarEmpDeductions";
	}
	
	
	@RequestMapping(value = "/inputEmpVarDeductions", method = RequestMethod.GET)
	public void getEmpVarDeduction() {
		System.out.println("@RequestMapping(value = /inputEmpVarDeductions, method = RequestMethod.GET)");
	
	}
//	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
//	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance) {
	
	@RequestMapping(value = "/inputEmpVarDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpVarDeductions(EmpVarDeductions empVarDeductions) {
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
		ModelAndView model = new ModelAndView("empVarDeductions", "command", empVarDeductions);
		model.addObject("empDeductions", empVarDeductions);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmpVarDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductDtls(@RequestBody EmpVarDeductions empVarDeductions){
	   System.out.println("addEmpDeductDtls -- empDeductions:"+empVarDeductions);
	   String result = new EmpVarDeductionsService().addUpdateEmpDeductions(empVarDeductions);
	   System.out.println("result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteEmpVarDeductions",method=RequestMethod.POST)
	public String deleteEmpDeductDtls(EmpVarDeductions empDeductions){
	   System.out.println("deleteEmpDeductDtls -- empDeductions:"+empDeductions.getEmployeeId());
	   String result = new EmpVarDeductionsService().deleteEmpDeductions(empDeductions.getEmployeeId());
	   System.out.println("Result:"+result);
	   return "listEmpDeductions";
	}
}
