package com.payroll.hrms.payroll.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmpDeductionsDAO;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.hrms.payroll.dao.EmployeePayrollDAO;
import com.payroll.hrms.payroll.dataobjects.Allowance;
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.dataobjects.Paybill;
import com.payroll.hrms.payroll.dataobjects.PaybillDAO;
import com.payroll.overtime.dataobjects.Overtime;

/**
 * Created by rajendra on 12/12/17.
 */
public class EmployeePayrollService {
    /**
     *
     */
	public static final String ABCENTIES = "ABCENT";
	List<Paybill> paybillList = null;
	List<EmployeeVO> empList = null;
	IncomeTaxCalculatorService incTaxservice = null;
	public EmployeePayrollService(List<EmployeeVO> empList){
		this.empList = empList;
	}
	public String addPaybill(EmployeePayroll payroll, Date date){
		String result = null;
		Paybill paybill = new Paybill();
		try{
			org.apache.commons.beanutils.BeanUtils.copyProperties(paybill, payroll);
			paybill.setMonth(date);
			result = new PaybillDAO().addPaybill(paybill);
			
			//System.out.println("***** Paybill TAllowance: " + paybill.gettAllowance() + " Payroll TAllowance: " + payroll.gettAllowance());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public boolean createPaybills(int deptId, Date date, int billType){
		//System.out.println("Creating paybill...");
		boolean success = false;
		try{
			EmployeePayrollDAO payrollDAO = new EmployeePayrollDAO();
			EmpDeductionsDAO deductionsDAO = new EmpDeductionsDAO();
			incTaxservice= new IncomeTaxCalculatorService();
			for (Iterator iterator = empList.iterator(); iterator.hasNext();) {
				EmployeeVO employee = (EmployeeVO) iterator.next();
				EmployeePayroll empPayroll = payrollDAO.loadPayrollInfo(employee.getEmployeeId(), employee.getHandicapFlag(), date, billType);
				EmpDeductionsVO empDeductions = deductionsDAO.getEmpDeductionsById(employee.getEmployeeId());
				if(empDeductions!=null){
					System.out.println("empDeductions createPaybills " + empDeductions.getDonation_80G());
				}
				if(empPayroll!=null){
					empPayroll.setEmployee(employee);
					double incomeTax = incTaxservice.getIncomeTax(employee.getEmployeeId(), date, empPayroll.getGrossPay());
					double tax = incTaxservice.getEmpITExemptions(employee.getEmployeeId(), date, empPayroll.getGrossPay(), empDeductions);
					empPayroll.setIncomeTax(incomeTax);
					empPayroll.setTotalDeductions(empPayroll.getTotalDeductions() + incomeTax);
					empPayroll.setNetPay(empPayroll.getNetPay() - incomeTax);
					
					addPaybill(empPayroll, date);
				}
			}
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
    
    public static double overtimeHours(List<Overtime> overtimeList){
    	double overtimeHrs = 0;
    	for (Iterator iterator = overtimeList.iterator(); iterator.hasNext();) {
			Overtime overtime = (Overtime) iterator.next();
			overtimeHrs += overtime.getOvertimeHours();
		}
    	return overtimeHrs;
    }
    
    public static double getAbsenties(List<LeaveRequest> leaveList){
    	double absenties = 0;
    	for (Iterator iterator = leaveList.iterator(); iterator.hasNext();) {
    		LeaveRequest leave = (LeaveRequest) iterator.next();
			if(leave.getLeaveType().getName().equalsIgnoreCase(ABCENTIES))
				absenties += leave.getNoOfLeaves();
		}
    	return absenties;
    }
    
    public static List<Allowance> listAllowances(EmpAllowance allowances){
    
    	
    	List<Allowance> allowanceList = new ArrayList<Allowance>();
    	Allowance allowance = null;
    	if(allowances.getCca()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("CCA");
    		allowance.setAllowance(allowances.getCca());
    		allowanceList.add(allowance);
    	}
/*    	if(allowances.getConvAlwance()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("Convayance Allowance");
    		allowance.setAllowance(allowances.getConvAlwance());
    		allowanceList.add(allowance);
    	}*/
    	if(allowances.getCycleAlwance()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("Cycle Allowance");
    		allowance.setAllowance(allowances.getCycleAlwance());
    		allowanceList.add(allowance);
    	}
    	if(allowances.getFamilyPlanAlwance()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("Family Palnning Allowance");
    		allowance.setAllowance(allowances.getFamilyPlanAlwance());
    		allowanceList.add(allowance);
    	}
    	if(allowances.getNonPracAwance()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("Non PRAC Allowance");
    		allowance.setAllowance(allowances.getNonPracAwance());
    		allowanceList.add(allowance);
    	}
    	if(allowances.getUniformAlwance()>0){
    		allowance = new Allowance();
    		allowance.setAllowanceName("Uniform Allowance");
    		allowance.setAllowance(allowances.getUniformAlwance());
    		allowanceList.add(allowance);
    	}
    	return allowanceList;
    }
    
}
