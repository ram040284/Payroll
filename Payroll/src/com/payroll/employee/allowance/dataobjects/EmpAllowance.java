package com.payroll.employee.allowance.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;


import com.payroll.employee.dataobjects.Employee;

public class EmpAllowance implements Serializable {

	private int employeeId;
	private double cca;
	private double washingAlwance;
	private double convAlwance;
	private double nonPracAwance;
	private double uniformAlwance;
	private double familyPlanAlwance;
	private double cycleAlwance;
	private Boolean hraFlag;
	private Timestamp rowUpdDate;
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public double getCca() {
		return cca;
	}
	public void setCca(double cCA) {
		cca = cCA;
	}
	public double getWashingAlwance() {
		return washingAlwance;
	}
	public void setWashingAlwance(double washingAlwance) {
		this.washingAlwance = washingAlwance;
	}
	public double getConvAlwance() {
		return convAlwance;
	}
	public void setConvAlwance(double convAlwance) {
		this.convAlwance = convAlwance;
	}
	public double getNonPracAwance() {
		return nonPracAwance;
	}
	public void setNonPracAwance(double nonPracAwance) {
		this.nonPracAwance = nonPracAwance;
	}
	public double getUniformAlwance() {
		return uniformAlwance;
	}
	public void setUniformAlwance(double uniformAlwance) {
		this.uniformAlwance = uniformAlwance;
	}
	public double getFamilyPlanAlwance() {
		return familyPlanAlwance;
	}
	public void setFamilyPlanAlwance(double familyPlanAlwance) {
		this.familyPlanAlwance = familyPlanAlwance;
	}
	public double getCycleAlwance() {
		return cycleAlwance;
	}
	public void setCycleAlwance(double cycleAlwance) {
		this.cycleAlwance = cycleAlwance;
	}
	public Boolean getHraFlag() {
		return hraFlag;
	}
	public void setHraFlag(Boolean hraFlag) {
		this.hraFlag = hraFlag;
	}
	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
