package com.payroll.employee.bank.vo;

import java.util.Date;

import com.payroll.Utils;

public class BankVO {
	
	private int employeeId;
	private String bankName;
	private String ifscCode;
	private String accountNo;
	private String fullName;
	private int departmentId;
	private int designationId;
	private int headId;
	private int bankId;
	public BankVO() {
		
	}
	
	public BankVO(int empId, String fName, String lName, int bankId, String bankName, String ifscCode, String accountNo) {
		this.employeeId = empId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.bankId = bankId;
	}
	
	public BankVO(int empId, int deptId, int desgId, int headId, int bankId, String bankName , String ifscCode, String accountNo) {
		this.employeeId = empId;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.accountNo = accountNo;
		this.departmentId = deptId;
		this.designationId = desgId;
		this.headId = headId;
		this.bankId = bankId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public String getBankName() {
		return bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public String getAccountNo() {
		return accountNo;
	}

	public String getFullName() {
		return fullName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public int getDesignationId() {
		return designationId;
	}

	public int getHeadId() {
		return headId;
	}

	public int getBankId() {
		return bankId;
	}
	

}
