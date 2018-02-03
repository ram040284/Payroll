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
import com.payroll.designation.business.DesignationService;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.overtime.business.OvertimeService;
import com.payroll.overtime.vo.OvertimeVO;

@Controller
public class OvertimeController {
	
	@RequestMapping(value="/listOvertimes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<OvertimeVO> getOvertimes(){
		System.out.println("listOvertimes-- getOvertimes");
	   List<OvertimeVO> overtimes = new OvertimeService().getOvertimeList();
	   return overtimes;
    }
	
	@RequestMapping(value = "/viewOvertime", method = RequestMethod.GET)
	public String viewOvertime(ModelMap model) {
		return "listOvertimes";
	}
	
	@RequestMapping(value = "/inputOvertime", method = RequestMethod.POST)
	public ModelAndView inputOvertime(OvertimeVO overtime) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("inputOvertime -- overtime:"+overtime);
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*if(overtime.getOvertimeDate() !=null)
			overtime.setOvertimeDate(null);*/
		if(overtime.getOvertimeId()!=0)
			overtime = new OvertimeService().getOvertimeById(overtime.getOvertimeId());
		ModelAndView model = new ModelAndView("overtime", "command", overtime);
		model.addObject("overtime", overtime);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addOvertime",method=RequestMethod.POST)
	public @ResponseBody
	String addOvertime(@RequestBody OvertimeVO overtime){
	   System.out.println("addOvertime -- overtime:"+overtime);
	   String result = new OvertimeService().addUpdateOvertime(overtime);
	   System.out.println("Add Overtime - Result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/loadEmployees",method=RequestMethod.POST, produces = "application/json")
	public @ResponseBody List<EmployeeVO> loadEmployees(@RequestBody OvertimeVO overtime){
	   System.out.println("overtime:"+overtime);
	   List<EmployeeVO> employees = new EmployeeService().getEmployees(overtime.getDesignationId());
	   return employees;
	}
	
	@RequestMapping(value="/deleteOvertime",method=RequestMethod.POST)
	public String deleteOvertime(OvertimeVO overtime){
	   System.out.println("deleteOvertime -- overtime:"+overtime);
	   if(new OvertimeService().deleteOvertime(overtime.getOvertimeId()))
		   System.out.println("Successfully deleted Overtime!!");
	   else
		   System.out.println("Failed to deleted Overtime!!");
	   return "listOvertimes";
	}
}
