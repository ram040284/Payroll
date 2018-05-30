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

import com.payroll.advance.business.AdvanceService;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.designation.business.DesignationService;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.advances.dataobjects.EmployeeAdvance;
import com.payroll.employee.advances.dataobjects.EmployeeAdvanceDetails;

@Controller
public class AdvanceController {
	
	@RequestMapping(value="/listAdvance", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeAdvance> getAdvances(){
	   List<EmployeeAdvance> employeeAdvanceList = new AdvanceService().getAdvanceList();
	   return employeeAdvanceList;
    }
	
	@RequestMapping(value = "/viewAdvance", method = RequestMethod.GET)
	public String viewAdvances(ModelMap model) {
		return "listAdvance";
	}
	
	@RequestMapping(value = "/inputAdvance", method = RequestMethod.POST)
	public ModelAndView inputAdvance(EmployeeAdvance advance) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("Advance:"+advance);
		List<Designation> desigList = new DesignationService().getDesignationList();
		List<Department> deptList = new DepartmentService().getDepartments();
		String desigJSON = "";
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
			desigJSON = mapper.writeValueAsString(desigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(advance.getAdvanceId() != 0)
			advance = new AdvanceService().getAdvanceById(advance.getAdvanceId());
		
		ModelAndView model = new ModelAndView("advance", "command", advance);
		model.addObject("advance", advance);
		model.addObject("departments", depJSON);
		model.addObject("designations", desigJSON);
		return model;
	}
	   
	@RequestMapping(value="/addAdvance",method=RequestMethod.POST)
	public @ResponseBody
	String addAdvance(@RequestBody EmployeeAdvanceDetails empAdvance){
	   System.out.println("advance:"+empAdvance);
	   String result = new AdvanceService().addUpdateAdvance(empAdvance);
	   System.out.println("result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteAdvance",method=RequestMethod.POST)
	public String deleteAdvance(com.payroll.advance.vo.AdvanceVO advance){
	   System.out.println("advance:"+advance);
	   if(new AdvanceService().deleteAdvance(advance.getAdvanceId()))
		   System.out.println("Successfully deleted Advance!!");
	   else
		   System.out.println("Failed to deleted Advance!!");
	   return "listAdvance";
	}



}
