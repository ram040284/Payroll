package com.payroll.employee.lic.vo;

import java.util.Date;

public class EmployeeLIC {
	private int empId;
	private double instlmtAmt;
	private String policyNo;
	private short addUpdate; // 0 - Add / 1 - update

	
	public EmployeeLIC(){
		
	}
	
	public EmployeeLIC(int empId, String policyNo, double instlmtAmt) {
		this.empId = empId;
		this.policyNo = policyNo;
		this.instlmtAmt = instlmtAmt;
	}

	public int getEmpId() {
		return empId;
	}
	public double getInstlmtAmt() {
		return instlmtAmt;
	}
	public String getPolicyNo() {
		return policyNo;
	}


	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	
}
