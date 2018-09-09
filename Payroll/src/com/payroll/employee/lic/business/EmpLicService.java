package com.payroll.employee.lic.business;

import java.text.SimpleDateFormat;
import java.util.List;

import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.dataobjects.EmpLicDAO;
import com.payroll.employee.lic.dataobjects.EmpLicMaster;
import com.payroll.employee.lic.vo.EmpLicMasterVO;
import com.payroll.employee.lic.vo.EmpLicVO;

public class EmpLicService {
	public List<EmpLicVO> getEmpLicList(){
		return new EmpLicDAO().getEmpLicList();
	}
	
	public List<EmpLicMasterVO> getEmpLicMasterList(){
		return new EmpLicDAO().getEmpLicMasterList();
	}
	
	public EmpLicVO getEmpLicById(String empId){
		return new EmpLicDAO().getEmpLicById(empId);
	}
	
	public EmpLicMasterVO getEmpLicMasterById(String empId){
		return new EmpLicDAO().getEmpLicMasterById(empId);
	}
	
	public String deleteLicMaster(String empId){
		return new EmpLicDAO().deleteEmpLicMaster(empId);
	}
	public String deleteLic(String empId){
		return new EmpLicDAO().deleteEmpLic(empId);
	}
	
	public String addUpdateEmpLic(EmpLicVO licVO){
		return new EmpLicDAO().addUpdateEmpLic(copyProperties(licVO));
	}
	
	public String addUpdateEmpLicMaster(EmpLicMasterVO licVO){
		System.out.println("Installment Amount :"+licVO.getInstlmtAmt());
		return new EmpLicDAO().addUpdateEmpLicMaster(copyProperties(licVO));
	}
	
	private EmpLic copyProperties(EmpLicVO licVO){
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
	}
	
	
	private EmpLicMaster copyProperties(EmpLicMasterVO licMasterVO){
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
	}
}
