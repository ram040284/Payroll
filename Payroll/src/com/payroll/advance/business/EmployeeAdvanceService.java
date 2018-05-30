package com.payroll.advance.business;

import java.util.List;

import com.payroll.advance.dataobjects.EmployeeAdvance;
import com.payroll.advance.dataobjects.EmployeeAdvanceDAO;

public class EmployeeAdvanceService {
	
	public List<EmployeeAdvance> getEmployeeAdvanceList(){
		return new EmployeeAdvanceDAO().getEmployeeAdvanceList();
	}
	public EmployeeAdvance getEmployeeAdvanceById(int advanceId){
		return new EmployeeAdvanceDAO().getEmployeeAvanceById(advanceId);
	}
	public String addUpdateAdvance(EmployeeAdvance employeeAdvance){
		//Overtime overtimeDB = copyProperties(employeeAdvance);
		return new EmployeeAdvanceDAO().addUpdateAdvance(employeeAdvance);
	}
	public boolean deleteEmployeeAdvance(int advanceId){

		return new EmployeeAdvanceDAO().deleteEmployeeAdvance(advanceId);
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

}
