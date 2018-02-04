package com.payroll.hrms.payroll.dataobjects;


import java.util.ArrayList;
import java.util.Date;

import com.payroll.Utils;

public class ReportDetails {
	
	private double gradePay;
	private double basic;
	private double dearnessAllowance;
	private double houseRentAllowance;
	private double cca;
	private double travelAllowance;
	private double nonPracticingAllowance;
	private double washingAllowance;
	private double conveyanceAllowance;
	private double uniformAllowance;
	private double familyPlanningAllowance;
	private double totalAllowance;
	private double overTimeAmount;
	private double otherPayAmount;
	private double rent;
	private double afkRent;
	private double absentAmount;
	private double festAdvRecovery;
	private double profTax;
	private double lic;
	private double societyInstallment;
	private double grpInsurance;
	private double bankLoanRcry;
	private double vehclLoanRcry;
	private double providentFund;
	private double apfacpf;
	private double pfLoanRecovery;
	private double cpfRecovery;
	private double incomeTax;
	private double unionFee;
	private double electricityRecovery;
	private double courtRecovery;
	private double otherDeductions;
	private double miscAllowance;
	private double grossPay;
	private double unionFeeKss;
	private double totalDeductions;
	private double netPay;
	private double lfee;
	private Date month;
	private String department;
	private String designation;
	private String deptCostHead;
	private String bankAcctNo;
	private String bankName;
	private String employeeName;
	private String panNo;
	private String dob;
	private String joiningDate;
	private String retirementDate;
	private String scale;
	private double incrementAmt;
	private String incrementDate;
	private String gender;
	private String pfNumber;
	private String employeeNumber;
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPfNumber() {
		return pfNumber;
	}
	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}
	public double getGradePay() {
		return gradePay;
	}
	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
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
	public double getCca() {
		return cca;
	}
	public void setCca(double cca) {
		this.cca = cca;
	}
	public double getTravelAllowance() {
		return travelAllowance;
	}
	public void setTravelAllowance(double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}
	public double getNonPracticingAllowance() {
		return nonPracticingAllowance;
	}
	public void setNonPracticingAllowance(double nonPracticingAllowance) {
		this.nonPracticingAllowance = nonPracticingAllowance;
	}
	public double getWashingAllowance() {
		return washingAllowance;
	}
	public void setWashingAllowance(double washingAllowance) {
		this.washingAllowance = washingAllowance;
	}
	public double getConveyanceAllowance() {
		return conveyanceAllowance;
	}
	public void setConveyanceAllowance(double conveyanceAllowance) {
		this.conveyanceAllowance = conveyanceAllowance;
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
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public double getAfkRent() {
		return afkRent;
	}
	public void setAfkRent(double afkRent) {
		this.afkRent = afkRent;
	}
	public double getAbsentAmount() {
		return absentAmount;
	}
	public void setAbsentAmount(double absentAmount) {
		this.absentAmount = absentAmount;
	}
	public double getFestAdvRecovery() {
		return festAdvRecovery;
	}
	public void setFestAdvRecovery(double festAdvRecovery) {
		this.festAdvRecovery = festAdvRecovery;
	}
	public double getProfTax() {
		return profTax;
	}
	public void setProfTax(double profTax) {
		this.profTax = profTax;
	}
	public double getLic() {
		return lic;
	}
	public void setLic(double lic) {
		this.lic = lic;
	}
	public double getSocietyInstallment() {
		return societyInstallment;
	}
	public void setSocietyInstallment(double societyInstallment) {
		this.societyInstallment = societyInstallment;
	}
	public double getGrpInsurance() {
		return grpInsurance;
	}
	public void setGrpInsurance(double grpInsurance) {
		this.grpInsurance = grpInsurance;
	}
	public double getBankLoanRcry() {
		return bankLoanRcry;
	}
	public void setBankLoanRcry(double bankLoanRcry) {
		this.bankLoanRcry = bankLoanRcry;
	}
	public double getVehclLoanRcry() {
		return vehclLoanRcry;
	}
	public void setVehclLoanRcry(double vehclLoanRcry) {
		this.vehclLoanRcry = vehclLoanRcry;
	}
	public double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}
	public double getApfacpf() {
		return apfacpf;
	}
	public void setApfacpf(double apfacpf) {
		this.apfacpf = apfacpf;
	}
	public double getPfLoanRecovery() {
		return pfLoanRecovery;
	}
	public void setPfLoanRecovery(double pfLoanRecovery) {
		this.pfLoanRecovery = pfLoanRecovery;
	}
	public double getCpfRecovery() {
		return cpfRecovery;
	}
	public void setCpfRecovery(double cpfRecovery) {
		this.cpfRecovery = cpfRecovery;
	}
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomTax) {
		this.incomeTax = incomTax;
	}
	public double getUnionFee() {
		return unionFee;
	}
	public void setUnionFee(double unionFee) {
		this.unionFee = unionFee;
	}
	public double getElectricityRecovery() {
		return electricityRecovery;
	}
	public void setElectricityRecovery(double electricityRecovery) {
		this.electricityRecovery = electricityRecovery;
	}
	public double getCourtRecovery() {
		return courtRecovery;
	}
	public void setCourtRecovery(double courtRecovery) {
		this.courtRecovery = courtRecovery;
	}
	public double getOtherDeductions() {
		return otherDeductions;
	}
	public void setOtherDeductions(double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}
	public double getMiscAllowance() {
		return miscAllowance;
	}
	public void setMiscAllowance(double miscAllowance) {
		this.miscAllowance = miscAllowance;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	public double getUnionFeeKss() {
		return unionFeeKss;
	}
	public void setUnionFeeKss(double unionFeeKss) {
		this.unionFeeKss = unionFeeKss;
	}
	public double getTotalDeductions() {
		return totalDeductions;
	}
	public void setTotalDeductions(double totalDeductions) {
		this.totalDeductions = totalDeductions;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public double getLfee() {
		return lfee;
	}
	public void setLfee(double lfee) {
		this.lfee = lfee;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDeptCostHead() {
		return deptCostHead;
	}
	public void setDeptCostHead(String deptCostHead) {
		this.deptCostHead = deptCostHead;
	}
	public String getBankAcctNo() {
		return bankAcctNo;
	}
	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
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
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public double getIncrementAmt() {
		return incrementAmt;
	}
	public void setIncrementAmt(double incrementAmt) {
		this.incrementAmt = incrementAmt;
	}
	public String getIncrementDate() {
		return incrementDate;
	}
	public void setIncrementDate(String incrementDate) {
		this.incrementDate = incrementDate;
	}
	
	/*protected void addPayrollTotals(EmployeePayroll payroll){
		gradePay+=payroll.getGradePay();
		basic +=payroll.getBasic();
		dearnessAllowance += payroll.getDearnessAllowance();
		houseRentAllowance+= payroll.getHouseRentAllowance();
		cca+= payroll.getCca();
		travelAllowance+= payroll.getTravelAllowance();
		nonPracticingAllowance+= payroll.getNonPracticingAllowance();
		washingAllowance+= payroll.getWashingAllowance();
		conveyanceAllowance+= payroll.getConveyanceAllowance();
		uniformAllowance+=payroll.getUniformAllowance();
		familyPlaningAlw+= payroll.getFamilyPlanningAllowance();
		totallw += payroll.getTotalAllowance();
		otAmt += payroll.getOverTimeAmount();
		others += payroll.getOtherPayAmount();
		//rent+= payroll.getRe;
		afkRent += payroll.getAfkRent();
		absentDed+= payroll.getAbsentAmount();
		festAdvRcry += payroll.getFestAdvRecovery();
		pt += payroll.getProfTax();
		lic+= payroll.getLic();
		socity += payroll.getSocietyInstallment();
		gis+= payroll.getGrpInsurance();
		bankLoanRcry += payroll.getBankLoanRcry();
		vehclLoanRcry += payroll.getVehclLoanRcry();
		pfsCpf +=payroll.getProvidentFund();
		apfAcf+= payroll.getApfacpf();
		
		pfLoanRcry+= payroll.getPfLoanRecovery();
		cpfRcry+= payroll.getCpfRecovery();
		incomTax += payroll.getIncomeTax();
		unionFee += payroll.getUnionFee();
		elecRcry += payroll.getElectricityRecovery();
		courtRcry += payroll.getCourtRecovery();
		otherDeducs += payroll.getOtherDeductions();
		misc += payroll.getMiscAllowance();
	}*/
	
		
}
