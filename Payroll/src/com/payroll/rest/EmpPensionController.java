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
import com.payroll.employee.pension.business.PensionService;
import com.payroll.employee.pension.dataobjects.Pension;
import com.payroll.employee.pension.vo.PensionVO;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.paybill.vo.PaybillVO;

@Controller
public class EmpPensionController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listPension", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<PensionVO> getEmpPensionList(){
		System.out.println("listPension-- getEmpPensionList");
	   List<PensionVO> pensionList = new PensionService().getPensionList();
	   return pensionList;
    }
	
	@RequestMapping(value = "/viewPension", method = RequestMethod.GET)
	public String viewPension(ModelMap model, HttpServletRequest request) {
		
		permissionForThis = "viewEmployeePension";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return "listPension";
		} else {
			request.getSession().setAttribute("message", "You do not have access to view employee pension. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	@RequestMapping(value = "/inputPension", method = RequestMethod.POST)
	public ModelAndView inputPension(PensionVO pension, HttpServletRequest request) {
		
		permissionForThis = "addEmployeePension";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			ObjectMapper mapper = new ObjectMapper();
			System.out.println("addEmployeePension -- pension - empId:"+pension.getEmployeeId());
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
		if(!pension.getEmployeeId().equals("0"))
		/*if(pension.getEmployeeId() != "0")*/
				pension = new PensionService().getEmpPension(pension.getEmployeeId());
			model = new ModelAndView("pension", "command", pension);
			model.addObject("pension", pension);
			model.addObject("departments", depJSON);
			//model.addObject("designations", desigJSON);
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add employee pension. Please click home button to go back.");
			model.addObject("unauthorizedMessage", true);
			return model;
		}
		
		
	}
	   
	@RequestMapping(value="/addPension",method=RequestMethod.POST)
	public @ResponseBody
	String addPension(@RequestBody Pension pension, HttpServletRequest request){
		
		permissionForThis = "addEmployeePension";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			//System.out.println("addPension -- pension : "+pension.getAddUpdate());
			   String result = new PensionService().addUpdatePension(pension);
			   System.out.println("Add/Update Pension -- result:"+result);
			   return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add employee pension. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
		
	   
	}
	
	@RequestMapping(value="/deletePension",method=RequestMethod.POST)
	public String deletePension(Pension pension, HttpServletRequest request){
		
		permissionForThis = "deleteEmployeePension";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("deletePension -- pension:"+pension.getEmployeeId());
			   String result = new PensionService().deletePension(pension.getEmployeeId());
			   System.out.println("Result:"+result);
			   return "listPension";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete employee pension. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}
	
	@RequestMapping(value = "/generatePensionPaybills", method = RequestMethod.GET)
	public ModelAndView generatePensionPayBill(PensionVO pensionVO, HttpServletRequest request) {
		
		String permissionForThis = "generateBills";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			return getInputForm(pensionVO, "generatePensionBills");
		} else {
		    ModelAndView model = new ModelAndView("unauthorized", "message", "You do not have access to generate paybills. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
			return model;
		}
	}
	
	private ModelAndView getInputForm(PensionVO pensionVO, String jspName){
		ObjectMapper mapper = new ObjectMapper();
		//List<Department> deptList = new DepartmentService().getDepartments();
		List<Department> deptList = new DepartmentService().getDeptSections();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView(jspName, "command", pensionVO);
		model.addObject(pensionVO);
		model.addObject("departments", depJSON);
		return model;
	}
	
	
}
