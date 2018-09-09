package com.payroll.employee.arrears.vo;

import java.sql.Timestamp;

import com.payroll.Utils;
import com.payroll.employee.arrears.dataobjects.EmpArrearsEnumData;

public class EmpArrearsVO {
	
	private int arrearId;
	private int employeeId;
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
	private int departmentId;
	private int designationId;
	private int headId;
	private short addUpdate;
	private String fullName;
	
	public EmpArrearsVO(){
		
	}
	
	public EmpArrearsVO(int arrearId, int employeeId, String arrearsType, double arrearsPay, double arrearsDeductions, double miscPay,
			double miscDeductions, String arrearsPayNote, String arrearsDeductionNote, String fName, String lName) {
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.employeeId = employeeId;
		this.arrearsType = arrearsType;
		this.arrearsPay = arrearsPay;
		this.arrearsDeductions = arrearsDeductions;
		this.miscPay = miscPay;
		this.miscDeductions = miscDeductions;
		this.arrearsPayNote = arrearsPayNote;
		this.arrearsDeductionNote = arrearsDeductionNote;
		this.arrearId = arrearId;
	}
	
	public EmpArrearsVO(int arrearId, int employeeId, String arrearsType, double arrearsPay, double arrearsDeductions, double miscPay,
			double miscDeductions, String arrearsPayNote, String arrearsDeductionNote, int departmentId, int designationId, int headId) {
		this.arrearsType = arrearsType;
		this.arrearsPay = arrearsPay;
		this.arrearsDeductions = arrearsDeductions;
		this.miscPay = miscPay;
		this.miscDeductions = miscDeductions;
		this.arrearsPayNote = arrearsPayNote;
		this.arrearsDeductionNote = arrearsDeductionNote;
		this.arrearId = arrearId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.employeeId = employeeId;
	}
	public String getArrearsType() {
		return arrearsType;
	}
	public int getArrearId() {
		return arrearId;
	}

	public void setArrearId(int arrearId) {
		this.arrearId = arrearId;
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
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
