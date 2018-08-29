package com.payroll.employee.deductions.vo;

import com.payroll.Utils;

public class EmpDeductionsDetailsVO {
	
	private int employeeId;
	private double afkRent;
	private double society;
	private double electRecovery;
	private double courtRecovery;
	private double unionFee;
	private double otherDeductions;
	private double miscRecovery;
	private double kssUnionRecovery;
	private double profTax;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	private double incomeTax;
	private double absenties;
	
	public EmpDeductionsDetailsVO() {
		
	}
	
	public EmpDeductionsDetailsVO(int employeeId, String firstName, String lastName, double afkRent, double society, 
			double electRecovery, double courtRecovery, double unionFee, double otherDeductions, 
			double miscRecovery, double kssUnionRecovery, double incomeTax, double absenties){
		this.employeeId = employeeId;
		this.afkRent = afkRent;
		this.society = society;
		this.electRecovery = electRecovery;
		this.courtRecovery = courtRecovery;
		this.unionFee = unionFee;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		this.kssUnionRecovery = kssUnionRecovery;
		this.profTax = profTax;
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
		this.incomeTax = incomeTax;
		this.absenties = absenties;
	}
	
	public EmpDeductionsDetailsVO(int employeeId, int departmentId , int designationId, int headId, double afkRent, 
			double society, double electRecovery, double courtRecovery, double unionFee, double otherDeductions, 
			double miscRecovery, double kssUnionRecovery, double incomeTax, double absenties){
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.afkRent = afkRent;
		this.society = society;
		this.electRecovery = electRecovery;
		this.courtRecovery = courtRecovery;
		this.unionFee = unionFee;
		this.otherDeductions = otherDeductions;
		this.miscRecovery = miscRecovery;
		this.kssUnionRecovery = kssUnionRecovery;
		this.profTax = profTax;
		this.incomeTax = incomeTax;
		this.absenties = absenties;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public double getAfkRent() {
		return afkRent;
	}
	public double getSociety() {
		return society;
	}
	public double getElectRecovery() {
		return electRecovery;
	}
	public double getCourtRecovery() {
		return courtRecovery;
	}
	public double getUnionFee() {
		return unionFee;
	}
	public double getOtherDeductions() {
		return otherDeductions;
	}
	public double getMiscRecovery() {
		return miscRecovery;
	}
	public double getKssUnionRecovery() {
		return kssUnionRecovery;
	}
	public double getProfTax() {
		return profTax;
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
	public double getIncomeTax() {
		return incomeTax;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public double getAbsenties() {
		return absenties;
	}

	public void setAbsenties(double absenties) {
		this.absenties = absenties;
	}
	
}
