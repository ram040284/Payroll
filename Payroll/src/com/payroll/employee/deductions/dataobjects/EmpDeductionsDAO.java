package com.payroll.employee.deductions.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.deductions.vo.EmpDeductionsVO;
import com.payroll.employee.pf.dataobjects.EmpPf;
import com.payroll.employee.pf.vo.EmpPfVO;

public class EmpDeductionsDAO {
	
	public List<EmpDeductionsVO> getEmpDeductionsList(){
		List<EmpDeductionsVO> deductionsList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.section80C, d.cess, d.homeLoanIntrst88EE, "
						+ "d.selfDisable80U) from EmpDeductions d where d.status = ? ";		
						
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				deductionsList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return deductionsList;
	}
	
	public EmpDeductionsVO getEmpDeductionsById(int empId){
		EmpDeductionsVO empDeductionsVO = null;
		Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.deductions.vo.EmpDeductionsVO(d.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = d.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = d.employee.employeeId and desg.lastWokingDate is null and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = d.employee.employeeId and dh.lastWokingDate is null and dh.status = 'A'), "
						+" d.section80C, d.cess, d.homeLoanIntrst88EE, d.selfDisable80U)"
						+ " from EmpDeductions d where d.employee.employeeId = ? and d.status = ? ";		
				
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
	
	public EmpDeductions getEmpDeductionsByEmpId(int empId){
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
	
	public String deleteEmpDeductions(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpDeductions d set d.status = ?, d.rowUpdDate = ? where d.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Employee Deductions!";
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
