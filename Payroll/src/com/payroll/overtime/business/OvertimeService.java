package com.payroll.overtime.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.payroll.overtime.dataobjects.Overtime;
import com.payroll.overtime.dataobjects.OvertimeDAO;

public class OvertimeService {
	
	public List<com.payroll.overtime.vo.Overtime> getOvertimeList(){
		return new OvertimeDAO().getOvertimeList();
	}
	
	public boolean addUpdateOvertime(com.payroll.overtime.vo.Overtime overtime){
		Overtime overtimeDB = copyProperties(overtime);
		return new OvertimeDAO().addUpdateOvertime(overtimeDB);
	}
	public boolean deleteOvertime(int empId, String overtimeDate){
		Date otimeDate = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			otimeDate = (overtimeDate != null ? dateFormat.parse(overtimeDate) : new Date());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new OvertimeDAO().deleteOvertime(empId, otimeDate);
	}
	
	private Overtime copyProperties(com.payroll.overtime.vo.Overtime overtime){
		Overtime overtimeDB = new Overtime();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			overtimeDB.setEmpId(overtime.getEmpId());
			overtimeDB.setDepartmentId(overtime.getDepartmentId());
			overtimeDB.setDesignationId(overtime.getDesignationId());
			overtimeDB.setOvertimeAmount(overtime.getOvertimeAmount());
			overtimeDB.setOvertimeDate((overtime.getOvertimeDate()!= null ? dateFormat.parse(overtime.getOvertimeDate()): new Date()));
		}catch(Exception e){
			
		}
		return overtimeDB;
	}
	
	private List<com.payroll.overtime.vo.Overtime> getValues(List<Overtime> overtimes){
		List<com.payroll.overtime.vo.Overtime> overtimeList = new ArrayList<com.payroll.overtime.vo.Overtime>();
		com.payroll.overtime.vo.Overtime overtimeDB = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			for (Iterator iterator = overtimes.iterator(); iterator.hasNext();) {
				Overtime overtime = (Overtime) iterator.next();
				overtimeDB = new com.payroll.overtime.vo.Overtime();
				overtimeDB.setEmpId(overtime.getEmpId());
				overtimeDB.setDepartmentId(overtime.getDepartmentId());
				overtimeDB.setDesignationId(overtime.getDesignationId());
				overtimeDB.setOvertimeAmount(overtime.getOvertimeAmount());
				overtimeDB.setOvertimeDate((overtime.getOvertimeDate()!= null ? dateFormat.format(overtime.getOvertimeDate()): ""));
				overtimeList.add(overtimeDB);
			}
			
		}catch(Exception e){
			
		}
		return overtimeList;
	}

}
