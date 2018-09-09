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
import com.payroll.employee.arrears.business.EmpArrearsService;
import com.payroll.employee.arrears.dataobjects.EmpArrears;
import com.payroll.employee.arrears.vo.EmpArrearsVO;

@Controller
public class EmployeeArrearsPayController {
	
	@RequestMapping(value="/listArrears", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpArrears> getEmpArears(){
		//System.out.println("listArrears-- getArrearsList");
	   List<EmpArrears> empArrears = new EmpArrearsService().getArrearsList();
	   return empArrears;
    }
	
	@RequestMapping(value = "/viewArrears", method = RequestMethod.GET)
	public String viewArrears(ModelMap model) {
		//System.out.println("viewArrears called...");
		return "listArrears";
	}
	
	@RequestMapping(value="/deleteArrears",method=RequestMethod.POST)
	public String deleteBank(EmpArrears empArrears){
	   //System.out.println("******deleteArrears -- Arrears : "+ empArrears.getEmployeeId() + " Arrears Id: " +empArrears.getArrearId());
	   String result = new EmpArrearsService().deleteArrearsPay(empArrears.getArrearId());
	   //System.out.println("result:"+result);
	   return "listArrears";
	}
	
	@RequestMapping(value = "/inputArrear", method = RequestMethod.POST)
	public ModelAndView inputArrear(EmpArrears arrears) {
		ObjectMapper mapper = new ObjectMapper();
		//System.out.println("inputArrear -- iArrear :"+arrears.getArrearId());
		//List<Designation> desigList = new DesignationService().getDesignationList();
		List<Department> deptList = new DepartmentService().getDepartments(); 
		String desigJSON = "";
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
			//desigJSON = mapper.writeValueAsString(desigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpArrearsVO arrearsVO = null; 
		arrearsVO = (arrears.getArrearId() !=0) ? new EmpArrearsService().getArrearsByArrearId(arrears.getArrearId()): new EmpArrearsVO();
		//System.out.println("arrearsVO department id " + arrearsVO.getDepartmentId());
		//System.out.println("arrearsVO arrears id " + arrearsVO.getArrearId());
		ModelAndView model = new ModelAndView("empArrears", "command", arrearsVO);
		model.addObject("empArrears", arrearsVO);
		model.addObject("departments", depJSON);
		//model.addObject("designations", desigJSON);
		return model;
	}
	
	@RequestMapping(value="/addArrears",method=RequestMethod.POST)
	public @ResponseBody
	String addArrears(@RequestBody EmpArrears arrears){
	   //System.out.println("arrears addupdate -- arrears :"+ arrears);
	   String result = new EmpArrearsService().addUpdateArrears(arrears);
	   //System.out.println("Result:"+result);
	   return result;
	}
	

}
