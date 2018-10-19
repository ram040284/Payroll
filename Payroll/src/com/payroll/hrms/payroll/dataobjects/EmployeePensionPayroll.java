package com.payroll.hrms.payroll.dataobjects;

import java.util.Date;

import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.vo.EmployeeVO;

public class EmployeePensionPayroll {
	
	public static double DA_PERCENT = 139.00;
	
	private String employeeId;
	private Employee employee;
	private double basicPension;
	private double totalPensionDeduction;
	private double netPension;
	private double residualPension;
	private double dearnessReliefArrears;
	private String fullName;
	private double commutationAmount;
	private double medicalAllowance;
	private byte familyPensionFlag;
	private String familyPensionName;
	private String pensionRemark;
	private Date month;
	private double da;
	private double basic;
    private double gradePay;
    
    private String employeeName;
    private String designation;
    private String deptCostHead;
    private String department;
    private String joiningDate;
    private String retirementDate;
    
	public EmployeePensionPayroll() {}
	
	public void setEmployee(EmployeeVO empVO){
    	this.employeeName = empVO.getFullName();
    	this.department = empVO.getDepartment();
    	this.deptCostHead = empVO.getHeadName();
    	this.designation = empVO.getDesignation();
    	this.joiningDate = empVO.getJoiningDate();
    	this.retirementDate = empVO.getRetirementDate();
    	this.employeeId = empVO.getEmployeeId();
    }
	
	public EmployeePensionPayroll(String employeeId, double basicPension, double residualPension, double dearnessReliefArrears,
			String fullName, double commutationAmount, double medicalAllowance, byte familyPensionFlag, String familyPensionName, String pensionRemark) {
		super();
		this.employeeId = employeeId;
		this.basicPension = basicPension;
		this.residualPension = residualPension;
		this.dearnessReliefArrears = dearnessReliefArrears;
		this.fullName = fullName;
		this.commutationAmount = commutationAmount;
		this.medicalAllowance = medicalAllowance;
		this.familyPensionFlag = familyPensionFlag;
		this.familyPensionName = familyPensionName;
		this.pensionRemark = pensionRemark;
		
		calculateDA();
		calculateTotalPensionDeduction();
		calculateNetPension();
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getBasicPension() {
		return basicPension;
	}
	public void setBasicPension(double basicPension) {
		this.basicPension = basicPension;
	}
	public double getDa() {
		return da;
	}
	public void setDa(double da) {
		this.da = da;
	}
	public double getTotalPensionDeduction() {
		return totalPensionDeduction;
	}
	public void setTotalPensionDeduction(double totalPensionDeduction) {
		this.totalPensionDeduction = totalPensionDeduction;
	}
	public double getNetPension() {
		return netPension;
	}
	public void setNetPension(double netPension) {
		this.netPension = netPension;
	}
	public double getResidualPension() {
		return residualPension;
	}
	public void setResidualPension(double residualPension) {
		this.residualPension = residualPension;
	}
	public double getDearnessReliefArrears() {
		return dearnessReliefArrears;
	}
	public void setDearnessReliefArrears(double dearnessReliefArrears) {
		this.dearnessReliefArrears = dearnessReliefArrears;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getCommutationAmount() {
		return commutationAmount;
	}
	public void setCommutationAmount(double commutationAmount) {
		this.commutationAmount = commutationAmount;
	}
	public double getMedicalAllowance() {
		return medicalAllowance;
	}
	public void setMedicalAllowance(double medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}
	public byte getFamilyPensionFlag() {
		return familyPensionFlag;
	}
	public void setFamilyPensionFlag(byte familyPensionFlag) {
		this.familyPensionFlag = familyPensionFlag;
	}
	public String getFamilyPensionName() {
		return familyPensionName;
	}
	public void setFamilyPensionName(String familyPensionName) {
		this.familyPensionName = familyPensionName;
	}
	public String getPensionRemark() {
		return pensionRemark;
	}
	public void setPensionRemark(String pensionRemark) {
		this.pensionRemark = pensionRemark;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	
	private void calculateDA(){
        this.da = 0.0;
        if (this.basic + this.gradePay >0)
            this.da = Math.round(DA_PERCENT * (this.basic+ this.gradePay)/100);
    }
	
	private void calculateTotalPensionDeduction(){
        this.totalPensionDeduction = 0.0;
        this.totalPensionDeduction = this.basicPension + this.residualPension + this.dearnessReliefArrears + this.medicalAllowance;
    }
	
	private void calculateNetPension(){
        this.netPension = 0.0;
        this.netPension = this.totalPensionDeduction;
    }

	public double getBasic() {
		return basic;
	}

	public void setBasic(double basic) {
		this.basic = basic;
	}

	public double getGradePay() {
		return gradePay;
	}

	public void setGradePay(double gradePay) {
		this.gradePay = gradePay;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDeptCostHead() {
		return deptCostHead;
	}

	public void setDeptCostHead(String deptCostHead) {
		this.deptCostHead = deptCostHead;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getRetirementDate() {
		return retirementDate;
	}

	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}
}
