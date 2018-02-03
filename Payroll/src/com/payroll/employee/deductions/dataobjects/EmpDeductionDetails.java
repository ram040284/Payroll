package com.payroll.employee.deductions.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class EmpDeductionDetails implements Serializable{
	private int employeeId;
	private double afkRent;
	private double society;
	private double electRecovery;
	private double courtRecovery;
	private double unionFee;
	private double otherDeductions;
	private double miscRecovery;
	private double kssUnionRecovery;
	private double profTax;
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Timestamp rowUpdDate;
	private Date monthDate;
	private Employee employee;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
	public double getElectRecovery() {
		return electRecovery;
	}
	public void setElectRecovery(double electRecovery) {
		this.electRecovery = electRecovery;
	}
	public double getCourtRecovery() {
		return courtRecovery;
	}
	public void setCourtRecovery(double courtRecovery) {
		this.courtRecovery = courtRecovery;
	}
	public double getUnionFee() {
		return unionFee;
	}
	public void setUnionFee(double unionFee) {
		this.unionFee = unionFee;
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
	public void setMiscRecovery(double missRecovery) {
		this.miscRecovery = missRecovery;
	}
	public double getKssUnionRecovery() {
		return kssUnionRecovery;
	}
	public void setKssUnionRecovery(double kssUnionRecovery) {
		this.kssUnionRecovery = kssUnionRecovery;
	}
	public double getProfTax() {
		return profTax;
	}
	public void setProfTax(double profTax) {
		this.profTax = profTax;
	}
	public Date getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}
	
}
