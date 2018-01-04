package com.payroll.employee.deductions.business;

import java.util.List;

import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmpDeductionsDAO;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;

public class EmpDeductionsService {
	public List<EmpDeductionsVO> getEmpDeductionsList(){
		return new EmpDeductionsDAO().getEmpDeductionsList();
	}
	
	public String addUpdateEmpDeductions(EmpDeductions empDeductions){
		return new EmpDeductionsDAO().addUpdateEmpDeductions(empDeductions);
	}
	public String deleteEmpDeductions(int empId){
		return new EmpDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpDeductionsVO getEmpDeductionsById(int empId){
		return new EmpDeductionsDAO().getEmpDeductionsById(empId);
	}
}
