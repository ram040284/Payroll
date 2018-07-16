package com.payroll.employee.advances.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.advance.dataobjects.Advance;
import com.payroll.employee.dataobjects.Employee;

public class EmpAdvances implements Serializable{
	
	private int advanceId;
	private int employeeId;
	private Employee employee;
	private Advance advance;
	private double advBalAmount;
	private double installAmount;
	private double nextInstallAmount;
	private String status;
	private Timestamp rowUpdDate;
	private Date paymentDate;
	
	public int getAdvanceId() {
		return advanceId;
	}
	public void setAdvanceId(int advanceId) {
		this.advanceId = advanceId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Advance getAdvance() {
		return advance;
	}
	public void setAdvance(Advance advance) {
		this.advance = advance;
	}
	public double getAdvBalAmount() {
		return advBalAmount;
	}
	public void setAdvBalAmount(double advBalAmount) {
		this.advBalAmount = advBalAmount;
	}
	public double getInstallAmount() {
		return installAmount;
	}
	public void setInstallAmount(double installAmount) {
		this.installAmount = installAmount;
	}
	public double getNextInstallAmount() {
		return nextInstallAmount;
	}
	public void setNextInstallAmount(double nextInstallAmount) {
		this.nextInstallAmount = nextInstallAmount;
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
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
