package com.payroll.advance.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.payroll.advance.dataobjects.EmployeeAdvance;
import com.payroll.advance.dataobjects.EmployeeAdvanceDAO;
import com.payroll.advance.dataobjects.EmployeeAdvanceVO;

public class EmployeeAdvanceService {
	
	public List<EmployeeAdvance> getEmployeeAdvanceList(){
		return new EmployeeAdvanceDAO().getEmployeeAdvanceList();
	}
	public EmployeeAdvance getEmployeeAdvanceById(int advanceId){
		return new EmployeeAdvanceDAO().getEmployeeAdvanceById(advanceId);
	}
	/**
	 * 
	 * @param employeeAdvance
	 * @return
	 */
	public String addUpdateAdvance(EmployeeAdvance employeeAdvance){
		EmployeeAdvanceVO empAdvanceVO = copyProperties(employeeAdvance);
		return new EmployeeAdvanceDAO().addUpdateAdvance(empAdvanceVO);
	}
	
	/**
	 * 
	 * @param advanceId
	 * @return
	 */
	public boolean deleteEmployeeAdvance(int advanceId){

		return new EmployeeAdvanceDAO().deleteEmployeeAdvance(advanceId);
	}
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public double getAdvanceInstallment(String employeeId){
		double festInstallmentAmount = 0;

		return festInstallmentAmount;
	}
	
	private EmployeeAdvanceVO copyProperties(EmployeeAdvance empAdvance){
		EmployeeAdvanceVO empAdvanceVO = new EmployeeAdvanceVO();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			empAdvanceVO.setAdvanceId(empAdvance.getAdvanceId());
			empAdvanceVO.setEmployeeId(empAdvance.getEmployeeId());
			empAdvanceVO.setAdvanceName(empAdvance.getAdvanceName());
			empAdvanceVO.setAdvanceAmount(empAdvance.getAdvanceAmount());
			empAdvanceVO.setInstallAmount(empAdvance.getInstallAmount());
			//empAdvanceVO.set
			
			empAdvanceVO.setAdvanceDate((empAdvance.getAdvanceDate()!= null ? dateFormat.parse(empAdvance.getAdvanceDate()): new Date()));
			empAdvanceVO.setInstallStartDate((empAdvance.getInstallStartDate()!= null ? dateFormat.parse(empAdvance.getInstallStartDate()): new Date()));
			empAdvanceVO.setInstallEndDate((empAdvance.getInstallEndDate()!= null ? dateFormat.parse(empAdvance.getInstallEndDate()): new Date()));

		}catch(Exception e){
			
		}
		return empAdvanceVO;
	}
	
	/*private List<com.payroll.overtime.vo.OvertimeVO> getValues(List<Overtime> overtimes){
		List<com.payroll.overtime.vo.OvertimeVO> overtimeList = new ArrayList<com.payroll.overtime.vo.OvertimeVO>();
		com.payroll.overtime.vo.OvertimeVO overtimeDB = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			for (Iterator iterator = overtimes.iterator(); iterator.hasNext();) {
				Overtime overtime = (Overtime) iterator.next();
				overtimeDB = new com.payroll.overtime.vo.OvertimeVO();
				overtimeDB.setEmployeeId(overtime.getEmployeeId());
				overtimeDB.setDepartmentId(overtime.getDepartmentId());
				overtimeDB.setDesignationId(overtime.getDesignationId());
				overtimeDB.setOvertimeAmount(overtime.getOvertimeAmount());
				overtimeDB.setOvertimeDate((overtime.getOvertimeDate()!= null ? dateFormat.format(overtime.getOvertimeDate()): ""));
				overtimeList.add(overtimeDB);
			}
			
		}catch(Exception e){
			
		}
		return overtimeList;
	}*/
	
	/*public double getAdvanceInstallment(int employeeId){
		double festInstallmentAmount = 0;
		EmployeeAdvance employeeAdvance = new AdvanceDAO().getAdvanceByEmployeeId(employeeId);
		List<EmployeeAdvanceDetails> listEmployeeAdvanceDetails = new AdvanceDAO().getEmployeeAdvanceDetails(employeeAdvance.getAdvanceId());
		return festInstallmentAmount;	
	}
	
	private double calculateInstallment(EmployeeAdvance employeeAdvance, List<EmployeeAdvanceDetails> listemployeeAdvanceDetails){
		Date sDate = employeeAdvance.getAdvanceDate();
		Date todaysDate = new Date();
		Calendar startDate = Calendar.getInstance();
		startDate.setTime(sDate);
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(todaysDate);
		int difInMonths = endDate.MONTH -  startDate.MONTH;
		return 0.0;
	}*/
}
