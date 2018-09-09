package com.payroll.employee.lic.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.payroll.Utils;

public class EmpLicVO {
	@Override
	public String toString() {
		return "EmpLicMasterVO [employeeId=" + employeeId + ", policyNo=" + policyNo
				+ ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", fullName=" + fullName + ", addUpdate=" + addUpdate + ", designationId=" + designationId
				+ ", departmentId=" + departmentId + ", headId=" + headId + "]";
	}
	
	private int employeeId;
	private double instlmtAmt;
	private String policyNo;
	private String paymentDate;
	private double paymentAmount;
	private String fullName;
	private short addUpdate; // 0 - Add / 1 - update
	private int designationId;
	private int departmentId;
	private int headId;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public EmpLicVO() {

	}

	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}
	
	public EmpLicVO(int empId, String fName, String lName, String policyNo, Date paymentDate,double paymentAmount) {
		this.employeeId = empId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.policyNo = policyNo;
		if (paymentDate != null)
			this.paymentDate = dateFormat.format(paymentDate);
		this.paymentAmount = paymentAmount;
	}

	public EmpLicVO(int empId, int departmentId, int designationId, int headId, String policyNo, Date paymentDate, double paymentAmount) {
		this.employeeId = empId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.policyNo = policyNo;
		if (paymentDate != null)
			this.paymentDate = dateFormat.format(paymentDate);
		this.paymentAmount = paymentAmount;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public double getInstlmtAmt() {
		return instlmtAmt;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
   public String getFullName() {
		return fullName;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public void setEmployeeId(int empId) {
		this.employeeId = empId;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public int getDesignationId() {
		return designationId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

}
