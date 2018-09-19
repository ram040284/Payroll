package com.payroll.hrms.payroll.dataobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payroll.Utils;

public class PaybillDetails {
	private String deptName;
	private String headName;
	private String bankName;
	private double gradePay;
	private double basicPay;
	private double da;
	private double hra;
	private double cca;
	private double ta;
	private double npa;
	private double wa;
	private double ca;
	private double uniformAlw;
	private double familyPlaningAlw;
	private double totallw;
	private double otAmt;
	private double others;
	private double rent;
	private double afkRent;
	private double absentDed;
	private double festAdvRcry;
	private double pt;
	private double lic;
	private double socity;
	private double gis;
	private double bankLoanRcry;
	private double vlr;
	private double apfAcf;
	private double pfLoanRcry;
	private double pfsCpf;
	
	
	/*private double cpfRcry;*/
//	private double incomTax;
	private double unionFee;
	private double unionFeeKss;
	private double elecRcry;
	private double courtRcry;
	private double otherDeducs;
	private double misc;
	private double grossPay;
//	private double lfee;
	private double pfInstment;
	private double totalDeductions;
	private double totalGrossPay; 
	private double netPay;
	private Date month;
	private int noOfEmployees;
	private double incomeTax;
	private double tAllowance;
	private int employeeType;
	private String employeeId;
	
	
	
	//adding new columns for contratuary
    private int empAbsentDays;
	private int empPresentDays;
	private double contAbsDedAmt;
	private double contTDS;
	private double conOtherded;
	private String contBankAcNumber;
	//private String gender;
	//private String pfNumber;
	//private String employeeNumber;
	
	
	/*public String getGender() {
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
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}*/

	
	
	private List<ReportDetails> payrollList;
	
	public void addTotals(PaybillDetails payroll){
		gradePay+=payroll.getGradePay();
		basicPay +=payroll.getBasicPay();
		da += payroll.getDa();
		hra+= payroll.getHra();
		cca+= payroll.getCca();
//		ca += payroll.getCycleAllowance();
		ta+= payroll.getTa();
		npa+= payroll.getNpa();
		wa+= payroll.getWa();
		ca+= payroll.getCa();
		uniformAlw+=payroll.getUniformAlw();
		familyPlaningAlw+= payroll.getFamilyPlaningAlw();
		totallw += payroll.getTotallw();
		otAmt += payroll.getOtAmt();
		others += payroll.getOthers();
		rent+= payroll.getRent();
		afkRent += payroll.getAfkRent();
		absentDed+= payroll.getAbsentDed();
		festAdvRcry += payroll.getFestAdvRcry();
		pt += payroll.getPt();
		lic+= payroll.getLic();
		socity += payroll.getSocity();
		gis+= payroll.getGis();
		bankLoanRcry += payroll.getBankLoanRcry();
		vlr += payroll.getVlr();
		apfAcf+= payroll.getApfAcf();
		pfLoanRcry+= payroll.getPfLoanRcry();
		pfsCpf +=payroll.getPfsCpf();
		
		
		/*cpfRcry+= payroll.getCpfRcry();*/
//		incomTax += payroll.getIncomTax();
		unionFee += payroll.getUnionFee();
		unionFeeKss += payroll.getUnionFeeKss();
		elecRcry += payroll.getElecRcry();
		courtRcry += payroll.getCourtRcry();
		otherDeducs += payroll.getOtherDeducs();
		employeeType +=payroll.getEmployeeType();
		misc += payroll.getMisc();
		incomeTax += payroll.getIncomeTax();
		tAllowance += payroll.gettAllowance();
		contAbsDedAmt +=payroll.getContAbsDedAmt();
		contTDS +=payroll.getContTDS();
		empAbsentDays += payroll.getEmpAbsentDays();
		empPresentDays += payroll.getEmpPresentDays();
		contBankAcNumber +=payroll.getContBankAcNumber();
		employeeId +=payroll.getEmployeeId();
	}
	
