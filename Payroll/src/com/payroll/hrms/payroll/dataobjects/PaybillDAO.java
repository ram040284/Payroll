package com.payroll.hrms.payroll.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;

public class PaybillDAO {
	private int headId=0;
	private Date startDate;
	private Date endDate;
	private boolean isBankWise;
	private int deptId;
	public PaybillDAO(){}
	public PaybillDAO(Date startDate, Date endDate, int deptId){
		this.startDate = startDate;
		this.endDate = endDate;
		this.deptId = deptId;
	}
	public String addPaybill(Paybill paybill){
		System.out.println("paybill.getEmployeeId() " + paybill.getEmployeeId());
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, paybill.getEmployeeId());
			
			paybill.setEmployee(employee);
			paybill.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			session.save(paybill);
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Paybill is Added!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add Paybill";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public List<Paybill> getPaybillsByDept(int headId, boolean bankWise, int billType){
		this.headId = headId;
		this.isBankWise = bankWise;
		return getPaybillsByDept(deptId, billType);
	}
	
	public List<Paybill> getPaybillsByBank(int deptId){
		
		return getPaybillsByDept(deptId, 1);
	}
	
	public List<Paybill> getPaybillsByDept(int deptId, int billType){
		System.out.println("get paybill by dept id...");
		List<Paybill> paybills = null;
			Session session = null;
			try{
				StringBuffer queryString = new StringBuffer(" from Paybill p where p.employee.employeeType = ? and ");
				//if(!isBankWise ){
					queryString.append("p.employee.employeeId in ");
					if(this.headId != 0)
						queryString.append("(select eHead.employee.employeeId from EmpHeadInfo eHead where eHead.headInfo.headId = ?) and ");
					else
						queryString.append("(select eDept.employee.employeeId from EmpDepartment eDept where eDept.department.departmentId = ?) and ");
				//}
				queryString.append("p.month >= :startDate and p.month <= :endDate ");
				if(isBankWise)
					queryString.append(" order by p.bankId asc");
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString.toString());
				query.setParameter(0, billType);
				//if(!isBankWise){
					if(this.headId != 0) {
						query.setParameter(1, headId);
					}
						
					else {
						query.setParameter(1, deptId);
					}
						
				//}
				
				query.setTimestamp("startDate", startDate);
				query.setTimestamp("endDate", endDate);
				paybills = query.list();
				System.out.println("Report data " + paybills.toString());
				System.out.println("**** getPaybillsByDept Query Count: " + query.list().size());
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return paybills;
	}
	
	public Paybill getPaybillByEmp(String empId){
		Paybill paybill = null;
		Session session = null;
		try{
			StringBuffer queryString = new StringBuffer(" from Paybill p where p.employee.employeeId = ? ");
			queryString.append("and p.month >= :startDate and p.month <= :endDate");
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString.toString());
			query.setParameter(0, empId);
			query.setTimestamp("startDate", startDate);
			query.setTimestamp("endDate", endDate);
			paybill = (query.list() !=null && !query.list().isEmpty()) ? (Paybill)query.list().get(0): null;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return paybill;
	}

	

}
