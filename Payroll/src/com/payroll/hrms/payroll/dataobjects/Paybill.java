package com.payroll.hrms.payroll.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class Paybill implements Serializable{

	private int employeeId;
	private Employee employee;
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
	private Timestamp rowUpdDate;
	private String department;
	private String designation;
	private String deptCostHead;
	private String bankAcctNo;
	private String bankName;
	private String incomeTax;
	private String scale;
	private double incrementAmt;
	private String incrementDate;
	private int bankId;
	
	/*private String employeeName;
	private String panNo;
	private String dob;
	private String joiningDate;
	private String retirementDate;
	*/
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public void setBasic(double basicPay) {
		this.basic = basicPay;
	}
	public double getDearnessAllowance() {
		return dearnessAllowance;
	}
	public void setDearnessAllowance(double da) {
		this.dearnessAllowance = da;
	}
	public double getHouseRentAllowance() {
		return houseRentAllowance;
	}
	public void setHouseRentAllowance(double hra) {
		this.houseRentAllowance = hra;
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
	public void setTravelAllowance(double ta) {
		this.travelAllowance = ta;
	}
	public double getNonPracticingAllowance() {
		return nonPracticingAllowance;
	}
	public void setNonPracticingAllowance(double npa) {
		this.nonPracticingAllowance = npa;
	}
	public double getWashingAllowance() {
		return washingAllowance;
	}
	public void setWashingAllowance(double wa) {
		this.washingAllowance = wa;
	}
	public double getConveyanceAllowance() {
		return conveyanceAllowance;
	}
	public void setConveyanceAllowance(double ca) {
		this.conveyanceAllowance = ca;
	}
	public double getUniformAllowance() {
		return uniformAllowance;
	}
	public void setUniformAllowance(double uniformAlw) {
		this.uniformAllowance = uniformAlw;
	}
	public double getFamilyPlanningAllowance() {
		return familyPlanningAllowance;
	}
	public void setFamilyPlanningAllowance(double familyPlaningAlw) {
		this.familyPlanningAllowance = familyPlaningAlw;
	}
	public double getTotalAllowance() {
		return totalAllowance;
	}
	public void setTotalAllowance(double totallw) {
		this.totalAllowance = totallw;
	}
	public double getOverTimeAmount() {
		return overTimeAmount;
	}
	public void setOverTimeAmount(double otAmt) {
		this.overTimeAmount = otAmt;
	}
	public double getOtherPayAmount() {
		return otherPayAmount;
	}
	public void setOtherPayAmount(double others) {
		this.otherPayAmount = others;
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
	public void setAbsentAmount(double absentDed) {
		this.absentAmount = absentDed;
	}
	public double getFestAdvRecovery() {
		return festAdvRecovery;
	}
	public void setFestAdvRecovery(double festAdvRcry) {
		this.festAdvRecovery = festAdvRcry;
	}
	public double getProfTax() {
		return profTax;
	}
	public void setProfTax(double pt) {
		this.profTax = pt;
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
	public void setSocietyInstallment(double socity) {
		this.societyInstallment = socity;
	}
	public double getGrpInsurance() {
		return grpInsurance;
	}
	public void setGrpInsurance(double gis) {
		this.grpInsurance = gis;
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
	public void setVehclLoanRcry(double vlr) {
		this.vehclLoanRcry = vlr;
	}
	public double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(double pfsCpf) {
		this.providentFund = pfsCpf;
	}
	public double getApfacpf() {
		return apfacpf;
	}
	public void setApfacpf(double apfAcf) {
		this.apfacpf = apfAcf;
	}
	public double getPfLoanRecovery() {
		return pfLoanRecovery;
	}
	public void setPfLoanRecovery(double pfLoanRcry) {
		this.pfLoanRecovery = pfLoanRcry;
	}
	public double getCpfRecovery() {
		return cpfRecovery;
	}
	public void setCpfRecovery(double cpfRcry) {
		this.cpfRecovery = cpfRcry;
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
	public void setElectricityRecovery(double elecRcry) {
		this.electricityRecovery = elecRcry;
	}
	public double getCourtRecovery() {
		return courtRecovery;
	}
	public void setCourtRecovery(double courtRcry) {
		this.courtRecovery = courtRcry;
	}
	public double getOtherDeductions() {
		return otherDeductions;
	}
	public void setOtherDeductions(double otherDeducs) {
		this.otherDeductions = otherDeducs;
	}
	public double getMiscAllowance() {
		return miscAllowance;
	}
	public void setMiscAllowance(double misc) {
		this.miscAllowance = misc;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double gross) {
		this.grossPay = gross;
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
	public void setTotalDeductions(double totalDeduct) {
		this.totalDeductions = totalDeduct;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}
	public double getLfee() {
		return lfee;
	}
	public void setLfee(double lfee) {
		this.lfee = lfee;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	/*public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}*/
	public String getDeptCostHead() {
		return deptCostHead;
	}
	public void setDeptCostHead(String deptCostHead) {
		this.deptCostHead = deptCostHead;
	}
	public String getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
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
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	
}
