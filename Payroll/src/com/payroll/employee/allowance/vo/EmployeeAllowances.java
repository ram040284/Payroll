package com.payroll.employee.allowance.vo;

public class EmployeeAllowances {
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

	public EmployeeAllowances(){
		
	}

	/**
	 */
	public EmployeeAllowances(int employeeId, double cca, double washingAlwance, double nonPracAwance, 
			double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag,Boolean qtrFlag,Boolean afkFlag,Boolean taFlag) {
		this.employeeId = employeeId;
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
	
	@Override
	public String toString() {
		return "EmployeeAllowances [employeeId=" + employeeId + ", cca=" + cca + ", washingAlwance=" + washingAlwance
				+ ", nonPracAwance=" + nonPracAwance + ", uniformAlwance=" + uniformAlwance + ", familyPlanAlwance="
				+ familyPlanAlwance + ", cycleAlwance=" + cycleAlwance + ", hraFlag=" + hraFlag + ", qtrFlag=" + qtrFlag
				+ ", afkFlag=" + afkFlag + ", taFlag=" + taFlag + "]";
	}

}
