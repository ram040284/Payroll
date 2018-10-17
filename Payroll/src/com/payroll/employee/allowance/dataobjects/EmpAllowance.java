package com.payroll.employee.allowance.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.opencsv.bean.CsvBindByName;
import com.payroll.employee.dataobjects.Employee;

public class EmpAllowance implements Serializable {
	@CsvBindByName(column= "EMP_ID")
	private String employeeId;
	@CsvBindByName(column= "CCA")
	private double cca;
	@CsvBindByName(column= "WASHING_ALLOWANCE")
	private double washingAlwance;
	@CsvBindByName(column= "NON_PRACT_ALLOWANCE")
	private double nonPracAwance;
	@CsvBindByName(column= "UNIFORM_ALLOWANCE")
	private double uniformAlwance;
	@CsvBindByName(column= "FAMILY_PLANNING_ALLOWANCE")
	private double familyPlanAlwance;
	@CsvBindByName(column= "CYCLE_ALLOWANCE")
	private double cycleAlwance;
	
	@CsvBindByName(column= "HRA_FLAG")
	private Boolean hraFlag;
	
//	@CsvBindByName(column= "QTR_FLAG")
	private Boolean qtrFlag;
	
//	@CsvBindByName(column= "AFK_FLAG")
	private Boolean afkFlag;
	
	@CsvBindByName(column= "TA_FLAG")
	private byte taFlag;
	
	@CsvBindByName(column= "PF_FLAG")
	private byte pfFlag;
	
	private Date rowUpdDate;
	@CsvBindByName(column= "STATUS")
	private String status;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;
	@CsvBindByName(column= "OTHER_ALLOWANCE")
	private double otherAllowance;
	@CsvBindByName(column= "T_ALLOWANCE")
	private double tAllowance;
	
	public EmpAllowance() {}
	
	public EmpAllowance(String employeeId, double cca, double washingAlwance, double nonPracAwance,
			double uniformAlwance, double familyPlanAlwance, double cycleAlwance, double tAllowance, double otherAllowance, Boolean hraFlag,
			Boolean qtrFlag, Boolean afkFlag, byte taFlag, byte pfFlag, String status) {
		this.employeeId = employeeId;
		this.cca = cca;
		this.washingAlwance = washingAlwance;
		this.nonPracAwance = nonPracAwance;
		this.uniformAlwance = uniformAlwance;
		this.familyPlanAlwance = familyPlanAlwance;
		this.cycleAlwance = cycleAlwance;
		this.hraFlag = hraFlag;
		this.qtrFlag = qtrFlag;
		this.afkFlag = afkFlag;
		this.taFlag = taFlag;
		this.pfFlag = pfFlag;
		this.status = status;
		this.otherAllowance = otherAllowance;
		this.tAllowance = tAllowance;
	}
	
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
