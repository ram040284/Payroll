package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.payroll.employee.attendance.business.EmployeeAttendanceService;
import com.payroll.employee.attendance.dataobjects.EmployeeAttendance;

@Controller
public class EmployeeAttendanceController {
	
//	@Autowired
//	private EmployeeAttendanceService employeeAttendanceService;
	
	@RequestMapping(value= "/viewEmployeeAttendance", method= RequestMethod.GET)
	public String employeeAttendance() {
		return "listEmpAttendance";
	}
	
	@RequestMapping(value="/listEmpAttendance", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeAttendance> getEmployeeAttendance(){
	   return new EmployeeAttendanceService().getEmployeeAttendance();
    }
	
	@RequestMapping(value= "/addEmployeeAttendance", method= RequestMethod.POST)
	public String addEmployeeAttendance(@RequestPart(value = "file") MultipartFile multipartFile) {
		new EmployeeAttendanceService().addEmployeeAttendance(multipartFile);
		return "redirect:/viewEmployeeAttendance";
	}
	
	@RequestMapping(value= "/processEmployeeAttendance", method= RequestMethod.GET)
	public String processEmployeeAttendance() {
		return "listProcessAttendance";
	}
	
//	@RequestMapping(value= "/processEmployeeAttendance", method= RequestMethod.POST)
//	public String updateEmployeeAttendance(@RequestParam int[] attendanceToProcess, @RequestParam String absenceReason) {
//		System.out.println(attendanceToProcess + " " + absenceReason);
//		new EmployeeAttendanceService().updateEmployeeAttendance(attendanceToProcess, absenceReason);
//		return "redirect:/listProcessAttendance";
//	}
	
	@RequestMapping(value= "/processEmployeeAttendance", method= RequestMethod.POST)
	public String updateEmployeeAttendance(EmployeeAttendance employeeAttendance) {
		System.out.println(employeeAttendance.getSrNoArray() + " :: " + employeeAttendance.getAbsenceReason());
//		System.out.println(attendanceToProcess + " " + absenceReason);
		new EmployeeAttendanceService().updateEmployeeAttendance(employeeAttendance.getSrNoArray(), employeeAttendance.getAbsenceReason());
		return "redirect:/viewProcessAttendance";
	}
	
	@RequestMapping(value="/listProcessAttendance", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmployeeAttendance> getAttendance(){
	   return new EmployeeAttendanceService().getAttendance();
    }
	
	@RequestMapping(value= "/viewProcessAttendance", method= RequestMethod.GET)
	public String viewProcessAttendance() {
		return "listProcessAttendance";
	}

}
