package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;
import java.util.Date;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpFixedDeductions {
	private String employeeId;
	private double kssUnionFee;
	private double rent;
	private double courtRecovery;
	private double unionFee;
	private double gis;
	private double additionalPF;
	private int departmentId;
	private int headId;
	private int designationId;
	private String fullName;
	private String status;
	private short addUpdate;
	private Date rowUpdDate;
	private Employee employee;
	//FIXME: Review Comments - Chetan - Review nemaing conventions
	private double ApfAcpf;

	public EmpFixedDeductions(String employeeId, String firstName, String lastName, double kssUnionFee, double rent, 
			 double courtRecovery, double unionFee, double gis, double additionalPF, double ApfAcpf, Date rowUpdDate){
		this.employeeId = employeeId;
		this.kssUnionFee = kssUnionFee;
		this.rent = rent;
		this.courtRecovery = courtRecovery;
		this.unionFee = unionFee;
		this.gis = gis;
		StringBuffer nameSB = new StringBuffer(Utils.safeTrim(firstName));
		nameSB.append(" ");
		nameSB.append(Utils.safeTrim(lastName));
		this.fullName = nameSB.toString();
		this.additionalPF = additionalPF;
		this.ApfAcpf = ApfAcpf;
		this.rowUpdDate = rowUpdDate;
	}
	
	public EmpFixedDeductions(String employeeId, int departmentId , int designationId, int headId, double kssUnionFee, 
			double rent, double courtRecovery, double unionFee, double gis, double additionalPF, double ApfAcpf, Date rowUpdDate){
		this.employeeId = employeeId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.rent = rent;
		this.kssUnionFee = kssUnionFee;
		this.courtRecovery = courtRecovery;
		this.unionFee = unionFee;
		this.gis = gis;
		this.additionalPF = additionalPF;
		this.ApfAcpf = ApfAcpf;
		this.rowUpdDate = rowUpdDate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public double getKssUnionFee() {
		return kssUnionFee;
	}

	public void setKssUnionFee(double kssUnionFee) {
		this.kssUnionFee = kssUnionFee;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public double getCourtRecovery() {
		return courtRecovery;
	}

	public void setCourtRecovery(double courtRecovery) {
		this.courtRecovery = courtRecovery;
	}

	public double getUnionFee() {
		return unionFee;
	}

	public void setUnionFee(double unionFee) {
		this.unionFee = unionFee;
	}

	public double getGis() {
		return gis;
	}

	public void setGis(double gis) {
		this.gis = gis;
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
	
	public double getAdditionalPF() {
		return additionalPF;
	}

	public void setAdditionalPF(double additionalPF) {
		this.additionalPF = additionalPF;
	}
	public EmpFixedDeductions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Date rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public double getApfAcpf() {
		return ApfAcpf;
	}

	public void setApfAcpf(double apfAcpf) {
		ApfAcpf = apfAcpf;
	}
}
