package com.payroll.employee.attendance.dataobjects;

import java.util.Date;

import com.opencsv.bean.CsvBindByName;

public class EmployeeAttendance {

//	@CsvBindByName(column= "S.No")
	private int srNo;
	
	@CsvBindByName(column= "Office Location")
	private String officeLocation;
	
	@CsvBindByName(column= "Division/Unit")
	private String divisionUnit;
	
	@CsvBindByName(column= "Attendance id")
	private String attendanceId;
	
	@CsvBindByName(column= "Employee Name")
	private String employeeName;
	
	@CsvBindByName(column= "Designation")
	private String designation;
	
	@CsvBindByName(column= "In Time")
	private String inTime;
	
	@CsvBindByName(column= "Out Time")
	private String outTime;
	
	@CsvBindByName(column= "Status")
	private char status;
	
	private String absenceReason;
	
	private String rowUpdateTime;
	
	private int[] srNoArray;
	
	public EmployeeAttendance() {
	}

//	public EmployeeAttendance(int srNo, String officeLocation, String divisionUnit, String attendanceId,
//			String employeeName, String designation, String inTime, String outTime, char status,
//			Timestamp rowUpdateTime) {
//		super();
//		this.srNo = srNo;
//		this.officeLocation = officeLocation;
//		this.divisionUnit = divisionUnit;
//		this.attendanceId = attendanceId;
//		this.employeeName = employeeName;
//		this.designation = designation;
//		this.inTime = inTime;
//		this.outTime = outTime;
//		this.status = status;
//		this.rowUpdateTime = rowUpdateTime;
//	}
	
	public EmployeeAttendance(String officeLocation, String divisionUnit, String attendanceId,
			String employeeName, String designation, String inTime, String outTime, char status,
			String absenceReason, String rowUpdateTime, int[] srNoArray) {
		super();
		this.officeLocation = officeLocation;
		this.divisionUnit = divisionUnit;
		this.attendanceId = attendanceId;
		this.employeeName = employeeName;
		this.designation = designation;
		this.inTime = inTime;
		this.outTime = outTime;
		this.status = status;
		this.absenceReason = absenceReason;
		this.rowUpdateTime = rowUpdateTime;
		this.srNoArray = srNoArray;
	}

	public EmployeeAttendance(int srNo, String absenceReason) {
		super();
		this.srNo = srNo;
		this.absenceReason = absenceReason;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getDivisionUnit() {
		return divisionUnit;
	}

	public void setDivisionUnit(String divisionUnit) {
		this.divisionUnit = divisionUnit;
	}

	public String getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(String attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public String getAbsenceReason() {
		return absenceReason;
	}

	public void setAbsenceReason(String absenceReason) {
		this.absenceReason = absenceReason;
	}

	public String getRowUpdateTime() {
		return rowUpdateTime;
	}

	public void setRowUpdateTime(String rowUpdateTime) {
		this.rowUpdateTime = rowUpdateTime;
	}

	public int[] getSrNoArray() {
		return srNoArray;
	}

	public void setSrNoArray(int[] srNoArray) {
		this.srNoArray = srNoArray;
	}

}
