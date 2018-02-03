package com.payroll.overtime.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.advance.dataobjects.Advance;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.overtime.vo.OvertimeVO;

public class OvertimeDAO {
	
	public List<com.payroll.overtime.vo.OvertimeVO> getOvertimeList(){
		List<com.payroll.overtime.vo.OvertimeVO> overtimeList = null;
		Session session = null;
		
		try{
			String queryString = " select new com.payroll.overtime.vo.OvertimeVO(o.overtimeId, o.employee.employeeId, "
					+" o.employee.firstName, o.employee.lastName, o.overtimeOrder, o.overtimeHours,"
					+ "o.overtimeDate, o.overtimeAmount) from Overtime o where o.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			overtimeList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return overtimeList;
	}
	
	public OvertimeVO getOvertimeById(int overtimeId){
		OvertimeVO overtime = null;
		Session session = null;
		try{
			String queryString ="select new com.payroll.overtime.vo.OvertimeVO(o.overtimeId, o.employee.employeeId,"
				+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = o.employee.employeeId and dept.status = 'A'), "
				+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = o.employee.employeeId and desg.lastWokingDate is null and desg.status='A'), "
				+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = o.employee.employeeId and dh.lastWokingDate is null and dh.status = 'A'), "
				+ "o.overtimeOrder, o.overtimeHours, o.overtimeDate, o.overtimeAmount) from Overtime o where o.status = ? and o.overtimeId = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, overtimeId);
			overtime = (OvertimeVO)(!query.list().isEmpty() ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return overtime;
	}
	public boolean deleteOvertime(int overtimeId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
				Query query = session.createQuery("update Overtime o set o.status = ?, o.rowUpdDate = ? where o.overtimeId = ? ");
				query.setParameter(0, "S");
				query.setParameter(1, new Timestamp(System.currentTimeMillis()));
				query.setParameter(2, overtimeId);
				int updated = query.executeUpdate();
				System.out.println("Deleted:"+updated);
				if(updated == 1){
					transaction.commit();
					success = true;
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
	
	public String addUpdateOvertime(Overtime overtime){
		String result = "Yes";
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, overtime.getEmployeeId());
			System.out.println("addUpdateOvertime -- overtime:"+overtime);
			overtime.setEmployee(employee);
			overtime.setStatus("A");
			overtime.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			if(overtime.getOvertimeId() !=0)
				session.update(overtime);
			else
				session.save(overtime);
			
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Overtime details are exist for Employee on selected date!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Overtime for selected Employee!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	private Overtime checkOvertime(int empId, Date paymentDate, Session session){
		Overtime overtime = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select o from Overtime o where o.empId = ? and o.overtimeDate = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, paymentDate);
			if(query.list() !=null && !query.list().isEmpty() )
				overtime = (Overtime)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return overtime;
	}
	
	private int getMaxEmpId(Session session){
		int maxDesgId = 0;
		//Session session = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Advance advance = (Advance)session.createQuery("select d from Advance a order by a.advanceId desc").setMaxResults(1).uniqueResult();
			int advanceId = (advance != null) ? advance.getAdvanceId() : 0;
			maxDesgId = advanceId + 1;
			System.out.println("maxDesgId:"+maxDesgId);
		}catch(Exception e){
			e.printStackTrace();
			maxDesgId = 0;
		}/*finally {
			HibernateConnection.closeSession(session);
		}*/
		return maxDesgId;
	}


}
