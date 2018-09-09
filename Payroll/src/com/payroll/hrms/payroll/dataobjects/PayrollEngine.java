package com.payroll.hrms.payroll.dataobjects;

import org.apache.log4j.Logger;

import com.payroll.hrms.payroll.dao.EmployeePayrollDAONew;

/**
 * Created by rajendra on 12/7/17.
 */
public class  PayrollEngine {
	public static Logger log = Logger.getLogger(PayrollEngine.class.getName());
    public static double DA_PERCENT = 139.00;
    public static double HRA_PERCENT = 30.00;
    public static int WORKING_DAYS=22;
    public static double PF_PERCENT=6.0;
    public static double CPF_PERCENT=10.0;
    private String section;
    private int[] arrDepartmentId; 
    private int [] arrEmployeeId;

    /**
     * get the list of departments for a section
     */
    private void getDepartments(){
    	
    }
    /**
     * Retrieve List OF EMPLOYEE IDs for all the departments in a section
     */
    private void getEmployeeList(){
    	
    }
    /**
     * processPayroll
     */
    public void processPayroll(String employeeId){
    	// for all employees in Section 
    	System.out.println("Entered processPayroll(int employeeId)" );
    	

    	EmployeePayrollNew employeePayroll = new EmployeePayrollDAONew().loadEmployeePayrollInfo(employeeId);
    	
    	employeePayroll.setEmployeeId(employeeId);
    	System.out.println("Exiting processPayroll(int employeeId)" );
    }
    /**
     * 
     * @param args
     */
    public static void main(String []args){
    	new PayrollEngine().processPayroll("199105024");
    	
    }
}