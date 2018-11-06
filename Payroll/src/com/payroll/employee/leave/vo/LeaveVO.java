package com.payroll.employee.leave.vo;

import java.io.Serializable;
import com.payroll.Utils;
import com.payroll.employee.SearchCriteria;
import com.payroll.employee.leave.dataobjects.LeaveType;
import com.payroll.employee.vo.EmployeeVO;

public class LeaveVO extends SearchCriteria implements Serializable{
	
	private String employeeId;
	private int leaveId;
	private LeaveType leaveType;
	private int leaveTypeId;
	private int noOfLeaves;
	private int leaveBalance;
	private String fullName;
	private int departmentId;
	private int designationId;
	private int headId;
	
	private int casualLeaves;
	private int childLeaves;
	
	private int paidLeaves;
	private int sickLeaves;
	private String leaveIds;
    private int casualLeaveInp;
	private int childLeaveInp;
	public int getChildLeaveInp() {
		return childLeaveInp;
	}

	public void setChildLeaveInp(int childLeaveInp) {
		this.childLeaveInp = childLeaveInp;
	}

	private int paidLeaveInp;
	private int sickLeaveInp;
	
	private int earnLeaveInp;
	private int maternityLeaveInp;
	private int paternityLeaveInp;
	private int extraLeaveInp;
	private int extraOrdinaryLeaveWithoutMediInp;
	
	private int earnLeave;
	private int maternityLeave;
	private int paternityLeave;
	private int extraLeave;
	private int extraOrdinaryLeaveWithoutMedi;
	private EmployeeVO employee;
	
	private Integer casualLeaveBal;
	private Integer sickLeaveBal;
	private Integer halfPaidLeaveBal;
	private Integer earnLeaveBal;
	private Integer maternityLeaveBal;
	private Integer paternitiLeaveBal;
	private Integer extraOrdLeaveBal;
	private Integer childCareLeaveBal;
	private Integer extraOrdLeaveWithoutMediBal;
	
	public LeaveVO(String employeeId, String fName, String lName, Integer casualLeaveBal
			, Integer sickLeaveBal, Integer halfPaidLeaveBal, Integer earnLeaveBal,
			Integer maternityLeaveBal, Integer paternitiLeaveBal, Integer extraOrdLeaveBal, Integer childCareLeaveBal,
			Integer extraOrdLeaveWithoutMediBal
			) {
		this.employeeId = employeeId;
		this.casualLeaveBal = casualLeaveBal;
		this.sickLeaveBal = sickLeaveBal;
		this.halfPaidLeaveBal = halfPaidLeaveBal;
		this.earnLeaveBal = earnLeaveBal;
		this.maternityLeaveBal = maternityLeaveBal;
		this.paternitiLeaveBal = paternitiLeaveBal;
		this.extraOrdLeaveBal = extraOrdLeaveBal;
		this.childCareLeaveBal = childCareLeaveBal;
		this.extraOrdLeaveWithoutMediBal = extraOrdLeaveWithoutMediBal;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}

	public LeaveVO(){
		super();
	}
	
	public LeaveVO(String empId, String fName, String lName, int leaveId, int leaveTypeId, int leaveBalance){
		this.employeeId = empId;
		this.leaveId = leaveId;
		this.leaveBalance = leaveBalance;
//		this.noOfLeaves = noOfLeaves;
		this.leaveTypeId = leaveTypeId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
	}
	
	public LeaveVO (String empId, int deptId, int desgId, int headId, int leaveId, int leaveTypeId,
			int leaveBalance){
		super(deptId, headId);
		this.employeeId = empId;
		//this.departmentId = deptId;
		this.designationId = desgId;
		this.leaveBalance = leaveBalance;
//		this.noOfLeaves = noOfLeaves;
		this.leaveTypeId = leaveTypeId;
		this.leaveId = leaveId;
		//this.headId = headId;
		
	}
	
