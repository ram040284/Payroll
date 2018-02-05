package com.payroll.hrms.payroll.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.headInfo.dataobjects.HeadInfo;
import com.payroll.headInfo.dataobjects.HeadInfoDAO;
import com.payroll.headInfo.vo.HeadInfoVO;
import com.payroll.hrms.payroll.dao.EmployeePayrollDAO;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.dataobjects.Paybill;
import com.payroll.hrms.payroll.dataobjects.PaybillDAO;
import com.payroll.hrms.payroll.dataobjects.PaybillDetails;
import com.payroll.hrms.payroll.dataobjects.ReportDetails;
import com.payroll.pdf.business.Payslip;

public class PaybillService {
	List<Paybill> paybillList = null;
	List<EmployeeVO> empList = null;
	int headId;
	private int deptId;
	private Date startDate;
	private Date endDate;
	private boolean bankWise;
	public PaybillService(int deptId, String date){
		this.deptId = deptId;
		this.startDate = Utils.getStartDateOfMonth(date);
		this.endDate = Utils.getEndDateOfMonth(date);
	}
	
	/**
	 * Getting Pay bills for Monthly comparision 
	 */
	public List<PaybillDetails> getMonthlyBills(){
		
		List<PaybillDetails> pbDetailsList = new ArrayList<PaybillDetails>();
		PaybillDetails pbDetails = getPayBills();
		pbDetailsList.add(pbDetails);
		Date prevMonth = Utils.getPrevMonth(startDate);
		String prevMonthTxt = Utils.getSimpleDate(prevMonth);
		this.startDate = Utils.getStartDateOfMonth(prevMonthTxt);
		this.endDate = Utils.getEndDateOfMonth(prevMonthTxt);
		pbDetails = getPayBills();
		pbDetailsList.add(pbDetails);
		return pbDetailsList;
	}
	
	/**
	 * Getting Head Wise Pay bills
	 * @return
	 */
	public List<PaybillDetails> getHeadsPayBills(){
		
		List<PaybillDetails> pbDetailsList = new ArrayList<PaybillDetails>();
		List<HeadInfoVO> headInfoList= new HeadInfoDAO().getHeadInfoList(deptId);
		PaybillDetails pbDetails = null;
		for (Iterator iterator = headInfoList.iterator(); iterator.hasNext();) {
			HeadInfoVO headInfoVO = (HeadInfoVO) iterator.next();
			this.headId = headInfoVO.getHeadId();
			pbDetails = getPayBills();
			if(pbDetails != null){
				pbDetails.setHeadName(headInfoVO.getHeadName());
				pbDetailsList.add(pbDetails);
			}
		}
		return pbDetailsList;
	}
	/**
	 * Getting Bank Wise Pay bills
	 * @return
	 */
	public List<PaybillDetails> getBankWisePayBills(){
		this.bankWise = true;
		checkPayBills(deptId);
		return getBankwiseDetails();
	}
	
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public PaybillDetails getPaySlip(int empId){
		EmployeeVO empVO = new EmployeeDAO().getEmployeeById(empId);
		Paybill paybill = new PaybillDAO(startDate, endDate, 0).getPaybillByEmp(empId);
		//Payslip payslip = new Payslip(empVO, paybill, startDate);
		if(paybillList == null)
			paybillList = new ArrayList();
		if(empList == null)
			empList = new ArrayList<>();
		empList.add(empVO);
		System.out.println("paybill:"+paybill);
		if(paybill!=null){
			if(paybillList == null)
				paybillList = new ArrayList();
			paybillList.add(paybill);
		}
		PaybillDetails details = getPaybillDetails();
		details.setMonth(startDate);
		return details;
	}
	
	/**
	 * Getting Pay Bills
	 * @return
	 */
	public int generatePayBills(int billType){
		int success = 0;
		if(checkPayBills(deptId)){
			success = 1;
			return success;
		}
		EmployeePayrollService payroll = new EmployeePayrollService(empList);
		boolean result = payroll.createPaybills(deptId, startDate);
		success = (result) ? 2 :3;
		//checkPayBills(deptId);
		return success;
	}
	
	/**
	 * Getting Pay Bills
	 * @return
	 */
	public PaybillDetails getPayBills(){
		PaybillDetails details = null;
		if(checkPayBills(deptId))
			details = getPaybillDetails();
		
		/*EmployeePayrollService payroll = new EmployeePayrollService(empList);
		payroll.createPaybills(deptId, startDate);
		checkPayBills(deptId);
		return getPaybillDetails();*/
		return details;
	}
	private boolean checkPayBills(int deptId){
		boolean billsExist = false;
		empList = new EmployeePayrollDAO().getEmployeesByDept(deptId);
		paybillList = new PaybillDAO(startDate, endDate, deptId).getPaybillsByDept(headId, bankWise);
		if(paybillList !=null && !paybillList.isEmpty()){
			billsExist = true;
		}
		return billsExist;
	}
    private PaybillDetails getPaybillDetails(){
    	PaybillDetails payrollTotals = new PaybillDetails();
    	try {
		for (Paybill paybill : paybillList) {
			//System.out.println("bankId:"+paybill.getBankId() +", Name:"+paybill.getBankName());
    		ReportDetails empPayroll = new ReportDetails(); 
    		org.apache.commons.beanutils.BeanUtils.copyProperties(empPayroll, paybill);
    		if(headId == 0){
	    		for (EmployeeVO employee : empList) {
					if(employee.getEmployeeId() == paybill.getEmployeeId()){
						empPayroll.setEmployeeName(employee.getFullName());
			    		empPayroll.setPanNo(employee.getPan());
			    		empPayroll.setDob(employee.getDob());
			    		empPayroll.setJoiningDate(employee.getJoiningDate());
			    		empPayroll.setRetirementDate(employee.getRetirementDate());
			    		empPayroll.setGender(employee.getGender());
			    		empPayroll.setPfNumber("");
			    		empPayroll.setEmployeeNumber(employee.getEmployeeId()+"");
			    		break;
					}
				}
    		}
    		payrollTotals.addEmployeePayroll(empPayroll);
		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	payrollTotals.setMonth(startDate);
        return payrollTotals;
    }
    
    private List<PaybillDetails> getBankwiseDetails(){
    	PaybillDetails payrollTotals = new PaybillDetails();
    	List<PaybillDetails> pbDetailsList = new ArrayList<PaybillDetails>();
    	int bankId = 0;
    	boolean reportExist = false;
    	try {
			for (Paybill paybill : paybillList) {
				ReportDetails empPayroll = new ReportDetails();
	    		System.out.println("bankId:"+paybill.getBankId() +", Name:"+paybill.getBankName());
	    		org.apache.commons.beanutils.BeanUtils.copyProperties(empPayroll, paybill);
	    		if(bankId == 0){
	    			bankId = paybill.getBankId();
					payrollTotals.setBankName(paybill.getBankName());
	    		}
	    		else {
	    			if(bankId != paybill.getBankId()){
	    				System.out.println("bank Name is:"+payrollTotals.getBankName());
	    				pbDetailsList.add(payrollTotals);
	    				payrollTotals = new PaybillDetails();
	    				payrollTotals.setBankName(paybill.getBankName());
	    				bankId = paybill.getBankId();
	    			}
	    		}
	    		payrollTotals.addEmployeePayroll(empPayroll);
	    		if(!reportExist)
					reportExist = true;
	    	}
			if(reportExist)
				pbDetailsList.add(payrollTotals);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	payrollTotals.setMonth(startDate);
        return pbDetailsList;
    }
    

}
