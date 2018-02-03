package com.payroll.hrms.payroll.incometax.dataobjects;

/**
 * Created by rajendra on 12/13/17.
 */
public class EMPITDecutions {

    private double section80C; // Limit 150000.00
    private double employerNPSsection80CCD2; //10% of salary
    private double npssection80CCD21B;     // Deduction in respect of the deposit under a pension scheme notified by Central Government (NPS) up to Rs. 50,000/-
    private double eductionLoan80E; //interest paid for last 8 years
    private double homeLoanInterest80E; //50K
    private double medicalInsurance80D; // Self Spouse : 25k Uninsured Parents >80 yes
    private double selfDisability80U; // Self Spouse : 25k Uninsured Parents >80 yes
    private double section891; // Arrears relief
    private double section24B; // Home Loan interest
    private double section1014; // children Education allowance, tranport allowance
    private double section87A; // Rebate for income under 5 Lakh
    private double section1013A; // HRA Exemption
    private char gender; // M/F
    
    public void processEmpITDeductions(){
    	
    }
    
    public double getSection80C() {
        return section80C;
    }

    public void setSection80C(double section80C) {
        this.section80C = section80C;
    }

    public double getEmployerNPSsection80CCD2() {

        return employerNPSsection80CCD2;
    }

    public void setEmployerNPSsection80CCD2(double employerNPSsection80CCD2) {
        this.employerNPSsection80CCD2 = employerNPSsection80CCD2;
    }


    public double getEductionLoan80E() {
        return eductionLoan80E;
    }

    public void setEductionLoan80E(double eductionLoan80E) {
        this.eductionLoan80E = eductionLoan80E;
    }

    public double getHomeLoanInterest80E() {
        return homeLoanInterest80E;
    }

    public void setHomeLoanInterest80E(double homeLoanInterest80E) {
        this.homeLoanInterest80E = homeLoanInterest80E;
    }

    public double getMedicalInsurance80D() {
        return medicalInsurance80D;
    }

    public void setMedicalInsurance80D(double medicalInsurance80D) {
        this.medicalInsurance80D = medicalInsurance80D;
    }

    public double getSelfDisability80U() {
        return selfDisability80U;
    }

    public void setSelfDisability80U(double selfDisability80U) {
        this.selfDisability80U = selfDisability80U;
    }
}