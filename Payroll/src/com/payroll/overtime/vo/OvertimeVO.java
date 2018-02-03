package com.payroll.overtime.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;

public class OvertimeVO {
	
	private int employeeId;
	private int departmentId;
	private int designationId;
	private int headId;
	private Double overtimeAmount;
	private int overtimeId;
	private Double overtimeHours;
	private String overtimeOrder;
	private String overtimeDate = null;
	private String fullName;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public String getFullName() {
		return fullName;
	}

	public OvertimeVO() {
		
	}
	
	public OvertimeVO(int overtimeId, int empId, int deptId, int desgId, int headId, String overtimeOrder, 
			double overtimeHours, Date overtimeDate, Double overtimeAmount){
		this.employeeId = empId;
		this.overtimeId = overtimeId;
		this.departmentId = deptId;
		this.designationId = desgId;
		this.overtimeAmount = overtimeAmount;
		if(overtimeDate != null)
			this.overtimeDate = dateFormat.format(overtimeDate);
		this.overtimeOrder = overtimeOrder;
		this.overtimeHours = overtimeHours;
		this.headId = headId;
	}
	
	public OvertimeVO(int overtimeId, int empId, String fName, String lName, String overtimeOrder, 
			double overtimeHours, Date overtimeDate, Double overtimeAmount){
		this.employeeId = empId;
		this.overtimeOrder = overtimeOrder;
		this.overtimeHours = overtimeHours;
		this.overtimeAmount = overtimeAmount;
		if(overtimeDate != null)
			this.overtimeDate = dateFormat.format(overtimeDate);
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.overtimeId = overtimeId;	
	}
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int empId) {
		this.employeeId = empId;
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
		return "EmpId |"+employeeId+"|overtimeID|"+overtimeId+"|DesgId|"+designationId+"|overtimeDate|"+overtimeDate
				+"overtimeAmount|"+overtimeAmount;
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

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

}
