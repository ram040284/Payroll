package com.payroll.advance.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import com.payroll.HibernateConnection;
import com.payroll.employee.advances.dataobjects.EmployeeAdvance;
import com.payroll.employee.advances.dataobjects.EmployeeAdvanceDetails;

public class AdvanceDAO {
	
	public List<EmployeeAdvance> getAdvances(){
		List<EmployeeAdvance> employeeAdvanceList = null;
		Session session = null;
		try{
			//String queryString = " from Advance";
			String queryString = " select new com.payroll.employee.advances.dataobjects.EmployeeAdvance"
					+ "(a.advanceId,a.employeeId, a.advanceName, "
					+ "a.advanceAmount, a.advanceDate, a.installAmount) from EmployeeAdvance a where a.status = ?";		
			
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
	/**
	 * Retreive Employee Advance
	 * @return
	 */
	public EmployeeAdvanceDetails getEmployeeAdvance(int employeeId){
		EmployeeAdvanceDetails employeeAdvance = null;
		Session session = null;
		
		try{
			//String queryString = " from Advance";
			String queryString =  "select new com.payroll.employee.advances.dataobjects.EmployeeAdvanc(a.advanceId, a.advanceName, "
					+ "a.paymentDate, a.advanceAmount) from Advance a where a.status = ?";
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, employeeId);
			query.setParameter(1, "A");
			employeeAdvance =  (EmployeeAdvanceDetails)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeeAdvance;
	}
	
	public boolean deleteAdvance(int advanceId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(advanceId != 0){
				Query query = session.createQuery("update Advance a set a.status = ?, a.rowUpdDate = ? where a.advanceId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Timestamp(System.currentTimeMillis()));
				query.setParameter(2, advanceId);
				int updated = query.executeUpdate();
				System.out.println("Deleted:"+updated);
				if(updated == 1){
					transaction.commit();
					success = true;
				}
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
	
	public EmployeeAdvance getAdvanceById(int advanceId){
		EmployeeAdvance advDB = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			String queryString = " select new com.payroll.employee.advances.dataobjects.EmplooyeeAdvanceDetails(a.advanceId,a.employeeId, a.advanceBalanceAmount, "
					+ "a.paymentDate, a.installmentAmount) from EmplopyeeAdvance a where a.advanceId = ? and status=?";		
			Query query = session.createQuery(queryString);
			query.setParameter(0, advanceId);
			query.setParameter(1, "A");
			advDB = (EmployeeAdvance)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return advDB;
	}
	
	/**
	 * 
	 * @param advance
	 * @return
	 */
	public String addUpdateAdvance(EmployeeAdvanceDetails advance){
		String result = "";
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			/*Advance advanceDB = checkAdvance(advance.getEmpId(), advance.getPaymentDate(), session);
			if(advanceDB !=null)
				session.update(advanceDB);
			else {
				//advance.setAdvanceId(getMaxEmpId(session));
				session.save(advance);
			}*/
			if(advance.getAdvanceId() != 0){
				advance.setStatus("A");
				advance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(advance);
			}else {
				//dept.setDepartmentId(getMaxDeptId(session));
				advance.setStatus("A");
				advance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(advance);
			}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Adnace is already exist!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Advance!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	



}
