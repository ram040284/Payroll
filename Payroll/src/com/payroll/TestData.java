package com.payroll;

import java.util.Date;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.deductions.dataobjects.EmpDeductionDetails;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.pf.dataobjects.EmpPf;
import com.payroll.employee.salary.dataobjects.Salary;

public class TestData {
		
	public static Salary getSalary(int empId){
		Salary sal = new Salary();
		sal.setBasic(0);
		sal.setGradePay(0);
		sal.setEmployeeId(empId);
		sal.setScaleInc(0);
		sal.setScalePay("");
		return sal;
	}
	
	public static EmpPf getEmpPf(int empId){
		EmpPf pf = new EmpPf();
		pf.setEmployeeId(empId);
		pf.setApfAcpfCntrbn(0);
		pf.setCfLoneRecAmt(0);
		pf.setPfDate(new Date());
		pf.setPfLoneRecAmt(0);
		pf.setPfsCpfCntrbn(0);
		return pf;
	}
	
	public static EmpLic getEmpLic(int empId){
		EmpLic empLic = new EmpLic();
		empLic.setEmployeeId(empId);
		empLic.setInstlmtAmt(0);
		empLic.setPaymentAmount(0);
		empLic.setPaymentDate(new Date());
		empLic.setPolicyNo("");
		return empLic;
	}
	
	public static EmpAllowance getAllowance(int empId){
		EmpAllowance alw = new EmpAllowance();
		alw.setCca(0);
		alw.setConvAlwance(0);
		alw.setCycleAlwance(0);
		alw.setEmployeeId(empId);
		alw.setFamilyPlanAlwance(0);
		alw.setHraFlag(false);
		alw.setNonPracAwance(0);
		alw.setUniformAlwance(0);
		alw.setWashingAlwance(0);
		return alw;
	}
	
	public static EmpDeductions getEmpDeductions(int empId){
		EmpDeductions empDeductions = new EmpDeductions();
		empDeductions.setHomeLoanIntrst88EE(0);
		empDeductions.setSection80C(0);
		empDeductions.setSelfDisable80U(0);
		empDeductions.setEmployeeId(empId);
		return empDeductions;
	}
	
	public static EmpDeductionDetails getEmpDeductDtls(int empId){
		EmpDeductionDetails empDeductions = new EmpDeductionDetails();
		empDeductions.setAfkRent(0);
		empDeductions.setCourtRecovery(0);
		empDeductions.setSociety(0);
		empDeductions.setElectRecovery(0);
		empDeductions.setUnionFee(0);
		empDeductions.setOtherDeductions(0);
		empDeductions.setEmployeeId(empId);
		return empDeductions;
	}
	
	public static BankVO getBAnkDetails(int empId){
		return new BankVO(0, "", ""); 
				
	}
}
