package com.payroll.department.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;

public class DepartmentDAO {
	/**
	 * 
	 * @param dept
	 * @return
	 */
	public String addUpdateDepartment(Department dept){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			/*boolean exist = nameExist(dept.getDepartmantName(), session);
			if(exist){
				result = "Given Department name already exist!";
				return result;
			}*/
			transaction = session.beginTransaction();
			if(dept.getDepartmentId() != 0){
				dept.setStatus("A");
				dept.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(dept);
			}else {
				//dept.setDepartmentId(getMaxDeptId(session));
				dept.setStatus("A");
				dept.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(dept);
				
			}
			result = "Yes";
			transaction.commit();
			
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Given Department details already exist!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Department!"; 
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public boolean deleteDepartment(int deptId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(deptId != 0){
				Query query = session.createQuery("update Department d set d.status = ?, d.rowUpdDate = ? where d.departmentId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Date());
				query.setParameter(2, deptId);
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
	/**
	 * 
	 * @param session
	 * @return
	 */
	private int getMaxDeptId(Session session){
		int maxDeptId = 0;
		//Session session = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Department dept = (Department)session.createQuery("select d from Department d order by d.departmentId desc").setMaxResults(1).uniqueResult();
			int deptId = (dept != null) ? dept.getDepartmentId() : 0;
			maxDeptId = deptId + 1;
			System.out.println("maxDeptId:"+maxDeptId);
		}catch(Exception e){
			e.printStackTrace();
			maxDeptId = 0;
		}/*finally {
			HibernateConnection.closeSession(session);
		}*/
		return maxDeptId;
	}
	/**
	 * 
	 * @return
	 */
	public List<Department> getDepartments(){
		List<Department> departmentList = null;
		Session session = null;
		
		try{
			String queryString = " from Department d where d.status = ?";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			departmentList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return departmentList;
	}
	/**
	 * 
	 * @return
	 */
	public List<Department> getDeptSections(){
		List<Department> departmentList = null;
		Session session = null;
		
		try{
			String queryString = " from Department d where d.status = ? order by d.section";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			departmentList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return departmentList;
	}
	/**
	 * 
	 * @param section
	 * @return
	 */
	public List<Department> getDepartmentsBySection(String section){
		
		List<Department> departmentList = null;
		Session session = null;
		
		try{
			String queryString = " from Department d where d.status = ? and d.section = ? ";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, section);
			departmentList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return departmentList;
	}
	
	/**
	 * 
	 * @param deptId
	 * @return
	 */
	public Department getDepartmentById(int deptId){
		Department department = null;
		Session session = null;
		
		try{
			String queryString = " from Department d where d.departmentId = ? and d.status = ?";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, deptId);
			query.setParameter(1, "A");
			department = (Department)(!(query.list().isEmpty())?query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return department;
	}
	/**
	 * 
	 * @param name
	 * @param session
	 * @return
	 */
	public boolean nameExist(String name, Session session){
		boolean exist = false;
		Department department = null;
		try{
			String queryString = " from Department d where d.departmantName = ? ";
					
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, name);
			department = (Department)(!(query.list().isEmpty())?query.list().get(0) : null);
			if(department != null){
				exist = department.getDepartmantName().equalsIgnoreCase(name) ? true : false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return exist;
	}

}
