package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpVarDeductions {
	private int employeeId;
	private double afkRent;
	private double society;
	private double otherDeductions;
	private double miscRecovery;
	private Timestamp monthDate;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	private String status;
	private short addUpdate;
	private Timestamp rowUpdDate;
	private Employee employee;

	public EmpVarDeductions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public EmpVarDeductions(int employeeId, String firstName, String lastName, double afkRent, double society, 
			 double otherDeductions, double miscRecovery, Date monthDate){
		this.employeeId = employeeId;
		this.afkRent = afkRent;
		this.society = society;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		this.monthDate = Timestamp.valueOf(monthDate.toString());
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
	}
	
	public EmpVarDeductions(int employeeId, int departmentId , int designationId, int headId, double afkRent, 
			double society, double otherDeductions, double miscRecovery, Date monthDate){
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.afkRent = afkRent;
		this.society = society;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		this.monthDate = Timestamp.valueOf(monthDate.toString());
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

	public Date getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(Date monthDate) {
		this.monthDate = Timestamp.valueOf(monthDate.toString());
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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
}
