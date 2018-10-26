package com.payroll.login.dataobjects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.advance.dataobjects.EmployeeAdvanceVO;
import com.payroll.department.dataobjects.Department;
import com.payroll.department.dataobjects.DepartmentDAO;
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
			//user.setRole(getUserRole(user.getRoleId()));
			session.save(user);
			
			Integer[] roles = user.getRoles();
			for (Integer roleId :roles) {
				Roles role = getRole(roleId);
				UserRoles userRoles = new UserRoles();
				userRoles.setRole(role);
				userRoles.setUser(user);
				userRoles.setStatus("A");
				userRoles.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.save(userRoles);
			}
			
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
			query.setParameter(0, userVo.getUserName().trim());
			query.setParameter(1, "A");
			user= (User)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
			if (user != null) {
				EmployeeDAO empDao = new EmployeeDAO();
				EmployeeVO emp = empDao.getEmployeeDetailsById(user.getEmpId(),0);
				if (emp != null) {
					user.setEmployee(emp);
					user.setDeptId(emp.getDepartmentId());
					//user.setRoleId(user.getRole().getRoleId());
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
	
	/*public User getUserByUserIdPk(User userVo){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles usrRoles where usrRoles.user.userIdPk = ? and usrRoles.status = ?";
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
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return user;
	}*/
	
	public User getUserRoleByUserId(User userVo){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles usrRoles where usrRoles.user.userId = ? and usrRoles.status = ? ";
			Query query = session.createQuery(queryString);
			query.setParameter(0, userVo.getUserId());
			query.setParameter(1, "A");
			
			List<UserRoles> userList = query.list();
			EmployeeDAO empDAO = new EmployeeDAO();
			UserDAO userDAO = new UserDAO();
			List<User> userFilterList = new ArrayList<User>();
			Map<String, User> usersMap = new HashMap();
			
			for (UserRoles userRole: userList) {
				if (usersMap.containsKey("" +userRole.getUser().getUserId())) {
					if (userRole.getRole()!=null) {
						User user = usersMap.get(""+userRole.getUser().getUserId());
						if (user != null) {
							List<Integer> roleIds = user.getRolesList();
							roleIds.add(userRole.getRole().getRoleId());
							user.setRolesList(roleIds);
							usersMap.put(""+userRole.getUser().getUserId(), user);
						}
					}
					
					continue;
				}
				
				User user = userRole.getUser();
				if (user.getEmpId() == null) continue;
				EmployeeVO emp = empDAO.getEmployeeDetailsById(user.getEmpId(), 0);
				if (emp == null) continue;
				
				user.setEmployee(emp);
				user.setDeptIds(user.getDeptIds());
				List<Integer> roleIds = new ArrayList();
				roleIds.add(userRole.getRole().getRoleId());
				user.setRolesList(roleIds);
				String deptNames = "";
				if(user.getDeptIds() != null) {
					user.setDeptIdsArray(user.getDeptIds().split(","));
				}
				user.setDeptNames(deptNames);
				usersMap.put(""+userRole.getUser().getUserId(), user);
			}
			userFilterList.addAll(usersMap.values());
			return userFilterList.size()>0? userFilterList.get(0):null;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	/****************************************/
		public String getUserRolesByEmpId(String employeeId) {
				String userRole = null;
				Session session = null;
				
				session = HibernateConnection.getSessionFactory().openSession();
				
				SQLQuery query = session.createSQLQuery("select rol.ROLE_NAME  from emp_master em  "
						+ "inner join user_master um on em.EMP_ID = um.EMP_ID "
						+ "inner join user_roles urol on um.USR_ID = urol.USR_ID "
						+ "inner join roles rol on urol.ROLE_ID = rol.ROLE_ID "
						+ "where um.emp_id = :employeeId");
				
				query.setParameter("employeeId", employeeId);
			
				if(query.list()!= null) {
					userRole = (String) query.list().get(0);
				}
				
				return userRole;
		}					
	/***************************************/	
	
	public User getUserByEmpId(User userVo){
		Session session = null;
		//Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			//transaction = session.beginTransaction();
			String queryString = " from User user where user.empId = ? and user.status = ? ";
			Query query = session.createQuery(queryString);
			query.setParameter(0, userVo.getUserName());
			query.setParameter(1, "A");
			
			List<User> userList = query.list();
			return userList.size()>0? userList.get(0):null;
		}catch(Exception e){
			e.printStackTrace();
			//transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	public boolean deleteUser(int userId){
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			String queryString = "Update User usr set usr.status = ? where usr.userId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "I");
			query.setParameter(1, userId);
			
			int recordCount = query.executeUpdate();
			
			String queryString1 = "Update UserRoles usrRoles set usrRoles.status = ? where usrRoles.user.userId = ?";
			Query query1 = session.createQuery(queryString1);
			query1.setParameter(0, "I");
			query1.setParameter(1, userId);
			
			int recordCount2 = query1.executeUpdate();
					
			transaction.commit();
			result = (recordCount > 0 || recordCount2 > 0)?true : false;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public boolean updateUserRoles(User userVo){
		Session session = null;
		Transaction transaction = null;
		boolean result = false;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles usrRoles where usrRoles.user.userId = ? and usrRoles.status = ? ";
			Query query = session.createQuery(queryString);
			query.setParameter(0, userVo.getUserId());
			query.setParameter(1, "A");
			
			List<UserRoles> userList = query.list();
			
			List<Integer> rolesArray = userVo.getRoles()!=null ? new ArrayList(Arrays.asList(userVo.getRoles())):new ArrayList();
			List<Integer> rolesArrayDB = new ArrayList();
			
			for (UserRoles userRole:userList) {
				rolesArrayDB.add(userRole.getRole().getRoleId());
				if (!rolesArray.contains(userRole.getRole().getRoleId())) {
					userRole.setStatus("I");
					userRole.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
					session.update(userRole);
				}
			}
			System.out.println("rolesArray before:" + rolesArray);
			rolesArray.removeAll(rolesArrayDB);
			System.out.println("rolesArray after:" + rolesArray);
			session.clear();
			User user = new UserDAO().getUserRoleByUserId(userVo);
			for (Integer roleId : rolesArray) {
				Roles role = getRole(roleId);
				UserRoles userRoles = new UserRoles();
				userRoles.setRole(role);
				userRoles.setUser(user);
				userRoles.setStatus("A");
				userRoles.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.save(userRoles);
			}
			result = true;
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public boolean updateUser(User userVo){
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			System.out.println("user before:" + userVo);
			User user = new UserDAO().getUserRoleByUserId(userVo);
			System.out.println("user :" + user);
			System.out.println("user after:" + userVo);
			user.setDeptIds(userVo.getDeptIds());
			user.setStatus("A");
			user.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			session.update(user);
			updateUserRoles(userVo);
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
	
	public List<Roles> getRoles(){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from Roles where status = ?";
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
	
	/*public List<User> getUsersList(int deptId, int roleId){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from User usr where usr.status = ? ";
		//	if (roleId != 0) queryString += " and usr.role.roleId = ?";
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
					//user.setRoleId(user.getRole().getRoleId());
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
	}*/
	
	public List<User> getUsersList(int deptId, int roleId){
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from UserRoles usrRoles where usrRoles.status = ? ";
			if (roleId != 0) queryString += " and usrRoles.role.roleId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			if (roleId != 0) query.setParameter(1, roleId);
			
			List<UserRoles> userList = query.list();
			EmployeeDAO empDAO = new EmployeeDAO();
			UserDAO userDAO = new UserDAO();
			List<User> userFilterList = new ArrayList<User>();
			Map<String, User> usersMap = new HashMap();
			for (UserRoles userRole: userList) {
				if (usersMap.containsKey("" +userRole.getUser().getUserId())) {
					if (userRole.getRole()!=null) {
						User user = usersMap.get(""+userRole.getUser().getUserId());
						if (user != null) {
							String roleNames = user.getRoleNames() + ", " + Utils.safeTrim(userRole.getRole().getRoleName());
							user.setRoleNames(roleNames);
							usersMap.put(""+userRole.getUser().getUserId(), user);
						}
					}
					
					continue;
				}
				
				User user = userRole.getUser();
				if (user.getEmpId() == null) continue;
				EmployeeVO emp = empDAO.getEmployeeDetailsById(user.getEmpId(), deptId);
				if (emp == null || (deptId!=0 && deptId!=emp.getDepartmentId())) continue;
				
				user.setEmployee(emp);
				user.setDeptIds(user.getDeptIds());
				user.setRoleNames(Utils.safeTrim(userRole.getRole().getRoleName()));
				String deptNames = "";
				if(user.getDeptIds() != null) {
					for (String deptIdTemp : user.getDeptIds().split(",")) {
						DepartmentDAO deptDao = new DepartmentDAO();
						Department dept = deptDao.getDepartmentById(new Integer(deptIdTemp));
						deptNames = deptNames + ", " + Utils.safeTrim(dept.getDepartmantName());
					}
					if (deptNames.length() > 2)
					deptNames = deptNames.substring(2);
				}
				user.setDeptNames(deptNames);
				usersMap.put(""+userRole.getUser().getUserId(), user);
			}
			userFilterList.addAll(usersMap.values());
			return userFilterList;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return null;
	}
	
	public Roles getRole(int roleId){
		User user = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			String queryString = " from Roles where status = ? and roleId = ?";
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, roleId);
			List<Roles> roles = query.list();
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
