package com.payroll.employee.deductions.vo;

import com.payroll.Utils;

public class EmpDeductionsVO {
	private int employeeId;
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

	public EmpDeductionsVO(){
		
	}
	
	public EmpDeductionsVO(int employeeId, String firstName, String lastName, double section80C, double cess, 
			double homeLoanIntrst88EE, double selfDisable80U, double loanPrincipal, double schoolFees, double lic, 
			double mutualFund, double section80D, double section80E , double nsc, double ppf, double donation,
			double section80DD, Double arrears, Double bonus, Double otAmount, Double otWages){
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
		this.schoolFees  = schoolFees;
		this.lic = lic; 
		this.mutualFund = mutualFund;
		this.section80D = section80D;
		this.section80E = section80E;
		this.nsc = nsc;
		this.ppf = ppf; 
		this.donation = donation;
		this.section80DD = section80DD;
		if(arrears != null)
		this.arrears = arrears;
		if(otAmount != null)
		this.otAmount = otAmount;
		if(otWages != null)
		this.otWages = otWages;
		if(bonus != null)
		this.bonus = bonus;
	}
	
	public EmpDeductionsVO(int employeeId, int deptId, int desgId, int headId, double section80C, double cess, 
			double homeLoanIntrst88EE, double selfDisable80U, double loanPrincipal, double schoolFees, double lic, 
			double mutualFund, double section80D, double section80E , double nsc, double ppf, double donation,
			double section80DD, Double arrears, Double bonus, Double otAmount, Double otWages){
		this.employeeId = employeeId;
		this.departmentId = deptId;
		this.headId = headId;
		this.designationId = desgId;
		this.section80C = section80C;
		this.cess = cess;
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
		this.selfDisable80U = selfDisable80U;
		this.loanPrincipal = loanPrincipal;
		this.schoolFees  = schoolFees;
		this.lic = lic; 
		this.mutualFund = mutualFund;
		this.section80D = section80D;
		this.section80E = section80E;
		this.nsc = nsc;
		this.ppf = ppf; 
		this.donation = donation;
		this.section80DD = section80DD;
		if(arrears != null)
			this.arrears = arrears;
			if(otAmount != null)
			this.otAmount = otAmount;
			if(otWages != null)
			this.otWages = otWages;
			if(bonus != null)
			this.bonus = bonus;
	}
	
	public int getEmployeeId() {
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

	public void setEmployeeId(int employeeId) {
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
				+ ", nsc=" + nsc + ", section80E=" + section80E + ", section80D=" + section80D + ", mutualFund=" + mutualFund
				+ ", ppf=" + ppf + ", donation=" + donation + ", section80DD="+section80DD+"]";
	}

}
