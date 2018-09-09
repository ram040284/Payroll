package com.payroll.employee.deductions.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductions;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;
import com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions;

public class EmpVarDeductionsDAO {
	public List<EmpVarDeductions> getEmpVarDeductions(){
		List<EmpVarDeductions> deductionsList = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.deductions.dataobjects.EmpVarDeductions(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.afkRent, d.society, d.pfLoanRecovery, d.otherDeductions, d.miscRecovery, d.monthDate,d.note, d.incomeTax, d.absenties)"
						+ " from EmpVarDeductionsVO d where d.status = ? ";
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
	/**
	 * @param empId
	 * @return
	 */
	
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public EmployeeVarDeductions getEmployeeVarDeductions(String empId){
		EmployeeVarDeductions employeeVarDeductions = null;
		Session session = null;
			try{
				String queryString = "select new com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions(s.employeeId, s.afkRent, s.society, s.pfLoanRecovery, s.otherDeductions,s.miscRecovery, s.monthDate, s.incomeTax, s.absenties) from EmpVarDeductionsVO s where s.employeeId = ? and s.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				employeeVarDeductions = (EmployeeVarDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return employeeVarDeductions;
	}
	
	public EmpVarDeductions getEmpVarDeductions(String empId){
		EmpVarDeductions empVarDeductions = null;
		Session session = null;
			try{
				String queryString = " select new com.payroll.employee.deductions.dataobjects.EmpVarDeductions(d.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = d.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = d.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = d.employee.employeeId and dh.status = 'A'), "
						+" d.afkRent, d.society, d.pfLoanRecovery, d.otherDeductions, d.miscRecovery, d.monthDate,d.note, d.incomeTax, d.absenties)"
						+ " from EmpVarDeductionsVO d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empVarDeductions = (EmpVarDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return empVarDeductions;
	}
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public EmployeeVarDeductions getEmpVarDeductionsNew(String empId){
		EmployeeVarDeductions employeeVarDeductions = null;
		Session session = null;
			try{
				String queryString = "select new com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions(s.employeeId, s.afkRent, s.society, s.pfLoanRecovery, s.otherDeductions,s.miscRecovery, s.monthDate, s.incomeTax, s.absenties) from EmpVarDeductionsVO s where s.employeeId = ? and s.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				employeeVarDeductions = (EmployeeVarDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return employeeVarDeductions;
	}
//	Appears to be not being used - Prasad
//	public EmployeeVarDeductions getEmpVarDeductionsNew(int empId){
//		EmployeeVarDeductions employeeVarDeductions = null;
//		Session session = null;
//			try{
//				String queryString = "select new com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions(s.employeeId, s.afkRent, s.society, s.otherDeductions) from EmpVarDeductionsVO s where s.employeeId = ? and s.status = ?";		
//				
//				session = HibernateConnection.getSessionFactory().openSession();
//				Query query = session.createQuery(queryString);
//				query.setParameter(0, empId);
//				query.setParameter(1, "A");
//				employeeVarDeductions = (EmployeeVarDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
//			}catch(Exception e){
//				e.printStackTrace();
//			}finally{
//				HibernateConnection.closeSession(session);
//			}
//		return employeeVarDeductions;
//	}

	
	public EmpVarDeductions getEmpDeductionsByEmpId(String empId){
		EmpVarDeductions empDeductions = null;
		Session session = null;
			
			try{
				String queryString = " from EmpVarDeductionsVO d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductions = (EmpVarDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
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
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpVarDeductionsVO d set d.status = ?, d.rowUpdDate = ? where d.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Employee Deduction details!";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Employee Deduction details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	/**
	 * @param empVarDeductions
	 * @return
	 */
	public String addUpdateEmpDeductions(EmpVarDeductionsVO empVarDeductions){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//Employee employee = (Employee)session.load(Employee.class, empVarDeductions.getEmployeeId());
			//empVarDeductions.setEmployee(employee);
			empVarDeductions.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			empVarDeductions.setStatus("A");
			System.out.println("empVarDeductions.getAddUpdate()"+ empVarDeductions.getAddUpdate());
			if(empVarDeductions.getAddUpdate() ==0)
				session.save(empVarDeductions);
			else
				session.update(empVarDeductions);
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Deductions already exist, for selected Employee!";
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
