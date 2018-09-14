package com.payroll.employee.lic.business;

import java.text.SimpleDateFormat;
import java.util.List;

import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.dataobjects.EmpLicDAO;
import com.payroll.employee.lic.dataobjects.EmpLicMaster;
//import com.payroll.employee.lic.vo.EmpLicMasterVO;
//import com.payroll.employee.lic.vo.EmpLicVO;

public class EmpLicService {
	/*public List<EmpLicVO> getEmpLicList(){
		return new EmpLicDAO().getEmpLicList();
	}*/
	public List<EmpLic> getEmpLicList(){
		return new EmpLicDAO().getEmpLicList();
	}
	public List<EmpLicMaster> getEmpLicMasterList(){
		return new EmpLicDAO().getEmpLicMasterList();
	}
	
	/*public EmpLic getEmpLicById(EmpLic empLic){
		return new EmpLicDAO().getEmpLicById(empLic);
	}*/
	
	public EmpLicMaster getEmpLicMasterById(EmpLicMaster empLicMaster){
		return new EmpLicDAO().getEmpLicMasterById(empLicMaster);
	}
	
	public EmpLic getEmpLicById(EmpLic empLic){
		return new EmpLicDAO().getEmpLicById(empLic);
	}
	public String deleteLicMaster(EmpLicMaster empLicMaster){
		return new EmpLicDAO().deleteEmpLicMaster(empLicMaster);
	}
	public String deleteLic(EmpLic empLic){
		return new EmpLicDAO().deleteEmpLic(empLic);
	}
	
	public String addUpdateEmpLic(EmpLic empLic){
		return new EmpLicDAO().addUpdateEmpLic(empLic);

//		return new EmpLicDAO().addUpdateEmpLic(copyProperties(licVO));
	}
	
	public String addUpdateEmpLicMaster(EmpLicMaster empLicMaster){
		//System.out.println("Installment Amount :"+empLicMaster.getInstlmtAmt());
		return new EmpLicDAO().addUpdateEmpLicMaster(empLicMaster);
	}
	
	/*private EmpLic copyProperties(EmpLic empLic){
		EmpLic empLic = new EmpLic();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			empLic.setEmployeeId(licVO.getEmployeeId());
            empLic.setPolicyNo(licVO.getPolicyNo());
			empLic.setAddUpdate(licVO.getAddUpdate());
			if(licVO.getPaymentDate() !=null)
			{
				empLic.setPaymentDate(dateFormat.parse(licVO.getPaymentDate()));
			}
		    empLic.setPaymentAmount(licVO.getPaymentAmount());
			empLic.setPolicyNo(licVO.getPolicyNo());
		}catch(Exception e){
			e.printStackTrace();
		}
		return empLic;
	}*/
	
	
	/*private EmpLicMaster copyProperties(EmpLicMaster licMasterVO){
		EmpLicMaster empLicMaster = new EmpLicMaster();
		try{
			empLicMaster.setEmployeeId(licMasterVO.getEmployeeId());
			empLicMaster.setPolicyNo(licMasterVO.getPolicyNo());
			empLicMaster.setInstlmtAmt(licMasterVO.getInstlmtAmt());
			empLicMaster.setAddUpdate(licMasterVO.getAddUpdate());
		}catch(Exception e){
			e.printStackTrace();
		}
		return empLicMaster;
	}*/
}
