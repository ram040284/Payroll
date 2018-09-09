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

import com.payroll.bank.business.BankDetailsService;
import com.payroll.bank.dataobjects.BankDetails;
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;

@Controller
public class BankDetailsController {
	
	String permissionForThis = null;
	
	@RequestMapping(value="/listBanks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<BankDetails> listBanks(){
	   List<BankDetails> banks = new BankDetailsService().getBankDetails();
	   return banks;
    }
	
	@RequestMapping(value = "/viewBankDetails", method = RequestMethod.GET)
	public String viewDept(ModelMap model) {
		return "listBanks";
	}
	
	@RequestMapping(value = "/inputBankDetails", method = RequestMethod.POST)
	public  ModelAndView inputDept(BankDetails bankDetails, HttpServletRequest request) {
		
		permissionForThis = "addBank";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		ModelAndView model = null;
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("bankDetails:"+bankDetails);
			if(bankDetails.getBankId() != 0) {
				bankDetails = new BankDetailsService().getBankDetailsById(bankDetails.getBankId());
			}
			model = new ModelAndView("bankDetails", "command", bankDetails);
			model.addObject("bankDetails", bankDetails);
			//RedirectView
			return model;
		} else {
			model = new ModelAndView("unauthorized", "message", "You do not have access to add/update bank details. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
		    return model;
		}
		
	}
	   
	@RequestMapping(value="/addBankDetails",method=RequestMethod.POST)
	public @ResponseBody
		String addBankDetails(@RequestBody BankDetails bankDetails){
		System.out.println("bankDetails:"+bankDetails);
		String result = new BankDetailsService().addUpdateBankDetails(bankDetails);
		System.out.println("result:"+result);
		return result;
	}
	
	@RequestMapping(value="/deleteBankDetails",method=RequestMethod.POST)
	public String deleteBankDetails(BankDetails bankDetails, HttpServletRequest request){
		
		permissionForThis = "deleteBank";
		User loggedInUser = (User) request.getSession().getAttribute("user");
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			System.out.println("bankDetails:"+bankDetails);
			if(new BankDetailsService().deleteBankDetails(bankDetails.getBankId()))
				System.out.println("Successfully deleted Bank Details!!");
			else
				System.out.println("Failed to deleted Bank Details!!");
			return "listBanks";
		} else {
			//FIXME - Prasad This delete is not working.
			request.getSession().setAttribute("message", "You do not have access to delete designation. Please click home button to go back.");
			request.getSession().setAttribute("unauthorizedMessage", true);
			return "unauthorized";
		}
	}

}
