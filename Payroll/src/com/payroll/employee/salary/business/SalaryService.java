package com.payroll.employee.salary.business;

import java.util.List;

import com.payroll.employee.allowance.business.EmpAllowanceService;
import com.payroll.employee.salary.dataobjects.Salary;
import com.payroll.employee.salary.dataobjects.SalaryDAO;
import com.payroll.employee.salary.vo.EmployeeSalary;
import com.payroll.employee.salary.vo.SalaryVO;

public class SalaryService {
	public List<SalaryVO> getSalaries(){
		return new SalaryDAO().getSalaries();
	}
	
	public String addUpdateSalary(Salary salary){
		return new SalaryDAO().addUpdateSalary(salary);
	}
	
	public SalaryVO getEmpSalary(String empId){
		return new SalaryDAO().getEmpSalary(empId);
	}
	
	public String deleteSalary(String empId){
		return new SalaryDAO().deleteEmpSal(empId);
	}
	public DaHra getDaHra(){
		DaHra daHra = null;
		daHra.setDa(139.00);
		daHra.setHra(30.00);
		return daHra;
	}
	/**
	 * calculate gross salary
	 * @param employeeId
	 * @return
	 */
	public double calculateEmployeeGrossSalary(String employeeId){
		double empGrossSalary = 0.0;
		SalaryVO empSalary =new SalaryDAO().getEmpSalary(employeeId);
		double totalAllowance = new EmpAllowanceService().getTotalEmpAllowanceById(employeeId);
		DaHra daHra = getDaHra();
		double dearnessAllowance = daHra.getDa() * (empSalary.getBasic()+ empSalary.getGradePay())/100;
		
		
		return empGrossSalary;
	}
	

	public double getNPAForEmployee(String employeeId) {
		 double npa = 0;
		if(employeeId != null) {
			SalaryVO empSalary = new SalaryDAO().getEmpSalary(employeeId);
			npa = (empSalary.getBasic() + empSalary.getGradePay()) * 0.25;
		}
		return npa;		
	}
}
