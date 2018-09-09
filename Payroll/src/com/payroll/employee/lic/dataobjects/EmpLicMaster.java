package com.payroll.employee.lic.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

import com.payroll.employee.dataobjects.Employee;

public class EmpLicMaster implements Serializable {

	@Override
	public String toString() {
		return "EmpLicMaster [employeeId=" + employeeId + ", policyNo=" + policyNo + ", instlmtAmt=" + instlmtAmt
				+ ", status=" + status + ", rowUpdDate=" + rowUpdDate + ", addUpdate=" + addUpdate + ", employee="
				+ employee + "]";
	}

	private int employeeId;
	private String policyNo;
	private double instlmtAmt;
	private String status;
	private Timestamp rowUpdDate;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}

	public double getInstlmtAmt() {
		return instlmtAmt;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
