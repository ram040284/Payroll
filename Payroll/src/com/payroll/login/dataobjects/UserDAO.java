package com.payroll.login.dataobjects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.vo.EmployeeVO;

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
	
	public User getUserByUserId(User userVo){
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
			
			if (user != null) {
				EmployeeDAO empDao = new EmployeeDAO();
				EmployeeVO emp = empDao.getEmployeeDetailsById(user.getEmpId(),0);
				if (emp != null) {
					user.setEmployee(emp);
					user.setDeptId(emp.getDepartmentId());
					user.setRoleId(user.getRole().getRoleId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return user;
	}
	
	public User getUserByUserIdPk(User userVo){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User where userIdPk = ? and status = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, userVo.getUserIdPk());
			query.setParameter(1, "A");
			user= (User)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
			if (user != null) {
				EmployeeDAO empDao = new EmployeeDAO();
				EmployeeVO emp = empDao.getEmployeeDetailsById(user.getEmpId(), 0);
				if(emp != null) {
					user.setEmployee(emp);
					user.setDeptId(emp.getDepartmentId());
					user.setRoleId(user.getRole().getRoleId());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return user;
	}
	
	public boolean deleteUser(int userIdPk){
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			String queryString = "Update User usr set usr.status = ? where userIdPk = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "S");
			query.setParameter(1, userIdPk);
			
			int recordCount = query.executeUpdate();
					
			transaction.commit();
			result = (recordCount > 0)?true : false;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public boolean updateUser(User user){
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			user.setStatus("A");
			user.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			user.setRole(getUserRole(user.getRoleId()));
			session.update(user);

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
	
	public List<User> getUsersList(int deptId, int roleId){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User usr where usr.status = ? ";
			if (roleId != 0) queryString += " and usr.role.roleId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			if (roleId != 0) query.setParameter(1, roleId);
			
			List<User> userList = query.list();
			EmployeeDAO empDAO = new EmployeeDAO();
			UserDAO userDAO = new UserDAO();
			List<User> userFilterList = new ArrayList<User>();
			for (User user: userList) {
				if (user.getEmpId() == null) continue;
				EmployeeVO emp = empDAO.getEmployeeDetailsById(user.getEmpId(), deptId);
				if (emp != null) {
					user.setEmployee(emp);
					user.setDeptId(emp.getDepartmentId());
					user.setRoleId(user.getRole().getRoleId());
					userFilterList.add(user);
				}
			}
			
			return userFilterList;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	public UserRoles getUserRole(int roleId){
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
