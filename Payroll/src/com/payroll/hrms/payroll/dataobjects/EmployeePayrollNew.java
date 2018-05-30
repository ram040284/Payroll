//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;

import com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions;
import com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions;
import com.payroll.employee.lic.vo.EmployeeLIC;
import com.payroll.employee.salary.vo.EmployeeSalary;

/**
 * Created by rajendra on 12/7/17.
 */
public class EmployeePayrollNew {
	private int employeeId;
	private EmployeeSalary employeeSalary;
	private EmployeePayrollEarnings employeePayrollEarnings;
	private EmployeePayrollDeductions employeePayrollDeductions;
	private EmployeeFixedDeductions employeeFixedDeductions;
	private EmployeeVarDeductions employeeVarDeductions;
	private EmployeeLIC employeeLIC; 
	private double grossPay;
	private double totalGrossPay;
	private double netPay;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public EmployeeSalary getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(EmployeeSalary employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public EmployeePayrollEarnings getEmployeePayrollEarnings() {
		return employeePayrollEarnings;
	}

	public void setEmployeePayrollEarnings(EmployeePayrollEarnings employeePayrollEarnings) {
		this.employeePayrollEarnings = employeePayrollEarnings;
	}

	public EmployeePayrollDeductions getEmployeePayrollDeductions() {
		return employeePayrollDeductions;
	}

	public void setEmployeePayrollDeductions(EmployeePayrollDeductions employeePayrollDeductions) {
		this.employeePayrollDeductions = employeePayrollDeductions;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public double getTotalGrossPay() {
		return totalGrossPay;
	}

	public void setTotalGrossPay(double totalGrossPay) {
		this.totalGrossPay = totalGrossPay;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	
    /**
     * load employee Payroll info
     * @param employeePayroll
     */


}