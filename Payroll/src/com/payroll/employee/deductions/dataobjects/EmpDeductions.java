package com.payroll.employee.deductions.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class EmpDeductions implements Serializable{
	private String employeeId;
	private double section80C;
	private double cess;
	private double homeLoanIntrst88EE;
	private double selfDisable80U;
	private double loanPrincipal;
	private double schoolFees; 
	private double lic; 
	private double mutualFund;
	private double section80D;
	private double section80E;
	private double nsc;
	private double ppf; 
	private double donation;
	private double section80DD;
	private Double bonus;
	private Double otWages;
	private Double arrears;
	private Double otAmount;
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Date rowUpdDate;
	private Employee employee;
	
	// added new fields as per the requirements.
	private double hra_section10_13A;
	private double income_tax_rebate_section_87C;
	private double child_trans_allw_10_14;
	private double home_loan_section_24B;
	private double hlp_pf_lic_80C;
	private double nps_80CCD_1B;
	private double health_insu_80D;
	private double des_dep_80DD;
	private double medical_80DDB;
	private double edu_load_80D;
	private double donation_80G;
	private double rent_80GG;
	private double int_bank_section_80TTA;
	private double phys_dis_per_section_80U;
	
	//added new fields
	private int headId;
	private int designationId;
	private String fullName;
	private int departmentId;
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public double getSection80C() {
		return section80C;
	}
	public void setSection80C(double section80c) {
		section80C = section80c;
	}
	public double getCess() {
		return cess;
	}
	public void setCess(double cess) {
		this.cess = cess;
	}
	public double getHomeLoanIntrst88EE() {
		return homeLoanIntrst88EE;
	}
	public void setHomeLoanIntrst88EE(double homeLoanIntrst88EE) {
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
	}
	public double getSelfDisable80U() {
		return selfDisable80U;
	}
	public void setSelfDisable80U(double selfDisable80U) {
		this.selfDisable80U = selfDisable80U;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public short getAddUpdate() {
		return addUpdate;
	}
	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}
	public Date getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Date rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public double getLoanPrincipal() {
		return loanPrincipal;
	}
	public void setLoanPrincipal(double loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}
	public double getSchoolFees() {
		return schoolFees;
	}
	public void setSchoolFees(double schoolFees) {
		this.schoolFees = schoolFees;
	}
	public double getLic() {
		return lic;
	}
	public void setLic(double lic) {
		this.lic = lic;
	}
	public double getMutualFund() {
		return mutualFund;
	}
	public void setMutualFund(double mutualFund) {
		this.mutualFund = mutualFund;
	}
	public double getSection80D() {
		return section80D;
	}
	public void setSection80D(double section80d) {
		section80D = section80d;
	}
	public double getSection80E() {
		return section80E;
	}
	public void setSection80E(double section80e) {
		section80E = section80e;
	}
	public double getNsc() {
		return nsc;
	}
	public void setNsc(double nsc) {
		this.nsc = nsc;
	}
	public double getPpf() {
		return ppf;
	}
	public void setPpf(double ppf) {
		this.ppf = ppf;
	}
	public double getDonation() {
		return donation;
	}
	public void setDonation(double donation) {
		this.donation = donation;
	}
	public double getSection80DD() {
		return section80DD;
	}
	public void setSection80DD(double section80dd) {
		section80DD = section80dd;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Double getOtWages() {
		return otWages;
	}
	public void setOtWages(Double otWages) {
		this.otWages = otWages;
	}
	public Double getArrears() {
		return arrears;
	}
	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}
	public Double getOtAmount() {
		return otAmount;
	}
	public void setOtAmount(Double otAmount) {
		this.otAmount = otAmount;
	}
	public double getHra_section10_13A() {
		return hra_section10_13A;
	}
	public void setHra_section10_13A(double hra_section10_13A) {
		this.hra_section10_13A = hra_section10_13A;
	}
	public double getIncome_tax_rebate_section_87C() {
		return income_tax_rebate_section_87C;
	}
	public void setIncome_tax_rebate_section_87C(double income_tax_rebate_section_87C) {
		this.income_tax_rebate_section_87C = income_tax_rebate_section_87C;
	}
	public double getChild_trans_allw_10_14() {
		return child_trans_allw_10_14;
	}
	public void setChild_trans_allw_10_14(double child_trans_allw_10_14) {
		this.child_trans_allw_10_14 = child_trans_allw_10_14;
	}
	public double getHome_loan_section_24B() {
		return home_loan_section_24B;
	}
	public void setHome_loan_section_24B(double home_loan_section_24B) {
		this.home_loan_section_24B = home_loan_section_24B;
	}
	public double getHlp_pf_lic_80C() {
		return hlp_pf_lic_80C;
	}
	public void setHlp_pf_lic_80C(double hlp_pf_lic_80C) {
		this.hlp_pf_lic_80C = hlp_pf_lic_80C;
	}
	public double getNps_80CCD_1B() {
		return nps_80CCD_1B;
	}
	public void setNps_80CCD_1B(double nps_80ccd_1b) {
		nps_80CCD_1B = nps_80ccd_1b;
	}
	public double getHealth_insu_80D() {
		return health_insu_80D;
	}
	public void setHealth_insu_80D(double health_insu_80D) {
		this.health_insu_80D = health_insu_80D;
	}
	public double getDes_dep_80DD() {
		return des_dep_80DD;
	}
	public void setDes_dep_80DD(double des_dep_80DD) {
		this.des_dep_80DD = des_dep_80DD;
	}
	public double getMedical_80DDB() {
		return medical_80DDB;
	}
	public void setMedical_80DDB(double medical_80ddb) {
		medical_80DDB = medical_80ddb;
	}
	public double getEdu_load_80D() {
		return edu_load_80D;
	}
	public void setEdu_load_80D(double edu_load_80D) {
		this.edu_load_80D = edu_load_80D;
	}
	public double getDonation_80G() {
		return donation_80G;
	}
	public void setDonation_80G(double donation_80g) {
		donation_80G = donation_80g;
	}
	public double getRent_80GG() {
		return rent_80GG;
	}
	public void setRent_80GG(double rent_80gg) {
		rent_80GG = rent_80gg;
	}
	public double getInt_bank_section_80TTA() {
		return int_bank_section_80TTA;
	}
	public void setInt_bank_section_80TTA(double int_bank_section_80TTA) {
		this.int_bank_section_80TTA = int_bank_section_80TTA;
	}
	public double getPhys_dis_per_section_80U() {
		return phys_dis_per_section_80U;
	}
	public void setPhys_dis_per_section_80U(double phys_dis_per_section_80U) {
		this.phys_dis_per_section_80U = phys_dis_per_section_80U;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
