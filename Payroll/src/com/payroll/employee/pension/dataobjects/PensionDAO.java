package com.payroll.employee.pension.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import com.payroll.HibernateConnection;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.pension.vo.EmployeePension;
import com.payroll.employee.pension.vo.PensionVO;

public class PensionDAO {
	
	public List<com.payroll.employee.pension.vo.PensionVO> getPensionList(){
		List<com.payroll.employee.pension.vo.PensionVO> salaries = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.pension.vo.PensionVO("
						+ "s.employee.employeeId, "
					//	+ "(select e.firstName from Employee e where e.employeeId = s.employeeId),"
					//	+ " (select e.lastName from Employee e where e.employeeId = s.employeeId), "
						+"s.employee.firstName, s.employee.lastName, "
						+ "s.basicPension, s.residualPension, s.medicalAllowance,s.commutationAmount) from Pension s where s.status = ?";
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
	
	public String addUpdatePension(Pension pension){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//Employee employee = new EmployeeDAO().getById(pension.getEmployeeId());
			Employee employee = (Employee)session.load(Employee.class, pension.getEmployeeId());
			//Pension salayDB = checkEmpPension(pension.getEmployeeId(), session);
			/*if(salayDB != null){
				/*if(pension.getAddUpdate() ==0){
					result = "Pension for selected employee is exist!";
					return result;
				}*/
				/*salayDB.setBasic(pension.getBasic());
				salayDB.setGradePay(pension.getGradePay());
				salayDB.setScaleInc(pension.getScaleInc());
				salayDB.setScalePay(pension.getScalePay());
				salayDB.setYear(pension.getYear());
				salayDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.update(salayDB);
			}
			else {*/
			//System.out.println("pension.getEmployeeId()********* " + pension.getEmployeeId());
				pension.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				pension.setStatus("A");
				if(pension.getAddUpdate() == 0 && pension.getEmployeeId().equals("0")){
					pension.setEmployee(employee);
					System.out.println("Inside add function");
					session.save(pension);
					transaction.commit();
					session.flush();
				}
				else{
					//System.out.println("Inside update function");
					pension.setStatus("I");
					session.update(pension);
					pension.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					transaction.commit();
					/*transaction = session.beginTransaction();
					pension.setEmployee(employee);
					pension.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					pension.setStatus("A");
					session.save(pension);
					transaction.commit();*/
					session.flush();	
				}
			//}
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Pension details are already exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable to Add/Update Pension for selected employee";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public PensionVO getEmpPension(String empId){
		PensionVO salVO = null;
		Session session = null;
		try{
			String queryString = " select new com.payroll.employee.pension.vo.PensionVO(s.employee.employeeId, "+
					"(select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = s.employee.employeeId and eDept.status = 'A'), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = s.employee.employeeId and eDesg.status='A'), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = s.employee.employeeId and dh.status = 'A'), "
					+ "s.year, s.basic, s.gradePay, s.scalePay, s.incrementAmount, s.incrementDate) from Pension s where s.employee.employeeId = ? and s.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			salVO = (PensionVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return salVO;
	}

	/**
	 * Get Employee Pension
	 * @param empId
	 * @return
	 */
	public EmployeePension getEmployeePension(String empId){
		System.out.println("Entered EmployeePension getEmployeePension(String empId) : "+ empId);
		EmployeePension employeePension = null;
		Session session = null;
		try{
			String queryString = "select new com.payroll.employee.pension.vo.EmployeePension(s.employeeId, s.basic, s.gradePay,s.scalePay,s.scaleCode) from Pension s where s.employeeId = ? and s.status = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employeePension = (EmployeePension)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeePension;
	}
	
	public String deleteEmpSal(String empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Pension s set s.status = ?, s.rowUpdDate = ? where s.employee.employeeId = ?");
			query.setParameter(0, "I");
			query.setParameter(1, new Date());
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Pension!";
			session.flush();	
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed deleted Pension!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	private Pension checkEmpPension(String empId, Session session){
		Pension pension = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select s from Pension s where s.employee.employeeId = ? and s.status = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if(query.list() !=null && !query.list().isEmpty() )
				pension = (Pension)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		
		}
		return pension;
	}
	
}
