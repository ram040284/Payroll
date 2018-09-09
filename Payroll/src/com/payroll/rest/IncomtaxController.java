package com.payroll.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.incomtax.business.IncomtaxSlabService;
import com.payroll.incomtax.dataobjects.IncomtaxSlab;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class IncomtaxController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listTaxSlabs", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<IncomtaxSlab> listTaxSlabs(){
	   List<IncomtaxSlab> banks = new IncomtaxSlabService().getTaxSlabs();
	   return banks;
    }
	
	@RequestMapping(value = "/viewTaxSlab", method = RequestMethod.GET)
	public String viewDept(ModelMap model) {
		return "listTaxSlabs";
	}
	
	@RequestMapping(value = "/inputTaxSlab", method = RequestMethod.POST)
	public  ModelAndView inputTaxSlab(IncomtaxSlab incomtaxSlab, HttpServletRequest request) {
		
		permissionForThis = "addIncomeTaxSlab";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("incomtaxSlab:"+incomtaxSlab);
			if(incomtaxSlab.getIncomtaxId() != 0) {
				incomtaxSlab = new IncomtaxSlabService().getTaxSlabById(incomtaxSlab.getIncomtaxId());
			}
			model = new ModelAndView("incomtaxSlab", "command", incomtaxSlab);
			model.addObject("incomtaxSlab", incomtaxSlab);
			//RedirectView
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add/update income tax slab details. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
		    return model;
		}
	}
	   
	@RequestMapping(value="/addTaxSlab",method=RequestMethod.POST)
	public @ResponseBody String addTaxSlab(@RequestBody IncomtaxSlab incomtaxSlab, HttpServletRequest request){
		
		permissionForThis = "addIncomeTaxSlab";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("incomtaxSlab:"+incomtaxSlab);
			String result = new IncomtaxSlabService().addUpdateTaxSlab(incomtaxSlab);
			System.out.println("result:"+result);
			return result;
		} else {
			request.getSession().setAttribute("message", "You do not have access to add income tax slab details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
		
	}
	
	@RequestMapping(value="/deleteTaxSlab",method=RequestMethod.POST)
	public String deleteTaxSlab(IncomtaxSlab incomtaxSlab, HttpServletRequest request){
		
		permissionForThis = "deleteIncomeTaxSlab";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
		   System.out.println("incomtaxSlab:"+incomtaxSlab);
		   if(new IncomtaxSlabService().deleteTaxSlab(incomtaxSlab.getIncomtaxId()))
			   System.out.println("Successfully deleted Tax Slab Details!!");
		   else
			   System.out.println("Failed to deleted Tax Slab Details!!");
		   return "listTaxSlabs";
		} else {
			request.getSession().setAttribute("message", "You do not have access to delete income tax slab details. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	   
	}
 
}
