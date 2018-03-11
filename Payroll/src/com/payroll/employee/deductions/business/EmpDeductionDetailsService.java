package com.payroll.employee.deductions.business;

import java.util.List;

import com.payroll.employee.deductions.dataobjects.EmpDeductionDetails;
import com.payroll.employee.deductions.dataobjects.EmpDeductionDetailsDAO;
import com.payroll.employee.deductions.vo.EmpDeductionsDetailsVO;

public class EmpDeductionDetailsService {
	public List<EmpDeductionsDetailsVO> getEmpDeductionsList(){
		return new EmpDeductionDetailsDAO().getEmpDeductionsList();
	}
	
	public String addUpdateEmpDeductions(EmpDeductionDetails empDeductions){
		return new EmpDeductionDetailsDAO().addUpdateEmpDeductions(empDeductions);
	}
	public String deleteEmpDeductions(int empId){
		return new EmpDeductionDetailsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpDeductionsDetailsVO getEmpDeductionsById(int empId){
		return new EmpDeductionDetailsDAO().getEmpDeductionsById(empId);
	}
}
