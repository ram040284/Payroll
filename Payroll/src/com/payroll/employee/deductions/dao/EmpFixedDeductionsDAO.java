package com.payroll.employee.deductions.dao;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.deductions.dataobjects.EmpFixedDeductions;

public class EmpFixedDeductionsDAO {
	
	public List<EmpFixedDeductions> getEmpFixedDeductions(){
		List<EmpFixedDeductions> deductionsList = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.deductions.dataobjects.EmpFixedDeductions(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.kssUnionFee, d.rent, d.courtRecovery, d.unionFee, d.gis)"
						+ " from EmpFixedDeductions d where d.status = ? ";
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
	 * 
	 * @param empId
	 * @return
	 */
	public EmpFixedDeductions getEmpFixedDeductions(int empId){
		EmpFixedDeductions empFixedDeductions = null;
		Session session = null;
			try{
				String queryString = " select new com.payroll.employee.deductions.dataobjects.EmpFixedDeductions(d.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = d.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = d.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = d.employee.employeeId and dh.status = 'A'), "
						+" d.kssUnionFee, d.rent, d.courtRecovery, d.unionFee, d.gis)"
						+ " from EmpFixedDeductions d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empFixedDeductions = (EmpFixedDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return empFixedDeductions;
	}
	
	public EmpFixedDeductions getEmpDeductionsByEmpId(int empId){
		EmpFixedDeductions empDeductions = null;
		Session session = null;
			
			try{
				String queryString = " from EmpFixedDeductions d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductions = (EmpFixedDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
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
			Query query = session.createQuery("update EmpFixedDeductions d set d.status = ?, d.rowUpdDate = ? where d.employee.employeeId = ?");
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
	 * @param empFixedDeductions
	 * @return
	 */
	public String addUpdateEmpDeductions(EmpFixedDeductions empFixedDeductions){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empFixedDeductions.getEmployeeId());
			empFixedDeductions.setEmployee(employee);
			empFixedDeductions.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			empFixedDeductions.setStatus("A");
			if(empFixedDeductions.getAddUpdate() ==0)
				session.save(empFixedDeductions);
			else
				session.update(empFixedDeductions);
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
