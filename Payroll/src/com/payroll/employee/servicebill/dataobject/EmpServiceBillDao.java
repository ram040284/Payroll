package com.payroll.employee.servicebill.dataobject;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.payroll.HibernateConnection;

public class EmpServiceBillDao {
	
public EmpServiceBill getEmployeeServiceBill(String employeeId) {

		List<EmpServiceBill> empServiceBills = new ArrayList<EmpServiceBill>();
		Session session = null;
		try {
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.getNamedQuery("employeeServiceBookQuery");
			
			query.setParameter("employeeId", employeeId);
			
			List<EmpServiceBill> bills = query.list();
			
			for (EmpServiceBill bill: bills) {
				empServiceBills.add(bill);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		
		return (EmpServiceBill) empServiceBills;
		
		/*EmpServiceBill empServiceBill = null;
		Session session = null;
		session = HibernateConnection.getSessionFactory().openSession();
		
		try{
			String empQuery = "SELECT emp.EMP_FNAME as fName, emp.EMP_MNAME as mName, " + 
					"    emp.EMP_LNAME as lName, " + 
					"    emp.EMP_PAN as empPan, " + 
					"    emp.BIRTH_DATE as birthDate, " + 
					"    emp.EMP_AADHAR as empAdhar, " + 
					"    emp.EMP_JOINING_DATE as empJoiningDate, " + 
					"    emp.EMP_LST_PROMO_DT as empLastPromDate, " + 
					"    dept.DEPT_NAME as deptName, " + 
					"    desg.DESG_NAME as desgname, " + 
					"    empSal.BASIC as basic, " + 
					"    empSal.GRD_PAY as gradePay, " + 
					"    empSal.SCALE_PAY as scalePay, " + 
					"    empSal.SCALE_CODE as scaleCode, " + 
					"    empBank.ACCT_NO as accountNumber, " + 
					"    bank.BANK_NAME as bankName, " + 
					"    bank.IFSC_CODE as ifscCode " + 
					"FROM " + 
					"    emp_master AS emp " + 
					"        INNER JOIN " + 
					"    emp_dept_details AS empDept ON emp.EMP_ID = empDept.EMP_ID " + 
					"        INNER JOIN " + 
					"    dept_master AS dept ON empDept.DEPT_ID = dept.DEPT_ID " + 
					"        INNER JOIN " + 
					"    emp_desg_details AS empDesDet ON emp.EMP_ID = empDesDet.EMP_ID " + 
					"        INNER JOIN " + 
					"    desg_master AS desg ON desg.DESG_ID = empDesDet.DESG_ID " + 
					"        INNER JOIN " + 
					"    emp_sal_master AS empSal ON emp.emp_id = empSal.emp_id " + 
					"		INNER JOIN " + 
					"	emp_bank_details as empBank ON emp.EMP_ID = empBank.emp_id " + 
					"		INNER JOIN " + 
					"	bank_master as bank ON empBank.bank_id = bank.bank_id " + 
					"WHERE " + 
					"    emp.emp_id = :employeeId";
			
			SQLQuery sqlQuery = session.createSQLQuery(empQuery);
			sqlQuery.setParameter("employeeId", employeeId);
			sqlQuery.list();
			//empServiceBill = (EmpServiceBill)(!sqlQuery.list().isEmpty() ? sqlQuery.list().get(0) : null);
			return null;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empServiceBill;*/
	}
	
}
