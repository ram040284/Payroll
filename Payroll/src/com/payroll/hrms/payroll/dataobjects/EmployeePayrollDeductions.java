package com.payroll.hrms.payroll.dataobjects;

public class EmployeePayrollDeductions {

	private double absentAmount;
    private double afkRent;
    private double festAdvRecovery;
    private double profTax;
    private double lic;
    private double societyInstallment;
    private double grpInsurance;
    private boolean pfFlag;
    private double providentFund;
    private double additionalProventFund;
    private double pfRecovery;
    private double pfLoanRecovery;
    private double incomeTax;
    private double unionFee;
    private double unionFeeKss;
	private double electricityRecovery;
    private double courtRecovery;
    private double otherDeductions;
    private double totalDeductions;
    private double netPay;
    private double apfAcpf;
    private double cpfRecovery;
    private double lfee;
    
    public double getUnionFeeKss() {
    	return unionFeeKss;
    }
    public void setUnionFeeKss(double unionFeeKss) {
    	this.unionFeeKss = unionFeeKss;
    }
    
	public EmployeePayrollDeductions() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getAbsentAmount() {
		return absentAmount;
	}
	public void setAbsentAmount(double absentAmount) {
		this.absentAmount = absentAmount;
	}
	public double getAfkRent() {
		return afkRent;
	}
	public void setAfkRent(double afkRent) {
		this.afkRent = afkRent;
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
	public boolean isPfFlag() {
		return pfFlag;
	}
	public void setPfFlag(boolean pfFlag) {
		this.pfFlag = pfFlag;
	}
	public double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(double providentFund) {
		this.providentFund = providentFund;
	}
	public double getAdditionalProventFund() {
		return additionalProventFund;
	}
	public void setAdditionalProventFund(double additionalProventFund) {
		this.additionalProventFund = additionalProventFund;
	}
	public double getPfRecovery() {
		return pfRecovery;
	}
	public void setPfRecovery(double pfRecovery) {
		this.pfRecovery = pfRecovery;
	}
	public double getPfLoanRecovery() {
		return pfLoanRecovery;
	}
	public void setPfLoanRecovery(double pfLoanRecovery) {
		this.pfLoanRecovery = pfLoanRecovery;
	}
	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
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
	public double getApfAcpf() {
		return apfAcpf;
	}
	public void setApfAcpf(double apfAcpf) {
		this.apfAcpf = apfAcpf;
	}
	public double getCpfRecovery() {
		return cpfRecovery;
	}
	public void setCpfRecovery(double cpfRecovery) {
		this.cpfRecovery = cpfRecovery;
	}
	public double getLfee() {
		return lfee;
	}
	public void setLfee(double lfee) {
		this.lfee = lfee;
	}
}