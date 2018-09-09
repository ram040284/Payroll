package com.payroll.advance.dataobjects;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmployeeAdvance {
	
	private String employeeId;
	private int departmentId;
	private int designationId;
	private int headId;
	private int advanceId;
	private String advanceName;
	private double advanceAmount;
	private double installAmount;
	private String advanceDate;
	private String installStartDate;
	private String status;
	private Timestamp rowUpdDate;
	private String fullName;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	private Employee employee;
	
	public EmployeeAdvance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeAdvance(int advanceId,String employeeId, int departmentId, int designationId, int headId, 
			String advanceName, double advanceAmount,  Date advanceDate, double installAmount, Date installStartDate) {
		super();
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.advanceId = advanceId;
		this.advanceName = advanceName;
		this.advanceAmount = advanceAmount;
		this.installAmount = installAmount;
		if(advanceDate != null)
			this.advanceDate = dateFormat.format(advanceDate);
		if(installStartDate != null)
			this.installStartDate = dateFormat.format(installStartDate);
	}

	public EmployeeAdvance( int advanceId,String employeeId, String fName, String lName, String advanceName, double advanceAmount, Date advanceDate, double installAmount, Date installStartDate) {
		super();
		this.advanceId = advanceId;
		this.employeeId = employeeId;
		this.advanceAmount = advanceAmount;
		this.installAmount = installAmount;
		if(advanceDate != null)
			this.advanceDate = dateFormat.format(advanceDate);
		this.advanceName = advanceName;
		if(installStartDate != null)
			this.installStartDate = dateFormat.format(installStartDate);
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}

	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}
	public int getAdvanceId() {
		return advanceId;
	}
	public void setAdvanceId(int advanceId) {
		this.advanceId = advanceId;
	}
	public String getAdvanceName() {
		return advanceName;
	}
	public void setAdvanceName(String advanceName) {
		this.advanceName = advanceName;
	}
	public double getAdvanceAmount() {
		return advanceAmount;
	}
	public void setAdvanceAmount(double advanceAmount) {
		this.advanceAmount = advanceAmount;
	}
	public double getInstallAmount() {
		return installAmount;
	}
	public void setInstallAmount(double installAmount) {
		this.installAmount = installAmount;
	}
	public String getAdvanceDate() {
		return advanceDate;
	}
	public void setAdvanceDate(String advanceDate) {
		this.advanceDate = advanceDate;
	}
	public String getInstallStartDate() {
		return installStartDate;
	}
	public void setInstallStartDate(String installStartDate) {
		this.installStartDate = installStartDate;
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
