package com.payroll.rest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpLicController {

	String permissionForThis = null;
	
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
	public String viewEmpLic(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeLIC";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmpLic";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	
	@RequestMapping(value = "/viewEmpLicMaster", method = RequestMethod.GET)
	public String viewEmpLicMaster(ModelMap mode, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeeLIC";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmpLicMaster";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view Master LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/inputEmpLic", method = RequestMethod.POST)
	public ModelAndView inputEmpLic(EmpLic empLic, HttpServletRequest request) {
		
		permissionForThis = "addEmployeeLIC";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
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
			model = new ModelAndView("empLic", "command", empLicVO);
			model.addObject("empLic", empLicVO);
			model.addObject("departments", depJSON);
			model.addObject("designations", desigJSON);

			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee LIC. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		
	}
	

/**
 * 
 * @param empLicVO
 * @return
 */
	@RequestMapping(value = "/inputEmpLicMaster", method = RequestMethod.POST)
	public ModelAndView inputEmpLicMaster(EmpLicMaster empLicMaster, HttpServletRequest request) {
		
		permissionForThis = "addMasterLIC";
		ModelAndView model = null;
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
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
			model = new ModelAndView("empLicMaster", "command", empLicMasterVO);
			model.addObject("empLic", empLicMasterVO);
			model.addObject("departments", depJSON);
			model.addObject("designations", desigJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add Master LIC. Please click home button to go back.");
			   model.addObject("unauthorizedMessage", true);
			   return model;
		}
		
		
	}
	
	@RequestMapping(value="/addEmpLic",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpLic(@RequestBody EmpLicVO empLicVO, HttpServletRequest request){
		
		permissionForThis = "addEmployeeLIC";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			String result = new EmpLicService().addUpdateEmpLic(empLicVO);
			return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/addEmpLicMaster",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpLicMaster(@RequestBody EmpLicMasterVO empLicMasterVO, HttpServletRequest request){
		permissionForThis = "addMasterLIC";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			String result = new EmpLicService().addUpdateEmpLicMaster(empLicMasterVO);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add Master LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
	
	@RequestMapping(value="/deleteLic",method=RequestMethod.POST)
	public String deleteLic(EmpLic empLic, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeeLIC";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			String result = new EmpLicService().deleteLic(empLic.getEmployeeId());
			   //FIXME: Prasad - add logic in UI based on result return value
			  return "listEmpLic";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}

	@RequestMapping(value="/deleteEmpLicMaster",method=RequestMethod.POST)
	public String deleteLicMaster(EmpLicMaster empLicMaster, HttpServletRequest request){
		
		permissionForThis = "deleteMasterLIC";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			String result = new EmpLicService().deleteLicMaster(empLicMaster.getEmployeeId());
			 //FIXME: Prasad - add logic in UI based on result return value
			   return "listEmpLicMaster";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete Master LIC. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
}
