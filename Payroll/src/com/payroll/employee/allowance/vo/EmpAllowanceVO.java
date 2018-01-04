package com.payroll.employee.allowance.vo;

import com.payroll.Utils;

public class EmpAllowanceVO {
	
	private int employeeId;
	private double cca;
	private double washingAlwance;
	private double convAlwance;
	private double nonPracAwance;
	private double uniformAlwance;
	private double familyPlanAlwance;
	private double cycleAlwance;
	private Boolean hraFlag;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	
	public EmpAllowanceVO(){
		
	}
	
	public EmpAllowanceVO(int employeeId, String firstName, String lastName, double cca, double washingAlwance, double convAlwance,
			double nonPracAwance, double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag) {
		this.employeeId = employeeId;
		StringBuffer fullNameSB = new StringBuffer(firstName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lastName));
		this.fullName = fullNameSB.toString();
		this.cca = cca;
		this.washingAlwance = washingAlwance;
		this.nonPracAwance = nonPracAwance;
		this.uniformAlwance = uniformAlwance;
		this.familyPlanAlwance = familyPlanAlwance;
		this.cycleAlwance = cycleAlwance;
		this.hraFlag = hraFlag;
		this.convAlwance = convAlwance;
	}
	
	public EmpAllowanceVO(int employeeId, int departmentId, int designationId, int headId, double cca, double washingAlwance, double convAlwance,
			double nonPracAwance, double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag) {
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.cca = cca;
		this.washingAlwance = washingAlwance;
		this.nonPracAwance = nonPracAwance;
		this.uniformAlwance = uniformAlwance;
		this.familyPlanAlwance = familyPlanAlwance;
		this.cycleAlwance = cycleAlwance;
		this.hraFlag = hraFlag;
		this.convAlwance = convAlwance;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public double getCca() {
		return cca;
	}
	public double getWashingAlwance() {
		return washingAlwance;
	}
	public double getConvAlwance() {
		return convAlwance;
	}
	public double getNonPracAwance() {
		return nonPracAwance;
	}
	public double getUniformAlwance() {
		return uniformAlwance;
	}
	public double getFamilyPlanAlwance() {
		return familyPlanAlwance;
	}
	public double getCycleAlwance() {
		return cycleAlwance;
	}
	public Boolean getHraFlag() {
		return hraFlag;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public int getHeadId() {
		return headId;
	}
	public int getDesignationId() {
		return designationId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setCca(double cca) {
		this.cca = cca;
	}

	public void setWashingAlwance(double washingAlwance) {
		this.washingAlwance = washingAlwance;
	}

	public void setConvAlwance(double convAlwance) {
		this.convAlwance = convAlwance;
	}

	public void setNonPracAwance(double nonPracAwance) {
		this.nonPracAwance = nonPracAwance;
	}

	public void setUniformAlwance(double uniformAlwance) {
		this.uniformAlwance = uniformAlwance;
	}

	public void setFamilyPlanAlwance(double familyPlanAlwance) {
		this.familyPlanAlwance = familyPlanAlwance;
	}

	public void setCycleAlwance(double cycleAlwance) {
		this.cycleAlwance = cycleAlwance;
	}

	public void setHraFlag(Boolean hraFlag) {
		this.hraFlag = hraFlag;
	}
	
	
	
}
