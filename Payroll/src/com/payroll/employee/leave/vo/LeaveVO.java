package com.payroll.employee.leave.vo;

import com.payroll.Utils;
import com.payroll.employee.SearchCriteria;

public class LeaveVO extends SearchCriteria{
	
	private int employeeId;
	private int leaveId;
	private String leaveType;
	private int noOfLeaves;
	private int leaveBalance;
	private String fullName;
	private int departmentId;
	private int designationId;
	private int headId;
	
	private int casualLeaves;
	private int paidLeaves;
	private int sickLeaves;
	private String leaveIds;
	private int casualLeaveInp;
	private int paidLeaveInp;
	private int sickLeaveInp;
	
	public LeaveVO(){
		super();
	}
	
	public LeaveVO(int empId, String fName, String lName, int leaveId, String leaveType, int noOfLeaves, int leaveBalance){
		this.employeeId = empId;
		this.leaveId = leaveId;
		this.leaveBalance = leaveBalance;
		this.noOfLeaves = noOfLeaves;
		this.leaveType = leaveType;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}
	
	public LeaveVO (int empId, int deptId, int desgId, int headId, int leaveId, String leaveType,
			int noOfLeaves, int leaveBalance){
		super(deptId, headId);
		this.employeeId = empId;
		//this.departmentId = deptId;
		this.designationId = desgId;
		this.leaveBalance = leaveBalance;
		this.noOfLeaves = noOfLeaves;
		this.leaveType = leaveType;
		this.leaveId = leaveId;
		//this.headId = headId;
		
	}
	
	public LeaveVO(int empId, String empName, int cLeave, int pLeave, int sLeave, String leaveIds){
		this.casualLeaves = cLeave;
		this.sickLeaves = sLeave;
		this.paidLeaves = pLeave;
		this.employeeId =empId;
		this.fullName = empName;
		this.leaveBalance = casualLeaves + sickLeaves + paidLeaves;
		this.leaveIds = leaveIds;
		
	}
	public LeaveVO (int empId, int deptId, int desgId, int headId, int sLeave, int cLeave, int pLeave,
			int leaveBalance, String leaveIds){
		super(deptId, headId);
		this.employeeId = empId;
		//this.departmentId = deptId;
		this.designationId = desgId;
		this.leaveBalance = leaveBalance;
		//this.headId = headId;
		this.casualLeaves = cLeave;
		this.sickLeaves = sLeave;
		this.paidLeaves = pLeave;
		this.leaveIds = leaveIds;
	}
	

	public int getEmployeeId() {
		return employeeId;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public int getNoOfLeaves() {
		return noOfLeaves;
	}

	public int getLeaveBalance() {
		return leaveBalance;
	}

	public String getFullName() {
		return fullName;
	}
	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public void setNoOfLeaves(int noOfLeaves) {
		this.noOfLeaves = noOfLeaves;
	}

	public void setLeaveBalance(int leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "employeeId:"+employeeId+" |  leaveType:"+this.leaveType+" | noOfLeaves:"+this.noOfLeaves +" | LeaveId:"+leaveId;
	}

	public int getCasualLeaves() {
		return casualLeaves;
	}

	public int getPaidLeaves() {
		return paidLeaves;
	}

	public int getSickLeaves() {
		return sickLeaves;
	}

	public void setCasualLeaves(int casualLeaves) {
		this.casualLeaves = casualLeaves;
	}

	public void setPaidLeaves(int paidLeaves) {
		this.paidLeaves = paidLeaves;
	}

	public void setSickLeaves(int sickLeaves) {
		this.sickLeaves = sickLeaves;
	}

	public String getLeaveIds() {
		return leaveIds;
	}

	public void setLeaveIds(String leaveIds) {
		this.leaveIds = leaveIds;
	}

	public int getCasualLeaveInp() {
		return casualLeaveInp;
	}

	public void setCasualLeaveInp(int casualLeaveInp) {
		this.casualLeaveInp = casualLeaveInp;
	}

	public int getPaidLeaveInp() {
		return paidLeaveInp;
	}

	public void setPaidLeaveInp(int paidLeaveInp) {
		this.paidLeaveInp = paidLeaveInp;
	}

	public int getSickLeaveInp() {
		return sickLeaveInp;
	}

	public void setSickLeaveInp(int sickLeaveInp) {
		this.sickLeaveInp = sickLeaveInp;
	}
}
