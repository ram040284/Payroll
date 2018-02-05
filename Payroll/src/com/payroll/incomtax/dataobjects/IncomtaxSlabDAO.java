package com.payroll.incomtax.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;

public class IncomtaxSlabDAO {
	
	public String addUpdate(IncomtaxSlab incomtaxSlab){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(incomtaxSlab.getIncomtaxId() != 0){
				incomtaxSlab.setStatus("A");
				incomtaxSlab.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(incomtaxSlab);
			}else {
				incomtaxSlab.setStatus("A");
				incomtaxSlab.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(incomtaxSlab);
			}
			result = "Yes";
			transaction.commit();
		} catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Given Incomtax Slab details already exist!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Incomtax Slab Details!"; 
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public List<IncomtaxSlab> getIncomtaxSlabs(){
		List<IncomtaxSlab> incomtaxSlabList = null;
		Session session = null;
		
		try{
			String queryString = " from IncomtaxSlab i where i.status = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			incomtaxSlabList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return incomtaxSlabList;
	}
	
	public boolean deleteIncomtaxSlab(int incomtaxId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(incomtaxId != 0){
				Query query = session.createQuery("update IncomtaxSlab i set i.status = ?, i.rowUpdDate = ? where i.incomtaxId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Date());
				query.setParameter(2, incomtaxId);
				int updated = query.executeUpdate();
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
	
	public IncomtaxSlab getIncomtaxSlabById(int incomtaxId){
		IncomtaxSlab incomtaxSlab = null;
		Session session = null;
		
		try{
			String queryString = " from IncomtaxSlab i where i.incomtaxId = ? and i.status = ?";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, incomtaxId);
			query.setParameter(1, "A");
			incomtaxSlab = (IncomtaxSlab)(!(query.list().isEmpty())?query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return incomtaxSlab;
	}
	
	public List<IncomtaxSlab> getIncomtaxSlabByYr(String finYr){
		List<IncomtaxSlab> incomtaxSlabList = null;
		Session session = null;
		
		try{
			String queryString = " from IncomtaxSlab i where i.financialYear = ? and i.status = ?";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, finYr);
			query.setParameter(1, "A");
			incomtaxSlabList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return incomtaxSlabList;
	}


}
