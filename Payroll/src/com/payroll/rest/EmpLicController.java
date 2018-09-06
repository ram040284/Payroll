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
import com.payroll.employee.lic.business.EmpLicService;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.dataobjects.EmpLicMaster;
import com.payroll.employee.lic.vo.EmpLicMasterVO;
import com.payroll.employee.lic.vo.EmpLicVO;

@Controller
public class EmpLicController {

	@RequestMapping(value="/listEmpLic", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpLicVO> getEmpLicList(){
	   List<EmpLicVO> empLic = new EmpLicService().getEmpLicList();
	   return empLic;
    }

	@RequestMapping(value="/listEmpLicMaster", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpLicMasterVO> getEmpLicListMaster(){
	   List<EmpLicMasterVO> empLics = new EmpLicService().getEmpLicMasterList();
	   return empLics;
    }
	
	@RequestMapping(value = "/viewEmpLic", method = RequestMethod.GET)
	public String viewEmpLic(ModelMap model) {
		return "listEmpLic";
	}
	
	
	@RequestMapping(value = "/viewEmpLicMaster", method = RequestMethod.GET)
	public String viewEmpLicMaster(ModelMap model) {
		return "listEmpLicMaster";
	}
	
	@RequestMapping(value = "/inputEmpLic", method = RequestMethod.POST)
	public ModelAndView inputEmpLic(EmpLic empLic) {
		ObjectMapper mapper = new ObjectMapper();
        List<Designation> desigList = new DesignationService().getDesignations();
        List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		String desigJSON = "";

		try {
			depJSON = mapper.writeValueAsString(deptList);
			desigJSON = mapper.writeValueAsString(desigList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpLicVO empLicVO = new EmpLicVO(); 
		if(empLic.getEmployeeId()!=0)
			empLicVO = new EmpLicService().getEmpLicById(empLic.getEmployeeId());
		ModelAndView model = new ModelAndView("empLic", "command", empLicVO);
		model.addObject("empLic", empLicVO);
		model.addObject("departments", depJSON);
		model.addObject("designations", desigJSON);

		return model;
	}
	

/**
 * 
 * @param empLicVO
 * @return
 */
	@RequestMapping(value = "/inputEmpLicMaster", method = RequestMethod.POST)
	public ModelAndView inputEmpLicMaster(EmpLicMaster empLicMaster) {
		ObjectMapper mapper = new ObjectMapper();
		List<Designation> desigList = new DesignationService().getDesignations();
		List<Department> deptList = new DepartmentService().getDepartments();
		String desigJSON = "";
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
			desigJSON = mapper.writeValueAsString(desigList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		EmpLicMasterVO empLicMasterVO = new EmpLicMasterVO(); 
         if(empLicMaster.getEmployeeId()!=0)
			empLicMasterVO = new EmpLicService().getEmpLicMasterById(empLicMaster.getEmployeeId());
		ModelAndView model = new ModelAndView("empLicMaster", "command", empLicMasterVO);
		model.addObject("empLic", empLicMasterVO);
		model.addObject("departments", depJSON);
		model.addObject("designations", desigJSON);
		return model;
	}
	
	@RequestMapping(value="/addEmpLic",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpLic(@RequestBody EmpLicVO empLicVO){
	   String result = new EmpLicService().addUpdateEmpLic(empLicVO);
	   return result;
	}
	
	@RequestMapping(value="/addEmpLicMaster",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpLicMaster(@RequestBody EmpLicMasterVO empLicMasterVO){
	   String result = new EmpLicService().addUpdateEmpLicMaster(empLicMasterVO);
	   return result;
	}
	
	@RequestMapping(value="/deleteLic",method=RequestMethod.POST)
	public String deleteLic(EmpLic empLic){
	   String result = new EmpLicService().deleteLic(empLic.getEmployeeId());
	   //FIXME: Prasad - add logic in UI based on result return value
	  return "listEmpLic";
	}

	@RequestMapping(value="/deleteEmpLicMaster",method=RequestMethod.POST)
	public String deleteLicMaster(EmpLicMaster empLicMaster){
	   String result = new EmpLicService().deleteLicMaster(empLicMaster.getEmployeeId());
	 //FIXME: Prasad - add logic in UI based on result return value
	   return "listEmpLicMaster";
	}
}
