package com.payroll.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.payroll.Utils;
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
	
	@RequestMapping(value = "/generateBills", method = RequestMethod.POST)
	public ModelAndView generateBills(PaybillVO paybill) {
		return getInputForm(paybill, "generateBills");
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
	
	
	@RequestMapping(value = "/generatePaybills", method = RequestMethod.POST)
    public ModelAndView generatePaybills(PaybillVO paybill) {
		ModelAndView model = null;
		try{
		int result = new PaybillService(paybill.getDepartmentId(), 
				paybill.getMonthDate()).generatePayBills(paybill.getBillType());
 		model = new ModelAndView("paybillsResp", "command", paybill);
 		model.addObject("result", result);
 		/*String result = "";
 		switch (billType) {
		case 1:
			result = "Paybills are exist for selected Department and Month!";
			break;
		case 2:
			result = "Paybills are generated for selected Department and Month!";
			break;
		case 3:
			result = "Unable to generate Paybills for selected Department and Month!";
			break;
			
		default:
			break;
		}*/
        //return new ModelAndView("pdfView", "monthlyDetails", monthlyDetails);
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to generate Paybills!");
		}
 	return model;
	}
	
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.POST)
		public ModelAndView downloadExcel(PaybillVO paybill) {
			List<PaybillDetails> monthlyDetails = null;
			try{
				monthlyDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getMonthlyBills();
				System.out.println("monthlyDetails:"+monthlyDetails+", monthlyDetails:"+monthlyDetails.isEmpty());
				if(monthlyDetails == null || monthlyDetails.isEmpty()){
					return new ModelAndView("noActivity", "", monthlyDetails);
				}else{
					if(monthlyDetails.get(0) == null)
						return new ModelAndView("noActivity", "", monthlyDetails);
				}
			}catch(Exception e){
				throw new AppException(new Date(), "Failed to get Monthly Report!");
			}
		    return new ModelAndView("pdfView", "monthlyDetails", monthlyDetails);
		}
	
	@RequestMapping(value = "/downloadPaybill", method = RequestMethod.POST)
    public ModelAndView downloadPaybill(PaybillVO paybill) {
		PaybillDetails paybillDetails = null;
		try{
			paybillDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getPayBills();
			if(paybillDetails == null){
				return new ModelAndView("noActivity", "", paybillDetails);
			}
		
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Paybills Report!");
		}
        return new ModelAndView("pdfView", "paybillDetails", paybillDetails);
    }
	
	@RequestMapping(value = "/headwiseReport", method = RequestMethod.POST)
    public ModelAndView headwiseReport(PaybillVO paybill) {
		List<PaybillDetails> headwiseDetails = null;
		try{
			headwiseDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getHeadsPayBills();
			if(headwiseDetails == null || headwiseDetails.isEmpty()){
				return new ModelAndView("noActivity", "", headwiseDetails);
			}
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Head Wise Report!");
		}
        return new ModelAndView("pdfView", "headwiseDetails", headwiseDetails);
    }
	
	@RequestMapping(value = "/bankwiseReport", method = RequestMethod.POST)
    public ModelAndView bankwiseReport(PaybillVO paybill) {
		List<PaybillDetails>  bankwiseDetails = null;
		try{
			bankwiseDetails = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getBankWisePayBills();
			if(bankwiseDetails == null || bankwiseDetails.isEmpty()){
				return new ModelAndView("noActivity", "", bankwiseDetails);
			}
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Bank Wise Report!");
		}
        return new ModelAndView("pdfView", "bankwiseDetails", bankwiseDetails);
    }
	
	@RequestMapping(value = "/payslip", method = RequestMethod.POST)
    public ModelAndView payslip(PaybillVO paybill) {
		PaybillDetails  payslip = null;
		try{
			payslip = new PaybillService(paybill.getDepartmentId(), paybill.getMonthDate()).getPaySlip(paybill.getEmployeeId());
			if(payslip == null || payslip.getPayrollList()== null || payslip.getPayrollList().isEmpty()){
				return new ModelAndView("noActivity", "", payslip);
			}
		}catch(Exception e){
			//throw new AppException(new Date(), "Failed to get get Payslip!");
			StringBuffer errorTxt = new StringBuffer("Failed to get get Payslip for selected month:");
			errorTxt.append(Utils.getMonthYear(Utils.getAsDate(paybill.getMonthDate(), Utils.DDMMYYYY)));
			throw new AppException(new Date(), errorTxt.toString());
		}
        return new ModelAndView("pdfView", "payslip", payslip);
    }
}
