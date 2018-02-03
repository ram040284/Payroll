//package com.kcb.hrms.payroll.dataobjects;
package com.payroll.hrms.payroll.dataobjects;

//import com.kcb.hrms.payroll.service.EmployeePayrollService;
import com.payroll.hrms.payroll.incometax.service.EmployeePayrollService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rajendra on 12/7/17.
 */
public class  PayrollEngine {
    private double daPercent;
    private double hraPercent;
    private int deptCode;

    public void runPayroll(){

        //List<String> empId = employeeService.getListOfEmployees(String deptId);

        String empId = null;
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        EmployeePayrollDTO employeePayrollDTO =  employeePayrollService.retrievePayrollData(empId);

        EmployeePayroll empPayroll = new EmployeePayroll(employeePayrollDTO);

        //********** load payroll info ***************
        //load basic grade pay
        // load allowances
        //load PF Flag, Load HRA flag
        //Calculate DA,
        // calculate HRA,
        // calculate TA
        //Calculate Allowances
        //Check Absentism
        //calculate over time
        //Calculate Gross Salary


    }
    public static void main(String [] arg){
        double basic = 7530.00;
        double gradepay = 1300.00;
        double da = Math.round(136*(basic+gradepay)/100);
        double hra = Math.round(30*(basic+gradepay)/100);
        double travelAllowance;
        travelAllowance = 0.0;
        boolean handiCapped = false;

        if (!handiCapped) {
            if (gradepay >= 5400.00)
                travelAllowance = 2400.00;
            else if (gradepay >= 4400.00 && gradepay < 5400.00)
                travelAllowance = 1200.00;
            else
                travelAllowance = 400.00;
        } else{
            if(gradepay>=4400.00)
                travelAllowance = 2400;
            else
                travelAllowance = 2000;
        }
        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //int month = cal.get(Calendar.MONTH);
        //cal.get(Calendar.DAY_OF_MONTH);
        //cal.set(Calendar.YEAR, Calendar.MONTH,1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        
        System.out.println("Dearness Alllowance is : " + Math.round(da));
        System.out.println("HRA Alllowance is : " + Math.round(hra));
        System.out.println("Travel Alllowance is : " + Math.round(travelAllowance));
       // System.out.println("Day of Month:"+Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
       // new Date(cal.getTime().getTime());
        System.out.println("date:"+new Date(cal.getTime().getTime()));
    }

}