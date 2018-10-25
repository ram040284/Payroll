package com.payroll.employee.contract;

import java.io.Serializable;
import java.util.Date;

import com.payroll.employee.dataobjects.Employee;

public class EmployeeContract  implements Serializable{
	
	private Employee employee;
	private String employeeId;
	private Date appointmentDate;
	private Date endDate;
	private String engagementLetterId;
	private String status;
	private short addUpdate;
	
	public EmployeeContract() {}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getEngagementLetterId() {
		return engagementLetterId;
	}
	public void setEngagementLetterId(String engagementLetterId) {
		this.engagementLetterId = engagementLetterId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}
	
}
