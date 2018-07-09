package com.payroll.employee.attendance.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.payroll.HibernateConnection;
import com.payroll.employee.attendance.dataobjects.EmployeeAttendance;

@Repository
public class EmployeeAttendanceDAO {
	
	@SuppressWarnings("unchecked")
	public List<EmployeeAttendance> getEmployeeAttendance() {
		Session session = null;
		List<EmployeeAttendance> eaList = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			eaList = Collections.checkedList(session.createCriteria(EmployeeAttendance.class).list(), EmployeeAttendance.class);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			HibernateConnection.closeSession(session);
		}
		return eaList;
	}
	
	public String addEmployeeAttendance(List<EmployeeAttendance> employeeAttendanceList) {
		
		Session session = null;
		Transaction transaction = null;
		
		String result;
		try {

			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (EmployeeAttendance ea: employeeAttendanceList) {
				session.save(ea);
				session.flush();
			}
			transaction.commit();
			result = "success";
		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
			result = "failure";
		} finally {
			HibernateConnection.closeSession(session);
		}
		
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeAttendance> getAttendance() {
		Session session = null;
		List<EmployeeAttendance> eaList = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Criteria criteria =  session.createCriteria(EmployeeAttendance.class);
			criteria.add(Restrictions.like("status", "A"));
			eaList = Collections.checkedList(criteria.list(), EmployeeAttendance.class);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			HibernateConnection.closeSession(session);
		}
		return eaList;
	}

	public String updateEmployeeAttendance(int[] attendanceToProcess, String absenceReason) {
		Session session = null;
		Transaction transaction = null;
		
		EmployeeAttendance ea;
		
		
		String result;
		try {

			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (int srNo : attendanceToProcess) {
				
				ea =  (EmployeeAttendance) session.get(EmployeeAttendance.class, srNo);
				ea.setAbsenceReason(absenceReason);
				session.update(ea);
//				session.flush();
			}
			transaction.commit();
			result = "success";
		} catch (Exception e) {
			System.out.println(e);
			transaction.rollback();
			result = "failure";
		} finally {
			HibernateConnection.closeSession(session);
		}
		
		return result;
	}

}
