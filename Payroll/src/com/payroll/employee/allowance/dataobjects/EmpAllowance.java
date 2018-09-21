package com.payroll.employee.allowance.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class EmpAllowance implements Serializable {
	private String employeeId;
	private double cca;
	private double washingAlwance;
	private double nonPracAwance;
	private double uniformAlwance;
	private double familyPlanAlwance;
	private double cycleAlwance;
	private Boolean hraFlag;
	private Boolean qtrFlag;
	private Boolean afkFlag;
	private byte taFlag;
	private byte pfFlag;
	private Date rowUpdDate;
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;
	private double otherAllowance;
	private double tAllowance;
	
	public byte getPfFlag() {
		return pfFlag;
	}
	public void setPfFlag(byte pfFlag) {
		this.pfFlag = pfFlag;
	}
	
	public Boolean getQtrFlag() {
		return qtrFlag;
	}
	public void setQtrFlag(Boolean qtrFlag) {
		this.qtrFlag = qtrFlag;
	}
	public Boolean getAfkFlag() {
		return afkFlag;
	}
	public void setAfkFlag(Boolean afkFlag) {
		this.afkFlag = afkFlag;
	}
	public byte getTaFlag() {
		return taFlag;
	}
	public void setTaFlag(byte taFlag) {
		this.taFlag = taFlag;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
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
	public Date getRowUpdDate() {
		return rowUpdDate;
	}
	public void setRowUpdDate(Date rowUpdDate) {
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
	public double getOtherAllowance() {
		return otherAllowance;
	}
	public void setOtherAllowance(double otherAllowance) {
		this.otherAllowance = otherAllowance;
	}
	public double gettAllowance() {
		return tAllowance;
	}
	public void settAllowance(double tAllowance) {
		this.tAllowance = tAllowance;
	}
}
