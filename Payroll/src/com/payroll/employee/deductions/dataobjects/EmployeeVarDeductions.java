package com.payroll.employee.deductions.dataobjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author rajendra
 *
 */
public class EmployeeVarDeductions {
	private int employeeId;
	private double afkRent;
	private double society;
	private double otherDeductions;
	private double miscRecovery;
	private String monthDate;
	private String status;
	private short addUpdate;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatRead = new SimpleDateFormat("yyyy-mm-dd");

	public EmployeeVarDeductions() {
		super();
	}
	
	public EmployeeVarDeductions(int employeeId, double afkRent, double society, 
			 double otherDeductions, double miscRecovery, Date monthDate){
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
			} catch (Exception e) {
				e.printStackTrace();
			}
		} /*else
			this.monthDate = "";*/
	}
	

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
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
	@Override
	public String toString() {
		return "EmployeeVarDeductions [employeeId=" + employeeId + ", afkRent=" + afkRent + ", society=" + society
				+ ", otherDeductions=" + otherDeductions + ", miscRecovery=" + miscRecovery + ", monthDate=" + monthDate
				+ ", status=" + status + ", addUpdate=" + addUpdate + "]";
	}
}
