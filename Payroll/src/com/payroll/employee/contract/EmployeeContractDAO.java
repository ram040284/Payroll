package com.payroll.employee.contract;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.vo.EmployeeVO;

public class EmployeeContractDAO {
	Session session = null;
	
	public List<com.payroll.employee.contract.EmployeeContractVO> getContractualEmployee(){
		List<com.payroll.employee.contract.EmployeeContractVO> contractVOs = null;
		
		try {
			String empContractData = "select new com.payroll.employee.contract.EmployeeContractVO(empCont.employee.employeeId, "
					+ "empCont.employee.firstName, empCont.employee.lastName, empCont.appointmentDate, empCont.endDate, empCont.engagementLetterId) from EmployeeContract empCont where empCont.status = ?";
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(empContractData);
			query.setParameter(0, "A");
			contractVOs = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contractVOs;
	}
	
	public EmployeeContractVO getContractualEmployeeById(String empId){
		EmployeeContractVO employee = null;
		try{
			String queryString = " select new com.payroll.employee.contract.EmployeeContractVO(empCont.employee.employeeId,"
					+ "empCont.employee.firstName, empCont.employee.lastName, empCont.appointmentDate, empCont.endDate, empCont.engagementLetterId) from EmployeeContract empCont where empCont.employeeId = ? and empCont.status = ?";		
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employee = (EmployeeContractVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			System.out.println("EmployeeContractVO :"+ employee);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
	
	public EmployeeContractVO checkContractEmp(String empId){
		EmployeeContractVO employee = null;
		try{
			String queryString = " select new com.payroll.employee.contract.EmployeeContractVO(empCont.employee.employeeId,"
					+ "empCont.employee.firstName, empCont.employee.lastName, empCont.appointmentDate, empCont.endDate, empCont.engagementLetterId) from EmployeeContract empCont where empCont.employeeId = ?";		
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			employee = (EmployeeContractVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			System.out.println("EmployeeContractVO :"+ employee);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
	
	public String addUpdateContractualEmplyee(EmployeeContract contract) {
		String result = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, contract.getEmployeeId());
			EmployeeContractVO employeeContractVO = checkContractEmp(contract.getEmployeeId());
			contract.setEmployee(employee);
			contract.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			contract.setStatus("A");
			if (contract.getAddUpdate() == 0) {
				session.save(contract);
				
			}else {
				Query query = session.createQuery("update EmployeeContract d set d.appointmentDate = ?, d.endDate = ?, d.engagementLetterId = ?, "
						+ "d.status = ?, d.rowUpdatedDate = ? where d.employee.employeeId = ? and  d.status = ?");
				
				query.setParameter(0, contract.getAppointmentDate());
				query.setParameter(1, contract.getEndDate());
				query.setParameter(2, contract.getEngagementLetterId());
				query.setParameter(3, "A");
				query.setParameter(4, contract.getRowUpdatedDate());
				
				query.setParameter(5, contract.getEmployeeId());
				query.setParameter(6, "A");
				
				int updated = query.executeUpdate();
				if(updated > 0)
					result = "Successfully update Contractual Employee details!";
			}
			session.flush();
			transaction.commit();
			result = "Yes";
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			result = "Contractual employee is exist!!!";
		}
		return result;
	}
	
	public String deleteContractEmp(String empId) {
		String result = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmployeeContract empCon set empCon.status = ? where empCon.employee.employeeId = ? and empCon.status = ?");
            query.setParameter(0, "I");
			query.setParameter(1, empId);
			query.setParameter(2, "A");
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Contractual Employee Details!";
			session.flush();	
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failed to delete EMP Allowance details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		
		return result;
	}

}
