package com.payroll.employee.salary.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class Salary implements Serializable{
	
	private int employeeId;
	private int year;
	private double basic;
	private double gradePay;
	private double scalePay;
	private double scaleInc;
	private short addUpdate; // 0 - Add / 1 - update
	private String status;
	private Timestamp rowUpdDate;
	private Employee employee;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getBasic() {
		return basic;
	}
	public void setBasic(double basic) {
		this.basic = basic;
	}
	public double getGradePay() {
		return gradePay;
	}
	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}
	public double getScalePay() {
		return scalePay;
	}
	public void setScalePay(double scalePay) {
		this.scalePay = scalePay;
	}
	public double getScaleInc() {
		return scaleInc;
	}
	public void setScaleInc(double scaleInc) {
		this.scaleInc = scaleInc;
	}
	public short getAddUpdate() {
		return addUpdate;
	}
	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeId:"+employeeId+" |  year:"+this.year+" | basic:"+this.basic 
				+" | scalePay:"+scalePay+" | scaleInc:"+scaleInc +" | addUpdate:"+addUpdate;
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

}