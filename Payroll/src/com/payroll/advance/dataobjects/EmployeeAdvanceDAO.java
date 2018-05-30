package com.payroll.advance.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
//import com.payroll.advance.vo.Advance;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.overtime.vo.OvertimeVO;

public class EmployeeAdvanceDAO {
	
	public List<EmployeeAdvance> getEmployeeAdvanceList(){
		List<EmployeeAdvance> employeeAdvanceList = null;
		Session session = null;		
		try{
			String queryString = " select new com.payroll.advance.dataobjects.EmployeeAdvance(o.advanceId, o.employee.employeeId, "
					+" o.employee.firstName, o.employee.lastName, o.advanceName, o.advanceAmount,o.advanceDate, o.installAmount, o.installStartDate) from EmployeeAdvance o where o.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			employeeAdvanceList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeeAdvanceList;
	}

	
	public EmployeeAdvance getEmployeeAvanceById(int overtimeId){
		EmployeeAdvance employeeAdvance = null;
		Session session = null;
		try{
			String queryString ="select new com.payroll.advance.dataobjects.EmployeeAdvance(o.overtimeId, o.employee.employeeId,"
				+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = o.employee.employeeId and dept.status = 'A'), "
				+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = o.employee.employeeId and desg.status='A'), "
				+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = o.employee.employeeId and dh.status = 'A'), "
				+ "o.advanceName, o.advanceAmount,o.advanceDate, o.installAmount, o.installStartDate) from EmployeeAdvance o where o.status = ? and o.advanceId = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, overtimeId);
			employeeAdvance = (EmployeeAdvance)(!query.list().isEmpty() ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeeAdvance;
	}
	
	public boolean deleteEmployeeAdvance(int advanceId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				Query query = session.createQuery("update EmployeeAdvance o set o.status = ?, o.rowUpdDate = ? where o.advanceId = ? ");
				query.setParameter(0, "S");
				query.setParameter(1, new Timestamp(System.currentTimeMillis()));
				query.setParameter(2, advanceId);
				int updated = query.executeUpdate();
				System.out.println("Deleted:"+updated);
				if(updated == 1){
					transaction.commit();
					success = true;
				}
			
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			success = false;
		}finally {
			HibernateConnection.closeSession(session);
		}
		return success;
	}
	
	public String addUpdateAdvance(EmployeeAdvance employeeAdvance){
		String result = "Yes";
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, employeeAdvance.getEmployeeId());
			System.out.println("addUpdateAdvance -- advance:"+employeeAdvance);
			employeeAdvance.setEmployee(employee);
			employeeAdvance.setStatus("A");
			employeeAdvance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			if(employeeAdvance.getAdvanceId() !=0)
				session.update(employeeAdvance);
			else
				session.save(employeeAdvance);
			
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Advance details are exist for Employee on selected date!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Advance for selected Employee!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	private EmployeeAdvance checkEmployeeAdvance(int empId, Date paymentDate, Session session){
		EmployeeAdvance employeeAdvance = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select o from Overtime o where o.empId = ? and o.overtimeDate = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, paymentDate);
			if(query.list() !=null && !query.list().isEmpty() )
				employeeAdvance = (EmployeeAdvance)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return employeeAdvance;
	}
	


}
