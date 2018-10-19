package com.payroll.hrms.payroll.dataobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payroll.employee.dataobjects.Employee;

public class PensionPaybillDetails {
	
	private String deptName;
	private String headName;
	private String employeeId;
	private Employee employee;
	private double basicPension;
	private double da;
	private double totalPensionDeduction;
	private double netPension;
	private double residualPension;
	private double dearnessReliefArrears;
	private String fullName;
	private double commutationAmount;
	private double medicalAllowance;
	private byte familyPensionFlag;
	private String familyPensionName;
	private String pensionRemark;
	private Date month;
	private String designation;
	private String retirementDate;
	
	private List<PensionReportDetails> payrollList;
	
	public void addPensionEmployeePayroll(PensionReportDetails reportDetails){
		deptName+=reportDetails.getDepartment();
		headName+=reportDetails.getHeadName();
		employeeId+=reportDetails.getEmployeeId();
		basicPension+=reportDetails.getBasicPension();
		da+=reportDetails.getDa();
		totalPensionDeduction+=reportDetails.getTotalPensionDeduction();
		netPension+=reportDetails.getNetPension();
		residualPension+=reportDetails.getResidualPension();
		dearnessReliefArrears+=reportDetails.getDearnessReliefArrears();
		fullName+=reportDetails.getFullName();
		commutationAmount+=reportDetails.getCommutationAmount();
		medicalAllowance+=reportDetails.getMedicalAllowance();
		familyPensionFlag+=reportDetails.getFamilyPensionFlag();
		familyPensionName+=reportDetails.getFamilyPensionName();
		pensionRemark+=reportDetails.getPensionRemark();
		designation+=reportDetails.getDesignation();
		retirementDate+=reportDetails.getRetirementDate();
		//month+=reportDetails.getMonth();
		
		if(payrollList == null)
			payrollList = new ArrayList<PensionReportDetails>();
		payrollList.add(reportDetails);
		
	}
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	public double getDearnessReliefArrears() {
		return dearnessReliefArrears;
	}
	public void setDearnessReliefArrears(double dearnessReliefArrears) {
		this.dearnessReliefArrears = dearnessReliefArrears;
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

	public List<PensionReportDetails> getPayrollList() {
		return payrollList;
	}

	public void setPayrollList(List<PensionReportDetails> payrollList) {
		this.payrollList = payrollList;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}
}