	public LeaveVO(String empId, String empName, int cLeave,int ccLeave, int pLeave, int sLeave, int eLeave, 
			int mLeave, int ptLeave, int xLeave, String leaveIds, int extraOrdinaryLeaveWithoutMedi){
		this.casualLeaves = cLeave;
		this.childLeaves = ccLeave;
		this.sickLeaves = sLeave;
		this.paidLeaves = pLeave;
		this.employeeId =empId;
		this.fullName = empName;
		this.earnLeave = eLeave;
		this.maternityLeave = mLeave;
		this.paternityLeave = ptLeave;
		this.extraLeave = xLeave;
		this.extraOrdinaryLeaveWithoutMedi = extraOrdinaryLeaveWithoutMedi;
		this.leaveBalance = casualLeaves + childLeaves + sickLeaves + paidLeaves + earnLeave + maternityLeave + paternityLeave + extraLeave + extraOrdinaryLeaveWithoutMedi;
		this.leaveIds = leaveIds;
		
	}
	public LeaveVO (String empId, int deptId, int desgId, int headId, int sLeave, int cLeave,int ccLeave, int pLeave,
			int eLeave, int mLeave, int ptLeave, int xLeave,int leaveBalance, String leaveIds, int extraOrdinaryLeaveWithoutMedi){
		super(deptId, headId);
		this.employeeId = empId;
		//this.departmentId = deptId;
		this.designationId = desgId;
		this.leaveBalance = leaveBalance;
		//this.headId = headId;
		this.casualLeaves = cLeave;
		this.childLeaves = ccLeave;
		this.sickLeaves = sLeave;
		this.paidLeaves = pLeave;
		this.leaveIds = leaveIds;
		this.earnLeave = eLeave;
		this.maternityLeave = mLeave;
		this.paternityLeave = ptLeave;
		this.extraLeave = xLeave;
		this.extraOrdinaryLeaveWithoutMedi = extraOrdinaryLeaveWithoutMedi;
	}
	

	public String getEmployeeId() {
		return employeeId;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public LeaveType getLeaveType() {
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

	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public void setLeaveType(LeaveType leaveType) {
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
		return "employeeId:"+employeeId+" |  leaveType:"+ (this.leaveType != null ? this.leaveType.getName() : " leave type is null ")+" | noOfLeaves:"+this.noOfLeaves +" | LeaveId:"+leaveId;
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
	public int getchildLeaves() {
		return childLeaves;
	}

	public void setchildLeaves(int childLeaves) {
		this.childLeaves = childLeaves;
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

	public int getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(int leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public int getExtraOrdinaryLeaveWithoutMediInp() {
		return extraOrdinaryLeaveWithoutMediInp;
	}

	public void setExtraOrdinaryLeaveWithoutMediInp(int extraOrdinaryLeaveWithoutMediInp) {
		this.extraOrdinaryLeaveWithoutMediInp = extraOrdinaryLeaveWithoutMediInp;
	}

	public int getExtraOrdinaryLeaveWithoutMedi() {
		return extraOrdinaryLeaveWithoutMedi;
	}

	public void setExtraOrdinaryLeaveWithoutMedi(int extraOrdinaryLeaveWithoutMedi) {
		this.extraOrdinaryLeaveWithoutMedi = extraOrdinaryLeaveWithoutMedi;
	}

	public Integer getCasualLeaveBal() {
		return casualLeaveBal;
	}

	public void setCasualLeaveBal(Integer casualLeaveBal) {
		this.casualLeaveBal = casualLeaveBal;
	}

	public Integer getSickLeaveBal() {
		return sickLeaveBal;
	}

	public void setSickLeaveBal(Integer sickLeaveBal) {
		this.sickLeaveBal = sickLeaveBal;
	}

	public Integer getHalfPaidLeaveBal() {
		return halfPaidLeaveBal;
	}

	public void setHalfPaidLeaveBal(Integer halfPaidLeaveBal) {
		this.halfPaidLeaveBal = halfPaidLeaveBal;
	}

	public Integer getEarnLeaveBal() {
		return earnLeaveBal;
	}

	public void setEarnLeaveBal(Integer earnLeaveBal) {
		this.earnLeaveBal = earnLeaveBal;
	}

	public Integer getMaternityLeaveBal() {
		return maternityLeaveBal;
	}

	public void setMaternityLeaveBal(Integer maternityLeaveBal) {
		this.maternityLeaveBal = maternityLeaveBal;
	}

	public Integer getPaternitiLeaveBal() {
		return paternitiLeaveBal;
	}

	public void setPaternitiLeaveBal(Integer paternitiLeaveBal) {
		this.paternitiLeaveBal = paternitiLeaveBal;
	}

	public Integer getExtraOrdLeaveBal() {
		return extraOrdLeaveBal;
	}

	public void setExtraOrdLeaveBal(Integer extraOrdLeaveBal) {
		this.extraOrdLeaveBal = extraOrdLeaveBal;
	}

	public Integer getChildCareLeaveBal() {
		return childCareLeaveBal;
	}

	public void setChildCareLeaveBal(Integer childCareLeaveBal) {
		this.childCareLeaveBal = childCareLeaveBal;
	}

	public Integer getExtraOrdLeaveWithoutMediBal() {
		return extraOrdLeaveWithoutMediBal;
	}

	public void setExtraOrdLeaveWithoutMediBal(Integer extraOrdLeaveWithoutMediBal) {
		this.extraOrdLeaveWithoutMediBal = extraOrdLeaveWithoutMediBal;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
