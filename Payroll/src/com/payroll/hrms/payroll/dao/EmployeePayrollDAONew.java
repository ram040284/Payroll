//package com.kcb.hrms.payroll.dao;
package com.payroll.hrms.payroll.dao;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.payroll.advance.business.EmployeeAdvanceService;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.allowance.vo.EmployeeAllowances;
import com.payroll.employee.deductions.dao.EmpFixedDeductionsDAO;
import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions;
import com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions;
import com.payroll.employee.lic.dataobjects.EmpLicDAO;
import com.payroll.employee.lic.vo.EmployeeLIC;
import com.payroll.employee.salary.dataobjects.SalaryDAO;
import com.payroll.employee.salary.vo.EmployeeSalary;
import com.payroll.hrms.payroll.dataobjects.EmployeePayrollNew;
import com.payroll.overtime.business.OvertimeService;
import com.payroll.overtime.vo.OvertimeVO;
/**
 * 
 * Created by rajendra on 12/8/17.
 * 
 */
public class EmployeePayrollDAONew {
	private Session session = null;
	
	
    @SuppressWarnings("unchecked")
	public EmployeePayrollNew loadEmployeePayrollInfo(int employeeId){
    	
    	System.out.println("Entered EmployeePayrollNew: "+employeeId);
    	EmployeePayrollNew employeePayroll = new EmployeePayrollNew();
    	employeePayroll.setEmployeeId(employeeId);
    	// Employee Salary details
    	EmployeeSalary employeeSalary = new SalaryDAO().getEmployeeSalary(employeeId);
    	System.out.println(employeeSalary.toString());
    	
    	// Employee Allowances details
    	EmployeeAllowances employeeAllowances = new EmpAllowanceDAO().getEmployeeAllowances(employeeId);
    	System.out.println(employeeAllowances.toString());
    	
    	// Employee Fixed Deductions details
    	EmployeeFixedDeductions employeeFixedDeductions = new EmpFixedDeductionsDAO().getEmployeeFixedDeductions(employeeId);
    	System.out.println("EmployeeFixedDeductions : "+ employeeFixedDeductions);
    	
    	// Employee Monthly Variable Deductions details
    	EmployeeVarDeductions employeeVarDeductions = new EmpVarDeductionsDAO().getEmployeeVarDeductions(employeeId);
    	System.out.println("employeeVarDeductions : "+ employeeVarDeductions);
    	
    	// Employee Lic Deductions details
    	List <EmployeeLIC> listEmployeeLICDeductions = new EmpLicDAO().getEmployeeLicDeductions(employeeId);
    	double licTotalInstallmentAmt = 0;
    	if (listEmployeeLICDeductions!=null){
    		Iterator<EmployeeLIC> listEmpLicIterator = listEmployeeLICDeductions.iterator();
    		
    		while (listEmpLicIterator.hasNext()){
    			EmployeeLIC employeeLIC = listEmpLicIterator.next();
    			licTotalInstallmentAmt =licTotalInstallmentAmt + employeeLIC.getInstlmtAmt();
    			}
    		}
    	System.out.println("listEmployeeLICDeductions : "+ listEmployeeLICDeductions +" licTotalInstallmentAmt: "+licTotalInstallmentAmt);    	
    	// Festival Advance loan recovery
    	double advanceInstallmentAmount = new EmployeeAdvanceService().getAdvanceInstallment(employeeId);
    	System.out.println("Employee Advance Amount : "+ advanceInstallmentAmount);
    	
    	//	Overtime
    	List<OvertimeVO> overTimeList = new OvertimeService().getOvertimeByEmpId(employeeId);
    	
    	//Rerieve Absetism or LWP
    	
    	// Absentism
    	//income tax calculations
    	
    	//professional tax

    	return employeePayroll;
    }
     
}