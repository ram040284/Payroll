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
import com.payroll.employee.deductions.business.EmpDeductionDetailsService;
import com.payroll.employee.deductions.business.EmpDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpDeductionDetails;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.vo.EmpDeductionsDetailsVO;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.employee.qtr.business.EmpQuartersService;

@Controller
public class EmpDeductDetailsController {
	
	@RequestMapping(value="/listEmpDeductDtls", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpDeductionsDetailsVO> getEmpDeductDtlsList(){
		System.out.println("listEmpDeductDtls-- getEmpDeductDtlsList");
	   List<EmpDeductionsDetailsVO> empdeductionsList = new EmpDeductionDetailsService().getEmpDeductionsList();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpDeductDtls", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model) {
		return "listEmpDeductDtls";
	}
	
	@RequestMapping(value = "/inputEmpDeductDtls", method = RequestMethod.POST)
	public ModelAndView inputEmpDeductDtls(EmpDeductionsDetailsVO empDeductions) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("inputEmpDeductions -- empDeductions:"+empDeductions.getEmployeeId());
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
			//desigJSON = mapper.writeValueAsString(desigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(empDeductions.getEmployeeId() != 0)
			empDeductions = new EmpDeductionDetailsService().getEmpDeductionsById(empDeductions.getEmployeeId());
		ModelAndView model = new ModelAndView("empDeductDtls", "command", empDeductions);
		model.addObject("empDeductDtls", empDeductions);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmpDeductDtls",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductDtls(@RequestBody EmpDeductionDetails empDeductions){
	   System.out.println("addEmpDeductDtls -- empDeductions:"+empDeductions);
	   String result = new EmpDeductionDetailsService().addUpdateEmpDeductions(empDeductions);
	   System.out.println("result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteEmpDeductDtls",method=RequestMethod.POST)
	public String deleteEmpDeductDtls(EmpDeductionDetails empDeductions){
	   System.out.println("deleteEmpDeductDtls -- empDeductions:"+empDeductions.getEmployeeId());
	   String result = new EmpDeductionDetailsService().deleteEmpDeductions(empDeductions.getEmployeeId());
	   System.out.println("Result:"+result);
	   return "listEmpDeductions";
	}
}
