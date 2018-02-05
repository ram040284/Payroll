package com.payroll.advance.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.payroll.advance.dataobjects.Advance;
import com.payroll.advance.dataobjects.AdvanceDAO;
import com.payroll.overtime.dataobjects.Overtime;

public class AdvanceService {
	
	public List<com.payroll.advance.vo.AdvanceVO> getAdvanceList(){
		return new AdvanceDAO().getAdvances();
	}
	
	public String addUpdateAdvance(com.payroll.advance.vo.AdvanceVO advance){
		return new AdvanceDAO().addUpdateAdvance(copyProperties(advance));
	}
	public boolean deleteAdvance(int advanceId){
		return new AdvanceDAO().deleteAdvance(advanceId);
	}
	
	public com.payroll.advance.vo.AdvanceVO getAdvanceById(int adanceId){
		return new AdvanceDAO().getAdvanceById(adanceId);
	}
	
	private Advance copyProperties(com.payroll.advance.vo.AdvanceVO advance){
		Advance advanceDB = new Advance();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			advanceDB.setAdvanceId(advance.getAdvanceId());
			advanceDB.setAdvanceName(advance.getAdvanceName());
			advanceDB.setAdvanceAmount(advance.getAdvanceAmount());
			advanceDB.setPaymentDate((advance.getPaymentDate()!= null ? dateFormat.parse(advance.getPaymentDate()): new Date()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return advanceDB;
	}
	
	/*private com.payroll.advance.vo.AdvanceVO getProperties(Advance advance, SimpleDateFormat dateFormat){
		com.payroll.advance.vo.AdvanceVO advanceDB = new com.payroll.advance.vo.AdvanceVO();
		try{
			
			advanceDB.setEmpId(advance.getEmpId());
			advanceDB.setDepartmentId(advance.getDepartmentId());
			advanceDB.setDesignationId(advance.getDesignationId());
			advanceDB.setAdvanceAmount(advance.getAdvanceAmount());
			advanceDB.setPaymentDate((advance.getPaymentDate()!= null ? dateFormat.format(advance.getPaymentDate()): ""));
		}catch(Exception e){
			e.printStackTrace();
		}
		return advanceDB;
	}*/
}
