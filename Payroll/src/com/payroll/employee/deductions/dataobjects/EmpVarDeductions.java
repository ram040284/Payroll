package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpVarDeductions {
	private int employeeId;
	private double afkRent;
	private double society;
	private double otherDeductions;
	private double miscRecovery;
	private String monthDate;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	private String status;
	private short addUpdate;
	private Timestamp rowUpdDate;
	private Employee employee;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatRead = new SimpleDateFormat("yyyy-mm-dd");

	public EmpVarDeductions() {
		super();
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public EmpVarDeductions(int employeeId, String firstName, String lastName, double afkRent, double society, 
			 double otherDeductions, double miscRecovery, String monthDate){
		this.employeeId = employeeId;
		this.afkRent = afkRent;
		this.society = society;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		System.out.println("monthDate:"+monthDate);
		Date dateMonthDate;
		
		if (monthDate!=null){
			try {
				dateMonthDate =  dateFormatRead.parse(monthDate);
				this.monthDate =  dateFormat.format(dateMonthDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else
			this.monthDate = "";
		
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
	}
	
	public EmpVarDeductions(int employeeId, int departmentId , int designationId, int headId, double afkRent, 
			double society, double otherDeductions, double miscRecovery, String monthDate){
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.afkRent = afkRent;
		this.society = society;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		Date dateMonthDate;
		
		if (monthDate!=null){
			try {
				dateMonthDate =  dateFormatRead.parse(monthDate);
				this.monthDate =  dateFormat.format(dateMonthDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else
			this.monthDate = "";
		
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public double getAfkRent() {
		return afkRent;
	}

	public void setAfkRent(double afkRent) {
		this.afkRent = afkRent;
	}

	public double getSociety() {
		return society;
	}

	public void setSociety(double society) {
		this.society = society;
	}

	public double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public double getMiscRecovery() {
		return miscRecovery;
	}

	public void setMiscRecovery(double miscRecovery) {
		this.miscRecovery = miscRecovery;
	}

	public String getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(String monthDate) {
		this.monthDate =  (monthDate != null) ? dateFormat.format(monthDate) : "";
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}	
}
