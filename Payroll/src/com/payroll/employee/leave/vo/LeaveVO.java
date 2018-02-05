package com.payroll.employee.leave.vo;

import java.io.Serializable;

import com.payroll.Utils;
import com.payroll.employee.SearchCriteria;
import com.payroll.employee.vo.EmployeeVO;

public class LeaveVO extends SearchCriteria implements Serializable{
	
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
	
	private int earnLeaveInp;
	private int maternityLeaveInp;
	private int paternityLeaveInp;
	private int extraLeaveInp;
	
	private int earnLeave;
	private int maternityLeave;
	private int paternityLeave;
	private int extraLeave;
	private EmployeeVO employee;
	
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
	
	public LeaveVO(int empId, String empName, int cLeave, int pLeave, int sLeave, int eLeave, 
			int mLeave, int ptLeave, int xLeave, String leaveIds){
		this.casualLeaves = cLeave;
		this.sickLeaves = sLeave;
		this.paidLeaves = pLeave;
		this.employeeId =empId;
		this.fullName = empName;
		this.earnLeave = eLeave;
		this.maternityLeave = mLeave;
		this.paternityLeave = ptLeave;
		this.extraLeave = xLeave;
		this.leaveBalance = casualLeaves + sickLeaves + paidLeaves + earnLeave + maternityLeave + paternityLeave + extraLeave;
		this.leaveIds = leaveIds;
		
	}
	public LeaveVO (int empId, int deptId, int desgId, int headId, int sLeave, int cLeave, int pLeave,
			int eLeave, int mLeave, int ptLeave, int xLeave,int leaveBalance, String leaveIds){
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
		this.earnLeave = eLeave;
		this.maternityLeave = mLeave;
		this.paternityLeave = ptLeave;
		this.extraLeave = xLeave;
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

	public int getEarnLeaveInp() {
		return earnLeaveInp;
	}

	public void setEarnLeaveInp(int earnLeaveInp) {
		this.earnLeaveInp = earnLeaveInp;
	}

	public int getMaternityLeaveInp() {
		return maternityLeaveInp;
	}

	public void setMaternityLeaveInp(int maternityLeaveInp) {
		this.maternityLeaveInp = maternityLeaveInp;
	}

	public int getPaternityLeaveInp() {
		return paternityLeaveInp;
	}

	public void setPaternityLeaveInp(int paternityLeaveInp) {
		this.paternityLeaveInp = paternityLeaveInp;
	}

	public int getExtraLeaveInp() {
		return extraLeaveInp;
	}

	public void setExtraLeaveInp(int extraLeaveInp) {
		this.extraLeaveInp = extraLeaveInp;
	}

	public int getEarnLeave() {
		return earnLeave;
	}

	public void setEarnLeave(int earnLeave) {
		this.earnLeave = earnLeave;
	}

	public int getMaternityLeave() {
		return maternityLeave;
	}

	public void setMaternityLeave(int maternityLeave) {
		this.maternityLeave = maternityLeave;
	}

	public int getPaternityLeave() {
		return paternityLeave;
	}

	public void setPaternityLeave(int paternityLeave) {
		this.paternityLeave = paternityLeave;
	}

	public int getExtraLeave() {
		return extraLeave;
	}

	public void setExtraLeave(int extraLeave) {
		this.extraLeave = extraLeave;
	}

	public EmployeeVO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeVO employee) {
		this.employee = employee;
	}
}
