package com.payroll.employee.lic.vo;

import com.payroll.Utils;

public class EmpLicMasterVO {
	@Override
	public String toString() {
		return "EmpLicMasterVO [employeeId=" + employeeId + ", instlmtAmt=" + instlmtAmt + ", policyNo=" + policyNo
				+ ", fullName=" + fullName + ", addUpdate=" + addUpdate + ", designationId=" + designationId
				+ ", departmentId=" + departmentId + ", headId=" + headId + "]";
	}

	private int employeeId;
	private double instlmtAmt;
	private String policyNo;
	private String fullName;
	private short addUpdate; // 0 - Add / 1 - update
	private int designationId;
	private int departmentId;
	private int headId;
		
	public int getHeadId() {
		return headId;
	}

	public void setHeadId(int headId) {
		this.headId = headId;
	}

	public EmpLicMasterVO(){
		
	}
	
	public EmpLicMasterVO(int empId, String fName, String lName, String policyNo, double instlmtAmt) {
		this.employeeId = empId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.policyNo = policyNo;
		this.instlmtAmt = instlmtAmt;
	}
	/** 
	 * @param empId
	 * @param departmentId
	 * @param designationId
	 * @param policyNo
	 * @param instlmtAmt
	 */
	public EmpLicMasterVO(int empId, int departmentId, int designationId,int headId, String policyNo,  double instlmtAmt) {
		this.employeeId = empId;
		this.policyNo = policyNo;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.instlmtAmt = instlmtAmt;
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

	public String getFullName() {
		return fullName;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}


	public int getDesignationId() {
		return designationId;
	}

	public int getDepartmentId() {
		return departmentId;
	}
	
}
