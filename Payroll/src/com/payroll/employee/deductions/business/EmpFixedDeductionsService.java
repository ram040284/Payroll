package com.payroll.employee.deductions.business;

import java.util.List;

import com.payroll.employee.deductions.dao.EmpFixedDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmpFixedDeductions;

public class EmpFixedDeductionsService {
	public List<EmpFixedDeductions> getEmpFixedDeductions(){
		return new EmpFixedDeductionsDAO().getEmpFixedDeductions();
	}
	
	public String addUpdateEmpDeductions(EmpFixedDeductions empFixedDeductions){
		return new EmpFixedDeductionsDAO().addUpdateEmpDeductions(empFixedDeductions);
	}
	public String deleteEmpDeductions(int empId){
		return new EmpFixedDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpFixedDeductions getEmpDeductionsById(int empId){
		return new EmpFixedDeductionsDAO().getEmpFixedDeductions(empId);
	}
}
