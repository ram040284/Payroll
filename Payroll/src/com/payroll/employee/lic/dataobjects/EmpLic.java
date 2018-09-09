package com.payroll.employee.lic.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class EmpLic implements Serializable {
	private String employeeId;
	@Override
	public String toString() {
          return "EmpLic [employeeId=" + employeeId + ", paymentAmount=" + paymentAmount
				+ ", policyNo=" + policyNo + ", paymentDate=" + paymentDate + ", status=" + status + ", rowUpdDate="
				+ rowUpdDate + ", addUpdate=" + addUpdate + ", employee=" + employee + "]";
	
	}
	private double instlmtAmt;
	private double paymentAmount;
	private String policyNo;
	private Date paymentDate;
	private String status;
	private Timestamp rowUpdDate;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}

   public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
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

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public double getInstlmtAmt() {
		return instlmtAmt;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}

	}
