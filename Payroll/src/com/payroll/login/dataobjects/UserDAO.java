package com.payroll.login.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.login.vo.UserVO;

public class UserDAO {
	Session session = null;
		
	public boolean addUser(User user){
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();

			user.setStatus("A");
			user.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			session.save(user);

			transaction.commit();
			result = true;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public User getUser(User userVo){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User where userId = ? and status = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, userVo.getUserId().trim());
			query.setParameter(1, "A");
			user= (User)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return user;
	}
	
	public List<UserRoles> getUserRoles(){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles where status = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			return query.list();
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	public boolean isUserNameAvailable(String userId){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User where status = ? and lower(userId) = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, userId.toLowerCase());
			List userList= query.list();
			return userList.isEmpty()? true:false;
			
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return false;
	}
}
