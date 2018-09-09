package com.payroll.hrms.payroll.service;

import com.payroll.TestData;
import com.payroll.Utils;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmpDeductionsDAO;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.hrms.payroll.incometax.dataobjects.EMPITDecutions;
import com.payroll.hrms.payroll.incometax.dataobjects.ITSlabsDTO;
import com.payroll.incomtax.dataobjects.IncomtaxSlab;
import com.payroll.incomtax.dataobjects.IncomtaxSlabDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
	//public static final double MINTAXPERAMOUNT = 250000;
	//public static final double MEDIUMPERTAXAMOUNT = 500001;
	//public static final double MAXPERTAXAMOUNT = 1000001;
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
    public void getIncomeTaxDeductions(String empId){
    	EmpDeductions empDeductions = new EmpDeductionsDAO().getEmpDeductionsByEmpId(empId);  
    	if(empDeductions == null)
    		empDeductions = TestData.getEmpDeductions(empId);
    	itDeductions = new EMPITDecutions();
    	itDeductions.setSection80C(empDeductions.getSection80C());
    	itDeductions.setHomeLoanInterest80E(empDeductions.getHomeLoanIntrst88EE());
    	itDeductions.setSelfDisability80U(empDeductions.getSelfDisable80U());
    }

    public double getIncomeTax(String empId, Date year, double grossPay){
    	String fullYr = Utils.getFullYear(year);
    	StringBuffer finYr = new StringBuffer(fullYr);
    	finYr.append("-");
    	finYr.append(Integer.parseInt(fullYr)+1);
    	getIncomeTaxSlabs(finYr.toString());
    	getIncomeTaxDeductions(empId);
    	
    	double incomeTax = (slabs != null && !slabs.isEmpty()) ? calculateIncomeTax(grossPay) : 0;
    	incomeTax = incomeTax < 0 ? 0 : incomeTax;
    	//Income tax per month
    	if(incomeTax > 0)
    		incomeTax = Math.round((incomeTax/12) * 100.0) / 100.0;
    	
    	System.out.println("incomeTax:"+incomeTax);
    	return incomeTax;
    }
    
    public double getEmpITExemptions(String empId, Date year, double grossPay, EmpDeductionsVO exemptions) {
    	
    	/*First Month :
    	Annual Gross Salary - Exemptions -> IT_BRACKET/SLAB
    	TAXABLE Amount * SLAB_PER /12

    	Second month onwards: 
    	e.g. AUG
    	(Sum (Salary from April - Aug) + projected Salary for sep-Mar) - Exemptions -> get tax slab
    	TAXABLE Amount * SLAB_PER /12*/
    	
    	//double incomeTax = grossPay - exemptions.getDes_dep_80DD() 
    	
    	return 0.0;
    }
    
    public double calculateIncomeTax(double pGrossSalary){
    	double grossSalary = pGrossSalary*12;  // Monthly Gross calculating to yearly
        double taxableAmount= 0.0;
        double section80C = itDeductions.getSection80C();
        double educationLoan80E = itDeductions.getEductionLoan80E();
        double employerNPSSec80CCD2 = itDeductions.getEmployerNPSsection80CCD2();
        double homeLoanInterest80E = itDeductions.getHomeLoanInterest80E();
        double medicalInsurance80D = itDeductions.getMedicalInsurance80D();
       
        //Deducting tax declarations from 
        grossSalary  = (section80C <= SECTION80C) ? grossSalary- section80C : grossSalary - SECTION80C;
        if(educationLoan80E > 0)
        	grossSalary = grossSalary - educationLoan80E;
        if(employerNPSSec80CCD2 > 0)
        	grossSalary = grossSalary - employerNPSSec80CCD2;
        grossSalary = (homeLoanInterest80E <= HOMELOANINTEREST80E) ? grossSalary - homeLoanInterest80E : grossSalary - HOMELOANINTEREST80E;
        grossSalary = (medicalInsurance80D <= MEDICALINSURANCE80D) ? grossSalary - medicalInsurance80D : grossSalary - MEDICALINSURANCE80D; 
        
        //Calculating Tax with different slabs
        
        for (Iterator iterator = slabs.iterator(); iterator.hasNext();) {
			IncomtaxSlab incomtaxSlab = (IncomtaxSlab) iterator.next();
			System.out.println("slab:"+incomtaxSlab);
			double lowerSlab = incomtaxSlab.getLowerSlab();
			if(grossSalary <= lowerSlab)
				return taxableAmount;
			grossSalary -= lowerSlab;
			double incomtaxPercent = incomtaxSlab.getIncomtaxPercent();
			double taxableAmt = (grossSalary > incomtaxSlab.getHigherSlab())? incomtaxSlab.getHigherSlab() : grossSalary;
			System.out.println("incomtaxPercent is:"+incomtaxPercent+" and taxableAmt:"+taxableAmt);
			taxableAmount += taxableAmt * incomtaxPercent/100; 
		}
         System.out.println("taxableAmount:"+taxableAmount);
        return taxableAmount;
    }
    
    public static void main(String a[]){
    	double taxableAmount= 0.0;
    	double grossSalary = 151503;
    	System.out.println("grossSalary:"+grossSalary);
    	Date startDate = Utils.getStartDateOfMonth(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    	taxableAmount = new IncomeTaxCalculatorService().getIncomeTax("198005008", startDate, grossSalary);
        System.out.println("taxableAmount:"+taxableAmount);
    }

}

