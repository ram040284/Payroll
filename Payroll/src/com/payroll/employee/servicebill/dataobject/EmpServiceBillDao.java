package com.payroll.employee.servicebill.dataobject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.payroll.HibernateConnection;

public class EmpServiceBillDao {
	
public List<EmpServiceBill> getEmployeeServiceBill(String employeeId) {

		List<EmpServiceBill> empServiceBills = new ArrayList<EmpServiceBill>();
		Session session = null;
		try {
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.getNamedQuery("employeeServiceBookQuery");
			
			query.setParameter("employeeId", employeeId);
			
			empServiceBills = query.list();
			System.out.println("Service Bill Size " + query.list().size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		
		return empServiceBills;
	}
	
}
