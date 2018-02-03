package com.payroll.overtime.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.payroll.overtime.dataobjects.Overtime;
import com.payroll.overtime.dataobjects.OvertimeDAO;
import com.payroll.overtime.vo.OvertimeVO;

public class OvertimeService {
	
	public List<com.payroll.overtime.vo.OvertimeVO> getOvertimeList(){
		return new OvertimeDAO().getOvertimeList();
	}
	public OvertimeVO getOvertimeById(int overtimeId){
		return new OvertimeDAO().getOvertimeById(overtimeId);
	}
	public String addUpdateOvertime(com.payroll.overtime.vo.OvertimeVO overtime){
		Overtime overtimeDB = copyProperties(overtime);
		return new OvertimeDAO().addUpdateOvertime(overtimeDB);
	}
	public boolean deleteOvertime(int overtimeId){
		/*Date otimeDate = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			otimeDate = (overtimeDate != null ? dateFormat.parse(overtimeDate) : new Date());
		}catch(Exception e){
			e.printStackTrace();
		}*/
		return new OvertimeDAO().deleteOvertime(overtimeId);
	}
	
	private Overtime copyProperties(com.payroll.overtime.vo.OvertimeVO overtime){
		Overtime overtimeDB = new Overtime();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			overtimeDB.setEmployeeId(overtime.getEmployeeId());
			overtimeDB.setOvertimeOrder(overtime.getOvertimeOrder());
			overtimeDB.setOvertimeHours(overtime.getOvertimeHours());
			overtimeDB.setOvertimeAmount(overtime.getOvertimeAmount());
			overtimeDB.setOvertimeId(overtime.getOvertimeId());
			overtimeDB.setOvertimeDate((overtime.getOvertimeDate()!= null ? dateFormat.parse(overtime.getOvertimeDate()): new Date()));
		}catch(Exception e){
			
		}
		return overtimeDB;
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
