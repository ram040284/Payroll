package com.payroll.employee.salary.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.salary.vo.SalaryVO;

public class SalaryDAO {
	
	public List<com.payroll.employee.salary.vo.SalaryVO> getSalaries(){
		List<com.payroll.employee.salary.vo.SalaryVO> salaries = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.salary.vo.SalaryVO(s.employee.employeeId, "
						//+ "(select e.firstName from Employee e where e.employeeId = s.empId),"
						//+ " (select e.lastName from Employee e where e.employeeId = s.empId), "
						+"s.employee.firstName, s.employee.lastName, "
						+ "s.year, s.basic, s.gradePay, s.scalePay, s.scaleInc) from Salary s where s.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				salaries = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return salaries;
	}
	
	public String addUpdateSalary(Salary salary){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//Employee employee = new EmployeeDAO().getById(salary.getEmployeeId());
			Employee employee = (Employee)session.load(Employee.class, salary.getEmployeeId());
			//Salary salayDB = checkEmpSalary(salary.getEmployeeId(), session);
			/*if(salayDB != null){
				/*if(salary.getAddUpdate() ==0){
					result = "Salary for selected employee is exist!";
					return result;
				}*/
				/*salayDB.setBasic(salary.getBasic());
				salayDB.setGradePay(salary.getGradePay());
				salayDB.setScaleInc(salary.getScaleInc());
				salayDB.setScalePay(salary.getScalePay());
				salayDB.setYear(salary.getYear());
				salayDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(salayDB);
			}
			else {*/
				salary.setEmployee(employee);
				salary.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				salary.setStatus("A");
				if(salary.getAddUpdate() == 0)
					session.save(salary);
				else
					session.update(salary);
				
			//}
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Salary details are already exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Salary for selected employee";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public SalaryVO getEmpSalary(int empId){
		SalaryVO salVO = null;
		Session session = null;
		try{
			String queryString = " select new com.payroll.employee.salary.vo.SalaryVO(s.employee.employeeId, "+
					"(select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = s.employee.employeeId), "
					//+ "from EmpDepartment eDept where eDept.employee.employeeId = s.employee.employeeId)), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = s.employee.employeeId and eDesg.lastWokingDate is null), "
					//+ "where eDesg.employee.employeeId = s.employee.employeeId and eDesg.lastWokingDate is null)), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = s.employee.employeeId and dh.lastWokingDate is null), "
					//+ "(select eDesg.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = s.employee.employeeId and eDesg.lastWokingDate is null)), "
					+ "s.year, s.basic, s.gradePay, s.scalePay, s.scaleInc) from Salary s where s.employee.employeeId = ? and s.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			salVO = (SalaryVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return salVO;
	}
	
	public String deleteEmpSal(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update Salary s set s.status = ?, s.rowUpdDate = ? where s.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Date());
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Salary!";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed deleted Salary!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	private Salary checkEmpSalary(int empId, Session session){
		Salary salary = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select s from Salary s where s.employee.employeeId = ? and s.status = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if(query.list() !=null && !query.list().isEmpty() )
				salary = (Salary)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return salary;
	}
	
}
