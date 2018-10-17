package com.payroll.employee.pension.vo;

import java.io.Serializable;
import java.util.Date;

import com.payroll.Utils;

public class PensionVO implements Serializable{
	private String employeeId;
	private double basicPension;
	private double residualPension;
	private String fullName;
	private double commutationAmount;
	private double medicalAllowance;
	private byte familyPensionFlag;
	private String familyPensionName;
	private String pensionRemark;
	
	public double getBasicPension() {
		return basicPension;
	}
	public void setBasicPension(double basicPension) {
		this.basicPension = basicPension;
	}
	public double getResidualPension() {
		return residualPension;
	}
	public void setResidualPension(double residualPension) {
		this.residualPension = residualPension;
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
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public PensionVO() {
		
	}
	public PensionVO (String empId, String fName, String lName, double basicPension,double residualPension,double medicalAllowance,double commutationAmt){
		this.employeeId = empId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.medicalAllowance = medicalAllowance;
		this.commutationAmount = commutationAmt;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}
	
	public PensionVO (String empId, double basicPension,double residualPension,double medicalAllowance,double commutationAmt){
		this.employeeId = empId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.medicalAllowance = medicalAllowance;
		this.commutationAmount = commutationAmt;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public String getFullName() {
		return fullName;
	}
	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}
}
