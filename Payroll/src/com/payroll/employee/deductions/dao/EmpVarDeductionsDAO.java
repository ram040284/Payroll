package com.payroll.employee.deductions.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.business.EmployeeService;
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

	
	public EmpVarDeductionsVO getEmpDeductionsByEmpId(String empId){
		EmpVarDeductionsVO empDeductionsVO = null;
		Session session = null;
			
			try{
				String queryString = " from EmpVarDeductionsVO d where d.employee.employeeId = ? and d.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empDeductionsVO = (EmpVarDeductionsVO)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empDeductionsVO;
	}
	
	public String deleteEmpDeductions(String empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			EmpVarDeductionsVO empVarDeductionsVO = getEmpDeductionsByEmpId(empId);
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpVarDeductionsVO d set d.status = ?, d.rowUpdDate = ? "
					+ "where d.employee.employeeId = ? and d.monthDate = ? and status = ?");
			
			query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			query.setParameter(3, empVarDeductionsVO.getMonthDate());
			query.setParameter(4,"A");
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Employee Deduction details!";
			session.flush();	
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Employee Deduction details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	//new code added
	public String UpdateEmpDeductions(EmpVarDeductionsVO empVarDeductionsVO){
 		String result = null;
 		Session session = null;
		Transaction transaction = null;
 		try{
 			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpVarDeductionsVO d set d.afkRent = ? , d.society = ?, d.pfLoanRecovery = ?,"
					+ " d.otherDeductions = ?, d.miscRecovery = ?, d.monthDate = ?,d.rowUpdDate = ?,d.note = ?, d.incomeTax = ?, d.absenties = ?  "
					+ "where d.employeeId = ? and d.status = ?");
			query.setParameter(0,empVarDeductionsVO.getAfkRent() ) ;
			query.setParameter(1,empVarDeductionsVO.getSociety());
			query.setParameter(2,empVarDeductionsVO.getPfLoanRecovery());
			query.setParameter(3,empVarDeductionsVO.getOtherDeductions());
			query.setParameter(4,empVarDeductionsVO.getMiscRecovery());
			query.setParameter(5,empVarDeductionsVO.getMonthDate());
			query.setParameter(6,empVarDeductionsVO.getRowUpdDate());
			query.setParameter(7,empVarDeductionsVO.getNote());
			query.setParameter(8,empVarDeductionsVO.getIncomeTax());
			query.setParameter(9,empVarDeductionsVO.getAbsenties());
			query.setParameter(10,empVarDeductionsVO.getEmployeeId());
			query.setParameter(11,"A");

			int updated = query.executeUpdate();
 			if(updated > 0)
 				result = "Successfully updated var deductions Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to update var deductions Details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	/**
	 * @param empVarDeductions
	 * @return
	 */
	public String addUpdateEmpDeductions(EmpVarDeductionsVO empVarDeductionsVO){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empVarDeductionsVO.getEmployeeId());
			empVarDeductionsVO.setEmployee(employee);
	
			empVarDeductionsVO.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			empVarDeductionsVO.setStatus("A");
			if(empVarDeductionsVO.getAddUpdate() ==0)
				session.save(empVarDeductionsVO);
			else
				 UpdateEmpDeductions(empVarDeductionsVO);
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
	
	public String addOrUpdateEmpVarDeductions(List<EmpVarDeductionsVO> deductions) {
		
		Session session = null;
		Transaction transaction = null;
		
		String result;
		try {

			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (EmpVarDeductionsVO varDeductions: deductions) {
				EmpVarDeductions deductionsVO = getEmpVarDeductions(varDeductions.getEmployeeId());
				if (deductionsVO != null) {
					Date date = new Date();
					List<Employee> employee = new EmployeeService().getEmployeeByEmployeeId(varDeductions.getEmployeeId());
					varDeductions.setEmployee(employee.get(0));
					varDeductions.setNote("");
					varDeductions.setMonthDate(date);
					varDeductions.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.update(varDeductions);
					session.flush();
				}else {
					Date date = new Date();
					List<Employee> employee = new EmployeeService().getEmployeeByEmployeeId(varDeductions.getEmployeeId());
					varDeductions.setEmployee(employee.get(0));
					varDeductions.setNote("");
					varDeductions.setMonthDate(date);
					varDeductions.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.save(varDeductions);
					session.flush(); 
				}
				
			}
			transaction.commit();
			result = "success";
		} catch (Exception e) {
			System.out.println(e.toString());
			transaction.rollback();
			result = "failure";
		} finally {
			HibernateConnection.closeSession(session);
		}
		
		return result;
	}
}
