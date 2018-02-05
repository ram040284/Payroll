package com.payroll.employee.qtr.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.qtr.vo.EmpQuartersVO;

public class EmpQuartersDAO {
	public List<EmpQuartersVO> getEmpOtrList(){
		List<EmpQuartersVO> qtrList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.qtr.vo.EmpQuartersVO(q.employee.employeeId, "
						/*+ "(select e.firstName from Employee e where e.employeeId = q.empId),"
						+ " (select e.lastName from Employee e where e.employeeId = q.empId), "*/
						+ "q.employee.firstName, q.employee.lastName, "
						+ "q.afkQtr) from EmpQuarters q where q.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				qtrList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return qtrList;
	}
	
	public EmpQuartersVO getEmpQtrById(int empId){
		EmpQuartersVO empQtr = null;
		Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.qtr.vo.EmpQuartersVO(q.employee.employeeId, q.afkQtr, "
								+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = q.employee.employeeId and dept.status = 'A'), "
								//+ "from EmpDepartment eDept where eDept.empId = b.empId)), (select desg.designationId "
								+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = q.employee.employeeId and desg.lastWokingDate is null and desg.status='A'), "
								+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = q.employee.employeeId and dh.lastWokingDate is null and dh.status = 'A'))"
								+ "from EmpQuarters q where q.employee.employeeId = ? and q.status= ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empQtr = (EmpQuartersVO)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return empQtr;
	}
	
	public String addUpdateEmpQtr(EmpQuarters empQtr){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empQtr.getEmployeeId());
			/*EmpQuarters empQtrDB = checkEmpQtr(empQtr.getEmployeeId(), session);
			if(empQtrDB != null){
				if(empQtr.getAddUpdate() ==0){
					result = "Quarters for selected employee is already added!";
					return result;
				}
				empQtrDB.setAfkQtr(empQtr.getAfkQtr());
				empQtr.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(empQtrDB);
			}
			else {*/
				empQtr.setEmployee(employee);
				empQtr.setStatus("A");
				empQtr.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				if(empQtr.getAddUpdate() ==0)
					session.save(empQtr);
				else
					session.update(empQtr);
			//}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Quarters is already alloted for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Quartes for selected employee";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	private EmpQuarters checkEmpQtr(int empId, Session session){
		EmpQuarters empQtr = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(" from EmpQuarters q where q.employee.employeeId = ? and q.status = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if(query.list() !=null && !query.list().isEmpty() )
				empQtr = (EmpQuarters)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return empQtr;
	}
	
	public String deleteEmpQtr(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpQuarters q set q.status = ?, q.rowUpdDate = ? where q.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Date());
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Quarters details!";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Quarters details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	

}
