package com.payroll.employee.arrears.business;

import java.util.List;

import com.payroll.employee.arrears.dataobjects.EmpArrearDAO;
import com.payroll.employee.arrears.dataobjects.EmpArrears;
import com.payroll.employee.arrears.vo.EmpArrearsVO;

public class EmpArrearsService {
	
	public List<EmpArrears> getArrearsList(){
		return new EmpArrearDAO().getArrearsList();
	}
	
	public String deleteArrearsPay(int arrearId){
		return new EmpArrearDAO().deleteArrearsPay(arrearId);
	}
	
	public EmpArrearsVO getArrearsByArrearId(int arrearId){
		return new EmpArrearDAO().getArrearsByArrearId(arrearId);
	}
	
	public String addUpdateArrears(EmpArrears arrears){
		return new EmpArrearDAO().addUpdateArrears(arrears);
	}
}
