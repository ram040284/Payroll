package com.payroll.employee.bank.business;

import java.util.List;

import com.payroll.employee.bank.dataobjects.EmpBank;
import com.payroll.employee.bank.dataobjects.EmpBankDAO;
import com.payroll.employee.bank.vo.BankVO;

public class BankService {
	public List<BankVO> getBankList(){
		return new EmpBankDAO().getBankList();
	}
	
	public String addUpdateBank(EmpBank bank){
		return new EmpBankDAO().addUpdateBank(bank);
	}
	
	public BankVO getBankByEmpId(int empId){
		return new EmpBankDAO().getBankByEmpId(empId);
	}
	
	public String deleteEmpBank(int empId){
		return new EmpBankDAO().deleteEmpBank(empId);
	}
}
