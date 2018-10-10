package com.payroll.employee.lic.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpLicMaster implements Serializable {

	@Override
	public String toString() {
		
		return "EmpLicMaster [employeeId=" + employeeId + ", instlmtAmt=" + instlmtAmt + ", policyNo=" + policyNo
				+ ", fullName=" + fullName + ", addUpdate=" + addUpdate + ",policyStartDate=" + policyStartDate + ",policyMaturityDate=" + policyMaturityDate + ", designationId=" + designationId
				+ ", departmentId=" + departmentId + ", headId=" + headId + "]";
	}

	private String employeeId;
	private String policyNo;
	private double instlmtAmt;
	private String status;
	private Timestamp rowUpdDate;
	private short addUpdate; // 0 - Add / 1 - update
	private String policyStartDate;
	private String policyMaturityDate;;
	private Employee employee;
	private int designationId;
	private int departmentId;
	private int headId;
	private String fullName;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatRead = new SimpleDateFormat("yyyy-MM-dd");


	public EmpLicMaster()
	{
	}

	public EmpLicMaster(String empId, String fName, String lName, String policyNo, double instlmtAmt,String policyStartDate,String policyMaturityDate ) {
		this.employeeId = empId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.policyNo = policyNo;
		this.instlmtAmt = instlmtAmt;
		if (policyStartDate != null)
		{
				try {
					this.policyStartDate = dateFormat.format(dateFormatRead.parse(policyStartDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (policyMaturityDate != null)
		{
				try {
					this.policyMaturityDate = dateFormat.format(dateFormatRead.parse(policyMaturityDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
	/** 
	 * @param empId
	 * @param departmentId
	 * @param designationId
	 * @param policyNo
	 * @param instlmtAmt
	 */
	public EmpLicMaster(String empId, int departmentId, int designationId,int headId, String policyNo,  double instlmtAmt,String policyStartDate,String policyMaturityDate) {
		this.employeeId = empId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.policyNo = policyNo;
        this.instlmtAmt = instlmtAmt;
        if (policyStartDate != null)
		{
				try {
					this.policyStartDate = dateFormat.format(dateFormatRead.parse(policyStartDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (policyMaturityDate != null)
		{
				try {
					this.policyMaturityDate = dateFormat.format(dateFormatRead.parse(policyMaturityDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
       	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getDesignationId() {
		return designationId;
	}

	public void setDesignationId(int designationId) {
		this.designationId = designationId;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}

	public double getInstlmtAmt() {
		return instlmtAmt;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(String policyStartDate) {
		try {
			this.policyStartDate = dateFormatRead.format(dateFormat.parse(policyStartDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPolicyMaturityDate() {
		return policyMaturityDate;
	}

	public void setPolicyMaturityDate(String policyMaturityDate) {
		try {
			this.policyMaturityDate = dateFormatRead.format(dateFormat.parse(policyMaturityDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRowUpdDate() {
		return rowUpdDate;
	}

	public void setRowUpdDate(Timestamp rowUpdDate) {
		this.rowUpdDate = rowUpdDate;
	}

	public short getAddUpdate() {
		return addUpdate;
	}

	public void setAddUpdate(short addUpdate) {
		this.addUpdate = addUpdate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
