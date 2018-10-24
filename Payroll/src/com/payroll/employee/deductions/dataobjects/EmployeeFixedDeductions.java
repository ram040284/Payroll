package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;

public class EmployeeFixedDeductions {
	private String employeeId;
	private double kssUnionFee;
	private double rent;
	private double courtRecovery;
	private double unionFee;
	private double gis;
	private double electricityRecovery;
	private String status;
	private short addUpdate;
	
	//FIXME
	private double ApfAcpf;


	private Timestamp rowUpdDate;

	public EmployeeFixedDeductions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public EmployeeFixedDeductions(String employeeId,  Double kssUnionFee, Double rent, 
			 Double courtRecovery, Double unionFee, Double gis, double ApfAcpf){
		this.employeeId = employeeId;
		this.kssUnionFee = kssUnionFee;
		this.rent = rent;
		this.courtRecovery = courtRecovery;
		this.unionFee = unionFee;
		this.gis = gis;
		this.ApfAcpf = ApfAcpf;
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
	
	public double getElectricityRecovery() {
		return electricityRecovery;
	}

	public void setElectricityRecovery(double electricityRecovery) {
		this.electricityRecovery = electricityRecovery;
	}

	
	public double getApfAcpf() {
		return ApfAcpf;
	}

	public void setApfAcpf(double apfAcpf) {
		ApfAcpf = apfAcpf;
	}

	@Override
	public String toString() {
		return "EmployeeFixedDeductions [employeeId=" + employeeId + ", kssUnionFee=" + kssUnionFee + ", rent=" + rent
				+ ", courtRecovery=" + courtRecovery + ", unionFee=" + unionFee + ", gis=" + gis + ", status=" + status
				+ ", addUpdate=" + addUpdate + ", rowUpdDate=" + rowUpdDate + ", electricityRecovery="+electricityRecovery+"]";
	}
}
