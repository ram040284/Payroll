package com.payroll.advance.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.advance.vo.AdvanceVO;
import com.payroll.overtime.dataobjects.Overtime;

public class AdvanceDAO {
	
	public List<com.payroll.advance.vo.AdvanceVO> getAdvances(){
		List<com.payroll.advance.vo.AdvanceVO> advanceList = null;
		Session session = null;
		
		try{
			//String queryString = " from Advance";
			String queryString = " select new com.payroll.advance.vo.AdvanceVO(a.advanceId, a.advanceName, "
					+ "a.paymentDate, a.advanceAmount) from Advance a where a.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			advanceList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return advanceList;
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
	
	public AdvanceVO getAdvanceById(int advanceId){
		AdvanceVO advDB = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			String queryString = " select new com.payroll.advance.vo.AdvanceVO(a.advanceId, a.advanceName, "
					+ "a.paymentDate, a.advanceAmount) from Advance a where a.advanceId = ? and status=?";		
			Query query = session.createQuery(queryString);
			query.setParameter(0, advanceId);
			query.setParameter(1, "A");
			advDB = (AdvanceVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return advDB;
	}
	public String addUpdateAdvance(Advance advance){
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
	
	/*private Advance checkAdvance(int empId, Date paymentDate, Session session){
		Advance advance = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select a from Advance a where a.empId = ? and a.paymentDate = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, paymentDate);
			if(query.list() !=null && !query.list().isEmpty() )
				advance = (Advance)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return advance;
	}*/
	
	
	/*private int getMaxEmpId(Session session){
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
		/*return maxDesgId;
	}*/


}
