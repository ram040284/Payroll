package com.payroll.rest;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.department.business.DepartmentService;
import com.payroll.department.dataobjects.Department;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.service.GeneratePaybill;
import com.payroll.hrms.payroll.service.PaybillService;
import com.payroll.paybill.vo.PaybillBean;
import com.payroll.paybill.vo.PaybillVO;
import com.payroll.pdf.business.Book;
@Controller
public class PaybillController {
	
	@RequestMapping(value = "/inputPaybill", method = RequestMethod.POST)
	public ModelAndView inputPaybill(PaybillVO paybill) {
		/*ObjectMapper mapper = new ObjectMapper();
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("paybill", "command", paybill);
		model.addObject(paybill);
		model.addObject("departments", depJSON);*/
		return getInputForm(paybill, "paybill");
	}
	
	@RequestMapping(value = "/monthlyRep", method = RequestMethod.POST)
	public ModelAndView monthlyRep(PaybillVO paybill) {
		return getInputForm(paybill, "monthlyRep");
	}
	
	@RequestMapping(value = "/bankwiseRep", method = RequestMethod.POST)
	public ModelAndView bankwiseRep(PaybillVO paybill) {
		return getInputForm(paybill, "bankwiseRep");
	}
	
	@RequestMapping(value = "/headwiseRep", method = RequestMethod.POST)
	public ModelAndView headwiseRep(PaybillVO paybill) {
		return getInputForm(paybill, "headwiseRep");
	}
	
	@RequestMapping(value = "/paycheckRep", method = RequestMethod.POST)
	public ModelAndView paycheckRep(PaybillVO paybill) {
		return getInputForm(paybill, "paycheckRep");
	}
	
	private ModelAndView getInputForm(PaybillVO paybill, String jspName){
		ObjectMapper mapper = new ObjectMapper();
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView(jspName, "command", paybill);
		model.addObject(paybill);
		model.addObject("departments", depJSON);
		return model;
	}
	
	@RequestMapping(value = "/generatePaybill", method = RequestMethod.POST)
	public String inputHead(PaybillVO paybill) {
		return null;
	}
	
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
	    public ModelAndView downloadExcel(PaybillVO paybill) {
	 	List<PaybillDetails> monthlyDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getMonthlyBills();
	        return new ModelAndView("pdfView", "monthlyDetails", monthlyDetails);
		}
	
	@RequestMapping(value = "/downloadPaybill", method = RequestMethod.POST)
    public ModelAndView downloadPaybill(PaybillVO paybill) {
		PaybillDetails paybillDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getPayBills();
        return new ModelAndView("pdfView", "paybillDetails", paybillDetails);
    }
	
	@RequestMapping(value = "/headwiseReport", method = RequestMethod.POST)
    public ModelAndView headwiseReport(PaybillVO paybill) {
		System.out.println("paybill:"+paybill);
		List<PaybillDetails> headwiseDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getHeadsPayBills();
        return new ModelAndView("pdfView", "headwiseDetails", headwiseDetails);
    }
	
	@RequestMapping(value = "/bankwiseReport", method = RequestMethod.POST)
    public ModelAndView bankwiseReport(PaybillVO paybill) {
		List<PaybillDetails>  bankwiseDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getBankWisePayBills();
        return new ModelAndView("pdfView", "bankwiseDetails", bankwiseDetails);
    }
}
