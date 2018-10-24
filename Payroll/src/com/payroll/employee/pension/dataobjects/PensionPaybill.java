package com.payroll.employee.pension.dataobjects;

import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class PensionPaybill {
	
	private String employeeId;
	private Employee employee;
	private double basicPension;
	private double da;
	private double totalPensionDeduction;
	private double netPension;
	private double residualPension;
	private double dearnessRelief;
	private String fullName;
	private double commutationAmount;
	private double medicalAllowance;
	private byte familyPensionFlag;
	private String familyPensionName;
	private String pensionRemark;
	private Date month;
	private double arrears;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public double getBasicPension() {
		return basicPension;
	}
	public void setBasicPension(double basicPension) {
		this.basicPension = basicPension;
	}
	public double getDa() {
		return da;
	}
	public void setDa(double da) {
		this.da = da;
	}
	public double getTotalPensionDeduction() {
		return totalPensionDeduction;
	}
	public void setTotalPensionDeduction(double totalPensionDeduction) {
		this.totalPensionDeduction = totalPensionDeduction;
	}
	public double getNetPension() {
		return netPension;
	}
	public void setNetPension(double netPension) {
		this.netPension = netPension;
	}
	public double getResidualPension() {
		return residualPension;
	}
	public void setResidualPension(double residualPension) {
		this.residualPension = residualPension;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getCommutationAmount() {
		return commutationAmount;
	}
	public void setCommutationAmount(double commutationAmount) {
		this.commutationAmount = commutationAmount;
	}
	public double getMedicalAllowance() {
		return medicalAllowance;
	}
	public void setMedicalAllowance(double medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}
	public byte getFamilyPensionFlag() {
		return familyPensionFlag;
	}
	public void setFamilyPensionFlag(byte familyPensionFlag) {
		this.familyPensionFlag = familyPensionFlag;
	}
	public String getFamilyPensionName() {
		return familyPensionName;
	}
	public void setFamilyPensionName(String familyPensionName) {
		this.familyPensionName = familyPensionName;
	}
	public String getPensionRemark() {
		return pensionRemark;
	}
	public void setPensionRemark(String pensionRemark) {
		this.pensionRemark = pensionRemark;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getDearnessRelief() {
		return dearnessRelief;
	}
	public void setDearnessRelief(double dearnessRelief) {
		this.dearnessRelief = dearnessRelief;
	}
	public double getArrears() {
		return arrears;
	}
	public void setArrears(double arrears) {
		this.arrears = arrears;
	}
}
