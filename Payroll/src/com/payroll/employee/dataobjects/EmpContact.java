package com.payroll.employee.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class EmpContact implements Serializable{
	
	private Employee employee;
	private int empContactId;
	private int employeeId;
	private String email;
	private String phone;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String pin;
	
	private String secEmail;
	private String secPhone;
	private String secAddressLine1;
	private String secAddressLine2;
	private String secAddressLine3;
	private String secCity;
	private String secState;
	private String secPin;
	private Timestamp rowUpdatedDate;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getSecEmail() {
		return secEmail;
	}
	public void setSecEmail(String secEmail) {
		this.secEmail = secEmail;
	}
	public String getSecPhone() {
		return secPhone;
	}
	public void setSecPhone(String secPhone) {
		this.secPhone = secPhone;
	}
	public String getSecAddressLine1() {
		return secAddressLine1;
	}
	public void setSecAddressLine1(String secAddressLine1) {
		this.secAddressLine1 = secAddressLine1;
	}
	public String getSecAddressLine2() {
		return secAddressLine2;
	}
	public void setSecAddressLine2(String secAddressLine2) {
		this.secAddressLine2 = secAddressLine2;
	}
	public String getSecAddressLine3() {
		return secAddressLine3;
	}
	public void setSecAddressLine3(String secAddressLine3) {
		this.secAddressLine3 = secAddressLine3;
	}
	public Timestamp getRowUpdatedDate() {
		return rowUpdatedDate;
	}
	public void setRowUpdatedDate(Timestamp rowUpdatedDate) {
		this.rowUpdatedDate = rowUpdatedDate;
	}
	public int getEmpContactId() {
		return empContactId;
	}
	public void setEmpContactId(int empContactId) {
		this.empContactId = empContactId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getSecCity() {
		return secCity;
	}
	public void setSecCity(String secCity) {
		this.secCity = secCity;
	}
	public String getSecState() {
		return secState;
	}
	public void setSecState(String secState) {
		this.secState = secState;
	}
	public String getSecPin() {
		return secPin;
	}
	public void setSecPin(String secPin) {
		this.secPin = secPin;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
}
