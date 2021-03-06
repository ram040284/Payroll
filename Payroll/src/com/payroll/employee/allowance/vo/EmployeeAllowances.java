package com.payroll.employee.allowance.vo;

public class EmployeeAllowances {
	private String employeeId;
	private double cca;
	private double washingAlwance;
	private double nonPracAwance;
	private double uniformAlwance;
	private double familyPlanAlwance;
	private double cycleAlwance;
	private Boolean hraFlag;
	private Boolean qtrFlag;
	private Boolean afkFlag;
	private byte taFlag;
	private byte pfFlag;
	private double otherAllowance;
	private double tAllowance;
	private byte npaFlag;
	
	public EmployeeAllowances(){
		
	}
	
	/**
	 */
	public EmployeeAllowances(String employeeId, double cca, double washingAlwance, double nonPracAwance, 
			double uniformAlwance, double familyPlanAlwance, double cycleAlwance, Boolean hraFlag,Boolean qtrFlag,Boolean afkFlag,byte taFlag, byte pfFlag, byte npaFlag, double otherAllowance, double tAllowance) {
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
		this.pfFlag = pfFlag;
		this.otherAllowance = otherAllowance;
		this.tAllowance = tAllowance;
		this.npaFlag = npaFlag;
	}
	
		public byte getPFFlag() {
		return pfFlag;
	}

	public void setPFFlag(byte pFFlag) {
		pfFlag = pFFlag;
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

	public byte getTaFlag() {
		return taFlag;
	}

	public void setTaFlag(byte taFlag) {
		this.taFlag = taFlag;
	}
	
	public String getEmployeeId() {
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

	public void setEmployeeId(String employeeId) {
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

	public double gettAllowance() {
		return tAllowance;
	}

	public void settAllowance(double tAllowance) {
		this.tAllowance = tAllowance;
	}

	public byte getNpaFlag() {
		return npaFlag;
	}

	public void setNpaFlag(byte npaFlag) {
		this.npaFlag = npaFlag;
	}

	public byte getPfFlag() {
		return pfFlag;
	}

	public void setPfFlag(byte pfFlag) {
		this.pfFlag = pfFlag;
	}

	@Override
	public String toString() {
		return "EmployeeAllowances [employeeId=" + employeeId + ", cca=" + cca + ", washingAlwance=" + washingAlwance
				+ ", nonPracAwance=" + nonPracAwance + ", uniformAlwance=" + uniformAlwance + ", familyPlanAlwance="
				+ familyPlanAlwance + ", cycleAlwance=" + cycleAlwance + ", hraFlag=" + hraFlag + ", qtrFlag=" + qtrFlag
				+ ", afkFlag=" + afkFlag + ", taFlag=" + taFlag + "]";
	}

}
