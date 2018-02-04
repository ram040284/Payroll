package com.payroll.pdf.business;

import java.util.Date;

import com.payroll.employee.vo.EmployeeVO;
import com.payroll.hrms.payroll.dataobjects.Paybill;

public class Payslip {
	
	private EmployeeVO empDetails;
	private Paybill paybill;
	private Date date;
	public Payslip(){}
	
	public Payslip(EmployeeVO empDet, Paybill paybill, Date date){
		this.paybill = paybill;
		this.empDetails = empDet;
		this.date = date;
	}

	public EmployeeVO getEmpDetails() {
		return empDetails;
	}

	public Paybill getPaybill() {
		return paybill;
	}

	public Date getDate() {
		return date;
	}
	
}
