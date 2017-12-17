package com.payroll.employee.bank.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.bank.dataobjects.BankDetails;
import com.payroll.employee.dataobjects.Employee;

public class EmpBank implements Serializable{
	
	private int employeeId;
	private String bankName;
	private String ifscCode;
	private String accountNo;
	private short addUpdate; // 0 - Add / 1 - update
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	private BankDetails bankDetails;
	private int bankId;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public short getAddUpdate() {
		return addUpdate;
	}
	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeId:"+employeeId+" |  bankName:"+this.bankName+" | accountNo:"+this.accountNo 
				+" | ifscCode:"+ifscCode+" | addUpdate:"+addUpdate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public BankDetails getBankDetails() {
		return bankDetails;
	}
	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	

}
