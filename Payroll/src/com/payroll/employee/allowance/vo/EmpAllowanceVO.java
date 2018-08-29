package com.payroll.employee.allowance.vo;

import com.payroll.Utils;

public class EmpAllowanceVO {
	private int employeeId;
	private double cca;
	private double washingAlwance;
	private double nonPracAwance;
	private double uniformAlwance;
	private double familyPlanAlwance;
	private double cycleAlwance;
	private Boolean hraFlag;
	private Boolean qtrFlag;
	private Boolean afkFlag;
	private Boolean taFlag;
	private Boolean pfFlag;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	private double otherAllowance;
	
	public Boolean getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(Boolean pfFlag) {
		this.pfFlag = pfFlag;
	}

	public Boolean getQtrFlag() {
		return qtrFlag;
	}

	public void setQtrFlag(Boolean qtrFlag) {
		this.qtrFlag = qtrFlag;
	}

	public Boolean getAfkFlag() {
		return afkFlag;
	}

	public void setAfkFlag(Boolean afkFlag) {
		this.afkFlag = afkFlag;
	}

	public Boolean getTaFlag() {
		return taFlag;
	}

	public void setTaFlag(Boolean taFlag) {
		this.taFlag = taFlag;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public EmpAllowanceVO(){
		
	}
	
	public EmpAllowanceVO(int employeeId, String firstName, String lastName, double cca, double washingAlwance,	double nonPracAwance, 
			double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag,Boolean qtrFlag,Boolean afkFlag,Boolean taFlag,Boolean pfFlag, double otherAllowance) {
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
		this.qtrFlag = qtrFlag;
		this.afkFlag = afkFlag;
		this.taFlag = taFlag;
		this.pfFlag = pfFlag;
		this.otherAllowance = otherAllowance;
	}
	
	public EmpAllowanceVO(int employeeId, int departmentId, int designationId, int headId, double cca, double washingAlwance, double nonPracAwance, 
			double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag,Boolean qtrFlag,Boolean afkFlag,Boolean taFlag, Boolean pfFlag, double otherAllowance) {
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
		this.qtrFlag = qtrFlag;
		this.afkFlag = afkFlag;
		this.taFlag = taFlag;
		this.pfFlag = pfFlag;
		this.otherAllowance = otherAllowance;
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

	public double getOtherAllowance() {
		return otherAllowance;
	}

	public void setOtherAllowance(double otherAllowance) {
		this.otherAllowance = otherAllowance;
	}

}
