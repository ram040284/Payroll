package com.payroll.login.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.EmployeeDAO;

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
			user.setRole(getUserRole(user.getRoleId()));
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
			
			//user.setRole(getUserRole(user.getRoleId()));
			EmployeeDAO empDao = new EmployeeDAO();
			user.setEmployee(empDao.getEmployeeById(user.getEmpId()));
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
	
	public List<User> getUsersList(Integer deptId){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User where status = ? and deptId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, deptId);
			List<User> userList = query.list();
			EmployeeDAO empDAO = new EmployeeDAO();
			for (User user: userList) {
				user.setEmployee(empDAO.getEmployeeById(user.getEmpId()));
			}
			
			return userList;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	public UserRoles getUserRole(Integer roleId){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles where status = ? and roleId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, roleId);
			List<UserRoles> roles = query.list();
			return (roles != null && !roles.isEmpty())? roles.get(0):null;
			
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
