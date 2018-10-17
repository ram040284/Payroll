package com.payroll.employee.deductions.dataobjects;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author rajendra
 *
 */
public class EmployeeVarDeductions {
	private String employeeId;
	private double afkRent;
	private double society;
	private double otherDeductions;
	private double miscRecovery;
	private String monthDate;
	private double incomeTax;
	private String status;
	private short addUpdate;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("m/d/yyyy");
//	private static SimpleDateFormat dateFormatRead = new SimpleDateFormat("yyyy-mm-dd");
	private double absenties;
	
	private double pfLoanRecovery;

	public EmployeeVarDeductions() {
		super();
	}
	
	public EmployeeVarDeductions(String employeeId, double afkRent, double society, double pfLoanRecovery,
			 double otherDeductions, double miscRecovery, Date monthDate, double incomeTax, double absenties){
		this.employeeId = employeeId;
		this.afkRent = afkRent;
		this.society = society;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		//this.monthDate = monthDate;
		/*System.out.println("monthDate:"+monthDate);
		Date dateMonthDate;
		*/
		if (monthDate!=null){
			try {
				//dateMonthDate =  dateFormatRead.parse(monthDate);
				this.monthDate =  dateFormat.format(monthDate);
//				System.out.println("***** Before Format: " + monthDate + " After Format: " + this.monthDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			this.monthDate = "";
		this.incomeTax = incomeTax;
		this.pfLoanRecovery = pfLoanRecovery;
		this.absenties = absenties;
	}
	

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public double getAfkRent() {
		return afkRent;
	}

	public void setAfkRent(double afkRent) {
		this.afkRent = afkRent;
	}

	public double getSociety() {
		return society;
	}

	public void setSociety(double society) {
		this.society = society;
	}

	public double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public double getMiscRecovery() {
		return miscRecovery;
	}

	public void setMiscRecovery(double miscRecovery) {
		this.miscRecovery = miscRecovery;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate =  (monthDate != null) ? dateFormat.format(monthDate) : "";
	}

	public double getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(double incomeTax) {
		this.incomeTax = incomeTax;
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
	public double getPfLoanRecovery() {
		return pfLoanRecovery;
	}

	public void setPfLoanRecovery(double pfLoanRecovery) {
		this.pfLoanRecovery = pfLoanRecovery;
	}

	public double getAbsenties() {
		return absenties;
	}

	public void setAbsenties(double absenties) {
		this.absenties = absenties;
	}

	@Override
	public String toString() {
		return "EmployeeVarDeductions [employeeId=" + employeeId + ", afkRent=" + afkRent + ", society=" + society
				+ ", otherDeductions=" + otherDeductions + ", miscRecovery=" + miscRecovery + ", monthDate=" + monthDate
				+ ", status=" + status + ", addUpdate=" + addUpdate + "]";
	}
}
