package com.payroll.hrms.payroll.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.scripting.groovy.GroovyScriptFactory;

import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.paybill.vo.PaybillBean;
import com.payroll.pdf.business.PdfUtils;

public class GeneratePaybill {

	private double gradePay;
	private double basicPay;
	private double da;
	private double hra;
	private double cca;
	private double ta;
	private double npa;
	private double wa;
	private double ca;
	private double uniformAlw;
	private double familyPlaningAlw;
	private double totallw;
	private double otAmt;
	private double others;
	private double rent;
	private double afkRent;
	private double absentDed;
	private double festAdvRcry;
	private double pt;
	private double lic;
	private double socity;
	private double gis;
	private double bankLoanRcry;
	private double vlr;
	private double pfsCpf;
	private double apfAcf;
	private double grossPay; 
	private double pfLoanRcry;
	private double cpfRcry;
	private double incomTax;
	private double unionFee;
	private double elecRcry;
	private double courtRcry;
	private double otherDeduct;
	private double totDeduct;
	private double netPay;
	private double society;
	
	public PaybillDetails generatePaybill(int deptId){
		/*EmployeePayrollService payrollService =  new EmployeePayrollService();
		PaybillDetails payrollTotals = payrollService.retreiveEmpsForPayroll(deptId);*/
		
		/*for (Iterator iterator = payrollTotals.getPayrollList().iterator(); iterator.hasNext();) {
			EmployeePayroll employeePayroll = (EmployeePayroll) iterator.next();
		}*/
		return new PaybillService(deptId, null).getPayBills();
	}
	
	private void payBillByDept(List<EmployeePayroll> payrollList){
		for (Iterator iterator = payrollList.iterator(); iterator.hasNext();) {
			EmployeePayroll employeePayroll = (EmployeePayroll) iterator.next();
			basicPay+= employeePayroll.getBasic();
			gradePay += employeePayroll.getGradePay();
			cca+= employeePayroll.getCca();
			da+= employeePayroll.getDearnessAllowance();
			hra+= employeePayroll.getHouseRentAllowance();
			ta+= employeePayroll.getTravelAllowance();
			npa+= employeePayroll.getNonPracticingAllowance();
			wa+= employeePayroll.getWashingAllowance();
			ca+= employeePayroll.getConveyanceAllowance();
			uniformAlw+= employeePayroll.getUniformAllowance();
			familyPlaningAlw+= employeePayroll.getFamilyPlanningAllowance();
			otAmt+= employeePayroll.getOverTimeAmount();
			grossPay += employeePayroll.getGrossPay();
			rent+= employeePayroll.getHouseRentAllowance();
			afkRent+= employeePayroll.getAfkRent();
			absentDed+= employeePayroll.getAbsentAmount();
			festAdvRcry+= employeePayroll.getFestAdvRecovery();
			pt+= employeePayroll.getProfTax();
			lic+= employeePayroll.getLic();
			society+= employeePayroll.getSocietyInstallment();
			gis+= employeePayroll.getGrpInsurance();
			//bankLoanRcry += employeePayroll.get
			//vlr+=employeePayroll.getV
			pfsCpf+= employeePayroll.getProvidentFund();
			//apfAcf+=employeePayroll.getApf
			pfLoanRcry+=employeePayroll.getPfLoanRecovery();
			//cpfRcry+= employeePayroll.get
			incomTax+=employeePayroll.getIncomeTax();
			unionFee+= employeePayroll.getUnionFee();
			elecRcry += employeePayroll.getElectricityRecovery();
			courtRcry += employeePayroll.getCourtRecovery();
			otherDeduct+= employeePayroll.getOtherDeductions();
			//employeePayroll.getMis
			totDeduct += employeePayroll.getTotalDeductions();
			netPay += employeePayroll.getNetPay();
		}
	}
	
	public List<PaybillBean> getPaybillItems(){
		List<PaybillBean> beanList = new ArrayList<PaybillBean>(); 
		String[] pbItems = PdfUtils.PBITEMS;
		for (int i = 0; i < pbItems.length; i++) {
			beanList.add(new PaybillBean(i+1, pbItems[i], 5000.00, 5000.00));
		}
		return beanList;
	}
}
