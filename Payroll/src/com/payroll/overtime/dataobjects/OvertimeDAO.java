package com.payroll.overtime.dataobjects;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.advance.dataobjects.Advance;

public class OvertimeDAO {
	
	public List<com.payroll.overtime.vo.Overtime> getOvertimeList(){
		List<com.payroll.overtime.vo.Overtime> overtimeList = null;
		Session session = null;
		
		try{
			//String queryString = " from Overtime o ";
			String queryString = " select new com.payroll.overtime.vo.Overtime(o.empId, (select e.firstName from Employee e where e.employeeId = o.empId),"
					+ " (select e.lastName from Employee e where e.employeeId = o.empId), o.departmentId, (select d.departmantName from Department d where d.departmentId = o.departmentId),"
					+ "o.designationId, (select desg.designationName from Designation desg where desg.designationId = o.designationId),"
					+ "o.overtimeDate, o.overtimeAmount) from Overtime o ";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			overtimeList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return overtimeList;
	}
	
	public boolean deleteOvertime(int empId, Date overtimeDate){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(empId !=0 && overtimeDate !=null){
				Query query = session.createQuery("delete from Overtime o where o.empId = ? "
						+ "and o.overtimeDate = ? ");
				query.setParameter(0, empId);
				query.setParameter(1, overtimeDate);
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
	
	public boolean addUpdateOvertime(Overtime overtime){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			System.out.println("addUpdateOvertime -- overtime:"+overtime);
			Overtime overtimedb = checkOvertime(overtime.getEmpId(), overtime.getOvertimeDate(), session);
			if(overtimedb != null){
				overtimedb.setOvertimeAmount(overtime.getOvertimeAmount());
				session.update(overtimedb);
			}
			else {
				session.save(overtime);
			}
			transaction.commit();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			success = false;
		}finally {
			HibernateConnection.closeSession(session);
		}
		return success;
	}
	
	private Overtime checkOvertime(int empId, Date paymentDate, Session session){
		Overtime overtime = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select o from Overtime o where o.empId = ? and o.overtimeDate = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, paymentDate);
			if(query.list() !=null && !query.list().isEmpty() )
				overtime = (Overtime)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return overtime;
	}
	
	private int getMaxEmpId(Session session){
		int maxDesgId = 0;
		//Session session = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Advance advance = (Advance)session.createQuery("select d from Advance a order by a.advanceId desc").setMaxResults(1).uniqueResult();
			int advanceId = (advance != null) ? advance.getAdvanceId() : 0;
			maxDesgId = advanceId + 1;
			System.out.println("maxDesgId:"+maxDesgId);
		}catch(Exception e){
			e.printStackTrace();
			maxDesgId = 0;
		}/*finally {
			HibernateConnection.closeSession(session);
		}*/
		return maxDesgId;
	}


}
