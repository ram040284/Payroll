package com.payroll.employee.contract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.payroll.Utils;

public class EmployeeContractService {
	public List<EmployeeContractVO> getEmployeeContract(){
		return new EmployeeContractDAO().getContractualEmployee();
	}
	
	public com.payroll.employee.contract.EmployeeContractVO getContractualEmpById(String empId){
		return copyDBEmp(new EmployeeContractDAO().getContractualEmployeeById(empId));
	}
	
	public String addUpdateContractEmp(com.payroll.employee.contract.EmployeeContractVO contract){
		return new EmployeeContractDAO().addUpdateContractualEmplyee(copyContractEmp(contract));
	}
	
	public String deleteContractEmp(String empId) {
		return new EmployeeContractDAO().deleteContractEmp(empId);
	}
	
	private EmployeeContract copyContractEmp(com.payroll.employee.contract.EmployeeContractVO contract) {
		EmployeeContract employeeContract = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			employeeContract = new EmployeeContract();
			employeeContract.setAppointmentDate(!Utils.isEmpty(contract.getAppointmentDate())? dateFormat.parse(contract.getAppointmentDate()): 
				new Date());
			employeeContract.setEndDate(!Utils.isEmpty(contract.getEndDate())? dateFormat.parse(contract.getEndDate()): 
				new Date());
			employeeContract.setEngagementLetterId(contract.getEngagementLetterId());
			employeeContract.setEmployeeId(contract.getEmployeeId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeeContract;
	}
	
	private com.payroll.employee.contract.EmployeeContractVO copyDBEmp(EmployeeContractVO emp){
		com.payroll.employee.contract.EmployeeContractVO dbEmp = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dbEmp =  new com.payroll.employee.contract.EmployeeContractVO();
			dbEmp.setEmployeeId(emp.getEmployeeId());
			dbEmp.setAppointmentDate(emp.getAppointmentDate());
			dbEmp.setEndDate(emp.getEndDate());
			dbEmp.setEngagementLetterId(emp.getEngagementLetterId());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbEmp;
	}
}
