package com.payroll.hrms.payroll.dataobjects;

public class EmployeePayrollEarnings {

    private double basic;
    private double gradePay;
    private double dearnessAllowance;
    private double houseRentAllowance;
    private double travelAllowance;
    private double cca;
    private double washingAllowance;
    private double nonPracticingAllowance;
    private double uniformAllowance;
    private double familyPlanningAllowance;
    private double miscAllowance;
    private double totalAllowance;
    private double overTimeAmount;
    private double otherPayAmount;
    private double grossPay;
    private double overTimeHours;
    private boolean handicappedFlag;
    private boolean taFlag; // false if employee leaves within 2km radiu of office
    private double cycleAllowance;

	public double getCycleAllowance() {
		return cycleAllowance;
	}
	public void setCycleAllowance(double cycleAllowance) {
		this.cycleAllowance = cycleAllowance;
	}
	public EmployeePayrollEarnings() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getOverTimeHours() {
		return overTimeHours;
	}
	public void setOverTimeHours(double overTimeHours) {
		this.overTimeHours = overTimeHours;
	}
	public boolean isHandicappedFlag() {
		return handicappedFlag;
	}
	public void setHandicappedFlag(boolean handicappedFlag) {
		this.handicappedFlag = handicappedFlag;
	}
	public boolean isTaFlag() {
		return taFlag;
	}
	public void setTaFlag(boolean taFlag) {
		this.taFlag = taFlag;
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
	public double getDearnessAllowance() {
		return dearnessAllowance;
	}
	public void setDearnessAllowance(double dearnessAllowance) {
		this.dearnessAllowance = dearnessAllowance;
	}
	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}
	public void setHouseRentAllowance(double houseRentAllowance) {
		this.houseRentAllowance = houseRentAllowance;
	}
	public double getTravelAllowance() {
		return travelAllowance;
	}
	public void setTravelAllowance(double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}
	public double getCca() {
		return cca;
	}
	public void setCca(double cca) {
		this.cca = cca;
	}
	public double getWashingAllowance() {
		return washingAllowance;
	}
	public void setWashingAllowance(double washingAllowance) {
		this.washingAllowance = washingAllowance;
	}
	public double getNonPracticingAllowance() {
		return nonPracticingAllowance;
	}
	public void setNonPracticingAllowance(double nonPracticingAllowance) {
		this.nonPracticingAllowance = nonPracticingAllowance;
	}
	public double getUniformAllowance() {
		return uniformAllowance;
	}
	public void setUniformAllowance(double uniformAllowance) {
		this.uniformAllowance = uniformAllowance;
	}
	public double getFamilyPlanningAllowance() {
		return familyPlanningAllowance;
	}
	public void setFamilyPlanningAllowance(double familyPlanningAllowance) {
		this.familyPlanningAllowance = familyPlanningAllowance;
	}
	public double getMiscAllowance() {
		return miscAllowance;
	}
	public void setMiscAllowance(double miscAllowance) {
		this.miscAllowance = miscAllowance;
	}
	public double getTotalAllowance() {
		return totalAllowance;
	}
	public void setTotalAllowance(double totalAllowance) {
		this.totalAllowance = totalAllowance;
	}
	public double getOverTimeAmount() {
		return overTimeAmount;
	}
	public void setOverTimeAmount(double overTimeAmount) {
		this.overTimeAmount = overTimeAmount;
	}
	public double getOtherPayAmount() {
		return otherPayAmount;
	}
	public void setOtherPayAmount(double otherPayAmount) {
		this.otherPayAmount = otherPayAmount;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
 
}