	public void addEmployeePayroll(ReportDetails payroll){
		if(Utils.isEmpty(deptName))
			deptName = payroll.getDepartment();
		/*if(Utils.isEmpty(bankName))
			bankName = payroll.getBankName();
		else
		if(!payroll.getBankName().equalsIgnoreCase(bankName))
			bankName = payroll.getBankName();*/
		gradePay+=payroll.getGradePay();
		basicPay +=payroll.getBasic();
		da += payroll.getDearnessAllowance();
		hra+= payroll.getHouseRentAllowance();
		cca+= payroll.getCca();
		ca+= payroll.getCa();
		ta+= payroll.getTravelAllowance();
		npa+= payroll.getNonPracticingAllowance();
		wa+= payroll.getWashingAllowance();
//		ca+= payroll.getConveyanceAllowance();
		uniformAlw+=payroll.getUniformAllowance();
		familyPlaningAlw+= payroll.getFamilyPlanningAllowance();
		totallw += payroll.getTotalAllowance();
		otAmt += payroll.getOverTimeAmount();
		others += payroll.getOtherPayAmount();
		rent+= payroll.getLfee();
		afkRent += payroll.getAfkRent();
		absentDed+= payroll.getAbsentAmount();
		festAdvRcry += payroll.getFestAdvRecovery();
		pt += payroll.getProfTax();
		lic+= payroll.getLic();
		socity += payroll.getSocietyInstallment();
		gis+= payroll.getGrpInsurance();
		bankLoanRcry += payroll.getBankLoanRcry();
		vlr += payroll.getVehclLoanRcry();
		apfAcf+= payroll.getApfacpf();
		pfLoanRcry+= payroll.getPfLoanRecovery();
		pfsCpf +=payroll.getProvidentFund();
		
		
	/*	cpfRcry+= payroll.getCpfRecovery();*/
		incomeTax += payroll.getIncomeTax();
		unionFee += payroll.getUnionFee();
		unionFeeKss += payroll.getUnionFeeKss();
		elecRcry += payroll.getElectricityRecovery();
		courtRcry += payroll.getCourtRecovery();
		otherDeducs += payroll.getOtherDeductions();
		misc += payroll.getMiscAllowance();
		tAllowance += payroll.gettAllowance();
		employeeType +=payroll.getEmployeeType();
		contAbsDedAmt +=payroll.getContAbsDedAmt();
		contTDS +=payroll.getContTDS();
		empAbsentDays += payroll.getEmpAbsentDays();
		empPresentDays += payroll.getEmpPresentDays();
		contBankAcNumber +=payroll.getContBankAcNumber();
		employeeId +=payroll.getEmployeeId();
		noOfEmployees += 1;
		if(payrollList == null)
			payrollList = new ArrayList<ReportDetails>();
		payrollList.add(payroll);
	}
	
	public double getTotal(){
        return basicPay;
    }
	
	public double getTDS(){
        return contTDS;
    }
	
	public double getAbsentDeduction(){
        return contAbsDedAmt;
    }
	
    public double getTotalGrossPay(){

        this.grossPay = this.basicPay + this.gradePay + this.da + this.ta  + this.totallw + this.tAllowance + this.others+this.hra;
        //System.out.println("***** GrossPay -> Basic: " + this.basicPay + " GradePay: " + this.gradePay + " DA: " + this.da + " TA: " + this.ta + " TotalAllowance: " + this.totallw);
        this.totalGrossPay = this.grossPay + this.otAmt;
        //System.out.println("***** Total Gross Pay -> GrossPay " + this.grossPay + " OverTimeAmount: " + this.otAmt + " Others/OtherPayment: " + this.others);
        return totalGrossPay;
    }
    
    public double getTotalDeductions() {
    	this.totalDeductions = 0;
    	this.totalDeductions = this.absentDed
//    						+ this.lfee // need to check what is it mean
    						+ this.rent
    						+ this.afkRent
    						+ this.festAdvRcry
    						+ this.pt
    						+ this.lic
    						+ this.socity
    						+ this.gis
    						+ this.bankLoanRcry
    						+ this.vlr
    						+ this.apfAcf
    						+ this.pfLoanRcry
    						+ this.pfsCpf
    						
    						
    						/*+ this.cpfRcry*/
    						+ this.incomeTax
    						+ this.unionFee
    						+ this.elecRcry
    						+ this.courtRcry
    						+ this.otherDeducs
    						+ this.misc
    						+ this.unionFeeKss
    						+ this.pfInstment;
    			return totalDeductions;			
    }
    public double getNetPay(){
    	this.netPay = totalGrossPay - totalDeductions;
    	return this.netPay;
    }

