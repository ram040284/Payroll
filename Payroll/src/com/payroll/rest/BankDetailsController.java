package com.payroll.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.bank.business.BankDetailsService;
import com.payroll.bank.dataobjects.BankDetails;

@Controller
public class BankDetailsController {
	
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
	public  ModelAndView inputDept(BankDetails bankDetails) {
		System.out.println("bankDetails:"+bankDetails);
		if(bankDetails.getBankId() != 0)
			bankDetails = new BankDetailsService().getBankDetailsById(bankDetails.getBankId());
		ModelAndView model = new ModelAndView("bankDetails", "command", bankDetails);
		model.addObject("bankDetails", bankDetails);
		//RedirectView
		return model;
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
	public String deleteBankDetails(BankDetails bankDetails){
	   System.out.println("bankDetails:"+bankDetails);
	   if(new BankDetailsService().deleteBankDetails(bankDetails.getBankId()))
		   System.out.println("Successfully deleted Bank Details!!");
	   else
		   System.out.println("Failed to deleted Bank Details!!");
	   return "listBanks";
	}

}
