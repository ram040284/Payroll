package com.payroll.login.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.payroll.HibernateConnection;

public class PermissionsDAO {
	
	public List<String> getPermissions(String employeeId) {
		
		Session session = null;
		
		session = HibernateConnection.getSessionFactory().openSession();
		
		String query = "select p.permission_name from permissions p \n" + 
				"inner join roles_permissions rp on rp.permission_id = p.permission_id \n" + 
				"inner join roles r on r.role_id = rp.role_id \n" + 
				"inner join user_roles ur on ur.role_id = r.role_id \n" + 
				"inner join user_master um on um.usr_id = ur.usr_id \n" + 
				"where um.emp_id = :employeeId";
		
		SQLQuery sqlQuery = session.createSQLQuery(query);
		sqlQuery.setParameter("employeeId", employeeId);
		return sqlQuery.list();
	}

}
