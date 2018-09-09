package com.payroll.employee.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.payroll.Utils;

public class EmpContactVO implements Serializable{
	private String employeeId;
	private int empContactId;
	/*private String firstName;
	private String lastName;
	private String middleName;
	private String designation;
	private String department;
	private String headName;
	private String fullName;*/
	private EmployeeVO employee;
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
	
	public EmpContactVO() {
		
	}
	public EmpContactVO(String employeeId, int empContactId, String email, String phone,   
			String addressLine1, String addressLine2, String addressLine3, String secEmail, String secPhone,   
			String secAddressLine1, String secAddressLine2, String secAddressLine3, String city, String state, String pin,
			String secCity, String secState, String secPin) {
		this.employeeId = employeeId;
		this.empContactId = empContactId;
		this.email = email;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.secEmail = secEmail;
		this.secPhone = secPhone;
		this.secAddressLine1 = secAddressLine1;
		this.secAddressLine2 = secAddressLine2;
		this.secAddressLine3 = secAddressLine3;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.secCity = secCity;
		this.secState = secState;
		this.secPin = secPin;
		
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
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getEmpContactId() {
		return empContactId;
	}
	public void setEmpContactId(int empContactId) {
		this.empContactId = empContactId;
	}
	/*public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	private String getName(String fName, String mName, String lName){
		StringBuffer fullNameSB = new StringBuffer(fName);
		mName = Utils.safeTrim(mName);
		if(!mName.equals(""))
			fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(mName)).append(" ").append(Utils.safeTrim(lName));
		return fullNameSB.toString();
	}
	@Override
	public String toString() {
		return "EmpContactVO [employeeId=" + employeeId + ", empContactId=" + empContactId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", middleName=" + middleName + ", designation=" + designation
				+ ", department=" + department + ", headName=" + headName + ", fullName=" + fullName + ", email="
				+ email + ", phone=" + phone + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", addressLine3=" + addressLine3 + ", secEmail=" + secEmail + ", secPhone=" + secPhone
				+ ", secAddressLine1=" + secAddressLine1 + ", secAddressLine2=" + secAddressLine2 + ", secAddressLine3="
				+ secAddressLine3 + "]";
	}*/
	public EmployeeVO getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
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
}
