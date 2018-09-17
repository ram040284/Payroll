package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
public class EmpDeductionsDAO {
	
	/*public List<EmpDeductionsVO> getEmpDeductionsList(){
		List<EmpDeductionsVO> deductionsList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U, d.loanPrincipal, d.schoolFees, d.lic, d.mutualFund, d.section80D, "
						+ "d.section80E , d.nsc, d.ppf, d.donation,	d.section80DD, d.arrears, d.bonus, d.otAmount, "
						+ "d.otWages, d.hra_section10_13A, d.income_tax_rebate_section_87C, d.child_trans_allw_10_14, "
						+ "d.home_loan_section_24B, d.hlp_pf_lic_80C, d.nps_80CCD_1B, d.health_insu_80D, d.des_dep_80DD, "
						+ "d.medical_80DDB, d.edu_load_80D, d.donation_80G, d.rent_80GG, d.int_bank_section_80TTA, "
						+ "d.phys_dis_per_section_80U ) from EmpDeductions d where d.status = ? ";		
						
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				deductionsList = query.list();
				System.out.println("Size:"+deductionsList.size());
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return deductionsList;
	}*/
	
	public List<EmpDeductionsVO> getEmpDeductionsList(int deptId, int headId, String name){
		List<EmpDeductionsVO> deductionsList = null;
			Session session = null;
			StringBuffer searchCriteria = new StringBuffer("");
			try{
				searchCriteria.append(" select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U, d.loanPrincipal, d.schoolFees, d.lic, d.mutualFund, d.section80D, "
						+ "d.section80E , d.nsc, d.ppf, d.donation,	d.section80DD, d.arrears, d.bonus, d.otAmount, "
						+ "d.otWages, d.hra_section10_13A, d.income_tax_rebate_section_87C, d.child_trans_allw_10_14, "
						+ "d.home_loan_section_24B, d.hlp_pf_lic_80C, d.nps_80CCD_1B, d.health_insu_80D, d.des_dep_80DD, "
						+ "d.medical_80DDB, d.edu_load_80D, d.donation_80G, d.rent_80GG, d.int_bank_section_80TTA, "
						+ "d.phys_dis_per_section_80U ) from EmpDeductions d where d.status = ? ");	
				
				if(deptId != 0)
					searchCriteria.append(" and d.employee.employeeId = (select eDept.employee.employeeId from EmpDepartment eDept where d.employee.employeeId = eDept.employee.employeeId and eDept.department.departmentId = ?)");
				if(headId != 0){
					searchCriteria.append(" and d.employee.employeeId = (select eMas.employee.employeeId from EmpHeadInfo eMas where d.employee.employeeId = eMas.employee.employeeId and eMas.headInfo.headId = ?)");
				}
				if(!Utils.isEmpty(name)) {
					searchCriteria.append(" and (d.employee.firstName like :fname or d.employee.middleName like :mname or d.employee.lastName like :lname)");
				}
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(searchCriteria.toString());
				int i=0;
				query.setParameter(i++, "A");
				if(deptId != 0)
					query.setParameter(i++, deptId);
				if(headId != 0)
					query.setParameter(i++, headId);
				if(!Utils.isEmpty(name)){
					query.setParameter("fname", "%"+name+"%");
					query.setParameter("mname", "%"+name+"%");
					query.setParameter("lname", "%"+name+"%");
				}
				deductionsList = query.list();
				if(!deductionsList.isEmpty())
					System.out.println("Size:"+deductionsList.size());
			}catch(Exception e){
				System.out.println("Error : "+ e.toString());
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return deductionsList;
	}
	
	
	public List<EmpDeductionsVO> getAllExemptionList(){
		List<EmpDeductionsVO> deductionsList = null;
			Session session = null;
			StringBuffer searchCriteria = new StringBuffer("");
			try{
				searchCriteria.append(" select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U, d.loanPrincipal, d.schoolFees, d.lic, d.mutualFund, d.section80D, "
						+ "d.section80E , d.nsc, d.ppf, d.donation,	d.section80DD, d.arrears, d.bonus, d.otAmount, "
						+ "d.otWages, d.hra_section10_13A, d.income_tax_rebate_section_87C, d.child_trans_allw_10_14, "
						+ "d.home_loan_section_24B, d.hlp_pf_lic_80C, d.nps_80CCD_1B, d.health_insu_80D, d.des_dep_80DD, "
						+ "d.medical_80DDB, d.edu_load_80D, d.donation_80G, d.rent_80GG, d.int_bank_section_80TTA, "
						+ "d.phys_dis_per_section_80U ) from EmpDeductions d where d.status = ? ");	
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(searchCriteria.toString());
				int i=0;
				query.setParameter(0, "A");
				deductionsList = query.list();
				if(!deductionsList.isEmpty())
					System.out.println("Size:"+deductionsList.size());
			}catch(Exception e){
				System.out.println("Error : "+ e.toString());
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return deductionsList;
	}
	
	public EmpDeductionsVO getEmpDeductionsById(String empId){
		EmpDeductionsVO empDeductionsVO = null;
		Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U, d.loanPrincipal, d.schoolFees, d.lic, d.mutualFund, d.section80D, "
						+ "d.section80E , d.nsc, d.ppf, d.donation,	d.section80DD, d.arrears, d.bonus, d.otAmount, "
						+ "d.otWages, d.hra_section10_13A, d.income_tax_rebate_section_87C, d.child_trans_allw_10_14, "
						+ "d.home_loan_section_24B, d.hlp_pf_lic_80C, d.nps_80CCD_1B, d.health_insu_80D, d.des_dep_80DD, "
						+ "d.medical_80DDB, d.edu_load_80D, d.donation_80G, d.rent_80GG, d.int_bank_section_80TTA, "
						+ "d.phys_dis_per_section_80U ) from EmpDeductions d where d.employee.employeeId = ? and d.status = ? ";
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductionsVO = (EmpDeductionsVO)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empDeductionsVO;
	}
	
	/*public EmpDeductionsVO getEmpDeductionsById(String empId){
		EmpDeductionsVO empDeductionsVO = null;
		Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U, d.loanPrincipal, d.schoolFees, d.lic, d.mutualFund, d.section80D, "
						+ "d.section80E , d.nsc, d.ppf, d.donation,	d.section80DD, d.arrears, d.bonus, d.otAmount, "
						+ "d.otWages, d.hra_section10_13A, d.income_tax_rebate_section_87C, d.child_trans_allw_10_14, "
						+ "d.home_loan_section_24B, d.hlp_pf_lic_80C, d.nps_80CCD_1B, d.health_insu_80D, d.des_dep_80DD, "
						+ "d.medical_80DDB, d.edu_load_80D, d.donation_80G, d.rent_80GG, d.int_bank_section_80TTA, "
						+ "d.phys_dis_per_section_80U "
						+ "(select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
						+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId)) "
						+ "from EmpDeductions d where d.employee.employeeId = ? and d.status = ? ";
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductionsVO = (EmpDeductionsVO)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empDeductionsVO;
	}*/
	
	public EmpDeductions getEmpDeductionsByEmpId(String empId){
		EmpDeductions empDeductions = null;
		Session session = null;
			
			try{
				String queryString = " from EmpDeductions d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductions = (EmpDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empDeductions;
	}
	
	public String deleteEmpDeductions(String empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpDeductions d set d.status = ?, d.rowUpdDate = ? where d.employee.employeeId = ?");
			query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0) {
				result = "Successfully deleted Employee Deductions!";
			}
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Employee Deductions!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String addUpdateEmpDeductions(EmpDeductions empDeduct){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			System.out.println("empDeduct emp id : " + empDeduct.getEmployeeId());
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empDeduct.getEmployeeId());
			empDeduct.setEmployee(employee);
			empDeduct.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			empDeduct.setStatus("A");
			if(empDeduct.getAddUpdate() ==0)
				session.save(empDeduct);
			else
				session.update(empDeduct);
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Deductions are already exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Employee Deductions!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}


}
