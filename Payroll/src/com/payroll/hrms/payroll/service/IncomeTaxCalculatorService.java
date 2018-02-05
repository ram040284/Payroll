package com.payroll.hrms.payroll.service;

import com.payroll.Utils;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmpDeductionsDAO;
import com.payroll.hrms.payroll.incometax.dataobjects.EMPITDecutions;
import com.payroll.hrms.payroll.incometax.dataobjects.ITSlabsDTO;
import com.payroll.incomtax.dataobjects.IncomtaxSlab;
import com.payroll.incomtax.dataobjects.IncomtaxSlabDAO;

import java.util.Date;
import java.util.List;

/**
 * Created by rajendra on 12/12/17.
 */
public class IncomeTaxCalculatorService {
	List<IncomtaxSlab> slabs = null;
	EMPITDecutions itDeductions = null;
	IncomtaxSlab slab = null;
	public static final double SECTION80C = 150000;
	public static final double HOMELOANINTEREST80E = 50000;
	public static final double MEDICALINSURANCE80D = 25000;
	public static final double MINTAXPERAMOUNT = 250000;
	public static final double MEDIUMPERTAXAMOUNT = 500001;
	public static final double MAXPERTAXAMOUNT = 1000001;
	//public static final double MEDICALINSURANCE80D = 25000;
    /**
     *
   */
    
	public List<IncomtaxSlab> getIncomeTaxSlabs(String finYear){
		slabs = new IncomtaxSlabDAO().getIncomtaxSlabByYr(finYear); 
        return slabs;
    }

    /**
     *
     */
    public void getIncomeTaxDeductions(int empId){
    	EmpDeductions empDeductions = new EmpDeductionsDAO().getEmpDeductionsByEmpId(empId);  
    	itDeductions = new EMPITDecutions();
    	itDeductions.setSection80C(empDeductions.getSection80C());
    	itDeductions.setHomeLoanInterest80E(empDeductions.getHomeLoanIntrst88EE());
    	itDeductions.setSelfDisability80U(empDeductions.getSelfDisable80U());
    	if(!slabs.isEmpty())
    		slab = slabs.get(0);
    }

    public double getIncomeTax(int empId, Date year, double grossPay){
    	getIncomeTaxSlabs(Utils.getFullYear(year));
    	getIncomeTaxDeductions(empId);
    	
    	double incomeTax = (slab != null) ? calculateIncomeTax(grossPay) : 0;
    	incomeTax = incomeTax < 0 ? 0 : incomeTax;
    	return incomeTax;
    }
    
    public double calculateIncomeTax(double grossSalary){
        double taxableAmount= 0.0;
        grossSalary -= slab.getLowerSlab(); // deducting lower slab
        //grossSalary -= hra; // Deducting HRA  // HRA already deducted 
        if(itDeductions.getSection80C() <= SECTION80C)
        	grossSalary -= itDeductions.getSection80C();
        else
        	grossSalary -= SECTION80C;
        if(itDeductions.getEductionLoan80E() > 0)
        	grossSalary -= itDeductions.getEductionLoan80E();
        if(itDeductions.getEmployerNPSsection80CCD2() > 0)
        	grossSalary -= itDeductions.getEmployerNPSsection80CCD2();
        
        if(itDeductions.getHomeLoanInterest80E() <= HOMELOANINTEREST80E)
        	grossSalary -= itDeductions.getEmployerNPSsection80CCD2();
        else
        	grossSalary -= HOMELOANINTEREST80E;
        if(itDeductions.getMedicalInsurance80D() <= MEDICALINSURANCE80D)
        	grossSalary -= itDeductions.getEmployerNPSsection80CCD2();
        else
        	grossSalary -= MEDICALINSURANCE80D;
     
        if(grossSalary > MINTAXPERAMOUNT) {
        	taxableAmount+= (MINTAXPERAMOUNT * 5/100); // 5% tax to remaining gross up to 5L
		    double remTaxAmount = grossSalary - MINTAXPERAMOUNT;
		    double medTaxAmt = 500000;
		    if(remTaxAmount > MEDIUMPERTAXAMOUNT){ // 20% tax to 
		    	taxableAmount+= medTaxAmt * 20/100;
		    	remTaxAmount -= medTaxAmt;
		    	taxableAmount+= remTaxAmount * 30/100;
		    }
		    else
		    	taxableAmount+= remTaxAmount * 20/100;
        }else
        	taxableAmount+= (grossSalary * 5/100); // 5% tax to remaining gross up to 5L
        return taxableAmount;
    }
    
    public static void main(String a[]){
    	double taxableAmount= 0.0;
    	double grossSalary = 600000;
    	if(grossSalary > MINTAXPERAMOUNT) {
        	taxableAmount+= (MINTAXPERAMOUNT * 5/100); // 5% tax to remaining gross up to 5L
		    double remTaxAmount = grossSalary - MINTAXPERAMOUNT;
		    double medTaxAmt = 500000;
		    if(remTaxAmount > MEDIUMPERTAXAMOUNT){ // 20% tax to 
		    	taxableAmount+= medTaxAmt * 20/100;
		    	remTaxAmount -= medTaxAmt;
		    	taxableAmount+= remTaxAmount * 30/100;
		    }
		    else
		    	taxableAmount+= remTaxAmount * 20/100;
        }else
        	taxableAmount+= (grossSalary * 5/100); // 5% tax to remaining gross up to 5L
        System.out.println("taxableAmount:"+taxableAmount);
    }

}

