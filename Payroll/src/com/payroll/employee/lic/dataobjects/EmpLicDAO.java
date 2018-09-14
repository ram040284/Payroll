package com.payroll.employee.lic.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
//import com.payroll.employee.lic.vo.EmpLicMasterVO;
//import com.payroll.employee.lic.vo.EmpLicVO;
import com.payroll.employee.lic.vo.EmployeeLIC;


public class EmpLicDAO {
	
	public List<EmpLic> getEmpLicList(){
		List<EmpLic> instlmtAmt = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.dataobjects.EmpLic(l.employee.employeeId, "
						+ "l.employee.firstName, l.employee.lastName, "
						+ "l.policyNo, l.paymentDate, l.paymentAmount) from EmpLic l where l.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				instlmtAmt = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return instlmtAmt;
	}
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	public List<EmployeeLIC> getEmployeeLicDeductions(String employeeId){
		List<EmployeeLIC> listEmpLICDeductions = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.dataobjects.EmployeeLIC(l.employeeId, "
						+ "l.policyNo, l.instlmtAmt) from EmpLicMaster l where  l.employeeId = ? and l.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, employeeId);
				query.setParameter(1, "A");
				listEmpLICDeductions = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return listEmpLICDeductions;
	}
	
	public List<EmpLicMaster> getEmpLicMasterList(){
		List<EmpLicMaster> empLicMasterList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.dataobjects.EmpLicMaster(l.employee.employeeId, "
						+ "l.employee.firstName, l.employee.lastName, "
						+ "l.policyNo, l.instlmtAmt) from EmpLicMaster l where l.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				empLicMasterList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empLicMasterList;
	}
	
	public EmpLic getEmpLicById(EmpLic empLic){
		//EmpLic empLic = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.lic.dataobjects.EmpLic(l.employee.employeeId, "
						/*+ "(select dept.departmentId from Department dept where dept.departmentId = (select eDept.departmentId "
						+ "from EmpDepartment eDept where eDept.empId = l.empId)), (select desg.designationId "
						+ "from Designation desg where desg.designationId = (select eDesg.designationId from EmpDesignation eDesg "
						+ "where eDesg.empId = l.empId)), "*/
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = l.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = l.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status = 'A'), "
						+ "l.policyNo, l.paymentDate, l.paymentAmount) from EmpLic l where l.status = ? and l.employee.employeeId =? and l.policyNo = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empLic.getEmployeeId());
				query.setParameter(2, empLic.getPolicyNo());
                empLic= (EmpLic)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empLic;
	}
	
	
	public EmpLicMaster getEmpLicMasterById(EmpLicMaster empLicMaster){
			Session session = null;
			try{

				String queryString = " select new com.payroll.employee.lic.dataobjects.EmpLicMaster(l.employee.employeeId, "
						/*+ "(select dept.departmentId from Department dept where dept.departmentId = (select eDept.departmentId "
						+ "from EmpDepartment eDept where eDept.empId = l.empId)), (select desg.designationId "
						+ "from Designation desg where desg.designationId = (select eDesg.designationId from EmpDesignation eDesg "
						+ "where eDesg.empId = l.empId)), "*/
						+ "(select dept.department.departmentId from EmpDepartment dept where "
						+ "dept.employee.employeeId = l.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = l.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status = 'A'), "
						+ "l.policyNo, l.instlmtAmt) from EmpLicMaster l where l.status = ? and l.employee.employeeId = ? and l.policyNo = ?";		

				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empLicMaster.getEmployeeId());
				query.setParameter(2, empLicMaster.getPolicyNo());
				empLicMaster = (EmpLicMaster)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empLicMaster;
	}
	
	public String deleteEmpLicMaster(EmpLicMaster empLicMaster){
 		String result = null;
 		Session session = null;
		Transaction transaction = null;
 		try{
 			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLicMaster l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ? and l.policyNo = ?");
            query.setParameter(0, "I");
 			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empLicMaster.getEmployeeId());
 			query.setParameter(3, empLicMaster.getPolicyNo());
 			int updated = query.executeUpdate();
 			if(updated > 0)
 				result = "Successfully deleted LIC Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete LIC details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String updateEmpLicMaster(EmpLicMaster empLicMaster){
 		String result = null;
 		Session session = null;
		Transaction transaction = null;
 		try{
 			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLicMaster l set l.instlmtAmt = ?, l.rowUpdDate = ? where l.employee.employeeId = ? and l.policyNo = ?");
 			query.setParameter(0, empLicMaster.getInstlmtAmt());
			query.setParameter(1, new Timestamp(System.currentTimeMillis())) ;
			query.setParameter(2, empLicMaster.getEmployeeId());
			query.setParameter(3, empLicMaster.getPolicyNo());

 			int updated = query.executeUpdate();
 			if(updated > 0)
 				result = "Successfully updated LIC Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to update LIC details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String updateEmpLic(EmpLic empLic){
 		String result = null;
 		Session session = null;
		Transaction transaction = null;
 		try{
 			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLic l set l.paymentAmount = ?, l.paymentDate = ? where l.employee.employeeId = ? and l.policyNo = ?");
 			query.setParameter(0, empLic.getPaymentAmount());
 			query.setParameter(1, empLic.getPaymentDate());
			query.setParameter(2, empLic.getEmployeeId());
			query.setParameter(3, empLic.getPolicyNo());

 			int updated = query.executeUpdate();
 			if(updated > 0)
 				result = "Successfully updated LIC Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to update LIC details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	
	public String deleteEmpLic(EmpLic empLic){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLic l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ? and l.policyNo= ?");
            query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empLic.getEmployeeId());
			query.setParameter(3, empLic.getPolicyNo());
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted LIC Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete LIC details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String addUpdateEmpLic(EmpLic empLic){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empLic.getEmployeeId());
			/*EmpLic licDB = checkEmpLic(empLic.getEmployeeId(), session);
			if(licDB != null){
				if(empLic.getAddUpdate() ==0){
					result ="LIC details are exist for selected Employee!";
					return result;
				}
				licDB.setPolicyNo(empLic.getPolicyNo());
				licDB.setInstlmtAmt(empLic.getInstlmtAmt());
				licDB.setPaymentDate(empLic.getPaymentDate());
				licDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(licDB);
			}
			else {*/
			System.out.println("empLic.toString()"+ empLic.toString());
				empLic.setEmployee(employee);
				empLic.setStatus("A");
				empLic.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				if(empLic.getAddUpdate() ==0)
				{
					session.save(empLic);					
				}
				else
				{
					updateEmpLic(empLic);
					//session.update(empLic);					
				}
			//}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "LIC details are exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Employee LIC details ";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	
	public String addUpdateEmpLicMaster(EmpLicMaster empLicMaster){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empLicMaster.getEmployeeId());
			/*EmpLic licDB = checkEmpLic(empLic.getEmployeeId(), session);
			if(licDB != null){
				if(empLic.getAddUpdate() ==0){
					result ="LIC details are exist for selected Employee!";
					return result;
				}
				licDB.setPolicyNo(empLic.getPolicyNo());
				licDB.setInstlmtAmt(empLic.getInstlmtAmt());
				licDB.setPaymentDate(empLic.getPaymentDate());
				licDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(licDB);
			}
			else {*/
			System.out.println("empLicMaster.toString()"+ empLicMaster.toString());
			empLicMaster.setEmployee(employee);
			empLicMaster.setStatus("A");
			empLicMaster.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				if(empLicMaster.getAddUpdate() ==0)
				{
					session.save(empLicMaster);
				}
				else
				{
//					session.update(empLicMaster);
					result = updateEmpLicMaster(empLicMaster);
				}
			//}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "LIC details are exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Employee LIC details ";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	/*private EmpLic checkEmpLic(String empId, Session session){
		EmpLic empLic = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select l from EmpLic l where l.empId = ? and l.status = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if(query.list() !=null && !query.list().isEmpty() )
				empLic = (EmpLic)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return empLic;
	}*/

}
