package com.payroll.overtime.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class Overtime implements Serializable{
	
	private int employeeId;
	private int overtimeId;
	private Double overtimeHours;
	private Double overtimeDayHours;
	private Double overtimeAmount;
	private Date overtimeDate;
	private String overtimeOrder;
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	/**
	 * 
	 */
	public Overtime() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param employeeId
	 * @param overtimeId
	 * @param overtimeHours
	 * @param overtimeDayHours
	 * @param overtimeAmount
	 * @param overtimeDate
	 * @param overtimeOrder
	 * @param status
	 * @param rowUpdDate
	 * @param employee
	 */
	public Overtime(int employeeId, int overtimeId, Double overtimeHours, Double overtimeDayHours,
			Double overtimeAmount, Date overtimeDate, String overtimeOrder, String status, Timestamp rowUpdDate,
			Employee employee) {
		super();
		this.employeeId = employeeId;
		this.overtimeId = overtimeId;
		this.overtimeHours = overtimeHours;
		this.overtimeDayHours = overtimeDayHours;
		this.overtimeAmount = overtimeAmount;
		this.overtimeDate = overtimeDate;
		this.overtimeOrder = overtimeOrder;
		this.status = status;
		this.rowUpdDate = rowUpdDate;
		this.employee = employee;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	
	public Double getOvertimeAmount() {
		return overtimeAmount;
	}
	public void setOvertimeAmount(Double overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}
	public Date getOvertimeDate() {
		return overtimeDate;
	}
	public void setOvertimeDate(Date overtimeDate) {
		this.overtimeDate = overtimeDate;
	}
	public int getOvertimeId() {
		return overtimeId;
	}
	public void setOvertimeId(int overtimeId) {
		this.overtimeId = overtimeId;
	}
	public Double getOvertimeHours() {
		return overtimeHours;
	}
	public void setOvertimeHours(Double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}
	public String getOvertimeOrder() {
		return overtimeOrder;
	}
	public void setOvertimeOrder(String overtimeOrder) {
		this.overtimeOrder = overtimeOrder;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Double getOvertimeDayHours() {
		return overtimeDayHours;
	}
	public void setOvertimeDayHours(Double overtimeDayHours) {
		this.overtimeDayHours = overtimeDayHours;
	}
}
