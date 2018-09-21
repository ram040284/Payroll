package com.payroll.employee.deductions.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpDeductionsVO {
	private String employeeId;
	private double section80C;
	private double cess;
	private double homeLoanIntrst88EE;
	private double selfDisable80U;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
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
	private double bonus;
	private double otWages;
	private double arrears;
	private double otAmount;
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
	private String firstName;
	

	public EmpDeductionsVO(String employeeId, String firstName, String lastName, double section80C, double cess) {
		this.employeeId = employeeId;
		this.section80C = section80C;
		this.cess = cess;
	}
	
	public EmpDeductionsVO(String employeeId, String firstName, String lastName, double section80C, double cess,
			double homeLoanIntrst88EE, double selfDisable80U, double loanPrincipal, double schoolFees, double lic,
			double mutualFund, double section80D, double section80E, double nsc, double ppf, double donation,
			double section80DD, Double arrears, Double bonus, Double otAmount, Double otWages, double hra_section10_13A,
			double income_tax_rebate_section_87C, double child_trans_allw_10_14, double home_loan_section_24B,
			double hlp_pf_lic_80C, double nps_80CCD_1B, double health_insu_80D, double des_dep_80DD,
			double medical_80DDB, double edu_load_80D, double donation_80G, double rent_80GG,
			double int_bank_section_80TTA, double phys_dis_per_section_80U, Date rowUpdDate, String status) {
		this.employeeId = employeeId;
		this.section80C = section80C;
		this.cess = cess;
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
		this.selfDisable80U = selfDisable80U;
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
		this.loanPrincipal = loanPrincipal;
		this.schoolFees = schoolFees;
		this.lic = lic;
		this.mutualFund = mutualFund;
		this.section80D = section80D;
		this.section80E = section80E;
		this.nsc = nsc;
		this.ppf = ppf;
		this.donation = donation;
		this.section80DD = section80DD;
		if (arrears != null)
			this.arrears = arrears;
		if (otAmount != null)
			this.otAmount = otAmount;
		if (otWages != null)
			this.otWages = otWages;
		if (bonus != null)
			this.bonus = bonus;
		this.hra_section10_13A = hra_section10_13A;
		this.income_tax_rebate_section_87C = income_tax_rebate_section_87C;
		this.child_trans_allw_10_14 = child_trans_allw_10_14;
		this.home_loan_section_24B = home_loan_section_24B;
		this.hlp_pf_lic_80C = hlp_pf_lic_80C;
		this.nps_80CCD_1B = nps_80CCD_1B;
		this.health_insu_80D = this.des_dep_80DD = this.medical_80DDB = medical_80DDB;
		this.edu_load_80D = edu_load_80D;
		this.donation_80G = donation_80G;
		this.rent_80GG = rent_80GG;
		this.int_bank_section_80TTA = int_bank_section_80TTA;
		this.phys_dis_per_section_80U = phys_dis_per_section_80U;
		this.rowUpdDate = rowUpdDate;
		this.status = status;
	}

	public EmpDeductionsVO(String employeeId, int deptId, int desgId, int headId, double section80C, double cess,
			double homeLoanIntrst88EE, double selfDisable80U, double loanPrincipal, double schoolFees, double lic,
			double mutualFund, double section80D, double section80E, double nsc, double ppf, double donation,
			double section80DD, Double arrears, Double bonus, Double otAmount, Double otWages) {
		this.employeeId = employeeId;
		this.departmentId = deptId;
		this.headId = headId;
		this.designationId = desgId;
		this.section80C = section80C;
		this.cess = cess;
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
		this.selfDisable80U = selfDisable80U;
		this.loanPrincipal = loanPrincipal;
		this.schoolFees = schoolFees;
		this.lic = lic;
		this.mutualFund = mutualFund;
		this.section80D = section80D;
		this.section80E = section80E;
		this.nsc = nsc;
		this.ppf = ppf;
		this.donation = donation;
		this.section80DD = section80DD;
		if (arrears != null)
			this.arrears = arrears;
		if (otAmount != null)
			this.otAmount = otAmount;
		if (otWages != null)
			this.otWages = otWages;
		if (bonus != null)
			this.bonus = bonus;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public double getBonus() {
		return bonus;
	}

	public double getOtWages() {
		return otWages;
	}

	public double getArrears() {
		return arrears;
	}

	public double getOtAmount() {
		return otAmount;
	}

	public EmpDeductionsVO() {

	}

	public String getEmployeeId() {
		return employeeId;
	}

	public double getSection80C() {
		return section80C;
	}

	public double getCess() {
		return cess;
	}

	public double getHomeLoanIntrst88EE() {
		return homeLoanIntrst88EE;
	}

	public double getSelfDisable80U() {
		return selfDisable80U;
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

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public double getLoanPrincipal() {
		return loanPrincipal;
	}

	public double getSchoolFees() {
		return schoolFees;
	}

	public double getLic() {
		return lic;
	}

	public double getMutualFund() {
		return mutualFund;
	}

	public double getSection80D() {
		return section80D;
	}

	public double getSection80E() {
		return section80E;
	}

	public double getNsc() {
		return nsc;
	}

	public double getPpf() {
		return ppf;
	}

	public double getDonation() {
		return donation;
	}

	public double getSection80DD() {
		return section80DD;
	}

	@Override
	public String toString() {
		return "EmployeeFixedDeductions [employeeId=" + employeeId + ", section80C=" + section80C + ", lic=" + lic
				+ ", nsc=" + nsc + ", section80E=" + section80E + ", section80D=" + section80D + ", mutualFund="
				+ mutualFund + ", ppf=" + ppf + ", donation=" + donation + ", section80DD=" + section80DD + "]";
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

}
