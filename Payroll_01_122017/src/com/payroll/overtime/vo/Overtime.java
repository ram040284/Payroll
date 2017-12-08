package com.payroll.overtime.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;

public class Overtime {
	
	private int empId;
	private int departmentId;
	private int designationId;
	private Double overtimeAmount;
	private String overtimeDate = null;
	private String fullName;
	private String departmentName;
	private String designationName;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public String getFullName() {
		return fullName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getDesignationName() {
		return designationName;
	}
	public Overtime() {
		
	}
	
	public Overtime(int empId, String fName, String lName, int deptId, String deptName, 
			int desgId, String desgName, Date overtimeDate, Double overtimeAmount){
		this.empId = empId;
		this.departmentId = deptId;
		this.designationId = desgId;
		this.departmentName = deptName;
		this.designationName = desgName;
		this.overtimeAmount = overtimeAmount;
		if(overtimeDate != null)
			this.overtimeDate = dateFormat.format(overtimeDate);
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
			
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public Double getOvertimeAmount() {
		return overtimeAmount;
	}
	public void setOvertimeAmount(Double overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}
	public String getOvertimeDate() {
		return overtimeDate;
	}
	public void setOvertimeDate(String overtimeDate) {
		this.overtimeDate = overtimeDate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "EmpId |"+empId+"|DeptId|"+departmentId+"|DesgId|"+designationId+"|overtimeDate|"+overtimeDate
				+"overtimeAmount|"+overtimeAmount;
	}

}
