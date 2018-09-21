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
import com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions;

public class EmpFixedDeductionsDAO {
	
	public List<EmpFixedDeductions> getEmpFixedDeductions(){
		List<EmpFixedDeductions> deductionsList = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.deductions.dataobjects.EmpFixedDeductions(d.employee.employeeId, "
						+"d.employee.firstName, d.employee.lastName, d.kssUnionFee, d.rent, d.courtRecovery, d.unionFee, d.gis,d.additionalPF, d.ApfAcpf, d.rowUpdDate)"
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
	 * @param empId
	 * @return
	 */
	public EmpFixedDeductions getEmpFixedDeductions(String empId){
		EmpFixedDeductions empFixedDeductions = null;
		Session session = null;
			try{
				String queryString = "select new com.payroll.employee.deductions.dataobjects.EmpFixedDeductions(d.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = d.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = d.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = d.employee.employeeId and dh.status = 'A'), "
						+" d.kssUnionFee, d.rent,d.courtRecovery,d.unionFee, d.gis,d.additionalPF, d.ApfAcpf, d.rowUpdDate)"
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
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public EmployeeFixedDeductions getEmployeeFixedDeductions(String empId){
		EmployeeFixedDeductions empFixedDeductions = null;
		Session session = null;
			try{
				String queryString = "select new com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions(s.employeeId, s.kssUnionFee, s.rent,s.courtRecovery,s.unionFee, s.gis, s.additionalPF, s.ApfAcpf) from EmpFixedDeductions s where s.employeeId = ? and s.status = ?";				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empFixedDeductions = (EmployeeFixedDeductions)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return empFixedDeductions;
	}

	/**
	 * 
	 * @param empId
	 * @return
	 */
	public EmpFixedDeductions getEmpDeductionsByEmpId(String empId){
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
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public String deleteEmpDeductions(String empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
        try{
        	
        	EmpFixedDeductions fixedDeductions = getEmpDeductionsByEmpId(empId);
        	
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpFixedDeductions d set d.status = ?, d.rowUpdDate = ? where d.employee.employeeId = ? and d.status = ? and d.rowUpdDate = ?");
			query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			query.setParameter(3, "A");
			query.setParameter(4, fixedDeductions.getRowUpdDate());
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Employee Deduction details!";
			session.flush();
			transaction.commit();
			result="yes";
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