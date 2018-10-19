package com.payroll.employee.pension.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	private Timestamp rowUpdDate;
	private short addUpdate;
	private String status;
	private double dearnessReliefArrears;
	private String department;
	private String headName;
	private String designation;
	private String joiningDate;
	private String retirementDate;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
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
	public PensionVO (String empId, String fName, String lName, double basicPension,double residualPension,double medicalAllowance,double commutationAmount
			,double dearnessReliefArrears,byte familyPensionFlag,String familyPensionName,String pensionRemark){
		this.employeeId = empId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.medicalAllowance = medicalAllowance;
		this.commutationAmount = commutationAmount;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.dearnessReliefArrears = dearnessReliefArrears;
		this.familyPensionFlag = familyPensionFlag;
		this.familyPensionName = familyPensionName;
		this.pensionRemark = pensionRemark;
	}
	
	public PensionVO (String empId, double basicPension,double residualPension,double medicalAllowance,double commutationAmount, 
			String pensionRemark, byte familyPensionFlag,String familyPensionName,double dearnessReliefArrears){
		this.employeeId = empId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.medicalAllowance = medicalAllowance;
		this.commutationAmount = commutationAmount;
		this.pensionRemark = pensionRemark;
		this.familyPensionFlag = familyPensionFlag;
		this.familyPensionName = familyPensionName;
		this.dearnessReliefArrears = dearnessReliefArrears;
	}
	
	public PensionVO(String empId, String fName, String lName, double basicPension, double residualPension, double medicalAllowance, double commutationAmount
			,double dearnessReliefArrears, String department, String headName, String designation, Date joiningDate, Date retirementDate ,String familyPensionName){
		this.employeeId = empId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.medicalAllowance = medicalAllowance;
		this.commutationAmount = commutationAmount;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.dearnessReliefArrears = dearnessReliefArrears;
		this.department = department;
		this.headName = headName;
		this.designation = designation;
		this.joiningDate = (joiningDate != null) ? dateFormat.format(joiningDate) : "";
		this.retirementDate = (retirementDate != null) ? dateFormat.format(retirementDate) : "";
		this.familyPensionName = familyPensionName;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getDearnessReliefArrears() {
		return dearnessReliefArrears;
	}
	public void setDearnessReliefArrears(double dearnessReliefArrears) {
		this.dearnessReliefArrears = dearnessReliefArrears;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}
}
