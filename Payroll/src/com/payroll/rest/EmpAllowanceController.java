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
import com.payroll.employee.allowance.business.EmpAllowanceService;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.employee.lic.business.EmpLicService;
import com.payroll.employee.lic.vo.EmpLicVO;
@Controller
public class EmpAllowanceController {
	
	@RequestMapping(value="/listEmpAlwnce", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpAllowanceVO> listEmpAlwnce(){
		System.out.println("listEmpLic-- getEmpLicList");
	   List<EmpAllowanceVO> empAllowances = new EmpAllowanceService().getEmpAllowanceList();
	   return empAllowances;
    }
	
	@RequestMapping(value = "/viewEmpAlwnce", method = RequestMethod.GET)
	public String viewEmpAlwnce(ModelMap model) {
		return "listEmpAlwnce";
	}
	
	@RequestMapping(value = "/inputEmpAlwnce", method = RequestMethod.POST)
	public ModelAndView inputEmpAlwnce(EmpAllowanceVO empAllowance) {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println("inputEmpAlwnce -- empAllowance:"+empAllowance);
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(empAllowance.getEmployeeId()!=0)
			empAllowance = new EmpAllowanceService().getEmpAllowanceById(empAllowance.getEmployeeId());
		ModelAndView model = new ModelAndView("empAllowance", "command", empAllowance);
		model.addObject("empAllowance", empAllowance);
		model.addObject("departments", depJSON);
		return model;
	}
	   
	@RequestMapping(value="/addEmpAllowance",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpAllowance(@RequestBody EmpAllowance empAllowance){
	   System.out.println("addEmpAllowance -- EmpAllowance:"+empAllowance);
	   String result = new EmpAllowanceService().addUpdateEmpAllowance(empAllowance);
	   System.out.println("Result:"+result);
	   return result;
	}
	
	@RequestMapping(value="/deleteEmpAllowance",method=RequestMethod.POST)
	public String deleteEmpAllowance(EmpAllowanceVO empAllowance){
	   System.out.println("deleteEmpAllowance -- EmpAllowanceVO:"+empAllowance.getEmployeeId());
	   String result = new EmpAllowanceService().deleteEmpAllowance(empAllowance.getEmployeeId());
	   System.out.println("Result:"+result);
	   return "listEmpAlwnce";
	}


}
