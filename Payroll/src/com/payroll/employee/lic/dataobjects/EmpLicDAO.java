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
import com.payroll.employee.lic.vo.EmpLicMasterVO;
import com.payroll.employee.lic.vo.EmpLicVO;
import com.payroll.employee.lic.vo.EmployeeLIC;


public class EmpLicDAO {
	
	public List<EmpLicVO> getEmpLicList(){
		List<EmpLicVO> instlmtAmt = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.vo.EmpLicVO(l.employee.employeeId, "
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
	public List<EmployeeLIC> getEmployeeLicDeductions(int employeeId){
		List<EmployeeLIC> listEmpLICDeductions = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.vo.EmployeeLIC(l.employeeId, "
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
	
	public List<EmpLicMasterVO> getEmpLicMasterList(){
		List<EmpLicMasterVO> instlmtAmt = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.lic.vo.EmpLicMasterVO(l.employee.employeeId, "
						+ "l.employee.firstName, l.employee.lastName, "
						+ "l.policyNo, l.instlmtAmt) from EmpLicMaster l where l.status = ?";		
				
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
	
	public EmpLicVO getEmpLicById(int empId){
		EmpLicVO empLicVO = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.lic.vo.EmpLicVO(l.employee.employeeId, "
						/*+ "(select dept.departmentId from Department dept where dept.departmentId = (select eDept.departmentId "
						+ "from EmpDepartment eDept where eDept.empId = l.empId)), (select desg.designationId "
						+ "from Designation desg where desg.designationId = (select eDesg.designationId from EmpDesignation eDesg "
						+ "where eDesg.empId = l.empId)), "*/
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = l.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = l.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status = 'A'), "
						+ "l.policyNo, l.paymentDate, l.paymentAmount) from EmpLic l where l.status = ? and l.employee.employeeId = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empId);
				empLicVO = (EmpLicVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empLicVO;
	}
	
	
	public EmpLicMasterVO getEmpLicMasterById(int empId){
		EmpLicMasterVO empLicMasterVO = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.lic.vo.EmpLicMasterVO(l.employee.employeeId, "
						/*+ "(select dept.departmentId from Department dept where dept.departmentId = (select eDept.departmentId "
						+ "from EmpDepartment eDept where eDept.empId = l.empId)), (select desg.designationId "
						+ "from Designation desg where desg.designationId = (select eDesg.designationId from EmpDesignation eDesg "
						+ "where eDesg.empId = l.empId)), "*/
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = l.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = l.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status = 'A'), "
						+ "l.policyNo, l.instlmtAmt) from EmpLicMaster l where l.status = ? and l.employee.employeeId = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empId);
				empLicMasterVO = (EmpLicMasterVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empLicMasterVO;
	}
	public String deleteEmpLicMaster(int empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLicMaster l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ?");
            query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
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
	
	
	public String deleteEmpLic(int empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpLic l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ?");
            query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
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
				empLic.setEmployee(employee);
				empLic.setStatus("A");
				empLic.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				if(empLic.getAddUpdate() ==0)
					session.save(empLic);
				else
					session.update(empLic);
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
					session.save(empLicMaster);
				else
					session.update(empLicMaster);
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
	
	/*private EmpLic checkEmpLic(int empId, Session session){
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
