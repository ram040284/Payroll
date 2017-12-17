package com.payroll.bank.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.department.dataobjects.Department;

public class BankDetailsDAO {
	public String addUpdateBankDetails(BankDetails bankDetails){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			/*boolean exist = nameExist(bankDetails.getBankName(), session);
			if(exist){
				result = "Given Bank name already exist!";
				return result;
			}*/
			transaction = session.beginTransaction();
			if(bankDetails.getBankId() != 0){
				bankDetails.setStatus("A");
				bankDetails.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(bankDetails);
			}else {
				//dept.setDepartmentId(getMaxDeptId(session));
				bankDetails.setStatus("A");
				bankDetails.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(bankDetails);
				
			}
			result = "Yes";
			transaction.commit();
			
		} catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Given Bank details already exist!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Bank Details!"; 
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public List<BankDetails> getBankDetails(){
		List<BankDetails> bankDetailsList = null;
		Session session = null;
		
		try{
			String queryString = " from BankDetails b where b.status = ?";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			bankDetailsList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return bankDetailsList;
	}
	
	public boolean deleteBankDetails(int bankId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(bankId != 0){
				Query query = session.createQuery("update BankDetails b set b.status = ?, b.rowUpdDate = ? where b.bankId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Date());
				query.setParameter(2, bankId);
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
	
	public BankDetails getBankDetailsById(int bankId){
		BankDetails bankDetails = null;
		Session session = null;
		
		try{
			String queryString = " from BankDetails d where d.bankId = ? ";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, bankId);
			bankDetails = (BankDetails)(!(query.list().isEmpty())?query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return bankDetails;
	}
	
	
	public boolean nameExist(String name, Session session){
		boolean exist = false;
		BankDetails bankDetails = null;
		try{
			String queryString = " from BankDetails b where b.bankName = ? ";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, name);
			bankDetails = (BankDetails)(!(query.list().isEmpty())?query.list().get(0) : null);
			if(bankDetails != null){
				exist = bankDetails.getBankName().equalsIgnoreCase(name) ? true : false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return exist;
	}

	
}
