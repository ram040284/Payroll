package com.payroll.employee.contract;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;

public class EmployeeContractVO {
	
	private String employeeId;
	private String appointmentDate;
	private String endDate;
	private String engagementLetterId;
	private String status;
	private String fullName;
	private short addUpdate;
	private Date rowUpdatedDate;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public EmployeeContractVO() {}
	
	public EmployeeContractVO(String employeeId, String fName, String lName, Date appointmentDate, Date endDate, String engagementLetterId) {
		super();
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.employeeId = employeeId;
		this.appointmentDate = (appointmentDate != null) ? dateFormat.format(appointmentDate) : "";
		this.endDate = (endDate != null) ? dateFormat.format(endDate) : "";
		this.engagementLetterId = engagementLetterId;
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public Date getRowUpdatedDate() {
		return rowUpdatedDate;
	}

	public void setRowUpdatedDate(Date rowUpdatedDate) {
		this.rowUpdatedDate = rowUpdatedDate;
	}
	
}
