package com.payroll.employee.deductions.vo;

import com.payroll.Utils;

public class EmpDeductionsVO {
	private int employeeId;
	private double section80C;
	private double cess;
	private double homeLoanIntrst88EE;
	private double selfDisable80U;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	
	public EmpDeductionsVO(){
		
	}
	
	public EmpDeductionsVO(int employeeId, String firstName, String lastName, double section80C, double cess, 
			double homeLoanIntrst88EE, double selfDisable80U){
		this.employeeId = employeeId;
		this.section80C = section80C;
		this.cess = cess;
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
		this.selfDisable80U = selfDisable80U;
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
	}
	
	public EmpDeductionsVO(int employeeId, int deptId, int desgId, int headId, double section80C, double cess, 
			double homeLoanIntrst88EE, double selfDisable80U){
		this.employeeId = employeeId;
		this.departmentId = deptId;
		this.headId = headId;
		this.designationId = desgId;
		this.section80C = section80C;
		this.cess = cess;
		this.homeLoanIntrst88EE = homeLoanIntrst88EE;
		this.selfDisable80U = selfDisable80U;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public double getSection80C() {
		return section80C;
	}
	public double getCess() {
		return cess;
	}
	public double getHomeLoanIntrst88EE() {
		return homeLoanIntrst88EE;
	}
	public double getSelfDisable80U() {
		return selfDisable80U;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public int getHeadId() {
		return headId;
	}
	public int getDesignationId() {
		return designationId;
	}
	public String getFullName() {
		return fullName;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
}
