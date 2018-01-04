package com.payroll.incomtax.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

public class IncomtaxSlab implements Serializable{
	
	 private int incomtaxId;
	 private String financialYear;
	 private double lowerSlab;
	 private double higherSlab;
	 private double incomtaxPercent;
	 private double surcharge;
	 private double educationCess;
	 private double otherCess;
	 private String status;
	 private Timestamp rowUpdDate;
	public int getIncomtaxId() {
		return incomtaxId;
	}
	public void setIncomtaxId(int incomtaxId) {
		this.incomtaxId = incomtaxId;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
	public double getLowerSlab() {
		return lowerSlab;
	}
	public void setLowerSlab(double lowerSlab) {
		this.lowerSlab = lowerSlab;
	}
	public double getHigherSlab() {
		return higherSlab;
	}
	public void setHigherSlab(double higherSlab) {
		this.higherSlab = higherSlab;
	}
	public double getIncomtaxPercent() {
		return incomtaxPercent;
	}
	public void setIncomtaxPercent(double incomtaxPercent) {
		this.incomtaxPercent = incomtaxPercent;
	}
	public double getSurcharge() {
		return surcharge;
	}
	public void setSurcharge(double surcharge) {
		this.surcharge = surcharge;
	}
	public double getEducationCess() {
		return educationCess;
	}
	public void setEducationCess(double eductionCess) {
		this.educationCess = eductionCess;
	}
	public double getOtherCess() {
		return otherCess;
	}
	public void setOtherCess(double otherCess) {
		this.otherCess = otherCess;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}
    
}
