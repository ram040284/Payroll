package com.payroll.employee.deductions.business;

import java.util.List;

import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;

public class EmpVarDeductionsService {
	public List<EmpVarDeductions> getEmpVarDeductions(){
		return new EmpVarDeductionsDAO().getEmpVarDeductions();
	}
	
	public String addUpdateEmpDeductions(EmpVarDeductions empVarDeductions){
		return new EmpVarDeductionsDAO().addUpdateEmpDeductions(empVarDeductions);
	}
	public String deleteEmpDeductions(int empId){
		return new EmpVarDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpVarDeductions getEmpDeductionsById(int empId){
		return new EmpVarDeductionsDAO().getEmpVarDeductions(empId);
	}
}
