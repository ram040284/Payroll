package com.payroll.employee.deductions.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

import com.payroll.employee.dataobjects.Employee;

public class EmpDeductions implements Serializable{
	private int employeeId;
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
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Timestamp rowUpdDate;
	private Employee employee;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
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
	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Timestamp rowUpdDate) {
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
	
}
