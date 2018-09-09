package com.payroll.employee.arrears.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;

import com.payroll.employee.dataobjects.Employee;

public class EmpArrears implements Serializable{
	private String employeeId;
	private int arrearId;
	private String arrearsType;
	private double arrearsPay;
	private double arrearsDeductions;
	private double miscPay;
	private double miscDeductions;
	private String arrearsPayNote;
	private String arrearsDeductionNote;
	private String status;
	private Timestamp rowUpdatedDate;
	private EmpArrearsEnumData arrearsEnumData;
	private String fullName;
	private Employee employee;
	private short addUpdate;
	
	public EmpArrears() {
		// TODO Auto-generated constructor stub
	}
	
	public EmpArrears(String employeeId, int arrearId, String arrearsType, double arrearsPay, double arrearsDeductions,
			double miscPay, double miscDeductions, String arrearsPayNote, String arrearsDeductionNote) {
		this.employeeId = employeeId;
		this.arrearId = arrearId;
		this.arrearsType = arrearsType;
		this.arrearsPay = arrearsPay;
		this.arrearsDeductions = arrearsDeductions;
		this.miscPay = miscPay;
		this.miscDeductions = miscDeductions;
		this.arrearsPayNote = arrearsPayNote;
		this.arrearsDeductionNote = arrearsDeductionNote;
	}
	public String getFullName() {
		return fullName;
	}
	public int getArrearId() {
		return arrearId;
	}

	public void setArrearId(int arrearId) {
		this.arrearId = arrearId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getArrearsType() {
		return arrearsType;
	}
	public void setArrearsType(String arrearsType) {
		this.arrearsType = arrearsType;
	}
	public double getArrearsPay() {
		return arrearsPay;
	}
	public void setArrearsPay(double arrearsPay) {
		this.arrearsPay = arrearsPay;
	}
	public double getArrearsDeductions() {
		return arrearsDeductions;
	}
	public void setArrearsDeductions(double arrearsDeductions) {
		this.arrearsDeductions = arrearsDeductions;
	}
	public double getMiscPay() {
		return miscPay;
	}
	public void setMiscPay(double miscPay) {
		this.miscPay = miscPay;
	}
	public double getMiscDeductions() {
		return miscDeductions;
	}
	public void setMiscDeductions(double miscDeductions) {
		this.miscDeductions = miscDeductions;
	}
	public String getArrearsPayNote() {
		return arrearsPayNote;
	}
	public void setArrearsPayNote(String arrearsPayNote) {
		this.arrearsPayNote = arrearsPayNote;
	}
	public String getArrearsDeductionNote() {
		return arrearsDeductionNote;
	}
	public void setArrearsDeductionNote(String arrearsDeductionNote) {
		this.arrearsDeductionNote = arrearsDeductionNote;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getRowUpdatedDate() {
		return rowUpdatedDate;
	}
	public void setRowUpdatedDate(Timestamp rowUpdatedDate) {
		this.rowUpdatedDate = rowUpdatedDate;
	}
	public EmpArrearsEnumData getArrearsEnumData() {
		return arrearsEnumData;
	}
	public void setArrearsEnumData(EmpArrearsEnumData arrearsEnumData) {
		this.arrearsEnumData = arrearsEnumData;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeId:"+employeeId+" |  arrearId:"+this.arrearId+" | arrearsType:"+this.arrearsType 
				+" | arrearsPay:"+this.arrearsPay+" | arrearsDeductions:"+this.arrearsDeductions+" | miscPay:"+this.miscPay
				+" | miscDeductions:"+this.miscDeductions+" | arrearsPayNote:"+this.arrearsPayNote+" | arrearsDeductionNote:"+this.arrearsDeductionNote ;
	}
	
}
