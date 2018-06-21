package com.payroll.employee.deductions.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;

public class EmpVarDeductionsService {
	public List<EmpVarDeductions> getEmpVarDeductions(){
		return new EmpVarDeductionsDAO().getEmpVarDeductions();
	}
	
	public String addUpdateEmpDeductions(EmpVarDeductions empVarDeductions){
		EmpVarDeductionsVO empVarDeductionVO = copyProperties(empVarDeductions);
		return new EmpVarDeductionsDAO().addUpdateEmpDeductions(empVarDeductionVO);
	}
	public String deleteEmpDeductions(int empId){
		return new EmpVarDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public EmpVarDeductions getEmpDeductionsById(int empId){
		return new EmpVarDeductionsDAO().getEmpVarDeductions(empId);
	}
	
	private EmpVarDeductionsVO copyProperties(EmpVarDeductions empVarDeductions){
		EmpVarDeductionsVO empVarDeductionsVO = new EmpVarDeductionsVO();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			empVarDeductionsVO.setAfkRent(empVarDeductions.getAfkRent());
			empVarDeductionsVO.setEmployeeId(empVarDeductions.getEmployeeId());
			empVarDeductionsVO.setSociety(empVarDeductions.getSociety());
			empVarDeductionsVO.setPfLoanRecovery(empVarDeductions.getPfLoanRecovery());
			empVarDeductionsVO.setOtherDeductions(empVarDeductions.getOtherDeductions());
			empVarDeductionsVO.setMiscRecovery(empVarDeductions.getMiscRecovery());
			empVarDeductionsVO.setAddUpdate(empVarDeductions.getAddUpdate());
			empVarDeductionsVO.setNote(empVarDeductions.getNote());
			
			empVarDeductionsVO.setMonthDate((empVarDeductions.getMonthDate()!= null ? dateFormat.parse(empVarDeductions.getMonthDate()): new Date()));
			
			
		}catch(Exception e){
			
		}
		return empVarDeductionsVO;
	}
	
}
