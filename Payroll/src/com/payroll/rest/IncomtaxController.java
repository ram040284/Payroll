package com.payroll.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.incomtax.business.IncomtaxSlabService;
import com.payroll.incomtax.dataobjects.IncomtaxSlab;

@Controller
public class IncomtaxController {
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
	public  ModelAndView inputTaxSlab(IncomtaxSlab incomtaxSlab) {
		System.out.println("incomtaxSlab:"+incomtaxSlab);
		if(incomtaxSlab.getIncomtaxId() != 0)
			incomtaxSlab = new IncomtaxSlabService().getTaxSlabById(incomtaxSlab.getIncomtaxId());
		ModelAndView model = new ModelAndView("incomtaxSlab", "command", incomtaxSlab);
		model.addObject("incomtaxSlab", incomtaxSlab);
		//RedirectView
		return model;
	}
	   
	@RequestMapping(value="/addTaxSlab",method=RequestMethod.POST)
	public @ResponseBody
		String addTaxSlab(@RequestBody IncomtaxSlab incomtaxSlab){
		System.out.println("incomtaxSlab:"+incomtaxSlab);
		String result = new IncomtaxSlabService().addUpdateTaxSlab(incomtaxSlab);
		System.out.println("result:"+result);
		return result;
	}
	
	@RequestMapping(value="/deleteTaxSlab",method=RequestMethod.POST)
	public String deleteTaxSlab(IncomtaxSlab incomtaxSlab){
	   System.out.println("incomtaxSlab:"+incomtaxSlab);
	   if(new IncomtaxSlabService().deleteTaxSlab(incomtaxSlab.getIncomtaxId()))
		   System.out.println("Successfully deleted Tax Slab Details!!");
	   else
		   System.out.println("Failed to deleted Tax Slab Details!!");
	   return "listTaxSlabs";
	}
 
}
