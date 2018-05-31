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

import com.payroll.advance.business.EmployeeAdvanceService;
import com.payroll.advance.dataobjects.EmployeeAdvance;
import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.overtime.business.OvertimeService;
import com.payroll.overtime.vo.OvertimeVO;

@Controller
public class EmployeeAdvanceController {
	
	@RequestMapping(value="/listAdvances", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeAdvance> getEmployeeAdvances(){
		System.out.println("listEmployeeAdvances-- getEmployeeAdvances");
	   List<EmployeeAdvance> listEmployeeAdvances = new EmployeeAdvanceService().getEmployeeAdvanceList();
	   return listEmployeeAdvances;
    }
	
	@RequestMapping(value = "/viewAdvance", method = RequestMethod.GET)
	public String viewOvertime(ModelMap model) {
		return "listAdvance";
	}
	
	@RequestMapping(value = "/inputAdvance", method = RequestMethod.POST)
	public ModelAndView inputEmployeeAdvance(EmployeeAdvance employeeAdvance) {
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
		ModelAndView model = new ModelAndView("advance", "command", employeeAdvance);
		model.addObject("advance", employeeAdvance);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmployeeAdvance",method=RequestMethod.POST)
	public @ResponseBody
	String addEmployeeAdvance(@RequestBody EmployeeAdvance employeeAdvance){
	   System.out.println("addEmployeeAdvance -- advance:"+employeeAdvance);
	   String result = new EmployeeAdvanceService().addUpdateAdvance(employeeAdvance);
	   System.out.println("Add addEmployeeAdvance - Result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/loadAdvEmployees",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<EmployeeVO> loadEmployees(@RequestBody OvertimeVO overtime){
	   System.out.println("overtime:"+overtime);
	   List<EmployeeVO> employees = new EmployeeService().getEmployees(overtime.getDesignationId());
	   return employees;
	}
	
	@RequestMapping(value="/deleteAdvance",method=RequestMethod.POST)
	public String deleteEmployeeAdvance(EmployeeAdvance employeeAdvance){
	   System.out.println("employeeAdvance -- advance:"+employeeAdvance);
	   if(new EmployeeAdvanceService().deleteEmployeeAdvance(employeeAdvance.getAdvanceId()))
		   System.out.println("Successfully deleted Advance!!");
	   else
		   System.out.println("Failed to deleted Advance!!");
	   return "listAdvance";
	}
}
