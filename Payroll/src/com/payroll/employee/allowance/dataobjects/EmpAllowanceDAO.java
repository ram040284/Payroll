package com.payroll.employee.allowance.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.employee.allowance.vo.EmployeeAllowances;
import com.payroll.employee.business.EmployeeService;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.deductions.dataobjects.EmpVarDeductionsVO;

public class EmpAllowanceDAO {
	
	public List<EmpAllowanceVO> getEmpAllowanceList(){
		List<EmpAllowanceVO> empAllowanceList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.allowance.vo.EmpAllowanceVO(a.employee.employeeId, "
						+ "a.employee.firstName, a.employee.lastName, a.cca, a.washingAlwance, "
						+ "a.nonPracAwance, a.uniformAlwance, a.familyPlanAlwance, a.cycleAlwance, a.hraFlag,a.qtrFlag, a.afkFlag, a.taFlag, a.pfFlag, a.otherAllowance, a.tAllowance) "
						+ "from EmpAllowance a where a.status = ?";
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				empAllowanceList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empAllowanceList;
	}
	
	public EmpAllowanceVO getEmpAllowanceById(String empId){
		EmpAllowanceVO empAllowanceVO = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.allowance.vo.EmpAllowanceVO(a.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = a.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = a.employee.employeeId and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = a.employee.employeeId and dh.status = 'A'), "
						+ "a.cca, a.washingAlwance, a.nonPracAwance, a.uniformAlwance, a.familyPlanAlwance, a.cycleAlwance, a.hraFlag, a.qtrFlag,a.afkFlag, a.taFlag, a.pfFlag, a.otherAllowance, a.tAllowance, a.rowUpdDate) from EmpAllowance a "
						+ "where a.status = ? and a.employee.employeeId = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empId);
				empAllowanceVO = (EmpAllowanceVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return empAllowanceVO;
	}
	
	/**
	 * Get employee allowances by employeed Id
	 * @param empId
	 * @return
	 */
	public EmployeeAllowances getEmployeeAllowances(String empId){
			EmployeeAllowances employeeAllowances = null;
			Session session = null;
			try{
				String queryString = "select new com.payroll.employee.allowance.vo.EmployeeAllowances(a.employeeId, a.cca, a.washingAlwance,"
						+ "a.nonPracAwance,a.uniformAlwance,a.familyPlanAlwance,"
						+ " a.cycleAlwance,a.hraFlag,a.qtrFlag,a.afkFlag, a.taFlag,"
						+ " a.pfFlag, a.otherAllowance, a.tAllowance) from EmpAllowance a"
						+ " where a.employee.employeeId = ? and a.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				System.out.println("EmployeeAllowances getEmployeeAllowances(int empId): "+ empId);
				employeeAllowances = (EmployeeAllowances)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}	
		return employeeAllowances;
	}
	//new code added
	public String updateEmpAllowance(EmpAllowance empAllowance){
 		String result = null;
 		Session session = null;
		Transaction transaction = null;
 		try{
 			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("update EmpAllowance a set a.cca = ?, a.washingAlwance = ?,a.nonPracAwance = ? , a.uniformAlwance = ?, a.familyPlanAlwance = ? ,a.cycleAlwance = ?, a.hraFlag =?, a.qtrFlag = ?,a.afkFlag = ?,a.taFlag = ?, a.pfFlag = ?, a.otherAllowance = ?,a.tAllowance = ?,a.rowUpdDate = ? "
					+ "where a.employeeId = ? and a.status = ?");
            query.setParameter(0,empAllowance.getCca()) ;
			query.setParameter(1,empAllowance.getWashingAlwance());
			query.setParameter(2,empAllowance.getNonPracAwance());
			query.setParameter(3,empAllowance.getUniformAlwance());
			query.setParameter(4,empAllowance.getFamilyPlanAlwance());
			query.setParameter(5,empAllowance.getCycleAlwance());
			query.setParameter(6,empAllowance.getHraFlag());
			query.setParameter(7,empAllowance.getQtrFlag());
			query.setParameter(8,empAllowance.getAfkFlag());
			query.setParameter(9,empAllowance.getTaFlag());
			query.setParameter(10,empAllowance.getPfFlag());
            query.setParameter(11,empAllowance.getOtherAllowance());
			query.setParameter(12,empAllowance.gettAllowance());
			query.setParameter(13,empAllowance.getRowUpdDate());
            query.setParameter(14,empAllowance.getEmployeeId());
			query.setParameter(15,"A");
          int updated = query.executeUpdate();
 			if(updated > 0)
 				result = "Successfully updated var allowance Details!";
			  session.flush();
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to update var allowance Details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}



	public String deleteEmpAllowance(String empId){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			EmpAllowanceVO allowanceVO = getEmpAllowanceById(empId);
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Query query = session.createQuery("update EmpAllowance a set a.status = ?, a.rowUpdDate = ?  where a.employee.employeeId = ? and a.status = ? and a.rowUpdDate = ?");
            query.setParameter(0, "I");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			query.setParameter(3, "A");
			query.setParameter(4, allowanceVO.getRowUpdDate());
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted EMP Allowance Details!";
			session.flush();	
			transaction.commit();
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete EMP Allowance details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String addUpdateEmpAllowance(EmpAllowance empAllowance){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee)session.load(Employee.class, empAllowance.getEmployeeId());
			empAllowance.setEmployee(employee);
			empAllowance.setStatus("A");
			empAllowance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			if(empAllowance.getAddUpdate() ==0)
			{
				session.save(empAllowance);
			}
			else {
				updateEmpAllowance(empAllowance);
			}
			session.flush();
			transaction.commit();
			result = "Yes";
			}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Allowances are exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to add/update Employee Allowances ";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public String addOrUpdateEmpAllowances(List<EmpAllowance> allowances) {
		
		Session session = null;
		Transaction transaction = null;
		
		String result;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			for (EmpAllowance alEmpAllowance: allowances) {
				EmployeeAllowances empAllowance = getEmployeeAllowances(alEmpAllowance.getEmployeeId());
				if (empAllowance != null) {
					List<Employee> employee = new EmployeeService().getEmployeeByEmployeeId(alEmpAllowance.getEmployeeId());
					alEmpAllowance.setEmployee(employee.get(0));
					alEmpAllowance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.update(alEmpAllowance);
					session.flush();
				}else {
					List<Employee> employee = new EmployeeService().getEmployeeByEmployeeId(alEmpAllowance.getEmployeeId());
					alEmpAllowance.setEmployee(employee.get(0));
					alEmpAllowance.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.save(alEmpAllowance);
					session.flush();
				}
				
			}
			transaction.commit();
			result = "success";
		} catch (Exception e) {
			System.out.println(e.toString());
			transaction.rollback();
			result = "failure";
		} finally {
			HibernateConnection.closeSession(session);
		}
		
		return result;
	}
}
