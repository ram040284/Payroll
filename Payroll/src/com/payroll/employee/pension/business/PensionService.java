package com.payroll.employee.pension.business;

import java.util.List;

import com.payroll.employee.allowance.business.EmpAllowanceService;
import com.payroll.employee.pension.dataobjects.Pension;
import com.payroll.employee.pension.dataobjects.PensionDAO;
import com.payroll.employee.pension.vo.PensionVO;
import com.payroll.employee.salary.business.DaHra;

public class PensionService {
	public List<PensionVO> getPensionList(){
		return new PensionDAO().getPensionList();
	}
	
	public String addUpdatePension(Pension pension){
		return new PensionDAO().addUpdatePension(pension);
	}
	
	public PensionVO getEmpPension(String empId){
		return new PensionDAO().getEmpPension(empId);
	}
	
	public String deletePension(String empId){
		return new PensionDAO().deleteEmpSal(empId);
	}
	public DaHra getDaHra(){
		DaHra daHra = null;
		daHra.setDa(139.00);
		daHra.setHra(30.00);
		return daHra;
	}
	/**
	 * calculate gross pension
	 * @param employeeId
	 * @return
	 */
	public double calculateEmployeeGrossPension(String employeeId){
		double empGrossPension = 0.0;
		PensionVO empPension =new PensionDAO().getEmpPension(employeeId);
		double totalAllowance = new EmpAllowanceService().getTotalEmpAllowanceById(employeeId);
		DaHra daHra = getDaHra();
		double dearnessAllowance = daHra.getDa() * (empPension.getBasicPension());
		
		
		return empGrossPension;
	}
}
