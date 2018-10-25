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
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;
import com.payroll.employee.pension.vo.EmployeePension;
import com.payroll.employee.pension.vo.PensionVO;

public class PensionDAO {
	public static double DR_PERCENT = 139.00;
	public static double COMMUTATION_PERCENT = 139.00;
	public List<com.payroll.employee.pension.vo.PensionVO> getPensionList(){
		List<com.payroll.employee.pension.vo.PensionVO> salaries = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.pension.vo.PensionVO(s.employee.employeeId, s.employee.firstName, s.employee.lastName, "
						+ "s.basicPension, s.residualPension, s.medicalAllowance,s.commutationAmount,s.dearnessRelief, s.familyPensionFlag, "
						+ "s.familyPensionName, s.pensionRemark,s.arrears) from Pension s where s.status = ?";
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
		double commutationAMT = 0;
		double DR;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, pension.getEmployeeId());
				pension.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				pension.setStatus("A");
				if(pension.getAddUpdate() == 0){
					if (pension.getFamilyPensionFlag()==2) {
						pension.setCommutationAmount(commutationAMT);
						pension.setResidualPension(pension.getBasicPension());
					}else {
						commutationAMT = Math.round(COMMUTATION_PERCENT * (pension.getBasicPension())/100);
						pension.setCommutationAmount(commutationAMT);
						pension.setResidualPension(pension.getBasicPension()-commutationAMT);
					}
					DR = Math.round(DR_PERCENT * (pension.getBasicPension())/100);
					pension.setDearnessRelief(DR);
					pension.setEmployee(employee);
					session.save(pension);
					transaction.commit();
					session.flush();
				}
				else{
					if (pension.getFamilyPensionFlag()==2) {
						pension.setCommutationAmount(commutationAMT);
						pension.setResidualPension(pension.getBasicPension());
					}else {
						commutationAMT = Math.round(COMMUTATION_PERCENT * (pension.getBasicPension())/100);
						pension.setCommutationAmount(commutationAMT);
						pension.setResidualPension(pension.getBasicPension()-commutationAMT);
					}
					DR = Math.round(DR_PERCENT * (pension.getBasicPension())/100);
					pension.setDearnessRelief(DR);
					session.update(pension);
					UpdateEmpPension(pension);
		            transaction.commit();
				    result = "Yes";	
				}
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
			String queryString = " select new com.payroll.employee.pension.vo.PensionVO(s.employee.employeeId,"
					+ "s.basicPension, s.residualPension, s.medicalAllowance, s.commutationAmount, s.pensionRemark, "
					+ "s.familyPensionFlag, s.familyPensionName, s.dearnessRelief,s.arrears) from Pension s where s.employee.employeeId = ? and s.status = ?";		
			
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
			String queryString = "select new com.payroll.employee.pension.vo.EmployeePension(s.employeeId, s.basicPension, s.residualPension, "
					+ " s.medicalAllowance, s.commutationAmount, s.pensionRemark, s.familyPensionFlag, s.familyPensionName, s.dearnessRelief,s.arrears) from Pension s where s.employeeId = ? and s.status = ?";
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
		
	public String UpdateEmpPension(Pension pension){
	 	String result = null;
	 	Session session = null;
		Transaction transaction = null;
	 	try{
	 		session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update Pension d set d.basicPension = ? , d.residualPension = ?, d.commutationAmount = ?,"
					+ " d.medicalAllowance = ?, d.familyPensionName = ?, d.pensionRemark = ? "
					+ " where d.employeeId = ? and d.status = ?");
				
			query.setParameter(0,pension.getBasicPension());
			query.setParameter(1,pension.getResidualPension());
			query.setParameter(2,pension.getCommutationAmount());
			query.setParameter(3,pension.getMedicalAllowance());
			query.setParameter(4,pension.getFamilyPensionName());
			query.setParameter(5,pension.getPensionRemark());
			query.setParameter(6,pension.getEmployeeId());
			query.setParameter(7,"A");

			int updated = query.executeUpdate();
	 		if(updated > 0)
	 			result = "Successfully updated pension details Details!";
			session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to update pension Details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
}
