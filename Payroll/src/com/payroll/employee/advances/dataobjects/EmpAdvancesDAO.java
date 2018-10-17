package com.payroll.employee.advances.dataobjects;

import java.sql.Timestamp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.advance.dataobjects.Advance;
import com.payroll.advance.dataobjects.EmployeeAdvanceVO;
import com.payroll.employee.dataobjects.Employee;

// This DAO is added for Emp_Adv_Details table

public class EmpAdvancesDAO {
	// Function to check if advance amount has been receovered or not
	public boolean checkTotalRecovery(EmployeeAdvanceVO employeeAdvanceVO) {
		Session session = null;
		boolean recovered = false;
		double recoveredAmount = 0;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(
					"select sum(o.installAmount) as total_install from EmpAdvances o where o.advance.advanceId = ?");
			// .setMaxResults(1).uniqueResult();
			// Query query = session.createQuery("from EmpAdvances o where o.advanceId =
			// ?");
			query.setParameter(0, employeeAdvanceVO.getAdvanceId());
			if (query.list() != null && !query.list().isEmpty())
				recoveredAmount = (double) query.list().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (recoveredAmount == employeeAdvanceVO.getAdvanceAmount())
			recovered = true;
		return recovered;
	}

	// function to add entry to advancedetails table when total advance amount is not recovered.
	public void addAdvanceDetail(EmployeeAdvanceVO employeeAdvanceVO, String empId) {
		Session session = null;
		Transaction transaction = null;
		EmpAdvances empAdvances = new EmpAdvances();
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			////// check
			Employee employee = (Employee) session.load(Employee.class, empId); ///
			Advance advance = (Advance) session.load(Advance.class, employeeAdvanceVO.getAdvanceId()); ///

			empAdvances.setEmployee(employee);
			empAdvances.setAdvance(advance);

			empAdvances.setEmployeeId(empId);
			empAdvances.setAdvanceId(employeeAdvanceVO.getAdvanceId());
			empAdvances.setInstallAmount(employeeAdvanceVO.getInstallAmount());
			empAdvances.setPaymentDate(new Timestamp(System.currentTimeMillis()));
			empAdvances.setStatus("A");
			empAdvances.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			session.save(empAdvances);
			session.flush();
			transaction.commit();

			// result = "Yes";
		} catch (ConstraintViolationException cv) {
			cv.printStackTrace();
			transaction.rollback();
			// result = "Advance details are exist for Employee on selected date!";
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			// result = "Unable to Add/Update Advance for selected Employee!";
		} finally {
			HibernateConnection.closeSession(session);
		}
	}
}
