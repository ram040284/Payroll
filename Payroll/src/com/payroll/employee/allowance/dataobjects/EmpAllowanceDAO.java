package com.payroll.employee.allowance.dataobjects;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.allowance.vo.EmpAllowanceVO;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.vo.EmpLicVO;

public class EmpAllowanceDAO {
	
	public List<EmpAllowanceVO> getEmpAllowanceList(){
		List<EmpAllowanceVO> empAllowanceList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.allowance.vo.EmpAllowanceVO(a.employee.employeeId, "
						+ "a.employee.firstName, a.employee.lastName, a.cca, a.washingAlwance, a.convAlwance,"
						+ "a.nonPracAwance, a.uniformAlwance, a.familyPlanAlwance, a.cycleAlwance, a.hraFlag) "
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
	
	public EmpAllowanceVO getEmpAllowanceById(int empId){
		EmpAllowanceVO empAllowanceVO = null;
			Session session = null;
			try{
				String queryString = " select new com.payroll.employee.allowance.vo.EmpAllowanceVO(a.employee.employeeId, "
						+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = a.employee.employeeId and dept.status = 'A'), "
						+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = a.employee.employeeId and desg.lastWokingDate is null and desg.status='A'), "
						+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = a.employee.employeeId and dh.lastWokingDate is null and dh.status = 'A'), "
						+ "a.cca, a.washingAlwance, a.convAlwance, a.nonPracAwance, a.uniformAlwance, a.familyPlanAlwance, a.cycleAlwance, a.hraFlag) from EmpAllowance a "
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
	
	public String deleteEmpAllowance(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpAllowance a set a.status = ?, a.rowUpdDate = ? where a.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Timestamp(System.currentTimeMillis()));
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted EMP Allowance Details!";
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
				session.save(empAllowance);
			else
				session.update(empAllowance);
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


}
