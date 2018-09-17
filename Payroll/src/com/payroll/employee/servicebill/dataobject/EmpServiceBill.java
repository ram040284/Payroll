package com.payroll.employee.servicebill.dataobject;

import java.util.Date;

public class EmpServiceBill {
	
	private String fName;
	private String mName;
	private String lName;
	private String empPan;
	private Date birthDate;
	private String empAdhar;
	private Date empJoiningDate;
	private Date empLastPromDate;
	private String deptName;
	private String desgname;
	private double basic;
	private double gradePay;
	private String scalePay;
	private String scaleCode;
	private String accountNumber;
	private String bankName;
	private String ifscCode;
	
	public EmpServiceBill() {}
	
	public EmpServiceBill(String fName, String mName, String lName, String empPan, Date birthDate,
			String empAdhar, Date empJoiningDate, Date empLastPromDate, String deptName, String desgname, double basic,
			double gradePay, String scalePay, String scaleCode, String accountNumber, String bankName, String ifscCode) {
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
		this.empPan = empPan;
		this.birthDate = birthDate;
		this.empAdhar = empAdhar;
		this.empJoiningDate = empJoiningDate;
		this.empLastPromDate = empLastPromDate;
		this.deptName = deptName;
		this.desgname = desgname;
		this.basic = basic;
		this.gradePay = gradePay;
		this.scalePay = scalePay;
		this.scaleCode = scaleCode;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.ifscCode = ifscCode;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmpPan() {
		return empPan;
	}
	public void setEmpPan(String empPan) {
		this.empPan = empPan;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmpAdhar() {
		return empAdhar;
	}
	public void setEmpAdhar(String empAdhar) {
		this.empAdhar = empAdhar;
	}
	public Date getEmpJoiningDate() {
		return empJoiningDate;
	}
	public void setEmpJoiningDate(Date empJoiningDate) {
		this.empJoiningDate = empJoiningDate;
	}
	public Date getEmpLastPromDate() {
		return empLastPromDate;
	}
	public void setEmpLastPromDate(Date empLastPromDate) {
		this.empLastPromDate = empLastPromDate;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDesgname() {
		return desgname;
	}
	public void setDesgname(String desgname) {
		this.desgname = desgname;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getGradePay() {
		return gradePay;
	}
	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}
	public String getScalePay() {
		return scalePay;
	}
	public void setScalePay(String scalePay) {
		this.scalePay = scalePay;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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

	public String getScaleCode() {
		return scaleCode;
	}

	public void setScaleCode(String scaleCode) {
		this.scaleCode = scaleCode;
	}
	
	

}
