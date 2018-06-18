package com.payroll.advance.dataobjects;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmployeeAdvanceVO {
	
	private int employeeId;
	private int departmentId;
	private int designationId;
	private int headId;
	private int advanceId;
	private String advanceName;
	private double advanceAmount;
	private double installAmount;
	private Date advanceDate;
	private Date installStartDate;
	private String status;
	private Timestamp rowUpdDate;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private String fullName;
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	private Employee employee;
	
	public EmployeeAdvanceVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeAdvanceVO(int advanceId,int employeeId, int departmentId, int designationId, int headId, 
			String advanceName, double advanceAmount, double installAmount, String advanceDate, String installStartDate,
			String status, Timestamp rowUpdDate) throws ParseException {
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
			this.advanceDate =  dateFormat.parse(advanceDate);
			
		if(installStartDate != null)
			this.installStartDate = dateFormat.parse(installStartDate);
		this.status = status;
	}

	public EmployeeAdvanceVO( int advanceId,int employeeId, String fName, String lName, String advanceName, double advanceAmount, String advanceDate, double installAmount, String installStartDate) throws ParseException {
		super();
		this.advanceId = advanceId;
		this.employeeId = employeeId;
		this.advanceAmount = advanceAmount;
		this.installAmount = installAmount;
		this.advanceName = advanceName;
		if(advanceDate != null)
			this.advanceDate =  dateFormat.parse(advanceDate);
			
		if(installStartDate != null)
			this.installStartDate = dateFormat.parse(installStartDate);
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}

	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
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
	public Date getAdvanceDate() {
		return advanceDate;
	}
	public void setAdvanceDate(Date advanceDate) {
		this.advanceDate = advanceDate;
	}
	public Date getInstallStartDate() {
		return installStartDate;
	}
	public void setInstallStartDate(Date installStartDate) {
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
