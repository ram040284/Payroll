package com.payroll.employee.lic.dataobjects;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;

public class EmpLic implements Serializable {
	private String employeeId;
	@Override
	public String toString() {
         /* return "EmpLic [employeeId=" + employeeId + ", paymentAmount=" + paymentAmount
				+ ", policyNo=" + policyNo + ", paymentDate=" + paymentDate + ", status=" + status + ", rowUpdDate="
				+ rowUpdDate + ", addUpdate=" + addUpdate + ", employee=" + employee + "]";
	*/
		return "EmpLic[employeeId=" + employeeId + ", policyNo=" + policyNo
				+ ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", fullName=" + fullName + ", addUpdate=" + addUpdate + ", designationId=" + designationId
				+ ", departmentId=" + departmentId + ", headId=" + headId + "]";
	}
	
	
	private double instlmtAmt;
	private double paymentAmount;
	private String policyNo;
	private String paymentDate;
	private String fullName;
	private String status;
	private Timestamp rowUpdDate;
	private short addUpdate; // 0 - Add / 1 - update
	private Employee employee;
	private int designationId;
	private int departmentId;
	private int headId;
	
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatRead = new SimpleDateFormat("yyyy-mm-dd");

	public EmpLic()
	{
		
	}


	public EmpLic(String empId, String fName, String lName, String policyNo, String paymentDate,double paymentAmount) {
		this.employeeId = empId;
		StringBuffer fullNameSB = new StringBuffer(fName);
		fullNameSB.append(" ");
		fullNameSB.append(Utils.safeTrim(lName));
		this.fullName = fullNameSB.toString();
		this.policyNo = policyNo;
		if (paymentDate != null)
		{
				try {
					this.paymentDate = dateFormat.format(dateFormatRead.parse(paymentDate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		this.paymentAmount = paymentAmount;
	}

	public EmpLic(String empId, int departmentId, int designationId, int headId, String policyNo, String paymentDate, double paymentAmount) {
		this.employeeId = empId;
		this.departmentId = departmentId;
		this.designationId = designationId;
		this.headId = headId;
		this.policyNo = policyNo;
		if (paymentDate != null)
		{
			try {
				this.paymentDate = dateFormat.format(dateFormatRead.parse(paymentDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		}
		this.paymentAmount = paymentAmount;
	}


	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String empId) {
		this.employeeId = empId;
	}

   public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
//		this.paymentDate = paymentDate;
		try {
			this.paymentDate = dateFormatRead.format(dateFormat.parse(paymentDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public double getInstlmtAmt() {
		return instlmtAmt;
	}

	public void setInstlmtAmt(double instlmtAmt) {
		this.instlmtAmt = instlmtAmt;
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




	}
