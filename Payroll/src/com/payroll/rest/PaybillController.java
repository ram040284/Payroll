package com.payroll.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.payroll.login.dao.PermissionsDAO;
import com.payroll.login.dataobjects.User;
import com.payroll.paybill.vo.PaybillBean;
import com.payroll.paybill.vo.PaybillVO;
import com.payroll.pdf.business.Book;
@Controller
public class PaybillController {
	@RequestMapping(value = "/inputPaybill", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/monthlyRep", method = RequestMethod.GET)
	public ModelAndView monthlyRep(PaybillVO paybill) {
		return getInputForm(paybill, "monthlyRep");
	}
	
	@RequestMapping(value = "/bankwiseRep", method = RequestMethod.GET)
	public ModelAndView bankwiseRep(PaybillVO paybill) {
		return getInputForm(paybill, "bankwiseRep");
	}
	
	@RequestMapping(value = "/headwiseRep", method = RequestMethod.GET)
	public ModelAndView headwiseRep(PaybillVO paybill) {
		return getInputForm(paybill, "headwiseRep");
	}
	
	@RequestMapping(value = "/paycheckRep", method = RequestMethod.GET)
	public ModelAndView paycheckRep(PaybillVO paybill) {
		ObjectMapper mapper = new ObjectMapper();
		List<Department> deptList = new DepartmentService().getDepartments();
		String depJSON = "";
		try {
			depJSON = mapper.writeValueAsString(deptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("paycheckRep", "command", paybill);
		model.addObject(paybill);
		model.addObject("departments", depJSON);
		return model;
	}
	
	@RequestMapping(value = "/generateBills", method = RequestMethod.GET)
	public ModelAndView generateBills(PaybillVO paybill, HttpServletRequest request) {
		
		String permissionForThis = "generateBills";
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if (new PermissionsDAO().getPermissions(loggedInUser.getEmployee().getEmployeeId()).contains(permissionForThis) ) {
			/*ObjectMapper mapper = new ObjectMapper();
			List<Department> deptList = new DepartmentService().getDeptSections();
			String depJSON = "";
			try {
				depJSON = mapper.writeValueAsString(deptList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ModelAndView model = new ModelAndView("generateBills", "command", paybill);
			model.addObject(paybill);
			model.addObject("departments", depJSON);
			return model;*/
			return getInputForm(paybill, "generateBills");
		} else {
		    ModelAndView model = new ModelAndView("unauthorized", "message", "You do not have access to generate paybills. Please click home button to go back.");
		    model.addObject("unauthorizedMessage", true);
			return model;
		}
		
		
	}
	
	private ModelAndView getInputForm(PaybillVO paybill, String jspName){
		ObjectMapper mapper = new ObjectMapper();
		//List<Department> deptList = new DepartmentService().getDepartments();
		List<Department> deptList = new DepartmentService().getDeptSections();
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
	
	@RequestMapping(value = "/generatePaybill", method = RequestMethod.GET)
	public String inputHead(PaybillVO paybill) {
		return null;
	}
	
	/**
	 * Generating Paybills
	 */
	@RequestMapping(value = "/generatePaybills", method = RequestMethod.GET)
    public ModelAndView generatePaybills(PaybillVO paybill) {
		ModelAndView model = null;
		System.out.println("Generating paybill..");
		try{
			String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
		int result = new PaybillService(paybill.getSection(), month).generatePayBills(paybill.getBillType());
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
	/**
	 * Monthly Comparison
	 * @param paybill
	 * @return
	 */
	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
		public ModelAndView downloadExcel(PaybillVO paybill) {
			List<PaybillDetails> monthlyDetails = null;
			try{
				String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
				monthlyDetails = new PaybillService(paybill.getSection(), month).getMonthlyBills();
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
	
	@RequestMapping(value = "/downloadPaybill", method = RequestMethod.GET)
    public ModelAndView downloadPaybill(PaybillVO paybill) {
		PaybillDetails paybillDetails = null;
		try{
			System.out.println("downloadPaybill called and billtype is " + paybill.getBillType());
			String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
			paybillDetails = new PaybillService(paybill.getSection(), month).getPayBills(paybill.getBillType());
			if(paybillDetails == null || (paybillDetails.getPayrollList() == null || paybillDetails.getPayrollList().isEmpty())){
				return new ModelAndView("noActivity", "", paybillDetails);
			}
		
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Paybills Report!");
		}
		System.out.println("paybillDetails getEmployeeType : " + paybillDetails.getEmployeeType());
        return new ModelAndView("pdfView", "paybillDetails", paybillDetails);
    }
	
	@RequestMapping(value = "/headwiseReport", method = RequestMethod.GET)
    public ModelAndView headwiseReport(PaybillVO paybill) {
		List<PaybillDetails> headwiseDetails = null;
		try{
			String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
			headwiseDetails = new PaybillService(paybill.getSection(), month).getHeadsPayBills();
			if(headwiseDetails == null || headwiseDetails.isEmpty()){
				return new ModelAndView("noActivity", "", headwiseDetails);
			}
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Head Wise Report!");
		}
        return new ModelAndView("pdfView", "headwiseDetails", headwiseDetails);
    }
	
	@RequestMapping(value = "/bankwiseReport", method = RequestMethod.GET)
    public ModelAndView bankwiseReport(PaybillVO paybill) {
		List<PaybillDetails>  bankwiseDetails = null;
		try{
			String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
			
			bankwiseDetails = new PaybillService(paybill.getSection(), month).getBankWisePayBills();
			if(bankwiseDetails == null || bankwiseDetails.isEmpty()){
				return new ModelAndView("noActivity", "", bankwiseDetails);
			}
		}catch(Exception e){
			throw new AppException(new Date(), "Failed to get Bank Wise Report!");
		}
        return new ModelAndView("pdfView", "bankwiseDetails", bankwiseDetails);
    }
	
	@RequestMapping(value = "/payslip", method = RequestMethod.GET)
    public ModelAndView payslip(PaybillVO paybill) {
		PaybillDetails  payslip = null;
		try{
			String month = Utils.getDateByMonth(Integer.parseInt(paybill.getMonthDate()));
			payslip = new PaybillService(paybill.getDepartmentId(), month).getPaySlip(paybill.getEmployeeId());
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
