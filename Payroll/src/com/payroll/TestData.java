package com.payroll;

import java.util.Date;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.pf.dataobjects.EmpPf;
import com.payroll.employee.salary.dataobjects.Salary;

public class TestData {
	
	public static Salary getSalary(int empId){
		Salary sal = new Salary();
		sal.setBasic(1000);
		sal.setGradePay(1000);
		sal.setEmployeeId(empId);
		sal.setScaleInc(10);
		sal.setScalePay(1000);
		return sal;
	}
	
	public static EmpPf getEmpPf(int empId){
		EmpPf pf = new EmpPf();
		pf.setEmployeeId(empId);
		pf.setApfAcpfCntrbn(1000);
		pf.setCfLoneRecAmt(1200);
		pf.setPfDate(new Date());
		pf.setPfLoneRecAmt(500);
		pf.setPfsCpfCntrbn(600);
		return pf;
	}
	
	public static EmpLic getEmpLic(int empId){
		EmpLic empLic = new EmpLic();
		empLic.setEmployeeId(empId);
		empLic.setInstlmtAmt(1000);
		empLic.setPaymentAmount(1200);
		empLic.setPaymentDate(new Date());
		empLic.setPolicyNo("98798798hhg");
		return empLic;
	}
	
	public static EmpAllowance getAllowance(int empId){
		EmpAllowance alw = new EmpAllowance();
		alw.setCca(200);
		alw.setConvAlwance(400);
		alw.setCycleAlwance(100);
		alw.setEmployeeId(empId);
		alw.setFamilyPlanAlwance(300);
		alw.setHraFlag(true);
		alw.setNonPracAwance(200);
		alw.setUniformAlwance(400);
		alw.setWashingAlwance(600);
		return alw;
	}
	
	public static BankVO getBAnkDetails(int empId){
		return new BankVO(1, "Indian Bank", "767867869869"); 
				
	}
}
