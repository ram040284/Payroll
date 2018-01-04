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
import com.payroll.employee.deductions.business.EmpDeductionsService;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.employee.qtr.business.EmpQuartersService;

@Controller
public class EmpDeductionsController {
	
	@RequestMapping(value="/listEmpDeductions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpDeductionsVO> getEmpDeductionsList(){
		System.out.println("listEmpDeductions-- getEmpDeductionsList");
	   List<EmpDeductionsVO> empdeductionsList = new EmpDeductionsService().getEmpDeductionsList();
	   return empdeductionsList;
    }
	
	@RequestMapping(value = "/viewEmpDeductions", method = RequestMethod.GET)
	public String viewEmpDeductions(ModelMap model) {
		return "listEmpDeductions";
	}
	
	@RequestMapping(value = "/inputEmpDeductions", method = RequestMethod.POST)
	public ModelAndView inputEmpDeductions(EmpDeductionsVO empDeductions) {
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
			empDeductions = new EmpDeductionsService().getEmpDeductionsById(empDeductions.getEmployeeId());
		ModelAndView model = new ModelAndView("empDeductions", "command", empDeductions);
		model.addObject("empDeductions", empDeductions);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmpDeductions",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpDeductions(@RequestBody EmpDeductions empDeductions){
	   System.out.println("addEmpDeductions -- empDeductions:"+empDeductions);
	   String result = new EmpDeductionsService().addUpdateEmpDeductions(empDeductions);
	   System.out.println("result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteEmpDeductions",method=RequestMethod.POST)
	public String deleteQtr(EmpDeductions empDeductions){
	   System.out.println("deleteEmpDeductions -- empDeductions:"+empDeductions.getEmployeeId());
	   String result = new EmpQuartersService().deleteEmpQtr(empDeductions.getEmployeeId());
	   System.out.println("Result:"+result);
	   return "listEmpDeductions";
	}


}
