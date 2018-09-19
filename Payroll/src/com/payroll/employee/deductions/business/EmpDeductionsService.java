package com.payroll.employee.deductions.business;

import java.util.List;

import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmpDeductionsDAO;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;

public class EmpDeductionsService {
	public List<EmpDeductionsVO> getEmpDeductionsList(int deptId, int headId, String name){
		return new EmpDeductionsDAO().getEmpDeductionsList(deptId, headId, name);
	}
	
	public List<EmpDeductionsVO> getAllExemptions(){
		return new EmpDeductionsDAO().getAllExemptionList();
	}
	
	public String addUpdateEmpDeductions(EmpDeductions empDeductions){
		return new EmpDeductionsDAO().addUpdateEmpDeductions(empDeductions);
	}
	public String deleteEmpDeductions(String empId){
		return new EmpDeductionsDAO().deleteEmpDeductions(empId);
	}
	
	public com.payroll.employee.deductions.dataobjects.EmpDeductions getEmpDeductionsById(String empId){
		return copyDBEmpDed(new EmpDeductionsDAO().getEmpDeductionsById(empId));
	}
	
	private com.payroll.employee.deductions.dataobjects.EmpDeductions copyDBEmpDed(EmpDeductionsVO deductionsVO){
		com.payroll.employee.deductions.dataobjects.EmpDeductions dbEmpDed = null;
		try{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dbEmpDed =  new com.payroll.employee.deductions.dataobjects.EmpDeductions();
			dbEmpDed.setEmployeeId(deductionsVO.getEmployeeId());
			dbEmpDed.setSection80C(deductionsVO.getSection80C());
			dbEmpDed.setCess(deductionsVO.getCess());
			dbEmpDed.setHomeLoanIntrst88EE(deductionsVO.getHomeLoanIntrst88EE());
			dbEmpDed.setSelfDisable80U(deductionsVO.getSelfDisable80U());
			dbEmpDed.setLoanPrincipal(deductionsVO.getLoanPrincipal());
			dbEmpDed.setSchoolFees(deductionsVO.getSchoolFees());
			dbEmpDed.setLic(deductionsVO.getLic());
			dbEmpDed.setSection80D(deductionsVO.getSection80D());
			dbEmpDed.setSection80E(deductionsVO.getSection80E());
			dbEmpDed.setNsc(deductionsVO.getNsc());
			dbEmpDed.setPpf(deductionsVO.getPpf());
			dbEmpDed.setDonation(deductionsVO.getDonation());
			dbEmpDed.setSection80DD(deductionsVO.getSection80DD());
			dbEmpDed.setBonus(deductionsVO.getBonus());
			dbEmpDed.setOtWages(deductionsVO.getOtWages());
			dbEmpDed.setArrears(deductionsVO.getArrears());
			dbEmpDed.setOtAmount(deductionsVO.getOtAmount());
			dbEmpDed.setStatus(deductionsVO.getStatus());
			dbEmpDed.setAddUpdate(deductionsVO.getAddUpdate());
			dbEmpDed.setHra_section10_13A(deductionsVO.getHra_section10_13A());
			dbEmpDed.setIncome_tax_rebate_section_87C(deductionsVO.getIncome_tax_rebate_section_87C());
			dbEmpDed.setChild_trans_allw_10_14(deductionsVO.getChild_trans_allw_10_14());
			dbEmpDed.setHome_loan_section_24B(deductionsVO.getHome_loan_section_24B());
			dbEmpDed.setHlp_pf_lic_80C(deductionsVO.getHlp_pf_lic_80C());
			dbEmpDed.setNps_80CCD_1B(deductionsVO.getNps_80CCD_1B());
			dbEmpDed.setHealth_insu_80D(deductionsVO.getHealth_insu_80D());
			dbEmpDed.setDes_dep_80DD(deductionsVO.getDes_dep_80DD());
			dbEmpDed.setMedical_80DDB(deductionsVO.getMedical_80DDB());
			dbEmpDed.setEdu_load_80D(deductionsVO.getEdu_load_80D());
			dbEmpDed.setDonation_80G(deductionsVO.getDonation_80G());
			dbEmpDed.setRent_80GG(deductionsVO.getRent_80GG());
			dbEmpDed.setInt_bank_section_80TTA(deductionsVO.getInt_bank_section_80TTA());
			dbEmpDed.setPhys_dis_per_section_80U(deductionsVO.getPhys_dis_per_section_80U());
			dbEmpDed.setHeadId(deductionsVO.getHeadId());
			dbEmpDed.setDesignationId(deductionsVO.getDesignationId());
			dbEmpDed.setFullName(deductionsVO.getFullName());
			dbEmpDed.setDepartmentId(deductionsVO.getDepartmentId());
			dbEmpDed.setMutualFund(deductionsVO.getMutualFund());
			dbEmpDed.setHeadId(deductionsVO.getHeadId());
			dbEmpDed.setDesignationId(deductionsVO.getDesignationId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return dbEmpDed;
	}
}
