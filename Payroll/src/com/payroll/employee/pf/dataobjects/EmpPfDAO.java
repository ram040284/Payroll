package com.payroll.employee.pf.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.bank.dataobjects.EmpBank;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.pf.vo.EmpPfVO;

public class EmpPfDAO {
	
	public List<EmpPfVO> getEmpPfList(){
		List<EmpPfVO> pfList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.pf.vo.EmpPfVO(p.employee.employeeId, "
						/*+ "(select e.firstName from Employee e where e.employeeId = p.empId),"
						+ " (select e.lastName from Employee e where e.employeeId = p.empId), "*/
						+"p.employee.firstName, p.employee.lastName, "
						+ "p.pfDate, p.pfsCpfCntrbn, p.pfLoneRecAmt, p.cfLoneRecAmt, p.apfAcpfCntrbn) from EmpPf p where p.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				pfList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return pfList;
	}
	
	public EmpPfVO getEmpPfById(int empId){
		EmpPfVO empPf = null;
		Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.pf.vo.EmpPfVO(p.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = p.employee.employeeId and dept.status = 'A'), "
						//+ "from EmpDepartment eDept where eDept.empId = b.empId)), (select desg.designationId "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = p.employee.employeeId and desg.lastWokingDate is null and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = p.employee.employeeId and dh.lastWokingDate is null and dh.status = 'A'), "
						//+ "(select eDesg.designationId from EmpDesignation eDesg where eDesg.empId = b.empId and eDesg.lastWokingDate is null)), "
						+" p.pfDate, p.pfsCpfCntrbn, p.pfLoneRecAmt, "
						+ "p.cfLoneRecAmt, p.apfAcpfCntrbn) from EmpPf p where p.employee.employeeId = ? and p.status = ? ";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				empPf = (EmpPfVO)(!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empPf;
	}
	
	public String deleteEmpPf(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpPf p set p.status = ?, p.rowUpdDate = ? where p.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Provident fund details!";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Provident fund details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String addUpdateEmpPf(EmpPf empPf){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//EmpPf empPfDB = checkEmpPf(empPf.getEmployeeId(), session);
			Employee employee = (Employee)session.load(Employee.class, empPf.getEmployeeId());
			/*if(empPfDB != null){
				if(empPf.getAddUpdate() ==0){
					result = "Provident details for Employee are exit!";
					return result;
				}
				empPfDB.setPfDate(empPf.getPfDate());
				empPfDB.setApfAcpfCntrbn(empPf.getApfAcpfCntrbn());
				empPfDB.setCfLoneRecAmt(empPf.getCfLoneRecAmt());
				empPfDB.setPfLoneRecAmt(empPf.getPfLoneRecAmt());
				empPfDB.setPfsCpfCntrbn(empPf.getPfsCpfCntrbn());
				empPfDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(empPfDB);
			}
			else {*/
				empPf.setEmployee(employee);
				empPf.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				empPf.setStatus("A");
				if(empPf.getAddUpdate() ==0)
					session.save(empPf);
				else
					session.update(empPf);
			//}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "PF details are already exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Provident Fund details";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	private EmpPf checkEmpPf(int empId, Session session){
		EmpPf empPf = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select p from EmpPf p where p.empId = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			if(query.list() !=null && !query.list().isEmpty() )
				empPf = (EmpPf)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return empPf;
	}


}
