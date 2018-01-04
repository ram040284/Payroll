package com.payroll.employee.allowance.business;

import java.util.List;

import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;

public class EmpAllowanceService {
	
	public List<EmpAllowanceVO> getEmpAllowanceList(){
		return new EmpAllowanceDAO().getEmpAllowanceList();
	}
	
	public String addUpdateEmpAllowance(EmpAllowance empAllowance){
		return new EmpAllowanceDAO().addUpdateEmpAllowance(empAllowance);
	}
	public String deleteEmpAllowance(int empId){
		return new EmpAllowanceDAO().deleteEmpAllowance(empId);
	}
	public EmpAllowanceVO getEmpAllowanceById(int empId){
		return new EmpAllowanceDAO().getEmpAllowanceById(empId);
	}
}
