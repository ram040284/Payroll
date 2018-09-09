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
import com.payroll.employee.pf.business.EmpPfService;
import com.payroll.employee.pf.vo.EmpPfVO;
import com.payroll.headInfo.business.HeadInfoService;
import com.payroll.headInfo.dataobjects.HeadInfo;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class EmpPfController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listEmpPf", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<EmpPfVO> getEmpPfList(){
		System.out.println("listEmpPf-- getEmpPfList");
	   List<EmpPfVO> empPfs = new EmpPfService().getPfList();
	   return empPfs;
    }
	
	@RequestMapping(value = "/viewEmpPf", method = RequestMethod.GET)
	public String viewEmpPf(ModelMap model, HttpServletRequest request) {
		permissionForThis = "viewEmployeePF";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listEmpPf";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee PF. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/inputEmpPf", method = RequestMethod.POST)
	public ModelAndView inputEmpPf(EmpPfVO empPfVO, HttpServletRequest request) {
		
		permissionForThis = "addEmployeePF";
		ModelAndView model = null;
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("inputEmpPf -- empPfVO:"+empPfVO);
			List<Designation> desigList = new DesignationService().getDesignationList();
			List<HeadInfoVO> headList = new HeadInfoService().getHeadInfoList();
			List<Department> deptList = new DepartmentService().getDepartments();
			
			String desigJSON = "";
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
				desigJSON = mapper.writeValueAsString(desigList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(empPfVO.getEmpId() != 0){
				System.out.println("empPfVO.getEmpId():"+empPfVO.getEmpId());
				empPfVO = new EmpPfService().getEmpPfById(empPfVO.getEmpId());
			}
			
			model = new ModelAndView("empPf", "command", empPfVO);
			model.addObject("empPf", empPfVO);
			model.addObject("departments", depJSON);
			model.addObject("designations", desigJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee PF. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
		
	}
	   
	@RequestMapping(value="/addEmpPf",method=RequestMethod.POST)
	public @ResponseBody
	String addEmpPf(@RequestBody EmpPfVO empPfVO, HttpServletRequest request){
		
		permissionForThis = "addEmployeePF";
			
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			 System.out.println("addEmpPf -- EmpLic:"+empPfVO);
			   String result = new EmpPfService().addUpdateEmpPf(empPfVO);
			   System.out.println("result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee PF. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	  
	}
	
	@RequestMapping(value="/deletePf",method=RequestMethod.POST)
	public String deletePf(EmpPfVO empPfVO, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeePF";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
			
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deletePf -- empPfVO:"+empPfVO.getEmpId());
			   String result = new EmpPfService().deleteEmpPf(empPfVO.getEmpId());
			   System.out.println("Result:"+result);
			   return "listEmpPf";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee PF. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
}