    public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
    public double getGradePay() {
		return gradePay;
	}

	public double getBasicPay() {
		return basicPay;
	}

	public double getDa() {
		return da;
	}

	public double getHra() {
		return hra;
	}

	public double getCca() {
		return cca;
	}

	public double getTa() {
		return ta;
	}

	public double getNpa() {
		return npa;
	}

	public double getWa() {
		return wa;
	}

	public double getCa() {
		return ca;
	}

	public double getUniformAlw() {
		return uniformAlw;
	}

	public double getFamilyPlaningAlw() {
		return familyPlaningAlw;
	}

	public double getTotallw() {
		return totallw;
	}

	public double getOtAmt() {
		return otAmt;
	}

	public double getOthers() {
		return others;
	}

	public double getRent() {
		return rent;
	}

	public double getAfkRent() {
		return afkRent;
	}

	public double getAbsentDed() {
		return absentDed;
	}

	public double getFestAdvRcry() {
		return festAdvRcry;
	}

	public double getPt() {
		return pt;
	}

	public double getLic() {
		return lic;
	}

	public double getSocity() {
		return socity;
	}

	public double getGis() {
		return gis;
	}

	public double getBankLoanRcry() {
		return bankLoanRcry;
	}

	public double getVlr() {
		return vlr;
	}
	public double getApfAcf() {
		return apfAcf;
	}
	
	public double getPfLoanRcry() {
		return pfLoanRcry;
	}
	public double getPfsCpf() {
		return pfsCpf;
	}

	/*	public double getCpfRcry() {
		return cpfRcry;
	}*/

//	public double getIncomTax() {
//		return incomTax;
//	}

	public double getUnionFee() {
		return unionFee;
	}

	public double getElecRcry() {
		return elecRcry;
	}

	public double getCourtRcry() {
		return courtRcry;
	}

	public double getOtherDeducs() {
		return otherDeducs;
	}

	public double getMisc() {
		return misc;
	}

	public List<ReportDetails> getPayrollList() {
		return payrollList;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public int getNoOfEmployees() {
		return noOfEmployees;
	}

	public double getIncomeTax() {
		return incomeTax;
	}
	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
	}
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getUnionFeeKss() {
		return unionFeeKss;
	}
	public void setUnionFeeKss(double unionFeeKss) {
		this.unionFeeKss = unionFeeKss;
	}
	public void setCa(double ca) {
		this.ca = ca;
	}
	public double gettAllowance() {
		return tAllowance;
	}
	public void settAllowance(double tAllowance) {
		this.tAllowance = tAllowance;
	}
	public int getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(int employeeType) {
		this.employeeType = employeeType;
	}
	public int getEmpAbsentDays() {
		return empAbsentDays;
	}
	public void setEmpAbsentDays(int empAbsentDays) {
		this.empAbsentDays = empAbsentDays;
	}
	public int getEmpPresentDays() {
		return empPresentDays;
	}
	public void setEmpPresentDays(int empPresentDays) {
		this.empPresentDays = empPresentDays;
	}
	public double getContAbsDedAmt() {
		return contAbsDedAmt;
	}
	public void setContAbsDedAmt(double contAbsDedAmt) {
		this.contAbsDedAmt = contAbsDedAmt;
	}
	public double getContTDS() {
		return contTDS;
	}
	public void setContTDS(double contTDS) {
		this.contTDS = contTDS;
	}
	public double getConOtherded() {
		return conOtherded;
	}
	public void setConOtherded(double conOtherded) {
		this.conOtherded = conOtherded;
	}
	public String getContBankAcNumber() {
		return contBankAcNumber;
	}
	public void setContBankAcNumber(String contBankAcNumber) {
		this.contBankAcNumber = contBankAcNumber;
	}

	public double getProvidentFund() {
		return pfsCpf;
	}
}
